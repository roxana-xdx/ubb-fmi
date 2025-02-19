package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PacientFactoryTest {

    @Test
    void createEntity() {
        final PacientFactory factory = new PacientFactory();
        String input = "1,pop,ana,20";
        Pacient p = factory.createEntity(input);
        assertEquals(1,p.getId());
        assertEquals("pop",p.getNume());
        assertEquals("ana",p.getPrenume());
        assertEquals(20,p.getVarsta());

    }
}