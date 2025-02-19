import Repository.MemoryRepository;
import Repository.Repository;
import domain.IDGenerator;
import domain.Pacient;
import domain.Programare;
import service.ServicePacienti;
import service.ServiceProgramari;
import ui.UI;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Repository<Pacient> pacientRepository = new MemoryRepository<Pacient>();
        Repository<Programare> programareRepository = new MemoryRepository<Programare>();

        ServicePacienti servicePacienti = new ServicePacienti(pacientRepository);
        ServiceProgramari serviceProgramari = new ServiceProgramari(programareRepository, pacientRepository);

        for (int i = 1; i <= 5; i++) {
            String nume = "Nume" + (i);
            String prenume = "Prenume" + (i);
            int id = IDGenerator.generateId();
            Pacient pacient = new Pacient(id, nume, prenume, 20 + i);
            pacientRepository.add(pacient);
        }

        Scanner scan = new Scanner(new File("last_id.txt"));
        int pacientID = scan.nextInt();
        //int pacientID = 100;
        scan.close();
        //System.out.println(pacientID);

        for (int i = 0; i < 5; i++) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            Date data = dateFormat.parse("2024-11-" + str(i) + " 14:00");
            String scop = "control" + (i + 1);
            int id = IDGenerator.generateId();
            Programare programare = new Programare(id, pacientRepository.getById(pacientID), data, scop);
            programareRepository.add(programare);
            pacientID += 1;
        }
        IDGenerator.saveId();

        UI.servicePacienti = servicePacienti;
        UI.serviceProgramari = serviceProgramari;

        UI.start();
    }

    private static String str(int i) {
        return i + "";
    }
}
