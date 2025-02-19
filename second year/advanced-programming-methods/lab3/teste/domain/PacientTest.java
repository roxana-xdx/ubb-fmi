package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PacientTest {

    @Test
    void getNume() {
        Pacient p = new Pacient(1,"Pop", "Ana", 20);
        assertEquals("Pop",p.getNume());
    }

    @Test
    void setNume() {
        Pacient p = new Pacient(1,"Pop", "Ana", 20);
        assertEquals("Pop",p.getNume());
        p.setNume("Popa");
        assertEquals("Popa",p.getNume());
    }

    @Test
    void getPrenume() {
        Pacient p = new Pacient(1,"Pop", "Ana", 20);
        assertEquals("Ana",p.getPrenume());
    }

    @Test
    void setPrenume() {
        Pacient p = new Pacient(1,"Pop", "Ana", 20);
        assertEquals("Ana",p.getPrenume());
        p.setPrenume("Anna");
        assertEquals("Anna",p.getPrenume());
    }

    @Test
    void getVarsta() {
        Pacient p = new Pacient(1,"Pop", "Ana", 20);
        assertEquals(20,p.getVarsta());
    }

    @Test
    void setVarsta() {
        Pacient p = new Pacient(1,"Pop", "Ana", 20);
        assertEquals(20,p.getVarsta());
        p.setVarsta(25);
        assertEquals(25,p.getVarsta());
    }
}