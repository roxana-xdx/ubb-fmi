package repository;

import domain.Pacient;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ObjectNotFoundExceptionTest {
    @Test
    void notFound() throws IOException {
        MemoryRepository<Pacient> repository = new MemoryRepository<>();
        try{
            repository.delete(1);
            fail("Exceptie id inexistent.");
        } catch (ObjectNotFoundException e){
            assertEquals("Entitatea nu a fost gasita.", e.getMessage());
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }
}