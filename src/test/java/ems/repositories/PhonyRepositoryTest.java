package ems.repositories;

import ems.models.Phony;
import ems.repositories.PhonyRepository;
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

public class PhonyRepositoryTest {
    @Mock private PhonyRepository phonyRepository;
    private Phony entity;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        entity = new Phony();

        entity.setSerialNumber("testValue");

        entity.setTime(LocalDateTime.parse("2025-04-07T13:22:04.462379071"));

        entity.setColumn1(42);

        entity.setColumn2("testValue");

        entity.setColumn3("testValue");

    }

    @Test
    public void testFindAll() {
        when(phonyRepository.findAll()).thenReturn(List.of(entity));
        List<Phony> result = phonyRepository.findAll();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    public void testFindBySerialNumber() {
        when(phonyRepository.findBySerialNumber("testValue")).thenReturn(List.of(entity));
        List<Phony> result = phonyRepository.findBySerialNumber("testValue");
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testFindByTime() {
        when(phonyRepository.findByTime(LocalDateTime.parse("2025-04-07T13:22:04.462379071"))).thenReturn(List.of(entity));
        List<Phony> result = phonyRepository.findByTime(LocalDateTime.parse("2025-04-07T13:22:04.462379071"));
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testFindByTimeBetween() {
        LocalDateTime start = LocalDateTime.parse("2025-04-01T00:00:00");
        LocalDateTime end = LocalDateTime.parse("2025-04-30T23:59:59");
        when(phonyRepository.findByTimeBetween(start, end)).thenReturn(List.of(entity));
        List<Phony> result = phonyRepository.findByTimeBetween(start, end);
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testFindByColumn1() {
        when(phonyRepository.findByColumn1(42)).thenReturn(List.of(entity));
        List<Phony> result = phonyRepository.findByColumn1(42);
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testFindByColumn2() {
        when(phonyRepository.findByColumn2("testValue")).thenReturn(List.of(entity));
        List<Phony> result = phonyRepository.findByColumn2("testValue");
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testFindByColumn3() {
        when(phonyRepository.findByColumn3("testValue")).thenReturn(List.of(entity));
        List<Phony> result = phonyRepository.findByColumn3("testValue");
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
}