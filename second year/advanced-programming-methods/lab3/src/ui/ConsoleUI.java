package ui;

import domain.Pacient;
import domain.Programare;
import repository.RepositoryException;
import service.ServicePacient;
import service.ServiceProgramare;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ConsoleUI {
    private ServicePacient pacientService;
    private ServiceProgramare programareService;
    private Scanner scanner = new Scanner(System.in);

    public ConsoleUI(ServicePacient pacientService, ServiceProgramare programareService) {
        this.pacientService = pacientService;
        this.programareService = programareService;
    }

    public void start() {
        while(true) {
            System.out.println("\nMeniu:");
            System.out.println("1. Adaugare pacient");
            System.out.println("2. Modifica pacient");
            System.out.println("3. Sterge pacient");
            System.out.println("4. Listare pacienti");
            System.out.println("5. Adaugare programare");
            System.out.println("6. Modifica programare");
            System.out.println("7. Sterge programare");
            System.out.println("8. Listare programari");
            System.out.println("9. Iesire");
            System.out.println("Alegeti o optiune: ");

            int optiune = Integer.parseInt(scanner.nextLine());

            try {
                switch (optiune) {
                    case 1 -> adaugaPacient();
                    case 2 -> modificaPacient();
                    case 3 -> stergePacient();
                    case 4 -> listarePacienti();
                    case 5 -> adaugaProgramare();
                    case 6 -> modificaProgramare();
                    case 7 -> stergeProgramare();
                    case 8 -> listareProgramari();
                    case 9 -> { System.out.println("La revedere!"); return; }
                    default -> System.out.println("Optiune invalida.");
                }
            } catch (RuntimeException | ParseException | RepositoryException e) {
                System.out.println("Eroare: " + e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void adaugaPacient() throws RepositoryException, IOException {
        System.out.println("ID pacient: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Nume: ");
        String nume = scanner.nextLine();
        System.out.print("Prenume: ");
        String prenume = scanner.nextLine();
        System.out.print("Vârsta: ");
        int varsta = Integer.parseInt(scanner.nextLine());

        pacientService.add(id, nume, prenume, varsta);
        System.out.println("Pacient adaugat cu succes.");
    }

    private void modificaPacient() throws RepositoryException, IOException {
        System.out.println("ID pacient: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Nume nou: ");
        String nume = scanner.nextLine();
        System.out.print("Prenume nou: ");
        String prenume = scanner.nextLine();
        System.out.println("Varsta noua: ");
        int varsta = Integer.parseInt(scanner.nextLine());
        pacientService.update(id, nume, prenume, varsta);
        System.out.println("Pacient actualizat cu succes.");
    }

    private void stergePacient() throws RepositoryException, IOException {
        System.out.println("ID pacient: ");
        int id = Integer.parseInt(scanner.nextLine());
        for (Pacient pacient : pacientService.getAll()) {
            if (pacient.getId() == id) {
                pacientService.remove(id);
                System.out.println("Pacient sters cu succes.");
            }
        }
    }

    private void listarePacienti() {
        for (Pacient pacient : pacientService.getAll()) {
            System.out.println(pacient);
        }
    }

    private void adaugaProgramare() throws ParseException, RepositoryException, IOException {
        System.out.println("ID programare: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("ID pacient: ");
        int pacientId = Integer.parseInt(scanner.nextLine());
        System.out.println("Data programare (yyyy-mm-dd hh:mm): ");
        String dateString = scanner.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date data = dateFormat.parse(dateString);
        System.out.println("Scopul programarii: ");
        String scop = scanner.nextLine();

        programareService.add(id, pacientService.print(pacientId), data, scop);
        System.out.println("Programare adaugata cu succes.");
    }

    private void modificaProgramare() throws ParseException, RepositoryException, IOException {
        System.out.println("ID programare: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("ID nou pacient: ");
        int pacientId = Integer.parseInt(scanner.nextLine());
        System.out.println("Data noua a programarii (yyyy-mm-dd hh:mm): ");
        String dateString = scanner.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date data = dateFormat.parse(dateString);
        System.out.println("Scopul nou al programarii: ");
        String scop = scanner.nextLine();
        programareService.update(id, pacientService.print(pacientId), data, scop);
    }

    private void stergeProgramare() throws RepositoryException, IOException {
        System.out.println("ID programare: ");
        int id = Integer.parseInt(scanner.nextLine());
        for (Programare programare : programareService.getAll()) {
            if (programare.getId() == id) {
                programareService.remove(id);
                System.out.println("Programare stearsa cu succes.");
            }
        }
    }

    private void listareProgramari() {
        for (Programare programare : programareService.getAll()) {
            System.out.println(programare);
        }
    }
}