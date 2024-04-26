import java.util.Scanner;
import java.time.Year;

public class mainClass {
    static Scanner mainScanner = new Scanner(System.in); // Declaring Scanner globally
    static int profit = 0;
    static String[][] busSeats = {
            {"1", "2", "3", "4"},
            {"5", "6", "7", "8"},
            {"9", "10", "11", "12"},
            {"13", "14", "15", "16"},
            {"17", "18", "19", "20"}};
    static String[][] customers = new String[20][4]; //array to store customers data.

    public static void main(String[] args) {
        startBusService();
    }

    static void startBusService() {
        int choice = promptUserForRoleChoice(); // Get the user's choice
        switch (choice) {
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
                System.out.println("Please enter a valid option from (1, 2, or 3).");
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

    static void printBusFrontSection() {
        System.out.println("   /\\    /\\");
        System.out.println("__>-<____>-<__");
    }

    public static void printBusBack() {
        System.out.println("\n|------------|");
        System.out.println("\\-/_\\----/_\\-/\n");
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
                if (j % 2 == 0 && j != 0) {
                    System.out.print("  "); // Add a space every third seat
                }
                System.out.print("|" + busSeats[i][j] + "|"); // Print each seat
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
                startBusService();
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

    static void bookSeat() {
        String userInfo = getUserInfo();
        int seatInfo = getCustomerSeatChoice(); // Get the seat info of the booked seat
        if (seatInfo[1] == -1 || seatInfo[0] != -1) { // Check if a valid seat was booked and user info is available
            String[] userInfoParts = userInfo.split(",");
            String seatNumber = String.valueOf(seatInfo[1]);
            String rowNumber = String.valueOf(seatInfo[0]);
            String fullName = userInfoParts[0];
            String birthDate = userInfoParts[1];
            addCustomerData(seatNumber, rowNumber, birthDate, fullName); // Add customer data
            int customerAge = getCustomerAge(birthDate);
            switch (customerAge) {
                case 1:
                    profit += 299;
                    break;
                case 2:
                    profit += 149;
            }
            informAboutTicketBooking(fullName, Integer.parseInt(birthDate), seatNumber);
            startCustomerService();
        } else {
            System.out.println("Error: Unable to book the seat or get user information.");
        }
    }

    static void informAboutTicketBooking(String fullName, int birthDate, String seatNumber) {
        System.out.println("Booking Info: ");
        System.out.println("Full Name: " + fullName);
        System.out.println("BirthDate : " + birthDate);
        System.out.println("Seat Number : " + seatNumber);
        System.out.println("Welcome on board <>");
    }

    static String getUserInfo() {
        String fullName = userName();
        int birthDate = promptPassengerForBirthDate();
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

    static boolean isValidDateFormat(String date) {
        // Regular expression to match the format yyyyMMdd
        String regex = "\\d{8}"; // This matches exactly 8 digits
        return date.matches(regex);
    }

    static int promptPassengerForBirthDate() {
        System.out.println("Please enter your birth date in the format yyyyMMdd:");
        String userInput = mainScanner.next();
        if (isValidDateFormat(userInput)) {
            return Integer.parseInt(userInput);
        } else {
            System.out.println("Invalid format. Please enter the date in the format yyyyMMdd. Try Again:");
            return promptPassengerForBirthDate(); // Recursive call to re-prompt the user
        }
    }

    static int getCustomerSeatChoice() {
        int windowSelectionAnswer = getUserWindowSelection();
        switch (windowSelectionAnswer) {
            case 1:
                getUserWindowSelection();
                break;
            case 2:
                customerSeatChoice();
                break;
            default:
                System.out.println("Error Try Again");
        }
        return 1;
    }

    static void customerSeatChoice() {
        System.out.println();
    }

    static void getUnbookedSeats() {
        String unBookedSeats = "";
        for (int i = 0; i < busSeats.length; i++) {
            for (int j = 0; j < busSeats[i].length; j++) {
                if (busSeats[j].equals("O")) {
                    unBookedSeats += busSeats[]
                }
            }
        }
    }

    static int getUserWindowSelection() {
        int selection = 0; // Default value
        boolean validInput = false;

        while (!validInput) {
            // Prompt the user to select a window seat
            System.out.println("Would you like to book a window seat? (yes/no)");
            String answer = mainScanner.next();

            // Validate user input and set the selection accordingly
            if (answer.equalsIgnoreCase("yes")) {
                selection = 1; // Yes
                validInput = true;
            } else if (answer.equalsIgnoreCase("no")) {
                selection = 2; // No
                validInput = true;
            } else {
                System.out.println("Please enter a correct answer: 'yes' or 'no'");
            }
        }

        return selection;
    }

    static int getCustomerAge(String birthDate) {
        int currentYear = Year.now().getValue();
        int customerBirthYear = Integer.parseInt(birthDate.substring(0, 4));
        if (customerBirthYear - currentYear >= 18) {
            return 1;
        } else {
            return 2;
        }
    }

    static void addCustomerData(String seatNumber, String rowIndex, String birthDate, String fullName) {
        boolean dataAdded = false;
        try {
            for (int i = 0; i < customers.length && !dataAdded; i++) {

                if (customers[i][0] == null || customers[i][0].isEmpty()) { // Checking if the seatNumber field is empty
                    customers[i][0] = seatNumber;
                    customers[i][1] = rowIndex;
                    customers[i][2] = birthDate;
                    customers[i][3] = fullName;
                    dataAdded = true; // Set it to true once the data is added
                }
            }
        } catch (Exception e) {
            System.out.println("Something went wrong");
        } finally {
            System.out.println("Your ticket information was successfully saved.");
        }
    }


    static void unBookSeat() {
        int birthDate = promptPassengerForBirthDate();
        System.out.print("Please Enter Which seat number you had: ");
        int passengerSeat = mainScanner.nextInt();
        for (String[] customer : customers) {
            if (customer[0].equals(String.valueOf(passengerSeat)) && customer[2].equals(birthDate)) {
                int rowNumber = Integer.parseInt(customer[0]);
                int seatNumber = Integer.parseInt(customer[0]);
                updateSeatReservation(rowNumber, seatNumber);
                System.out.println("Ticket Cancelling Was Successfully: ");
                int customerAge = getCustomerAge(Integer.toString(birthDate));
                switch (customerAge) {
                    case 1:
                        profit -= 299;
                        break;
                    case 2:
                        profit -= 149;
                }
            }
        }
    }

    static void updateSeatReservation(int row, int seat) {
        busSeats[row][seat] = ("0");
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

    static void getInspectorChoice() {
        printInspectorChoices();
        int inspectorChoice = mainScanner.nextInt();
        switch (inspectorChoice) {
            case 1:
                System.out.println("Current profit is " + profit);
                busInspector();
                break;
            case 2:
                System.out.println("Sorted customers will be here: ");
                busInspector();
                break;
            case 3:
                getCurrentCustomers();
                break;
            case 4:
                printBusSeats();
                break;
            default:
                startBusService();
        }
    }

    static void getCurrentCustomers() {
        int currentCount = 0;
        for (String[] customer : customers) {
            if (customer[0] != null) { // Check if there is customer data
                currentCount++;
                System.out.println("Customer: " + currentCount + ": ");
                System.out.println("FullName: " + customer[3]);
                System.out.println("Seat Number: " + customer[0]);
                System.out.println("Birthdate: " + customer[2]);
                System.out.println();
            }
        }

    }

}

