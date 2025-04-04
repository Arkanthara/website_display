import re
import os

# Mapping of Django field types to Java types
field_type_mapping = {
    "CharField": "String",
    "DateTimeField": "LocalDateTime",
    "BooleanField": "Boolean",
    "IntegerField": "Integer",
}


def convert_django_to_springboot(django_model):
    # Extract the class name
    class_name = re.search(r'class (\w+)\(models.Model\):', django_model)
    if class_name:
        class_name = class_name.group(1)
    else:
        raise ValueError("Could not extract class name from Django model.")

    # Extract all fields
    fields = re.findall(r'(\w+) = models.(\w+)\(.*?db_column="(.*?)".*?\)', django_model)

    # Generate Java entity class
    java_entity = f"package ems.models;\n\nimport jakarta.persistence.*;\nimport java.time.LocalDateTime;\n\n"
    java_entity += f"@Entity\n@Table(name = \"{class_name}\", uniqueConstraints = @UniqueConstraint(name = \"unique{class_name}\", columnNames = {{"
    
    # Handle constraints
    unique_constraints = []
    for field in fields:
        field_name, field_type, db_column = field
        if field_type == "CharField" and db_column in ["serialNumber", "time"]:
            unique_constraints.append(f"\"{field_name}\"")
    
    java_entity += ", ".join(unique_constraints) + "}))\n"
    java_entity += f"public class {class_name} {{\n"
    
    java_entity += f"    @Id\n    @GeneratedValue(strategy = GenerationType.IDENTITY)\n    private Long id;\n"
    # Generate fields and annotations for each field
    for field in fields:
        field_name, field_type, db_column = field
        java_type = field_type_mapping.get(field_type, "String")  # Default to String
        java_entity += f"    @Column(name = \"{db_column}\")\n"
        java_entity += f"    private {java_type} {field_name};\n"

    java_entity += "\n    // Getters and Setters\n"
    
    # Generate getters and setters for each field
    for field in fields:
        field_name, field_type, _ = field
        java_type = field_type_mapping.get(field_type, "String")  # Get the corresponding Java type

        # Getter
        java_entity += f"    public {java_type} get{field_name.capitalize()}() {{\n"
        java_entity += f"        return {field_name};\n"
        java_entity += f"    }}\n\n"
        
        # Setter
        java_entity += f"    public void set{field_name.capitalize()}({java_type} {field_name}) {{\n"
        java_entity += f"        this.{field_name} = {field_name};\n"
        java_entity += f"    }}\n\n"

    java_entity += "}"

    return java_entity


def generate_repository_interface(class_name, fields, output_dir):
    # Generate the repository interface for the given class
    repo_interface = f"package ems.repositories;\n\n"
    repo_interface += f"import ems.models.{class_name};\n"
    repo_interface += f"import org.springframework.data.jpa.repository.JpaRepository;\n"
    repo_interface += f"import java.time.LocalDateTime;\n\n"
    repo_interface += f"@Repository\n"
    repo_interface += f"public interface {class_name}Repository extends JpaRepository<{class_name}, Long> {{\n"

    # Create finders for each field
    repo_interface += f"    // Custom query methods\n"

    for field_name, field_type, db_column in fields:
        java_type = field_type_mapping.get(field_type, "String")
        
        if java_type == "LocalDateTime":
            repo_interface += f"    List<{class_name}> findBy{field_name.capitalize()}Between(LocalDateTime start, LocalDateTime end);\n"
        elif java_type == "String":
            repo_interface += f"    List<{class_name}> findBy{field_name.capitalize()}(String {field_name});\n"
        elif java_type == "Integer":
            repo_interface += f"    List<{class_name}> findBy{field_name.capitalize()}(Integer {field_name});\n"
        elif java_type == "Boolean":
            repo_interface += f"    List<{class_name}> findBy{field_name.capitalize()}(Boolean {field_name});\n"

    # Add a method to find all rows
    repo_interface += f"    List<{class_name}> findAll();\n"

    repo_interface += "}"

    # Write the repository interface to a file
    with open(os.path.join(output_dir, f"{class_name}Repository.java"), 'w') as repo_file:
        repo_file.write(repo_interface)


def generate_service_impl(class_name, fields, output_dir):
    # Generate the service implementation for the given class
    service_impl = f"package ems.services.impl;\n\n"
    service_impl += f"import ems.models.{class_name};\n"
    service_impl += f"import ems.repositories.{class_name}Repository;\n"
    service_impl += f"import org.springframework.beans.factory.annotation.Autowired;\n"
    service_impl += f"import org.springframework.stereotype.Service;\n"
    service_impl += f"import java.time.LocalDateTime;\n"
    service_impl += f"import java.util.List;\n\n"
    service_impl += f"@Service\n"
    service_impl += f"public class {class_name}ServiceImpl {{\n"
    service_impl += f"    @Autowired\n"
    service_impl += f"    private {class_name}Repository {class_name.lower()}Repository;\n\n"

    service_impl += f"    public List<{class_name}> findAll() {{\n"
    service_impl += f"        return {class_name.lower()}Repository.findAll();\n"
    service_impl += f"    }}\n\n"

    service_impl += f"    public {class_name} findById(Long id) {{\n"
    service_impl += f"        return {class_name.lower()}Repository.findById(id).orElse(null);\n"
    service_impl += f"    }}\n\n"

    service_impl += f"    public {class_name} save({class_name} {class_name.lower()}) {{\n"
    service_impl += f"        return {class_name.lower()}Repository.save({class_name.lower()});\n"
    service_impl += f"    }}\n\n"

    service_impl += f"    public void deleteById(Long id) {{\n"
    service_impl += f"        {class_name.lower()}Repository.deleteById(id);\n"
    service_impl += f"    }}\n\n"

    # Custom query methods
    for field_name, field_type, db_column in fields:
        java_type = field_type_mapping.get(field_type, "String")
        
        if java_type == "LocalDateTime":
            service_impl += f"    public List<{class_name}> findBy{field_name.capitalize()}Between(LocalDateTime start, LocalDateTime end) {{\n"
            service_impl += f"        return {class_name.lower()}Repository.findBy{field_name.capitalize()}Between(start, end);\n"
            service_impl += f"    }}\n\n"
        elif java_type == "String":
            service_impl += f"    public List<{class_name}> findBy{field_name.capitalize()}(String {field_name}) {{\n"
            service_impl += f"        return {class_name.lower()}Repository.findBy{field_name.capitalize()}({field_name});\n"
            service_impl += f"    }}\n\n"
        elif java_type == "Integer":
            service_impl += f"    public List<{class_name}> findBy{field_name.capitalize()}(Integer {field_name}) {{\n"
            service_impl += f"        return {class_name.lower()}Repository.findBy{field_name.capitalize()}({field_name});\n"
            service_impl += f"    }}\n\n"
        elif java_type == "Boolean":
            service_impl += f"    public List<{class_name}> findBy{field_name.capitalize()}(Boolean {field_name}) {{\n"
            service_impl += f"        return {class_name.lower()}Repository.findBy{field_name.capitalize()}({field_name});\n"
            service_impl += f"    }}\n\n"

    service_impl += "}"

    # Write the service implementation to a file
    with open(os.path.join(output_dir, f"{class_name}ServiceImpl.java"), 'w') as service_file:
        service_file.write(service_impl)


def convert_django_file_to_java(input_file, output_dir):
    # Read Django models from the input file
    with open(input_file, 'r') as file:
        django_models = file.read()

    # Split the file content into individual models using 'class' as a delimiter
    model_strings = re.split(r'class (\w+)\(models.Model\):', django_models)[1:]

    # Create output directory if it does not exist
    if not os.path.exists(output_dir):
        os.makedirs(output_dir)

    # Process each model string and generate corresponding Java class and repository
    for i in range(0, len(model_strings), 2):
        class_name = model_strings[i].strip()
        django_model = f"class {class_name}(models.Model):\n{model_strings[i + 1].strip()}"

        # Extract fields
        fields = re.findall(r'(\w+) = models.(\w+)\(.*?db_column="(.*?)".*?\)', django_model)

        # Generate the Java class
        java_entity = convert_django_to_springboot(django_model)

        # Write the Java class to a file
        with open(os.path.join(output_dir, f"{class_name}.java"), 'w') as java_file:
            java_file.write(java_entity)

        # Generate the repository interface
        generate_repository_interface(class_name, fields, output_dir)

        # Generate the service implementation
        generate_service_impl(class_name, fields, output_dir)


# Input file with Django models
input_file = "/home/fitzwilliam/ES-1023/script/postgresql/database/models.py"  # Replace with your actual file
output_dir = "/home/fitzwilliam/Documents/website_display/src/main/java/ems/services"  # Directory to save Java files

# Convert Django models in the input file to Java classes and repositories
convert_django_file_to_java(input_file, output_dir)
