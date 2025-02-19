package repository;

import domain.Pacient;
import domain.PacientFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class FileRepositoryTest {
    private static final String file_name = "test.txt";

    @Test
    void add() throws RepositoryException, ParseException, IOException {
        PacientFactory tortFactory = new PacientFactory();
        FileRepository<Pacient> fileRepo = new FileRepository<>(file_name,tortFactory);
        if (fileRepo.find(1) != null)
            fileRepo.delete(1);
        Pacient p = new Pacient(1, "Popa", "Ana", 24);
        fileRepo.add(p);
        assertEquals(p,fileRepo.find(1));
    }

    @Test
    void delete() throws RepositoryException, IOException, ParseException {
        PacientFactory tortFactory = new PacientFactory();
        FileRepository<Pacient> fileRepo = new FileRepository<>(file_name,tortFactory);
        if (fileRepo.find(1) != null)
            fileRepo.delete(1);
        Pacient p = new Pacient(1, "Popa", "Ana", 24);
        fileRepo.add(p);
        fileRepo.delete(1);
        assertNull(fileRepo.find(1));
    }

    @Test
    void update() throws RepositoryException, IOException, ParseException {
        PacientFactory tortFactory = new PacientFactory();
        FileRepository<Pacient> fileRepo = new FileRepository<>(file_name,tortFactory);
        if (fileRepo.find(1) != null)
            fileRepo.delete(1);
        Pacient p = new Pacient(1, "Popa", "Ana", 24);
        fileRepo.add(p);
        Pacient p1 = new Pacient(1, "Pop", "Andreea", 34);
        fileRepo.update(1,p1);
        assertEquals(p1,fileRepo.find(1));
    }
}