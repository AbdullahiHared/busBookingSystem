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
        System.out.println("3. Exit");
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

    public static void printBusBack() {
        System.out.println("\n|--------------|");
        System.out.println("\\--------------/");
        System.out.println(" \\-/_\\----/_\\-/\n");
    }


    static void startCustomerService() {
        switch (getCustomerChoice()) {
            case 1:
                bookSeat();
                break;
            case 2:
                showBus();
                break;
            case 3:
                System.out.println("Find Booked Seat.");
                break;
            case 4:
                System.out.println("Un Book Seat");
                ;
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
        userInfo();
    }

    static void userInfo() {
        userName();
        promptPassengerForBirthDate();
    }

    static String[] userName() {
        System.out.print("Enter your first name: ");
        String firstName = mainScanner.nextLine();
        System.out.print("Enter your last name: ");
        String lastName = mainScanner.nextLine();

        if (lastName == "" || firstName == "") {
            System.out.println("Please Enter Name Info Correctly:");
            userName();
        }

        String[] userNameInfoReturner = {firstName, lastName};
        return userNameInfoReturner;
    }

    final static String promptPassengerForBirthDate() {
        System.out.println("Please Enter Your Age in the format: ");
        System.out.println("YYYY-MM-DD, ex: 2000-08-29");
        String userBirthDate = mainScanner.next(); // Read as string

        if (!userBirthDate.matches("\\d{4}-\\d{2}-\\d{2}")) { // Check if it matches the pattern
            System.out.println("Invalid format. Please enter the date in the format yyyy-MM-dd. Try Again: ");
            promptPassengerForBirthDate(); // Restart the method if the format is incorrect
            return null;
        }

        return userBirthDate;
    }

    static void unBookSeat() {
        // To be implemented
        System.out.println("Unbooking logic coming soon.");
    }

    static void busInspector() {
        // To be implemented
    }
}
