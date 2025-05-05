package ems.services;

import ems.models.Phony;
import ems.repositories.PhonyRepository;
import ems.services.PhonyService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PhonyServiceTest {
    @Mock private PhonyRepository phonyRepository;
    @InjectMocks private PhonyService phonyService;

    public PhonyServiceTest() { MockitoAnnotations.openMocks(this); }

    @Test
    public void testFindAll() {
        when(phonyRepository.findAll()).thenReturn(Collections.singletonList(new Phony()));
        List<Phony> result = phonyService.findAll();
        assertEquals(1, result.size());
    }

    @Test
    public void testSave() {
        Phony obj = new Phony();
        when(phonyRepository.save(obj)).thenReturn(obj);
        Phony result = phonyService.save(obj);
        assertNotNull(result);
    }

    @Test
    public void testDeleteById() {
        doNothing().when(phonyRepository).deleteById(1L);
        phonyService.deleteById(1L);
        verify(phonyRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testFindBySerialNumber() {
        when(phonyRepository.findBySerialNumber("testValue")).thenReturn(List.of(new Phony()));
        List<Phony> result = phonyService.findBySerialNumber("testValue");
        assertNotNull(result);
    }

    @Test
    public void testFindByTime() {
        when(phonyRepository.findByTime(LocalDateTime.parse("2025-04-07T13:22:04.462379071"))).thenReturn(List.of(new Phony()));
        List<Phony> result = phonyService.findByTime(LocalDateTime.parse("2025-04-07T13:22:04.462379071"));
        assertNotNull(result);
    }

    @Test
    public void testFindByTimeBetween() {
        LocalDateTime start = LocalDateTime.parse("2025-04-01T00:00:00");
        LocalDateTime end = LocalDateTime.parse("2025-04-30T23:59:59");
        when(phonyRepository.findByTimeBetween(start, end)).thenReturn(List.of(new Phony()));
        List<Phony> result = phonyService.findByTimeBetween(start, end);
        assertNotNull(result);
    }

    @Test
    public void testFindByColumn1() {
        when(phonyRepository.findByColumn1(42)).thenReturn(List.of(new Phony()));
        List<Phony> result = phonyService.findByColumn1(42);
        assertNotNull(result);
    }

    @Test
    public void testFindByColumn2() {
        when(phonyRepository.findByColumn2("testValue")).thenReturn(List.of(new Phony()));
        List<Phony> result = phonyService.findByColumn2("testValue");
        assertNotNull(result);
    }

    @Test
    public void testFindByColumn3() {
        when(phonyRepository.findByColumn3("testValue")).thenReturn(List.of(new Phony()));
        List<Phony> result = phonyService.findByColumn3("testValue");
        assertNotNull(result);
    }
}