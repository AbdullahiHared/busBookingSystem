import java.time.LocalDate;
import java.util.Scanner;
import java.util.InputMismatchException;


/**
 * This is a simple bus booking system that allows passengers to book seats on a bus.
 * The system also allows the bus inspector to view the current profit, sort customers by age,
 * and view the current passengers on board.
 */
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

    /**
     * This method starts the bus service and prompts the user to choose a role.
     * The user can choose to be a bus inspector or a passenger.
     */
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

    /**
     * This method prompts the user to choose a role.
     *
     * @return the user's choice
     */
    static int promptUserForRoleChoice() {
        int choice;
        while (true) {
            printRolesChoices();
            try {
                choice = mainScanner.nextInt();
                if (choice == 1 || choice == 2 || choice == 3) {
                    return choice;
                } else {
                    System.out.println("Please enter a valid option from (1, 2, or 3).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                mainScanner.next(); // Consume the invalid token
            }
        }
    }

    /**
     * This method prints the choices for the user to choose a role.
     */
    static void printRolesChoices() {
        System.out.println("Choose from below:");
        System.out.println("1. Bus  Inspector:");
        System.out.println("2. Passenger");
        System.out.println("3. Exit");
        System.out.print(": ");
    }

    /**
     * This method prints the front section of the bus.
     */

    static void printBusFrontSection() {
        System.out.println("   /\\    /\\");
        System.out.println("__>-<____>-<__");
    }

    /**
     * This method prints the back section of the bus.
     */

    public static void printBusBack() {
        System.out.println("\n|----------------|");
        System.out.println("\\-/_\\--------/_\\-/\n");
    }

    /**
     * This method prints the wheels of the bus.
     *
     * @param row the row number
     */
    static void printWheels(int row) {
        if (row == 1 || row == 3) {
            System.out.print("\n()--------------()");
        }
    }

    /**
     * This method prints the bus seats.
     */
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


    /**
     * This method starts the customer service and prompts the user to choose an action.
     */
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

    /**
     * This method gets the customer's choice.
     *
     * @return the customer's choice
     */
    static int getCustomerChoice() {
        printCustomerChoices();
        int customerChoice;
        while (true) {
            try {
                customerChoice = mainScanner.nextInt();
                if (customerChoice >= 0 && customerChoice <= 4) {
                    return customerChoice;
                } else {
                    System.out.println("Please enter a valid option from (0, 1, 2, or 3).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                mainScanner.next(); // Consume the invalid token
            }
        }
    }

    static void printCustomerChoices() {
        System.out.println("0. Exit");
        System.out.println("1. Book seat");
        System.out.println("2. Show bus");
        System.out.println("3. Find your booking");
        System.out.println("4. Cancel booking");
        System.out.print("> ");
    }

    /**
     * This method prompts the user to choose a seat.
     *
     * @return the seat number
     */
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

    /**
     * Books a seat for a customer, collects user information, reserves the seat,
     * calculates ticket price based on customer age, saves passenger data,
     * displays booking information, and starts customer service.
     * <p>
     * This method performs the following steps:
     * 1. Collects user information using {@link #getUserInfo()}.
     * 2. Prompts the user to choose a seat using {@link #getCustomerSeatChoice()}.
     * 3. Reserves the selected seat using {@link #reserveSeat(int)}.
     * 4. Calculates the ticket price based on the customer's age.
     * 5. Saves the passenger's data using {@link #addCustomerData(String, String, String)}.
     * 6. Displays booking information using {@link #displayBookingInfo(String, String, int, double)}.
     * 7. Starts the customer service again by calling {@link #startCustomerService()}.
     */
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


    /**
     * This method displays the booking information.
     *
     * @param fullName   the full name of the passenger
     * @param birthDate  the birthdate of the passenger
     * @param seatNumber the seat number
     * @param price      the ticket price
     */
    static void displayBookingInfo(String fullName, String birthDate, int seatNumber, double price) {
        System.out.println("Booking Info: ");
        System.out.println("Full Name: " + fullName);
        System.out.println("BirthDate : " + birthDate);
        System.out.println("Seat Number : " + seatNumber);
        System.out.println("Price : " + price + " kr");
        System.out.println("Welcome on board <>");
    }

    /**
     * This method gets the user's information.
     *
     * @return the user's information ie full name and birthdate
     */
    static String[] getUserInfo() {
        String fullName = userName();
        int birthDate = promptPassengerForBirthDate();
        return new String[]{fullName, Integer.toString(birthDate)};
    }

    /**
     * This method gets the user's name.
     *
     * @return the user's name ie first name and last name
     */
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

    /**
     * This method checks if the date format is valid.
     *
     * @param date the date to check
     * @return true if the date format is valid, otherwise false
     */
    static boolean isValidDateFormat(String date) {
        // Regular expression to match the format yyyyMMdd
        String regex = "\\d{8}"; // This matches exactly 8 digits
        return date.matches(regex);
    }

    /**
     * This method prompts the passenger for their birthdate.
     *
     * @return the passenger's birthdate
     */
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

    /**
     * This method gets the customer's seat choice.
     *
     * @return the customer's seat choice
     */
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

    /**
     * This method gets the customer's seat choice.
     *
     * @return the customer's seat choice
     */
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

    /**
     * This method reserves a seat for a customer.
     *
     * @param seat the seat number
     */
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

    /**
     * This method gets the unbooked seats.
     */
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

    /**
     * This method gets the unbooked window seats.
     */
    static void getUnbookedWindowSeats() {
        StringBuilder availableWindowSeats = new StringBuilder();
        for (int i = 0; i < busSeats.length; i++) {
            for (int j = 0; j < busSeats[i].length; j++) {
                if (!busSeats[i][j].equals("X")) {
                    if (j == 0 || j == busSeats[i].length - 1) { // Check if it's the first or last seat in the row
                        availableWindowSeats.append(" ").append(busSeats[i][j]);
                    }
                }
            }
        }

        System.out.println("Available Windows Seats: ");
        System.out.println(availableWindowSeats);
    }

    /**
     * This method gets the user's window selection.
     *
     * @return the user's window selection
     */
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

    /**
     * This method checks if the customer is an adult.
     *
     * @param birthDate the birthdate of the customer
     * @return true if the customer is an adult, otherwise false
     */
    static boolean isCustomerAdult(int birthDate) {
        int birthYear = birthDate / 10000; // Extract the birth year from the full date

        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();

        int age = currentYear - birthYear;

        return age >= 18;
    }

    /**
     * This method gets the customer's age.
     *
     * @param birthDate the birthdate of the customer
     * @return the customer's age
     */
    static int getCustomerAge(int birthDate) {
        int birthYear = birthDate / 10000; // Extract the birth year from the full date

        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        return currentYear - birthYear;
    }

    /**
     * This method adds the customer data to the customers array.
     *
     * @param seatNumber the seat number
     * @param birthDate  the birthdate
     * @param fullName   the full name
     */
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

    /**
     * This method finds the customer data based on the birth ate and seat number.
     */
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

    /**
     * This method unbooks a seat for a customer.
     */
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

    /**
     * This method updates the seat reservation.
     *
     * @param seat the seat number
     */
    static void updateSeatReservation(int seat) {
        for (String[] seatPosition : busSeats) {
            seatPosition[seat - 1] = String.valueOf(seat); // Corrected this line
        }
    }

    /**
     * This method removes the customer data from the customers array.
     *
     * @param seatNumber the seat number
     * @param birthDate  the birthdate
     */
    static void removeCustomerData(int seatNumber, int birthDate) {
        for (String[] customer : customers) {
            if (customer[0] != null && customer[0].equals(String.valueOf(seatNumber)) && customer[1].equals(String.valueOf(birthDate))) {
                customer[0] = null;
                customer[1] = null;
                customer[2] = null;
            }
        }
    }

    /**
     * This method allows the bus inspector to view the current profit, sort customers by age,
     * and view the current passengers on board.
     */
    static void busInspector() {
        getInspectorChoice();
    }

    /**
     * This method prints the choices for the bus inspector.
     */
    static void printInspectorChoices() {
        System.out.println("0. Exit");
        System.out.println("1. Display current profit:");
        System.out.println("2. Sort Customers by age:");
        System.out.println("3. Shows customers:");
        System.out.println("4. Show Bus");
        System.out.print("> ");
    }

    /**
     * This method gets the bus inspector's choice.
     */
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

    /**
     * This method prints the current passengers on board.
     */
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


    /**
     * This method sorts the customers by age.
     */
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

