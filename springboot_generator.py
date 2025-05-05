import re
import os
import sys
import numpy as np

sys.path.append("/home/fitzwilliam/ES-1023/script/app")

APPLICATION = "ems"

DUMMY_VALUES = {
    "String": '"testValue"',
    "LocalDateTime": 'LocalDateTime.parse("2025-04-07T13:22:04.462379071")',
    "Boolean": "true",
    "Integer": "42",
}

def camel_to_snake(name):
    return re.sub(r'(?<!^)(?=[A-Z])', '_', name).lower()

def to_camel_case(name):
    return name[0].lower() + name[1:]

def to_pascal_case(name):
    return name[0].upper() + name[1:]

def fix_in_suffix(name):
    return name[:-2] + "IN" if name.endswith("In") else name

def write_to_file(path, content):
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, 'w') as f:
        f.write(content)

def generate_entity(class_name, fields):
    lines = (
f"""\
package {APPLICATION}.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = \"{camel_to_snake(class_name)}\")
public class {class_name} {{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
""")

    for name, field_type, column in fields:
        name = fix_in_suffix(name)
        if name == "time":
            lines += f"    @Column(name = \"[{column}]\", unique=true)\n"
        else:
            lines += f"    @Column(name = \"[{column}]\")\n"
        lines += f"    private {field_type} {name};\n"

    lines += (
"""
    // Getters and Setters")
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
""")
    for name, field_type, _ in fields:
        name = fix_in_suffix(name)
        camel = to_pascal_case(name)
        lines += (
f"""
    public {field_type} get{camel}() {{
        return this.{name};
    }}

    public void set{camel}({field_type} {name}) {{
        this.{name} = {name};
    }}
""")
    lines += "\n}"
    return lines

def generate_repository(class_name, fields):
    lines = (
f"""\
package {APPLICATION}.repositories;

import {APPLICATION}.models.{class_name};
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDateTime;

@Repository
public interface {class_name}Repository extends JpaRepository<{class_name}, Long> {{
""")
    
    for name, field_type, _ in fields:
        name = fix_in_suffix(name)
        camel = to_pascal_case(name)
        if field_type == "LocalDateTime":
            lines += f"    List<{class_name}> findBy{camel}Between(LocalDateTime start, LocalDateTime end);\n"
        lines += f"    List<{class_name}> findBy{camel}({field_type} {name});\n"
    lines += "}"
    return lines

def generate_service(class_name, fields):
    bean = to_camel_case(class_name)
    lines = (
f"""\
package {APPLICATION}.services;

import {APPLICATION}.models.{class_name};
import {APPLICATION}.repositories.{class_name}Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class {class_name}Service {{
    @Autowired private {class_name}Repository {bean}Repository;

    public List<{class_name}> findAll() {{
        return {bean}Repository.findAll();
    }}

    public {class_name} save({class_name} obj) {{
        return {bean}Repository.save(obj);
    }}
    public void deleteById(Long id) {{
        {bean}Repository.deleteById(id);
    }}
""")

    for name, field_type, _ in fields:
        name = fix_in_suffix(name)
        camel = to_pascal_case(name)
        if field_type == "LocalDateTime":
            lines += (
f"""
    public List<{class_name}> findBy{camel}Between(LocalDateTime s, LocalDateTime e) {{
        return {bean}Repository.findBy{camel}Between(s, e);
    }}
""")
        lines += (
f"""
    public List<{class_name}> findBy{camel}({field_type} val) {{
        return {bean}Repository.findBy{camel}(val);
    }}
""")

    lines += "}"
    return lines

def generate_controller(class_name, fields):
    bean = to_camel_case(class_name)
    lines = (
f"""\
package {APPLICATION}.controllers;

import {APPLICATION}.models.{class_name};
import {APPLICATION}.services.{class_name}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(\"/api/{class_name.lower()}\")
public class {class_name}Controller {{

    @Autowired
    private {class_name}Service {bean}Service;

    @GetMapping
    public List<{class_name}> getAll() {{
        return {bean}Service.findAll();
    }}

    @PostMapping
    public ResponseEntity<{class_name}> create(@RequestBody {class_name} obj) {{
        return new ResponseEntity<>({bean}Service.save(obj), HttpStatus.CREATED);
    }}

    @PutMapping(\"/{{id}}\")
    public ResponseEntity<{class_name}> update(@PathVariable Long id, @RequestBody {class_name} obj) {{
        return new ResponseEntity<>({bean}Service.save(obj), HttpStatus.OK);
    }}

    @DeleteMapping(\"/{{id}}\")
    public ResponseEntity<Void> delete(@PathVariable Long id) {{
        {bean}Service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }}
""")

    for name, field_type, _ in fields:
        name = fix_in_suffix(name)
        camel = to_pascal_case(name)
        if field_type == "LocalDateTime":
            lines += (
f"""
    @GetMapping(\"/findBy{camel}Between\")
    public List<{class_name}> findBy{camel}Between(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {{
        return {bean}Service.findBy{camel}Between(start, end);
    }}
""")
        lines += (
f"""
    @GetMapping(\"/findBy{camel}\")
    public List<{class_name}> findBy{camel}(@RequestParam {field_type} {name}) {{
        return {bean}Service.findBy{camel}({name});
    }}
""")

    lines += "}"
    return lines

def generate_webconfig():
    return f"""\
package {APPLICATION};

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {{

    @Bean
    public WebMvcConfigurer corsConfigurer() {{
        return new WebMvcConfigurer() {{
            @Override
            public void addCorsMappings(CorsRegistry registry) {{
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000/")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }}
        }};
    }}
}}
"""

def generate_application():
    return f"""\
package {APPLICATION};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {{

	public static void main(String[] args) {{
		SpringApplication.run(Application.class, args);
	}}

}}
"""

def generate_property():
    return """\
spring:
  config:
    import: optional:file:.env[.properties]
  application:
    name: backend
  datasource:
    url: jdbc:postgresql://localhost:5356/mydb
    username: myuser
    password: mypassword
    driverClassName: org.postgresql.Driver
  jpa:
    show-sql: true
    properties:
      hibernate:
        format_sql: true
        dialect: org.hibernate.dialect.PostgreSQLDialect
    hibernate:
      ddl-auto: update
"""

def generage_gradle():
    return f"""\
plugins {{
	java
	id("org.springframework.boot") version "3.4.4"
	id("io.spring.dependency-management") version "1.1.7"
  id("org.sonarqube") version "6.0.1.5171"
  id("jacoco")
  id("com.google.cloud.tools.jib") version "3.4.5"
}}

group = "ems"
version = "0.0.1-SNAPSHOT"

tasks.bootRun {{
    mainClass.set("ems.Application")  // updated location after moving App.java
}}

java {{
	toolchain {{
		languageVersion = JavaLanguageVersion.of(21)
	}}
}}

repositories {{
	mavenCentral()
}}

dependencies {{
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}}

tasks.withType<Test> {{
	useJUnitPlatform()
}}

sonar {{
  properties {{
    property("sonar.projectKey", "website-display_backend")
    property("sonar.organization", "website-display")
    property("sonar.host.url", "https://sonarcloud.io")
    property("sonar.projectName", "Backend")
    property("sonar.exclude", "**/*.py, **/test/**")
    property("sonar.java.source", "21")
    property("sonar.coverage.jacoco.xmlReportPaths", "./build/reports/jacoco/test/jacocoTestReport.xml")
    property("sonar.junit.reportPaths", "./build/test-results/test")
  }}
}}

tasks.jacocoTestReport {{
    dependsOn(tasks.build)
    reports {{
        xml.required = true
    }}
}}
"""

def convert_python_to_java(input_path, output_dir):
    from dataclass import Data
    data = Data(input_path, ';')
    # data.extractData()
    models = data.extract_class_and_fields({"int": "Integer", "str": "String", "datetime": "LocalDateTime"})
    write_to_file(f"{output_dir}/src/main/java/{APPLICATION}/WebConfig.java", generate_webconfig())
    write_to_file(f"{output_dir}/src/main/java/{APPLICATION}/Application.java", generate_application())
    write_to_file(f"{output_dir}/src/main/resources/application.yml", generate_property())
    write_to_file(f"{output_dir}/build.gradle.kts", generage_gradle())

    for class_name, fields in models:
        write_to_file(f"{output_dir}/src/main/java/{APPLICATION}/models/{class_name}.java", generate_entity(class_name, fields))
        write_to_file(f"{output_dir}/src/main/java/{APPLICATION}/repositories/{class_name}Repository.java", generate_repository(class_name, fields))
        write_to_file(f"{output_dir}/src/main/java/{APPLICATION}/services/{class_name}Service.java", generate_service(class_name, fields))
        write_to_file(f"{output_dir}/src/main/java/{APPLICATION}/controllers/{class_name}Controller.java", generate_controller(class_name, fields))

convert_python_to_java("/home/fitzwilliam/Documents/data/2025_03_31_10-39-50.MM00493.txt", ".")
