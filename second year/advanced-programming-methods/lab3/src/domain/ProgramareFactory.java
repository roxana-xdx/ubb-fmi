package domain;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProgramareFactory implements EntityFactory<Programare> {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

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
