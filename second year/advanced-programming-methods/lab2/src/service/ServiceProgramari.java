package service;

import domain.IDGenerator;
import domain.Programare;
import domain.Pacient;
import Repository.Repository;
import java.util.Date;

public class ServiceProgramari {

    private Repository<Programare> programareRepository;
    private Repository<Pacient> pacientRepository;

    public ServiceProgramari(Repository<Programare> programareRepository, Repository<Pacient> pacientRepository) {
        this.programareRepository = programareRepository;
        this.pacientRepository = pacientRepository;
    }

    public void addProgramare(int pacientId, Date date, String scop) throws Exception {
        Pacient pacient = pacientRepository.getById(pacientId);
        if (pacient == null) {
            throw new Exception("Pacient with ID " + pacientId + " not found.");
        }

        for (Programare existingProgramare : programareRepository.getAll()) {
            if (isOverlapping(existingProgramare.getData(), date)) {
                throw new Exception("Programarea noua se suprapune cu alta programare.");
            }
        }
        int id = IDGenerator.generateId();
        Programare newProgramare = new Programare(id, pacient, date, scop);
        programareRepository.add(newProgramare);
    }

    private boolean isOverlapping(Date existingDate, Date newDate) {
        long duration = 60;
        long existingStart = existingDate.getTime() / 1000;
        long existingEnd = existingStart + duration;
        long newStart = newDate.getTime() / 1000;
        long newEnd = newStart + duration;

        return (newStart < existingEnd) && (newEnd > existingStart);
    }

    public Iterable<Programare> getAllProgramari() {
        return programareRepository.getAll();
    }
}
