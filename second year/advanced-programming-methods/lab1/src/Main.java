/* Display the prime numbers found within the command-line arguments of the program.
If the program is started without providing at least one number in the command line,
read these values through the user interface.*/

import java.util.Scanner;

public class Main {
    public static void main(String[] args) { //checks if there are numbers in the command line
        boolean numbers = false;
        if (args.length > 0) {
            for (int i = 0; i <= args.length; ++i) {
                if (is_number(args[i])) {
                    numbers = true;
                    break;
                }
            }
        }
        if (numbers) { //if there are numbers in the command line
            for (String arg : args) {
                try {
                    int iarg = Integer.parseInt(arg);
                    if (prime_number(iarg)) //checks if number is prime
                        System.out.println(iarg); //prints prime numbers
                } catch (NumberFormatException e) { //for values in the command line that are not numbers
                    System.out.println("Invalid argument: " + arg);
                }
            }
        }
        else { //if there are no numbers in the command line, the user inserts them
            System.out.println("Insert numbers: ");
            while (true) {
                Scanner scanner = new Scanner(System.in);
                int x;
                try {
                    String n = scanner.nextLine();
                    if (n.equalsIgnoreCase("stop")){ //to stop inserting input, the user writes "stop"
                        break;
                    }
                    x = Integer.parseInt(n);
                    if (prime_number(x)) {
                        System.out.println(x); //prime numbers are printed
                    }
                } catch (NumberFormatException e) { //for inserted values that are not numbers
                    System.out.println("Pick a number.");
                }
            }
        }
    }
    public static boolean prime_number(int num) { //returns true if number is prime and false otherwise
        if (num < 2) {
            return false;
        } else if (num == 2) {
            return true;
        } else if (num % 2 == 0) {
            return false;
        } else {
            for (int i = 3; i * i <= num; i += 2) {
                if (num % i == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean is_number(String str) { //returns true if input is a number and false otherwise
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
