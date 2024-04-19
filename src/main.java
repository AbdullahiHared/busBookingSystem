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
        System.out.println(" \\-/_\\----/_\\-/\n");
    }

    static void printWheels(int row) {
        if (row == 1 || row == 3) {
            System.out.print("\n()----------()");
        }
    }

    static void printBusSeats() {
        printBusFrontSection(); // Print the front section of the bus
        for (int i = 0; i < busSeats.length; i++) {
            printWheels(i); // Print the wheels for even-indexed rows
            for (int j = 0; j < busSeats[i].length; j++) {
                if (j == 0) {
                    System.out.print("\n"); // Move to the next line at the start of each row
                }
                System.out.print("|" + busSeats[i][j] + "|"); // Print each seat
                if (j == busSeats[i].length - 1 && i % 2 != 0) {
                    System.out.println("/");
                }
            }
        }
        printBusBack(); // Print the back section of the bus
    }

    static void startCustomerService() {
        switch (getCustomerChoice()) {
            case 1:
                bookSeat();
                break;
            case 2:
                printBusSeats();
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

    static String validateWindowSeatSelection() {
        System.out.println("Would you like to book a window seat: Yes || NO: ");
        String userSelection = mainScanner.next();
        return userSelection;
    }

    static void bookSeat() {
        String userInfo = getUserInfo();
        if (userInfo != null) {
            String[] userInfoParts = userInfo.split(",");
            String fullName = userInfoParts[0];
            String birthDate = userInfoParts[1];
            // Print or use the fullName and birthDate as needed
            System.out.println("Full Name: " + fullName);
            System.out.println("Birth Date: " + birthDate);
        } else {
            System.out.println("Error: Unable to get user information.");
        }
        System.out.println("Your Seat was Successfully booked");
        startCustomerService();
    }

    static String getUserInfo() {
        String fullName = userName();
        mainScanner.nextLine();
        String birthDate = promptPassengerForBirthDate();
        return fullName + "," + birthDate;
    }

    static String userName() {
        System.out.print("Enter your first name: ");
        String firstName = mainScanner.next(); //
        System.out.print("Enter your last name: ");
        String lastName = mainScanner.next();

        mainScanner.nextLine();

        if (lastName.isEmpty() || firstName.isEmpty()) {
            System.out.println("Please enter name information correctly:");
            return userName();
        }
        return firstName + " " + lastName;
    }

    final static String promptPassengerForBirthDate() {
        System.out.println("Please Enter Your Age in the format: ");
        System.out.println("YYYY-MM-DD, ex: 2000-08-29");
        String userBirthDate = mainScanner.next(); // Read as string

        if (!userBirthDate.matches("\\d{4}-\\d{2}-\\d{2}")) { // Check if it matches the pattern
            System.out.println("Invalid format. Please enter the date in the format yyyy-MM-dd. Try Again: ");
            promptPassengerForBirthDate(); // Restart the method if the format is incorrect
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
