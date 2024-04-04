import java.util.Scanner;
import java.time.Year;

public class main {
    static Scanner mainScanner = new Scanner(System.in); // Declaring Scanner globally


    static String[][] frontSeats = {
            {"0", "0", "0", "0"},
            {"0", "0", "0", "0"}
    };

    static String[][] centralSeats = {
            {"0", "0", "0", "0"},
            {"0", "0", "0", "0"}
    };

    static String[][] backSeats = {
            {"0", "0", "0", "0"},
            {"0", "0", "0", "0"}
    };

    /**
     * Print the menu of role options for user selection.
     *
     * @return `1` for Bus Inspector, `2` for Customer, '0' to Exit
     */

    static int promptUserForRoleChoice() {
        int choice;
        while (true) {
            System.out.print("Choose from below:\t");
            System.out.println("0.Exit: \t 1. Bus Inspector: \t 2. Passenger: ");
            System.out.print(": ");
            choice = mainScanner.nextInt();

            if (choice == 0 || choice == 1 || choice == 2) {
                return choice;
            } else {
                System.out.println("Please enter a valid option from (0, 1, or 2).");
            }
        }
    }

    static void startBusService() {
        showBus();
        switch (promptUserForRoleChoice()) {
            case 1:
                bussInspector();
                break;
            case 2:
                startCustomerService();
                break;
            default:
                System.out.println("Thanks for using our service ");
        }
    }

    static void showBus() {
        System.out.println("          _______");
        System.out.println("   ___ /_____\\_____\\ ___");

        System.out.println("  |       Front      |");
        printSeats(frontSeats);
        System.out.println("  |       Central    |");
        printSeats(centralSeats);
        System.out.println("  |       Back       |");
        printSeats(backSeats);

        System.out.println("  |_____________________|");
    }

    static void printSeats(String[][] seats) {
        for (String[] row : seats) {
            printRow(row);
            printEmptyRow();
        }
    }

    static void printRow(String[] row) {
        System.out.println("  |                     |");
        System.out.print("  |");
        for (int seat = 0; seat < row.length; seat++) {
            System.out.print(" " + 0 + " ");
        }
        System.out.println("|");
    }

    static void printEmptyRow() {
        System.out.println("  |                     |");
    }

    static int customerChoiceCenter() {
        int customerChoice;
        System.out.println("Welcome: Choose from the options below to continue.");
        System.out.println("0. Exit 1. \t Show The Bus: \t 2. Book Seat: \t 3. Unbook Seat: ");
        System.out.print(": ");
        customerChoice = mainScanner.nextInt();

        if (customerChoice == 0 || customerChoice == 1 || customerChoice == 2) {
            return customerChoice;
        } else {
            System.out.println("Please enter a valid option from (0, 1, or 2).");
            //recursive call for the user to try again.
            return customerChoiceCenter();
        }

    }

    static void startCustomerService() {
        switch (customerChoiceCenter()) {
            case 1:
                showBus();
                break;
            case 2:
                bookSeat();
                break;
            case 3:
                unBookSeat();
                break;
            default:
                System.out.println("Thanks for using our service ");
        }
    }

    static void bookSeat() {
        System.out.println("Booking logic coming soon. ");
    }

    static void unBookSeat() {
        System.out.println("Un Booking logic coming soon. ");
    }

    static void bussInspector() {

    }

    public static void main(String[] args) {
        startBusService();
    }
}
