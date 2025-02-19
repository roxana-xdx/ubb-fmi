package domain;

import java.io.*;
import java.util.Scanner;

public class IDGenerator {
    private static final String ID_FILE = "last_id.txt";
    private static int currentId;

    static {
        try (Scanner scanner = new Scanner(new File(ID_FILE))) {
            if (scanner.hasNextInt()) {
                currentId = scanner.nextInt();
            } else {
                currentId = 100;
            }
        } catch (FileNotFoundException e) {
            currentId = 100;
        }
    }

    public static int generateId() {
        return currentId++;
    }

    public static void saveId() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ID_FILE))) {
            writer.write(String.valueOf(currentId));
        } catch (IOException e) {
            System.out.println("Error saving ID to file: " + e.getMessage());
        }
    }
}
