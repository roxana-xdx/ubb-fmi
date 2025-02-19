package repository;

import domain.Pacient;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class BinaryFileRepositoryTest {
    private static final String file_name = "test.bin";
    private BinaryFileRepository<Pacient> repoPacient;

    @Test
    void add() throws RepositoryException, IOException {
        repoPacient = new BinaryFileRepository<>(file_name);
        Pacient p = new Pacient(1, "Popa", "Ana", 24);
        repoPacient.add(p);
        repoPacient.saveFile();
        assertEquals(p,repoPacient.find(1));
        File file = new File(file_name);
        if (file.exists()){
            new FileWriter(file_name, false).close();
        }
        repoPacient.delete(1);
        repoPacient.saveFile();
    }

    @Test
    void delete() throws RepositoryException, IOException {
        repoPacient = new BinaryFileRepository<>(file_name);
        Pacient p = new Pacient(1, "Popa", "Ana", 24);
        repoPacient.add(p);
        repoPacient.delete(1);
        repoPacient.saveFile();
        assertNull(repoPacient.find(1));
        File file = new File(file_name);
        if (file.exists()){
            new FileWriter(file_name, false).close();
        }
    }

    @Test
    void update() throws RepositoryException, IOException {
        repoPacient = new BinaryFileRepository<>(file_name);
        Pacient p = new Pacient(1, "Popa", "Ana", 24);
        repoPacient.add(p);
        Pacient p1 = new Pacient(1, "Popescu", "Antonia", 31);
        repoPacient.update(1,p1);
        repoPacient.saveFile();
        assertEquals(p1,repoPacient.find(1));
        File file = new File(file_name);
        if (file.exists()){
            new FileWriter(file_name, false).close();
        }
        repoPacient.delete(1);
        repoPacient.saveFile();
    }
}