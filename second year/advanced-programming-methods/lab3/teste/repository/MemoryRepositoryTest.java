package repository;

import domain.Pacient;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MemoryRepositoryTest {

    @Test
    void add() throws RepositoryException, IOException {
        MemoryRepository<Pacient> repository = new MemoryRepository<>();
        int l1 = repository.getAll().size();
        Pacient p = new Pacient(1, "Popa", "Ana", 24);
        repository.add(p);
        int l2 = repository.getAll().size();
        assertEquals(l1+1,l2);
    }

    @Test
    void delete() throws RepositoryException, IOException {
        MemoryRepository<Pacient> repository = new MemoryRepository<>();
        Pacient p = new Pacient(1, "Popa", "Ana", 24);
        repository.add(p);
        int l1 = repository.getAll().size();
        repository.delete(1);
        int l2 = repository.getAll().size();
        assertEquals(l1,l2+1);
    }

    @Test
    void find() throws RepositoryException, IOException {
        MemoryRepository<Pacient> repository = new MemoryRepository<>();
        Pacient p = new Pacient(1, "Popa", "Ana", 24);
        repository.add(p);
        Pacient p1 = repository.find(1);
        assertEquals(p,p1);
    }

    @Test
    void update() throws RepositoryException, IOException {
        MemoryRepository<Pacient> repository = new MemoryRepository<>();
        Pacient p = new Pacient(1, "Popa", "Ana", 24);
        repository.add(p);
        Pacient p1 = new Pacient(1, "Pop", "Anna", 23);
        repository.update(1,p1);
        assertEquals(p1,repository.find(1));
    }

    @Test
    void size() throws RepositoryException, IOException {
        MemoryRepository<Pacient> repository = new MemoryRepository<>();
        Pacient p = new Pacient(1, "Popa", "Ana", 24);
        Pacient p1 = new Pacient(2, "Pop", "Anna", 23);
        repository.add(p);
        repository.add(p1);
        assertEquals(2, repository.size());
    }
}