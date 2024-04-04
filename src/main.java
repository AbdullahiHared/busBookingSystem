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

    public static void main(String[] args) {
        startBusService();
    }

    static void startBusService() {
        showBus();
        switch (promptUserForRoleChoice()) {
            case 1:
                busInspector();
                break;
            case 2:
                startCustomerService();
                break;
            default:
                System.out.println("Thanks for using our service ");
        }
    }

    static int promptUserForRoleChoice() {
        int choice;
        while (true) {
            System.out.print("Choose from below:\t");
            System.out.println("0. Exit\t 1. Bus Inspector\t 2. Passenger");
            System.out.print(": ");
            choice = mainScanner.nextInt();

            if (choice == 0 || choice == 1 || choice == 2) {
                return choice;
            } else {
                System.out.println("Please enter a valid option from (0, 1, or 2).");
            }
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
        for (String seat : row) {
            System.out.print(" " + seat + " ");
        }
        System.out.println("|");
    }

    static void printEmptyRow() {
        System.out.println("  |                     |");
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

    static int customerChoiceCenter() {
        int customerChoice;
        System.out.println("Welcome: Choose from the options below to continue.");
        System.out.println("0. Exit\t 1. Show The Bus\t 2. Book Seat\t 3. Unbook Seat");
        System.out.print(": ");
        customerChoice = mainScanner.nextInt();

        if (customerChoice >= 0 && customerChoice <= 3) {
            return customerChoice;
        } else {
            System.out.println("Please enter a valid option from (0, 1, 2, or 3).");
            // Recursive call for the user to try again.
            return customerChoiceCenter();
        }
    }

    static void bookSeat() {
        System.out.print("Enter row number (1 for first row, 2 for second row): ");
        int row = mainScanner.nextInt() - 1; // Adjusting to array index
        System.out.print("Enter seat number (1-4): ");
        int seat = mainScanner.nextInt() - 1; // Adjusting to array index

        if (isValidSeat(row, seat)) {
            if (isSeatAvailable(row, seat)) {
                System.out.println("Seat booked successfully!");
                markSeatAsBooked(row, seat);
            } else {
                System.out.println("This seat is already booked. Please choose another seat.");
            }
        } else {
            System.out.println("Invalid seat number. Please enter a valid row and seat number.");
        }
    }

    static boolean isValidSeat(int row, int seat) {
        return row >= 0 && row < frontSeats.length && seat >= 0 && seat < frontSeats[0].length;
    }

    static boolean isSeatAvailable(int row, int seat) {
        return frontSeats[row][seat].equals("0") && centralSeats[row][seat].equals("0") && backSeats[row][seat].equals("0");
    }

    static void markSeatAsBooked(int row, int seat) {
        if (row < 2) {
            frontSeats[row][seat] = "X";
        } else if (row < 4) {
            centralSeats[row - 2][seat] = "X";
        } else {
            backSeats[row - 4][seat] = "X";
        }
    }

    static void unBookSeat() {
        // To be implemented
        System.out.println("Unbooking logic coming soon.");
    }

    static void busInspector() {
        // To be implemented
    }
}
