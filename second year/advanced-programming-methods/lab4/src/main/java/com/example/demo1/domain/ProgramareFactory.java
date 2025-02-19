package main.java.com.example.demo1.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProgramareFactory implements EntityFactory<Programare> {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");

    @Override
    public Programare createEntity(String line) {
        try {
            String[] parts = line.split(",");
            int id = Integer.parseInt(parts[0]);
            int pacientId = Integer.parseInt(parts[1]);
            String nume = parts[2];
            String prenume = parts[3];
            int varsta = Integer.parseInt(parts[4]);
            Date data = dateFormat.parse(parts[5]);
            String scop = parts[6];
            return new Programare(id, new Pacient(pacientId, nume, prenume, varsta), data, scop);
        } catch (Exception e) {
            throw new RuntimeException("Eroare la conversia programarii.", e);
        }
    }
}
