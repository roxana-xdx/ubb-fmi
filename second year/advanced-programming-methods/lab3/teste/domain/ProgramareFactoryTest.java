package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProgramareFactoryTest {

    @Test
    void createEntity() {
        ProgramareFactory factory = new ProgramareFactory();
        String input = "1,1,2024-11-05 02:00,control";
        Programare pr = factory.createEntity(input);
        assertEquals(1,pr.getId());
    }
}