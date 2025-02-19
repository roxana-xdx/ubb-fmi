package main.java.com.example.demo1;

import com.example.demo1.domain.Pacient;
import com.example.demo1.domain.PacientFactory;
import com.example.demo1.domain.Programare;
import com.example.demo1.domain.ProgramareFactory;
import com.example.demo1.repository.*;
import com.example.demo1.service.ServicePacient;
import com.example.demo1.service.ServiceProgramare;
import com.example.demo1.ui.ConsoleUI;
import com.example.demo1.util.Settings;

import java.sql.SQLException;
import java.text.ParseException;

public class Main {
    public static MemoryRepository<Pacient> getRepository1() throws RepositoryException, ParseException, SQLException {

        Settings settings = Settings.getInstance("src/settings.properties");

        if ("file".equals(settings.getRepoType())) {
            return new FileRepository<Pacient>(settings.getFilePacient(), new PacientFactory());
        } else if ("bin".equals(settings.getRepoType())) {
            return new BinaryFileRepository<Pacient>(settings.getFilePacient());
        } else if ("memory".equals(settings.getRepoType())) {
            return new MemoryRepository<Pacient>();
        } else if ("sql".equals(settings.getRepoType())) {
            return new SQLPacientRepository();
        }
        throw new IllegalArgumentException("Fisierul de setari e gresit.");
    }

    public static MemoryRepository<Programare> getRepository2() throws RepositoryException, ParseException, SQLException {

        Settings settings = Settings.getInstance("src/settings.properties");

        if ("file".equals(settings.getRepoType())) {
            return new FileRepository<Programare>(settings.getFileProgramare(), new ProgramareFactory());
        } else if ("bin".equals(settings.getRepoType())) {
            return new BinaryFileRepository<Programare>(settings.getFileProgramare());
        } else if ("memory".equals(settings.getRepoType())) {
            return new MemoryRepository<Programare>();
        } else if ("sql".equals(settings.getRepoType())) {
            return new SQLProgramareRepository();
        }
        throw new IllegalArgumentException("Fisierul de setari e gresit.");
    }

    public static void main(String[] args) throws ParseException {
        try{
            MemoryRepository<Pacient> repository1 = getRepository1();
            ServicePacient service1 = new ServicePacient(repository1);
            MemoryRepository<Programare> repository2 = getRepository2();
            ServiceProgramare service2 = new ServiceProgramare(repository2);
            ConsoleUI ui = new ConsoleUI(service1, service2);
            ui.start();
        } catch (RepositoryException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
