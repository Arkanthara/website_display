package ems.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

public class PhonyTest {

    @Test
    public void testGettersAndSetters() {
        Phony obj = new Phony();
        obj.setId(1L);
        assertEquals(1L, obj.getId());

        obj.setSerialNumber("testValue");
        assertEquals("testValue", obj.getSerialNumber());

        obj.setTime(LocalDateTime.parse("2025-04-07T13:22:04.462379071"));
        assertEquals(LocalDateTime.parse("2025-04-07T13:22:04.462379071"), obj.getTime());

        obj.setColumn1(42);
        assertEquals(42, obj.getColumn1());

        obj.setColumn2("testValue");
        assertEquals("testValue", obj.getColumn2());

        obj.setColumn3("testValue");
        assertEquals("testValue", obj.getColumn3());

    }
}
