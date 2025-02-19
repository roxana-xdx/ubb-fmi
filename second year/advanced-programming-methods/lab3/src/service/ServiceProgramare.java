package service;

import domain.Pacient;
import domain.Programare;
import domain.ProgramareFactory;
import repository.MemoryRepository;
import repository.RepositoryException;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

public class ServiceProgramare {
    MemoryRepository<Programare> repository;

    public ServiceProgramare(MemoryRepository<Programare> repository) {
        this.repository = repository;
    }

    public void add(int id, Pacient pacient, Date data, String scop) throws RepositoryException, IOException {
        Programare programare = new Programare(id, pacient, data, scop);
        for (Programare p : repository.getAll()) {
            if (p.getData().equals(programare.getData())) {
                throw new RuntimeException("Programare suprapusa! Aceasta ora e deja rezervata.");
            }
            else if (programare.getId() == p.getId()) {
                throw new RuntimeException("ID-ul trebuie sa fie unic.");
            }
        }
        repository.add(programare);
    }

    public void remove(int id) throws RepositoryException, IOException {
        repository.delete(id);
    }

    public void update(int id, Pacient pacient, Date data, String scop) throws RepositoryException, IOException {
        boolean idGasit = false;
        for (Programare p : repository.getAll()) {
            if (p.getId() == id) {
                p.setPacient(pacient);
                p.setData(data);
                p.setScop(scop);
                idGasit = true;
            }
        }
        if (idGasit) {
            System.out.println("Programare actualizata cu succes.");
        }
        else {
            System.out.println("Nu exista nicio programare cu acest ID.");
        }
    }

    public Programare print(int id) {
        return repository.find(id);
    }

    public Collection<Programare> getAll() {
        return repository.getAll();
    }

}
