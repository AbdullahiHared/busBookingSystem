import java.time.LocalDate;
import java.util.Scanner;
import java.util.InputMismatchException;

public class mainClass {
    static Scanner mainScanner = new Scanner(System.in); // Declaring Scanner globally
    static int profit = 0;
    static String[][] busSeats = {
            {"1", "2", "3", "4"},
            {"5", "6", "7", "8"},
            {"9", "10", "11", "12"},
            {"13", "14", "15", "16"},
            {"17", "18", "19", "20"}};
    static String[][] customers = new String[20][3]; //array to store customers data.

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
        System.out.println("\n|----------------|");
        System.out.println("\\-/_\\--------/_\\-/\n");
    }

    static void printWheels(int row) {
        if (row == 1 || row == 3) {
            System.out.print("\n()--------------()");
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
                startCustomerService();
                break;
            case 3:
                findCustomerData();
                startCustomerService();
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

    static boolean checkSeatBooked(int seat) {
        for (String[] busRow : busSeats) {
            for (String s : busRow) {
                if (s.equals(String.valueOf(seat))) {
                    return false;
                }
            }
        }
        return true;
    }


    static void bookSeat() {
        String[] userInfo = getUserInfo();
        // get the seat number from the user
        int seatChoice = getCustomerSeatChoice();

        //reserve the seat
        reserveSeat(seatChoice);

        // Extract user information
        String fullName = userInfo[0];
        String birthDate = userInfo[1];

        boolean isAdult = isCustomerAdult(Integer.parseInt(birthDate));
        double ticketPrice;

        // Calculate profit based on customer age
        if (isAdult) {
            profit += (int) 299.90;
            ticketPrice = 299.90;
        } else {
            profit += (int) 149.90;
            ticketPrice = 149.90;
        }

        //Save passenger data
        addCustomerData(String.valueOf(seatChoice), birthDate, fullName);

        // Display booking information
        displayBookingInfo(fullName, birthDate, seatChoice, ticketPrice);
        startCustomerService();
    }

    static void displayBookingInfo(String fullName, String birthDate, int seatNumber, double price) {
        System.out.println("Booking Info: ");
        System.out.println("Full Name: " + fullName);
        System.out.println("BirthDate : " + birthDate);
        System.out.println("Seat Number : " + seatNumber);
        System.out.println("Price : " + price + " kr");
        System.out.println("Welcome on board <>");
    }


    static String[] getUserInfo() {
        String fullName = userName();
        int birthDate = promptPassengerForBirthDate();
        return new String[]{fullName, Integer.toString(birthDate)};
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
        int seatChoice = 0;
        switch (windowSelectionAnswer) {
            case 1:
                getUnbookedWindowSeats();
                seatChoice = customerSeatChoice();
                break;
            case 2:
                getUnbookedSeats();
                seatChoice = customerSeatChoice();
                break;
            default:
                System.out.println("Error Try Again");
        }
        return seatChoice;
    }

    static int customerSeatChoice() {
        int seatChoice;
        while (true) {
            System.out.println();
            System.out.print("Which seat would you like to book? ");
            try {
                int seatNumber = mainScanner.nextInt();
                if (!checkSeatBooked(seatNumber)) {
                    seatChoice = seatNumber;
                    break;
                } else {
                    System.out.println("Seat is already booked. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid seat number.");
                mainScanner.next(); // Consume invalid input to avoid infinite loop
            }
        }
        return seatChoice;
    }


    static void reserveSeat(int seat) {
        String seatChoice = Integer.toString(seat);
        boolean seatReserved = false; // Flag to track if seat is reserved

        for (int i = 0; i < busSeats.length; i++) {
            for (int j = 0; j < busSeats[i].length; j++) {
                if (busSeats[i][j].equals(seatChoice)) {
                    busSeats[i][j] = "X";
                    seatReserved = true; // Set flag to true when seat is reserved
                    break; // Exit inner loop since seat is reserved
                }
            }
            if (seatReserved) {
                break; // Exit outer loop since seat is reserved
            }
        }

        if (!seatReserved) {
            System.out.println("Seat could not be reserved");
        }
    }


    static void getUnbookedSeats() {
        StringBuilder availableSeats = new StringBuilder();
        for (String[] busSeat : busSeats) {
            for (String seat : busSeat) {
                if (!seat.equals("X")) {
                    availableSeats.append(" ").append(seat);
                }
            }
        }

        System.out.println("Available Seats: ");
        System.out.println(availableSeats);
    }

    static void getUnbookedWindowSeats() {
        StringBuilder availableWindowSeats = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (!busSeats[i][j].equals("X")) {
                    if (j % 4 == 1 || j % 4 == 0) {
                        availableWindowSeats.append(" ").append(busSeats[i][j]);
                    }
                }
            }
        }

        System.out.println("Available Windows Seats: ");
        System.out.println(availableWindowSeats);
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

    static boolean isCustomerAdult(int birthDate) {
        int birthYear = birthDate / 10000; // Extract the birth year from the full date

        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();

        int age = currentYear - birthYear;

        return age >= 18;
    }

    static int getCustomerAge(int birthDate) {
        int birthYear = birthDate / 10000; // Extract the birth year from the full date

        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        return currentYear - birthYear;
    }

    static void addCustomerData(String seatNumber, String birthDate, String fullName) {
        boolean dataAdded = false;
        try {
            for (int i = 0; i < customers.length && !dataAdded; i++) {
                if (customers[i][0] == null) { // Checking if the field is empty
                    customers[i][0] = seatNumber;
                    customers[i][1] = birthDate;
                    customers[i][2] = fullName;
                    dataAdded = true; // Set it to true once the data is added
                }
            }
        } catch (Exception e) {
            System.out.println("Something went wrong");
        } finally {
            System.out.println("Your ticket information was successfully saved.");
        }
    }


    static void findCustomerData() {
        int birthDate = promptPassengerForBirthDate();
        System.out.println("Please enter the seat you had booked: ");
        int chosenSeat = mainScanner.nextInt();
        for (String[] customer : customers) {
            if (customer[1] != null && customer[1].equals(String.valueOf(birthDate)) && customer[0].equals(String.valueOf(chosenSeat))) {
                System.out.println("Customer: ");
                System.out.println("Seat Number: " + customer[0]);
                System.out.println("Birthdate: " + customer[1]);
                System.out.println("Full Name: " + customer[2]);
                break;
            } else {
                System.out.println("No customer found with that birth date.");
            }
        }
    }

    static void unBookSeat() {
        int birthDate = promptPassengerForBirthDate();
        System.out.print("Please Enter Which seat number you had: ");
        int passengerSeat = mainScanner.nextInt();
        for (String[] customer : customers) {
            if (customer[0] != null && customer[0].equals(String.valueOf(passengerSeat)) && customer[1].equals(String.valueOf(birthDate))) {
                int seatNumber = Integer.parseInt(customer[0]);
                updateSeatReservation(seatNumber);
                System.out.println("Ticket Cancelling Was Successful.");
                boolean isAdult = isCustomerAdult(birthDate); // Corrected this line
                double ticketPrice;

                // Calculate profit based on customer age
                if (isAdult) {
                    profit -= 299;
                    ticketPrice = 299.90;
                } else {
                    profit -= 149;
                    ticketPrice = 149.90;
                }

                // remove customer from customers
                removeCustomerData(seatNumber, birthDate);

                // Output the ticket price
                System.out.println("Refund Amount: kr" + ticketPrice);
                startCustomerService();
            }
        }
    }

    static void updateSeatReservation(int seat) {
        for (String[] seatPosition : busSeats) {
            seatPosition[seat - 1] = String.valueOf(seat); // Corrected this line
        }
    }

    // remove customer data
    static void removeCustomerData(int seatNumber, int birthDate) {
        for (String[] customer : customers) {
            if (customer[0] != null && customer[0].equals(String.valueOf(seatNumber)) && customer[1].equals(String.valueOf(birthDate))) {
                customer[0] = null;
                customer[1] = null;
                customer[2] = null;
            }
        }
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
                sortCustomersByAge();
                busInspector();
                break;
            case 3:
                getCurrentCustomers();
                busInspector();
                break;
            case 4:
                printBusSeats();
                busInspector();
                break;
            default:
                startBusService();
        }
    }

    static void getCurrentCustomers() {
        int currentCount = 0;
        for (String[] customer : customers) {
            if (customer[0] != null && customer[2] != null) {
                currentCount++;
                System.out.println("Customer: " + currentCount + ": ");
                System.out.println("FullName: " + customer[2]);
                System.out.println("Seat Number: " + customer[0]);
                System.out.println("Birthdate: " + customer[1]);
                System.out.println();
            }
        }
        if (currentCount == 0) {
            System.out.println("No customers on board");
        }
    }


    //sort customers by age
    static void sortCustomersByAge() {
        System.out.println("Customers Sorted By Age: ");
        for (int i = 0; i < customers.length; i++) {
            for (int j = i + 1; j < customers.length; j++) {
                if (customers[i][1] != null && customers[j][1] != null) {
                    int age1 = getCustomerAge(Integer.parseInt(customers[i][1]));
                    int age2 = getCustomerAge(Integer.parseInt(customers[j][1]));

                    if (age1 < age2) {
                        String[] temp = customers[i];
                        customers[i] = customers[j];
                        customers[j] = temp;
                    }
                }
            }
        }

        // Print all customers after sorting
        for (String[] customer : customers) {
            if (customer[0] != null && customer[2] != null) {
                System.out.println("Full Name: " + customer[2]);
                System.out.println("Birth Date: " + customer[1]);
                System.out.println("Seat Number: " + customer[0]);
                System.out.println();
            }
        }
    }
}

