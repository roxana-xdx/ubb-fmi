import domain.Pacient;
import domain.PacientFactory;
import domain.Programare;
import domain.ProgramareFactory;
import repository.BinaryFileRepository;
import repository.FileRepository;
import repository.MemoryRepository;
import repository.RepositoryException;
import service.ServicePacient;
import service.ServiceProgramare;
import ui.ConsoleUI;
import util.Settings;

import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static MemoryRepository<Pacient> getRepository1() throws RepositoryException, ParseException {

        Settings settings = Settings.getInstance("src/settings.properties");

        if ("file".equals(settings.getRepoType())) {
            return new FileRepository<Pacient>(settings.getFilePacient(), new PacientFactory());
        } else if ("bin".equals(settings.getRepoType())) {
            return new BinaryFileRepository<Pacient>(settings.getFilePacient());
        } else if ("memory".equals(settings.getRepoType())) {
            return new MemoryRepository<Pacient>();
        }
        throw new IllegalArgumentException("Fisierul de setari e gresit.");
    }

    public static MemoryRepository<Programare> getRepository2() throws RepositoryException, ParseException {

        Settings settings = Settings.getInstance("src/settings.properties");

        if ("file".equals(settings.getRepoType())) {
            return new FileRepository<Programare>(settings.getFileProgramare(), new ProgramareFactory());
        } else if ("bin".equals(settings.getRepoType())) {
            return new BinaryFileRepository<Programare>(settings.getFileProgramare());
        } else if ("memory".equals(settings.getRepoType())) {
            return new MemoryRepository<Programare>();
        }
        throw new IllegalArgumentException("Fisierul de setari e gresit.");
    }

    public static void main(String[] args) throws RepositoryException, ParseException {

        try{
            MemoryRepository<Pacient> repository1 = getRepository1();
            ServicePacient service1 = new ServicePacient(repository1);
            MemoryRepository<Programare> repository2 = getRepository2();
            ServiceProgramare service2 = new ServiceProgramare(repository2);
            ConsoleUI ui = new ConsoleUI(service1, service2);
            ui.start();
        } catch (RepositoryException e) {
            System.out.println(e.getMessage());
        }
    }
//        Pacient Alice = new Pacient(1, "Pop", "Alice", 21);
//        Pacient Bob = new Pacient(2, "Pop", "Bob", 23);
//        Pacient Charlie = new Pacient(3, "Popescu", "Charlie", 24);
//        Pacient Dan = new Pacient(4, "Popescu", "Dan", 25);
//        Pacient Ernest = new Pacient(5, "Popa", "Ernest", 26);
//
//        pacientRepository.add(Alice);
//        pacientRepository.add(Bob);
//        pacientRepository.add(Charlie);
//        pacientRepository.add(Dan);
//        pacientRepository.add(Ernest);
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
//        programareRepository.add(new Programare(1, Alice, dateFormat.parse("2024-11-5 14:00"), "control"));
//        programareRepository.add(new Programare(2, Bob, dateFormat.parse("2024-11-6 15:00"), "interventie"));
//        programareRepository.add(new Programare(3, Charlie, dateFormat.parse("2024-11-6 16:00"), "interventie"));
//        programareRepository.add(new Programare(4, Dan, dateFormat.parse("2024-11-5 16:00"), "control"));
//        programareRepository.add(new Programare(5, Dan, dateFormat.parse("2024-11-7 14:00"), "interventie"));

//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("pacienti.bin"))) {
//            ArrayList<Entity> pacienti = new ArrayList<>();
//            pacienti.add(new Pacient(1, "Pop", "Ana", 20));
//            pacienti.add(new Pacient(2, "Bob", "Andrei", 21));
//            oos.writeObject(pacienti);
//        } catch (IOException e) {
//            System.err.println("Eroare la initializarea pacienti.bin: " + e.getMessage());
//        }
//
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("programari.bin"))) {
//            ArrayList<Programare> programari = new ArrayList<>();
//            Pacient Ana = new Pacient(8, "Popa", "Ana", 23);
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
//            programari.add(new Programare(1, Ana, dateFormat.parse("2021-11-20 13:00"), "control"));
//            oos.writeObject(programari);
//        } catch (IOException e) {
//            System.err.println("Eroare la initializarea programari.bin: " + e.getMessage());
//        }

//        UI.start();
}
