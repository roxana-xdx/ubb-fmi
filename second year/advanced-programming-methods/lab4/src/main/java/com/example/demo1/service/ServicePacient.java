package main.java.com.example.demo1.service;

import com.example.demo1.domain.Pacient;
import com.example.demo1.repository.MemoryRepository;
import com.example.demo1.repository.RepositoryException;

import java.io.IOException;
import java.util.Collection;

public class ServicePacient {
    MemoryRepository<Pacient> repoPacient;

    public ServicePacient(MemoryRepository<Pacient> repoPacient) {
        this.repoPacient = repoPacient;
    }

    public void add(int id, String nume, String prenume, int varsta) throws RepositoryException, IOException {
        Pacient t = new Pacient(id, nume, prenume, varsta);
        repoPacient.add(t);
    }

    public void remove(int id) throws RepositoryException, IOException {
        repoPacient.delete(id);
    }

    public void update(int id, String nume, String prenume, int varsta) throws RepositoryException, IOException {
        Pacient t = new Pacient(id, nume, prenume, varsta);
        repoPacient.update(id, t);
    }

    public Pacient print(int id) {
        return repoPacient.find(id);
    }

    public Collection<Pacient> getAll() {
        return repoPacient.getAll();
    }
}
