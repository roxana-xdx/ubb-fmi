package repository;

import domain.Pacient;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DuplicateIDExceptionTest {
    @Test
    void duplicateID() {
        MemoryRepository<Pacient> repository = new MemoryRepository<>();

        Pacient p = new Pacient(1, "Popa", "Ana", 24);
        try{
            repository.add(p);
            repository.add(p);
            fail("Exceptie id duplicat.");
        } catch (DuplicateIDException e){
            assertEquals("Deja exista o entitate cu acest id.", e.getMessage());
        } catch (RepositoryException | IOException e){
            throw new RuntimeException(e);
        }
    }
}