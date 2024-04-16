import java.util.Scanner;
import java.time.Year;

public class main {
    static Scanner mainScanner = new Scanner(System.in); // Declaring Scanner globally

    static String[][] busSeats = {
            {"0", "0", "0", "0"},
            {"0", "0", "0", "0"},
            {"0", "0", "0", "0"},
            {"0", "0", "0", "0"}
    };
    public static void main(String[] args) {
        startBusService();
    }

    static void startBusService() {
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
            printRolesChoices();
            choice = mainScanner.nextInt();
            if (choice == 1 || choice == 2 || choice == 3) {
                return choice;
            } else {
                System.out.println("Please enter a valid option from (0, 1, or 2).");
            }
        }
    }

    static void printRolesChoices() {
        System.out.println("Choose from below:");
        System.out.println("1. Bus  Inspector:");
        System.out.println("2. Passenger");
        System.out.println("3. Exist");
        System.out.print(": ");
    }

    static void showBus() {
        printBusFrontSection();
        printBusBack(); // Print the back of the bus
    }

    static void printBusFrontSection() {
        System.out.println("   /\\    /\\");
        System.out.println(" __>-<____>-<__");
        System.out.println("/___|----------\\");
        System.out.print("|__|__/   _===:");
    }

    static void printSeats(String[][] seats, String section) {
        for (int i = 0; i < seats.length; i++) {
            printRow(seats[i], section + " Row " + (i + 1));
            printEmptyRow();
        }
    }

    static void printRow(String[] row, String rowName) {
        System.out.println("  |                     |");
        System.out.print("  |" + rowName + ": ");
        for (String seat : row) {
            if (seat.equals("0")) {
                System.out.print(" O ");
            } else {
                System.out.print(" X ");
            }
        }
        System.out.println("|");
    }

    static void printEmptyRow() {
        System.out.println("  |                     |");
    }


    static void startCustomerService() {
        switch (getCustomerChoice()) {
            case 1:
                bookSeat();
                break;
            case 2: showBus();
                break;
            case 3:
                System.out.println("Find Booked Seat.");
                break;
            case 4: System.out.println("Un Book Seat");;
            break;
            default:
                System.out.println("Thanks for using our service");
        }
    }

    static int getCustomerChoice() {
        int customerChoice;
        System.out.println("0.Exit");
        System.out.println("1.Book seat");
        System.out.println("2.Show bus");
        System.out.println("3.Find your booking");
        System.out.println("4.Cancel booking");
        System.out.print("> ");
        customerChoice = mainScanner.nextInt();

        if (customerChoice >= 0 && customerChoice <= 3) {
            return customerChoice;
        } else {
            System.out.println("Please enter a valid option from (0, 1, 2, or 3).");
            // Recursive call for the user to try again.
            return getCustomerChoice();
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
        return row >= 0 && row < busSeats.length && seat >= 0 && seat < busSeats[0].length;
    }

    static boolean isSeatAvailable(int row, int seat) {
        return busSeats[row][seat].equals("0") && busSeats[row][seat].equals("0") && busSeats[row][seat].equals("0");
    }

    static void markSeatAsBooked(int row, int seat) {
        if (row < 2) {
            busSeats[row][seat] = "X";
        } else if (row < 4) {
            busSeats[row - 2][seat] = "X";
        } else {
            busSeats[row - 4][seat] = "X";
        }
    }

    static void unBookSeat() {
        // To be implemented
        System.out.println("Unbooking logic coming soon.");
    }

    static void busInspector() {
        // To be implemented
    }

    public static void printBusBack(){
        System.out.println("\n|--------------|");
        System.out.println("\\--------------/");
        System.out.println(" \\-/_\\----/_\\-/\n");
    }
}
