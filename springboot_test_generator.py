from springboot_generator import *

def generate_test_class(class_name, fields):
    lines = (
f"""\
package {APPLICATION}.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

public class {class_name}Test {{

    @Test
    public void testGettersAndSetters() {{
        {class_name} obj = new {class_name}();
        obj.setId(1L);
        assertEquals(1L, obj.getId());
""")

    for name, field_type, _ in fields:
        name = fix_in_suffix(name)
        camel = to_pascal_case(name)
        val = DUMMY_VALUES.get(field_type, '"default"')
        lines += (
f"""
        obj.set{camel}({val});
        assertEquals({val}, obj.get{camel}());
""")
    lines += (
"""
    }
}
""")
    return lines

def generate_repository_test(class_name, fields):
    repo = to_camel_case(class_name)
    lines = (
f"""\
package {APPLICATION}.repositories;

import {APPLICATION}.models.{class_name};
import {APPLICATION}.repositories.{class_name}Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Collections;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class {class_name}RepositoryTest {{
    @Mock private {class_name}Repository {repo}Repository;
    private {class_name} entity;

    @BeforeEach
    public void setup() {{
        MockitoAnnotations.openMocks(this);
        entity = new {class_name}();
""")

    for name, field_type, _ in fields:
        name = fix_in_suffix(name)
        camel = to_pascal_case(name)
        val = DUMMY_VALUES.get(field_type, '"test"')
        lines += (
f"""
        entity.set{camel}({val});
""")

    lines += (
f"""
    }}
""")

    lines += (
f"""
    @Test
    public void testFindAll() {{
        when({repo}Repository.findAll()).thenReturn(List.of(entity));
        List<{class_name}> result = {repo}Repository.findAll();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }}
""")

    for name, field_type, _ in fields:
        name = fix_in_suffix(name)
        camel = to_pascal_case(name)
        val = DUMMY_VALUES.get(field_type, '"test"')

        lines += (
f"""
    @Test
    public void testFindBy{camel}() {{
        when({repo}Repository.findBy{camel}({val})).thenReturn(List.of(entity));
        List<{class_name}> result = {repo}Repository.findBy{camel}({val});
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }}
""")

        if field_type == "LocalDateTime":
            lines += (
f"""
    @Test
    public void testFindBy{camel}Between() {{
        LocalDateTime start = LocalDateTime.parse(\"2025-04-01T00:00:00\");
        LocalDateTime end = LocalDateTime.parse(\"2025-04-30T23:59:59\");
        when({repo}Repository.findBy{camel}Between(start, end)).thenReturn(List.of(entity));
        List<{class_name}> result = {repo}Repository.findBy{camel}Between(start, end);
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }}
""")

    lines += "}"
    return lines


def generate_service_test(class_name, fields):
    camelClassName = to_camel_case(class_name)
    lines = ( 
f"""\
package {APPLICATION}.services;

import {APPLICATION}.models.{class_name};
import {APPLICATION}.repositories.{class_name}Repository;
import {APPLICATION}.services.{class_name}Service;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class {class_name}ServiceTest {{
    @Mock private {class_name}Repository {camelClassName}Repository;
    @InjectMocks private {class_name}Service {camelClassName}Service;

    public {class_name}ServiceTest() {{ MockitoAnnotations.openMocks(this); }}

    @Test
    public void testFindAll() {{
        when({camelClassName}Repository.findAll()).thenReturn(Collections.singletonList(new {class_name}()));
        List<{class_name}> result = {camelClassName}Service.findAll();
        assertEquals(1, result.size());
    }}

    @Test
    public void testSave() {{
        {class_name} obj = new {class_name}();
        when({camelClassName}Repository.save(obj)).thenReturn(obj);
        {class_name} result = {camelClassName}Service.save(obj);
        assertNotNull(result);
    }}

    @Test
    public void testDeleteById() {{
        doNothing().when({camelClassName}Repository).deleteById(1L);
        {camelClassName}Service.deleteById(1L);
        verify({camelClassName}Repository, times(1)).deleteById(1L);
    }}
""")

    for name, field_type, _ in fields:
        name = fix_in_suffix(name)
        camel = to_pascal_case(name)
        dummy = DUMMY_VALUES.get(field_type, '"test"')
        lines += (
f"""
    @Test
    public void testFindBy{camel}() {{
        when({camelClassName}Repository.findBy{camel}({dummy})).thenReturn(List.of(new {class_name}()));
        List<{class_name}> result = {camelClassName}Service.findBy{camel}({dummy});
        assertNotNull(result);
    }}
""")
        if field_type == "LocalDateTime":
            lines += (
f"""
    @Test
    public void testFindBy{camel}Between() {{
        LocalDateTime start = LocalDateTime.parse(\"2025-04-01T00:00:00\");
        LocalDateTime end = LocalDateTime.parse(\"2025-04-30T23:59:59\");
        when({camelClassName}Repository.findBy{camel}Between(start, end)).thenReturn(List.of(new {class_name}()));
        List<{class_name}> result = {camelClassName}Service.findBy{camel}Between(start, end);
        assertNotNull(result);
    }}
""")

    lines += "}"
    return lines

def generate_controller_test(class_name, fields):
    camelClassName = to_camel_case(class_name)
    lines = (
f"""\
package {APPLICATION}.controllers;

import {APPLICATION}.models.{class_name};
import {APPLICATION}.services.{class_name}Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import java.time.LocalDateTime;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest({class_name}Controller.class)
public class {class_name}ControllerTest {{

    @Autowired private MockMvc mockMvc;
    @MockitoBean private {class_name}Service {camelClassName}Service;

    @Test
    public void testGetAll() throws Exception {{
        when({camelClassName}Service.findAll()).thenReturn(Collections.singletonList(new {class_name}()));
        mockMvc.perform(get(\"/api/{class_name.lower()}\"))
               .andExpect(status().isOk());
    }}

    @Test
    public void testCreate() throws Exception {{
        when({camelClassName}Service.save(any())).thenReturn(new {class_name}());
        mockMvc.perform(post(\"/api/{class_name.lower()}\")
               .contentType(MediaType.APPLICATION_JSON)
               .content(\"{{}}\"))
               .andExpect(status().isCreated());
    }}

    @Test
    public void testUpdate() throws Exception {{
        when({camelClassName}Service.save(any())).thenReturn(new {class_name}());
        mockMvc.perform(put(\"/api/{class_name.lower()}/1\")
               .contentType(MediaType.APPLICATION_JSON)
               .content(\"{{}}\"))
               .andExpect(status().isOk());
    }}

    @Test
    public void testDelete() throws Exception {{
        doNothing().when({camelClassName}Service).deleteById(1L);
        mockMvc.perform(delete(\"/api/{class_name.lower()}/1\"))
               .andExpect(status().isNoContent());
    }}
""")

    for name, field_type, _ in fields:
        name = fix_in_suffix(name)
        camel = to_pascal_case(name)
        param = DUMMY_VALUES.get(field_type, '"val"')
        lines += (
f"""
    @Test
    public void testFindBy{camel}() throws Exception {{
        when({camelClassName}Service.findBy{camel}(any()))
            .thenReturn(Collections.singletonList(new {class_name}()));
        mockMvc.perform(get(\"/api/{class_name.lower()}/findBy{camel}\")
            .param(\"{name}\", \"\" + {param}))
            .andExpect(status().isOk());
    }}
""")
        if field_type == "LocalDateTime":
            lines += (
f"""
    @Test
    public void testFindBy{camel}Between() throws Exception {{
        when({camelClassName}Service.findBy{camel}Between(any(), any()))
            .thenReturn(Collections.singletonList(new {class_name}()));
        mockMvc.perform(get(\"/api/{class_name.lower()}/findBy{camel}Between\")
            .param(\"start\", \"2025-04-01T00:00:00\")
            .param(\"end\", \"2025-04-30T23:59:59\"))
            .andExpect(status().isOk());
    }}
""")

    lines += "}"
    return lines

def convert_python_to_javatest(input_path, output_dir):
    from dataclass import Data
    data = Data(input_path, ';')
    # data.extractData()
    models = data.extract_class_and_fields({"int": "Integer", "str": "String", "datetime": "LocalDateTime"})
    for class_name, fields in models:
        write_to_file(f"{output_dir}/src/test/java/{APPLICATION}/models/{class_name}Test.java", generate_test_class(class_name, fields))
        write_to_file(f"{output_dir}/src/test/java/{APPLICATION}/services/{class_name}ServiceTest.java", generate_service_test(class_name, fields))
        write_to_file(f"{output_dir}/src/test/java/{APPLICATION}/repositories/{class_name}RepositoryTest.java", generate_repository_test(class_name, fields))
        write_to_file(f"{output_dir}/src/test/java/{APPLICATION}/controllers/{class_name}ControllerTest.java", generate_controller_test(class_name, fields))

convert_python_to_javatest("/home/fitzwilliam/Documents/data/2025_03_31_10-39-50.MM00493.txt", ".")
