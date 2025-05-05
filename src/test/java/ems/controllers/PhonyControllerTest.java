package ems.controllers;

import ems.models.Phony;
import ems.services.PhonyService;
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

@WebMvcTest(PhonyController.class)
public class PhonyControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockitoBean private PhonyService phonyService;

    @Test
    public void testGetAll() throws Exception {
        when(phonyService.findAll()).thenReturn(Collections.singletonList(new Phony()));
        mockMvc.perform(get("/api/phony"))
               .andExpect(status().isOk());
    }

    @Test
    public void testCreate() throws Exception {
        when(phonyService.save(any())).thenReturn(new Phony());
        mockMvc.perform(post("/api/phony")
               .contentType(MediaType.APPLICATION_JSON)
               .content("{}"))
               .andExpect(status().isCreated());
    }

    @Test
    public void testUpdate() throws Exception {
        when(phonyService.save(any())).thenReturn(new Phony());
        mockMvc.perform(put("/api/phony/1")
               .contentType(MediaType.APPLICATION_JSON)
               .content("{}"))
               .andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception {
        doNothing().when(phonyService).deleteById(1L);
        mockMvc.perform(delete("/api/phony/1"))
               .andExpect(status().isNoContent());
    }

    @Test
    public void testFindBySerialNumber() throws Exception {
        when(phonyService.findBySerialNumber(any()))
            .thenReturn(Collections.singletonList(new Phony()));
        mockMvc.perform(get("/api/phony/findBySerialNumber")
            .param("serialNumber", "" + "testValue"))
            .andExpect(status().isOk());
    }

    @Test
    public void testFindByTime() throws Exception {
        when(phonyService.findByTime(any()))
            .thenReturn(Collections.singletonList(new Phony()));
        mockMvc.perform(get("/api/phony/findByTime")
            .param("time", "" + LocalDateTime.parse("2025-04-07T13:22:04.462379071")))
            .andExpect(status().isOk());
    }

    @Test
    public void testFindByTimeBetween() throws Exception {
        when(phonyService.findByTimeBetween(any(), any()))
            .thenReturn(Collections.singletonList(new Phony()));
        mockMvc.perform(get("/api/phony/findByTimeBetween")
            .param("start", "2025-04-01T00:00:00")
            .param("end", "2025-04-30T23:59:59"))
            .andExpect(status().isOk());
    }

    @Test
    public void testFindByColumn1() throws Exception {
        when(phonyService.findByColumn1(any()))
            .thenReturn(Collections.singletonList(new Phony()));
        mockMvc.perform(get("/api/phony/findByColumn1")
            .param("column1", "" + 42))
            .andExpect(status().isOk());
    }

    @Test
    public void testFindByColumn2() throws Exception {
        when(phonyService.findByColumn2(any()))
            .thenReturn(Collections.singletonList(new Phony()));
        mockMvc.perform(get("/api/phony/findByColumn2")
            .param("column2", "" + "testValue"))
            .andExpect(status().isOk());
    }

    @Test
    public void testFindByColumn3() throws Exception {
        when(phonyService.findByColumn3(any()))
            .thenReturn(Collections.singletonList(new Phony()));
        mockMvc.perform(get("/api/phony/findByColumn3")
            .param("column3", "" + "testValue"))
            .andExpect(status().isOk());
    }
}