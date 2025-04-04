import re
import os

def convert_django_to_springboot(django_model):
    # Mapping of Django field types to Java types
    field_type_mapping = {
        "CharField": "String",
        "DateTimeField": "LocalDateTime",
        "BooleanField": "Boolean",
        "IntegerField": "Integer",
    }

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
        field_name, _, _ = field
        java_entity += f"    public {field_type_mapping.get(field_name, 'String')} get{field_name.capitalize()}() {{\n"
        java_entity += f"        return {field_name};\n"
        java_entity += f"    }}\n\n"
        
        java_entity += f"    public void set{field_name.capitalize()}({field_type_mapping.get(field_name, 'String')} {field_name}) {{\n"
        java_entity += f"        this.{field_name} = {field_name};\n"
        java_entity += f"    }}\n\n"

    java_entity += "}"

    return java_entity


def convert_django_file_to_java(input_file, output_dir):
    # Read Django models from the input file
    with open(input_file, 'r') as file:
        django_models = file.read()

    # Split the file content into individual models using 'class' as a delimiter
    model_strings = re.split(r'class (\w+)\(models.Model\):', django_models)[1:]

    # Create output directory if it does not exist
    if not os.path.exists(output_dir):
        os.makedirs(output_dir)

    # Process each model string and generate corresponding Java class
    for i in range(0, len(model_strings), 2):
        class_name = model_strings[i].strip()
        django_model = f"class {class_name}(models.Model):\n{model_strings[i + 1].strip()}"

        # Generate the Java class
        java_entity = convert_django_to_springboot(django_model)

        # Write the Java class to a file
        with open(os.path.join(output_dir, f"{class_name}.java"), 'w') as java_file:
            java_file.write(java_entity)


# Input file with Django models
input_file = "/home/fitzwilliam/ES-1023/script/postgresql/database/models.py"  # Replace with your actual file
output_dir = "test_dir"  # Directory to save Java files

# Convert Django models in the input file to Java classes
convert_django_file_to_java(input_file, output_dir)

