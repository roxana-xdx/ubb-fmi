package ui;

import Repository.Repository;
import domain.Pacient;
import domain.Programare;
import Repository.MemoryRepository;
import service.ServicePacienti;
import service.ServiceProgramari;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;

public class UI {
        static Scanner scanner = new Scanner(System.in);

        public static Repository<Pacient> pacientRepository = new MemoryRepository<Pacient>();
        static Repository<Programare> programareRepository = new MemoryRepository<Programare>();

        public static ServicePacienti servicePacienti = new ServicePacienti(pacientRepository);
        public static ServiceProgramari serviceProgramari = new ServiceProgramari(programareRepository, pacientRepository);

        public static void start() throws ParseException {
        boolean running = true;
        while (running) {
            System.out.println("1. Adauga Pacient");
            System.out.println("2. Adauga Programare");
            System.out.println("3. Vezi Pacienti");
            System.out.println("4. Vezi Programari");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Prenume: ");
                    String name = scanner.nextLine();
                    System.out.print("Nume: ");
                    String surname = scanner.nextLine();
                    System.out.print("Varsta: ");
                    int age = scanner.nextInt();
                    try {
                        servicePacienti.addPacient(name, surname, age);
                        System.out.println("Pacient adaugat cu succes!");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("ID Pacient: ");
                    int pacientId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Data (yyyy-MM-dd hh:mm): ");
                    String dateString = scanner.nextLine();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                    Date date = dateFormat.parse(dateString);
                    System.out.print("Scop: ");
                    String purpose = scanner.nextLine();
                    try {
                        serviceProgramari.addProgramare(pacientId, date, purpose);
                        System.out.println("Programare adaugata cu succes!");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Pacienti:");
                    servicePacienti.getAllPacienti().forEach(p ->
                            System.out.println("ID: " + p.getId() + " Nume: " + p.getNume() + " " + p.getPrenume() + " Varsta: " + p.getVarsta()));
                    break;
                case 4:
                    System.out.println("Programari:");
                    serviceProgramari.getAllProgramari().forEach(p ->
                            System.out.println("ID: " + p.getId() + " ID Pacient: " + p.getPacient().getId() + " Data: " + p.getData() + " Scop: " + p.getScop()));
                    break;
                case 5:
                    running = false;
                    break;
            }
        }

        scanner.close();
    }
}
