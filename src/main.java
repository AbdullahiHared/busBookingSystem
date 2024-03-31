import java.util.Scanner;
import java.time.Year;

public class main {
    static Scanner mainScanner = new Scanner(System.in); // Declaring Scanner globally

    static String[][] seats = {
            {"0", "0", "0", "0"},
            {"0", "0", "0", "0"},
            {"0", "0", "0", "0"},
            {"0", "0", "0", "0"},
            {"0", "0", "0", "0"}
    };

    /**
     * Print the menu of role options for user selection.
     *  @return `1` for Bus Inspector, `2` for Customer, '0' to Exit
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

    static void startBusServie() {
          showBus();
          switch (promptUserForRoleChoice()) {
              case 1 : bussInspector();
              break;
              case 2 : customerChoiceCenter();
              break;
              default:
                  System.out.println("Thanks for using our service ");
          }
    }

    static void showBus() {

    }

    static void bussInspector() {

    }

    static void customerChoiceCenter() {

    }

    public static void main(String[] args) {
       startBusServie();
    }
}
