import re
import os

# Mapping of Django field types to Java types
field_type_mapping = {
    "CharField": "String",
    "DateTimeField": "LocalDateTime",
    "BooleanField": "Boolean",
    "IntegerField": "Integer",
}


def convert_django_to_springboot(django_model, fields, output_dir):
    # Extract the class name
    class_name = re.search(r'class (\w+)\(models.Model\):', django_model)
    if class_name:
        class_name = class_name.group(1)
    else:
        raise ValueError("Could not extract class name from Django model.")

    # Generate Java entity class
    java_entity = f"package ems.models;\n\nimport jakarta.persistence.*;\nimport java.time.LocalDateTime;\n\n"
    java_entity += f"@Entity\n@Table(name = \"{class_name}\", uniqueConstraints = @UniqueConstraint(name = \"unique{class_name}\", columnNames = {{ \"time\", \"serialNumber\" }}))\n"
    
    # Handle constraints
    # unique_constraints = []
    # for field in fields:
    #     field_name, field_type, db_column = field
    #     if field_type == "CharField" and db_column in ["serialNumber", "time"]:
    #         unique_constraints.append(f"\"{field_name}\"")
    #
    # java_entity += ", ".join(unique_constraints) + "}))\n"
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

    # Create output directory if it does not exist
    if not os.path.exists(output_dir):
        os.makedirs(output_dir)

    with open(os.path.join(output_dir, f"{class_name}.java"), 'w') as java_file:
        java_file.write(java_entity)



def generate_repository_interface(class_name, fields, output_dir):
    # Generate the repository interface for the given class
    repo_interface = f"package ems.repositories;\n\n"
    repo_interface += f"import ems.models.{class_name};\n"
    repo_interface += f"import org.springframework.data.jpa.repository.JpaRepository;\n"
    repo_interface += f"import org.springframework.stereotype.Repository;\n"
    repo_interface += f"import java.time.LocalDateTime;\n"
    repo_interface += f"import java.util.List;\n\n"
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

    # Create output directory if it does not exist
    if not os.path.exists(output_dir):
        os.makedirs(output_dir)

    # Write the repository interface to a file
    with open(os.path.join(output_dir, f"{class_name}Repository.java"), 'w') as repo_file:
        repo_file.write(repo_interface)


def generate_service_impl(class_name, fields, output_dir):
    # Generate the service implementation for the given class
    service_impl = f"package ems.services;\n\n"
    service_impl += f"import ems.models.{class_name};\n"
    service_impl += f"import ems.repositories.{class_name}Repository;\n"
    service_impl += f"import org.springframework.beans.factory.annotation.Autowired;\n"
    service_impl += f"import org.springframework.stereotype.Service;\n"
    service_impl += f"import java.time.LocalDateTime;\n"
    service_impl += f"import java.util.List;\n\n"
    service_impl += f"@Service\n"
    service_impl += f"public class {class_name}Service {{\n"
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

    # Create output directory if it does not exist
    if not os.path.exists(output_dir):
        os.makedirs(output_dir)

    # Write the service implementation to a file
    with open(os.path.join(output_dir, f"{class_name}Service.java"), 'w') as service_file:
        service_file.write(service_impl)




def generate_rest_controller(class_name, fields, output_dir):
    # Generate the REST controller for the given class
    rest_controller = f"package ems.controllers;\n\n"
    rest_controller += f"import ems.models.{class_name};\n"
    rest_controller += f"import ems.services.{class_name}Service;\n"
    rest_controller += f"import org.springframework.beans.factory.annotation.Autowired;\n"
    rest_controller += f"import org.springframework.http.HttpStatus;\n"
    rest_controller += f"import org.springframework.http.ResponseEntity;\n"
    rest_controller += f"import org.springframework.web.bind.annotation.*;\n"
    rest_controller += f"import java.time.LocalDateTime;\n"
    rest_controller += f"import java.util.List;\n\n"
    rest_controller += f"@RestController\n"
    rest_controller += f"@RequestMapping(\"/api/{class_name.lower()}\")\n"
    rest_controller += f"public class {class_name}Controller {{\n\n"
    rest_controller += f"    @Autowired\n"
    rest_controller += f"    private {class_name}Service {class_name.lower()}Service;\n\n"

    # Standard CRUD methods
    rest_controller += f"    @GetMapping\n"
    rest_controller += f"    public List<{class_name}> getAll{class_name}s() {{\n"
    rest_controller += f"        return {class_name.lower()}Service.findAll();\n"
    rest_controller += f"    }}\n\n"

    rest_controller += f"    @GetMapping(\"/{{id}}\")\n"
    rest_controller += f"    public ResponseEntity<{class_name}> get{class_name}ById(@PathVariable Long id) {{\n"
    rest_controller += f"        {class_name} {class_name.lower()} = {class_name.lower()}Service.findById(id);\n"
    rest_controller += f"        if ({class_name.lower()} == null) {{\n"
    rest_controller += f"            return new ResponseEntity<>(HttpStatus.NOT_FOUND);\n"
    rest_controller += f"        }}\n"
    rest_controller += f"        return new ResponseEntity<>({class_name.lower()}, HttpStatus.OK);\n"
    rest_controller += f"    }}\n\n"

    rest_controller += f"    @PostMapping\n"
    rest_controller += f"    public ResponseEntity<{class_name}> create{class_name}(@RequestBody {class_name} {class_name.lower()}) {{\n"
    rest_controller += f"        {class_name} created = {class_name.lower()}Service.save({class_name.lower()});\n"
    rest_controller += f"        return new ResponseEntity<>(created, HttpStatus.CREATED);\n"
    rest_controller += f"    }}\n\n"

    rest_controller += f"    @PutMapping(\"/{{id}}\")\n"
    rest_controller += f"    public ResponseEntity<{class_name}> update{class_name}(@PathVariable Long id, @RequestBody {class_name} {class_name.lower()}) {{\n"
    rest_controller += f"        {class_name} updated = {class_name.lower()}Service.save({class_name.lower()});\n"
    rest_controller += f"        return new ResponseEntity<>(updated, HttpStatus.OK);\n"
    rest_controller += f"    }}\n\n"

    rest_controller += f"    @DeleteMapping(\"/{{id}}\")\n"
    rest_controller += f"    public ResponseEntity<Void> delete{class_name}(@PathVariable Long id) {{\n"
    rest_controller += f"        {class_name.lower()}Service.deleteById(id);\n"
    rest_controller += f"        return new ResponseEntity<>(HttpStatus.NO_CONTENT);\n"
    rest_controller += f"    }}\n\n"

    # Custom query methods (from Service)
    for field_name, field_type, db_column in fields:
        java_type = field_type_mapping.get(field_type, "String")
        
        if java_type == "LocalDateTime":
            rest_controller += f"    @GetMapping(\"/findBy{field_name.capitalize()}Between\")\n"
            rest_controller += f"    public List<{class_name}> findBy{field_name.capitalize()}Between(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {{\n"
            rest_controller += f"        return {class_name.lower()}Service.findBy{field_name.capitalize()}Between(start, end);\n"
            rest_controller += f"    }}\n\n"
        elif java_type == "String":
            rest_controller += f"    @GetMapping(\"/findBy{field_name.capitalize()}\")\n"
            rest_controller += f"    public List<{class_name}> findBy{field_name.capitalize()}(@RequestParam String {field_name}) {{\n"
            rest_controller += f"        return {class_name.lower()}Service.findBy{field_name.capitalize()}({field_name});\n"
            rest_controller += f"    }}\n\n"
        elif java_type == "Integer":
            rest_controller += f"    @GetMapping(\"/findBy{field_name.capitalize()}\")\n"
            rest_controller += f"    public List<{class_name}> findBy{field_name.capitalize()}(@RequestParam Integer {field_name}) {{\n"
            rest_controller += f"        return {class_name.lower()}Service.findBy{field_name.capitalize()}({field_name});\n"
            rest_controller += f"    }}\n\n"
        elif java_type == "Boolean":
            rest_controller += f"    @GetMapping(\"/findBy{field_name.capitalize()}\")\n"
            rest_controller += f"    public List<{class_name}> findBy{field_name.capitalize()}(@RequestParam Boolean {field_name}) {{\n"
            rest_controller += f"        return {class_name.lower()}Service.findBy{field_name.capitalize()}({field_name});\n"
            rest_controller += f"    }}\n\n"

    rest_controller += "}"

    # Create output directory if it does not exist
    if not os.path.exists(output_dir):
        os.makedirs(output_dir)

    # Write the REST controller to a file
    with open(os.path.join(output_dir, f"{class_name}Controller.java"), 'w') as controller_file:
        controller_file.write(rest_controller)


def convert_django_file_to_java(input_file, output_dir):
    # Read Django models from the input file
    with open(input_file, 'r') as file:
        django_models = file.read()

    # Split the file content into individual models using 'class' as a delimiter
    model_strings = re.split(r'class (\w+)\(models.Model\):', django_models)[1:]

    # Create output directory if it does not exist
    if not os.path.exists(output_dir):
        os.makedirs(output_dir)

    # Process each model string and generate corresponding Java class, repository, service, and controller
    for i in range(0, len(model_strings), 2):
        class_name = model_strings[i].strip()
        django_model = f"class {class_name}(models.Model):\n{model_strings[i + 1].strip()}"

        # Extract fields
        fields = re.findall(r'(\w+) = models.(\w+)\(.*?db_column="(.*?)".*?\)', django_model)

        # Generate the Java class
        convert_django_to_springboot(django_model, fields, output_dir + "/models")

        # Generate the repository interface
        generate_repository_interface(class_name, fields, output_dir + "/repositories")

        # Generate the service implementation
        generate_service_impl(class_name, fields, output_dir + "/services")

        # Generate the REST controller
        generate_rest_controller(class_name, fields, output_dir + "/controllers")



input_file = "/home/fitzwilliam/ES-1023/script/postgresql/database/models.py"  # Replace with your actual file
output_dir = "/home/fitzwilliam/Documents/website_display/src/main/java/ems"  # Directory to save Java files

# Convert Django models in the input file to Java classes, repositories, services, and controllers
convert_django_file_to_java(input_file, output_dir)
