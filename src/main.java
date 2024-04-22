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
    static String[][] customers = new String[20][3]; //array to store customers data.

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
        System.out.println();
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

        System.out.println();
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
                unBookSeat();
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

        if (customerChoice >= 0 && customerChoice <= 4) {
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
        customerSeatChoice();
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

    static int customerSeatChoice() {
        try {
            System.out.println("Which row would you like to book a seat in (0-3): ");
            int row = mainScanner.nextInt();
            System.out.println("Which seat would you like to book from this row (0-3): ");
            informAboutWindowSeats();
            int seat = mainScanner.nextInt();

            if (row >= 0 && row < busSeats.length && seat >= 0 && seat < busSeats[row].length) {
                if (!checkSeatBooked(row, seat)) {
                    System.out.println("Seat is already booked. Please select an unreserved seat.");
                    customerSeatChoice();
                } else {
                    System.out.println("Seat was Successfully Booked");
                    busSeats[row][seat] = "X"; // Mark seat as booked
                    return row;
                }
            } else {
                System.out.println("Invalid seat selection. Please select a valid seat.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please select an unreserved seat.");
            e.printStackTrace(); // Print the stack trace for debugging
            mainScanner.nextLine(); // Consume invalid input
        }
        return -1; // Default value in case of exceptions or invalid input
    }

    static void informAboutWindowSeats() {
        // Inform about window seats (Seats Numbers 0 and 3 are windows)
        System.out.println("Seats Numbers 0 and 3 are windows");

    }

    static boolean checkSeatBooked(int row, int seat) {
        return !busSeats[row][seat].equals("X");
    }

    static int getCustomerBirthYear(String birthDate) {
        int currentYear = Year.now().getValue();
        int customerBirthYear = Integer.parseInt(birthDate.substring(0, 4));
        if (currentYear >= customerBirthYear) {
            return customerBirthYear;
        } else {
            return -1;
        }
    }

    static void unBookSeat() {
        String birthDate = promptPassengerForBirthDate();
        if (birthDate.length() == 10) {
            boolean bookingCancelled = false;

            for (int i = 0; i < busSeats.length; i++) {
                for (int j = 0; j < busSeats.length; j++) {

                }
            }
        }
    }


    static void UnreserveSeat(int row, int seat) {
        busSeats[row][seat].equals("0");
    }

    static void busInspector() {
        getInspectorChoice();
    }

    static void printInspectorChoices() {
        System.out.println("0. Exit");
        System.out.println("1. Display current profit:");
        System.out.println("2. Sort Customers by age:");
        System.out.println("3. Shows customers:");
        System.out.println("4. Show Bus");
        System.out.print("> ");
    }
    static int getInspectorChoice() {
        printInspectorChoices();
        int inspectorChoice = mainScanner.nextInt();
        switch (inspectorChoice) {
            case 1:
                System.out.println("current profit is : " + 299);
                break;
            case 2:
                System.out.println("Sorted customers will be here: ");
                break;
            case 3:
                System.out.println("Current total customers are: " + customers.length);
                break;
            case 4:
                printBusSeats();
                busInspector(); // resume Inspector service;

        }
        return inspectorChoice;
    }

}

