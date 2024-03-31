import java.util.Scanner;

public class main {
    static Scanner mainScanner = new Scanner(System.in); // Declaring Scanner globally

    static String[][] seats = {
            {"0", "0", "0", "0"},
            {"0", "0", "0", "0"},
            {"0", "0", "0", "0"},
            {"0", "0", "0", "0"},
            {"0", "0", "0", "0"}
    };

    static int promptUserForRoleChoice() {
        System.out.print("Choose from below:\t");
        System.out.println("0.Exit: \t 1. Bus Inspector: \t 2. Passenger: ");
        System.out.print(": ");
        int choice = mainScanner.nextInt();
        return choice;
    }

    public static void main(String[] args) {
        promptUserForRoleChoice();
    }
}
