package service;

import domain.Pacient;
import org.junit.jupiter.api.Test;
import repository.MemoryRepository;
import repository.RepositoryException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServicePacientTest {

    @Test
    void add() throws RepositoryException, IOException {
        MemoryRepository<Pacient> repository = new MemoryRepository<>();
        ServicePacient servicePacient = new ServicePacient(repository);
        int l1 = servicePacient.getAll().size();
        servicePacient.add(1, "Popa", "Ana", 24);
        int l2 = servicePacient.getAll().size();
        assertEquals(l1+1,l2);
    }

    @Test
    void remove() throws RepositoryException, IOException {
        MemoryRepository<Pacient> repository = new MemoryRepository<>();
        ServicePacient servicePacient = new ServicePacient(repository);
        servicePacient.add(1, "Popa", "Ana", 24);
        int l1 = servicePacient.getAll().size();
        servicePacient.remove(1);
        int l2 = servicePacient.getAll().size();
        assertEquals(l1,l2+1);
    }

    @Test
    void update() throws RepositoryException, IOException {
        MemoryRepository<Pacient> repository = new MemoryRepository<>();
        ServicePacient servicePacient = new ServicePacient(repository);
        servicePacient.add(1, "Popa", "Ana", 24);
        servicePacient.update(1, "Popescu", "Ana", 24);
        assertEquals("Popescu", servicePacient.print(1).getNume());
    }

    @Test
    void print() throws RepositoryException, IOException {
        MemoryRepository<Pacient> repository = new MemoryRepository<>();
        ServicePacient servicePacient = new ServicePacient(repository);
        Pacient p = new Pacient(1, "Popa", "Ana", 24);
        servicePacient.add(1,"Popa", "Ana", 24);
        Pacient p1 = servicePacient.print(1);
        assertEquals(p.getId(),p1.getId());
        assertEquals(p.getNume(),p1.getNume());
        assertEquals(p.getPrenume(),p1.getPrenume());
        assertEquals(p.getVarsta(),p1.getVarsta());
    }

    @Test
    void getAll() throws RepositoryException, IOException {
        MemoryRepository<Pacient> repository = new MemoryRepository<>();
        ServicePacient servicePacient = new ServicePacient(repository);
        servicePacient.add(1,"Popescu", "Ana", 24);
        servicePacient.add(2,"Popa", "Ana", 24);
        assertEquals(2,servicePacient.getAll().size());
    }
}