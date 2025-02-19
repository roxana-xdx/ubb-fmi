package service;

import domain.Pacient;
import domain.Programare;
import org.junit.jupiter.api.Test;
import repository.MemoryRepository;
import repository.RepositoryException;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServiceProgramareTest {

    @Test
    void add() throws ParseException, RepositoryException, IOException {
        MemoryRepository<Programare> repository = new MemoryRepository<>();
        ServiceProgramare serviceProgramare = new ServiceProgramare(repository);
        Pacient p = new Pacient(1, "Popa", "Ana", 24);
        int l1=serviceProgramare.getAll().size();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        serviceProgramare.add(1,p,dateFormat.parse("2024-12-10 20:10"),"control");
        int l2=serviceProgramare.getAll().size();
        assertEquals(l1+1,l2);
    }

    @Test
    void remove() throws ParseException, RepositoryException, IOException {
        MemoryRepository<Programare> repository = new MemoryRepository<>();
        ServiceProgramare serviceProgramare = new ServiceProgramare(repository);
        Pacient p = new Pacient(1, "Popa", "Ana", 24);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        serviceProgramare.add(1,p,dateFormat.parse("2024-12-10 20:10"),"control");
        int l1=serviceProgramare.getAll().size();
        serviceProgramare.remove(1);
        int l2=serviceProgramare.getAll().size();
        assertEquals(l1-1,l2);
    }

    @Test
    void update() throws ParseException, RepositoryException, IOException {
        MemoryRepository<Programare> repository = new MemoryRepository<>();
        ServiceProgramare serviceProgramare = new ServiceProgramare(repository);
        Pacient p = new Pacient(1, "Popa", "Ana", 24);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        serviceProgramare.add(1,p,dateFormat.parse("2024-12-10 20:10"),"control");
        serviceProgramare.update(1,p,dateFormat.parse("2024-12-10 18:00"),"control");
        assertEquals(dateFormat.parse("2024-12-10 18:00"),serviceProgramare.print(1).getData());
    }

    @Test
    void print() throws ParseException, RepositoryException, IOException {
        MemoryRepository<Programare> repository = new MemoryRepository<>();
        ServiceProgramare serviceProgramare = new ServiceProgramare(repository);
        Pacient p = new Pacient(1, "Popa", "Ana", 24);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        serviceProgramare.add(1,p,dateFormat.parse("2024-12-10 20:10"),"control");
        assertEquals(dateFormat.parse("2024-12-10 20:10"),serviceProgramare.print(1).getData());
    }

    @Test
    void getAll() throws ParseException, RepositoryException, IOException {
        MemoryRepository<Programare> repository = new MemoryRepository<>();
        ServiceProgramare serviceProgramare = new ServiceProgramare(repository);
        Pacient p = new Pacient(1, "Popa", "Ana", 24);
        Pacient p1 = new Pacient(2, "Pop", "Ana", 24);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        serviceProgramare.add(1,p,dateFormat.parse("2024-12-10 20:10"),"control");
        serviceProgramare.add(2,p1,dateFormat.parse("2024-12-11 20:10"),"control");
        assertEquals(2,serviceProgramare.getAll().size());
    }
}