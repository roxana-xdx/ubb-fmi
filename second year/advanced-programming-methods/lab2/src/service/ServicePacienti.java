package service;

import Repository.Repository;
import domain.IDGenerator;
import domain.Pacient;

import java.util.List;

public class ServicePacienti {
    private Repository<Pacient> pacientRepository;

    public ServicePacienti(Repository<Pacient> repository) {
        this.pacientRepository = repository;
    }

    public void addPacient(String nume, String prenume, int varsta) throws Exception {
        int id = IDGenerator.generateId();
        Pacient pacient = new Pacient(id, nume, prenume, varsta);
        pacientRepository.add(pacient);
    }

    public Pacient getPacientById(int id) throws Exception {
        return pacientRepository.getById(id);
    }

    public List<Pacient> getAllPacienti() {
        return pacientRepository.getAll();
    }

    public void deletePacient(int id) throws Exception {
        pacientRepository.delete(id);
    }
}

