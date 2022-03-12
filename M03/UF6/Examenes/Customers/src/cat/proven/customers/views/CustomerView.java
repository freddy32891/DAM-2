package cat.proven.customers.views;

import cat.proven.customers.controllers.CustomerController;
import cat.proven.customers.model.Customer;
import java.util.List;
import java.util.Scanner;

/**
 * Main view for flights application.
 *
 * @author ProvenSoft
 */
public class CustomerView {

    private final CustomerController controller;
    private boolean exit; //flag to exit application.
    private final CustomerMenu mainMenu;

    public CustomerView(CustomerController controller) {
        this.controller = controller;
        this.mainMenu = new CustomerMenu();
    }

    /**
     * makes the view visible and starts interacting with user.
     */
    public void show() {
        exit = false;
        // control loop for user interaction.
        do {
            mainMenu.show();
            String action = mainMenu.getSelectedOptionActionCommand();
            if (action != null) {
                controller.processAction(action);
            }
        } while (!exit);
    }

    /**
     * displays a message to user.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * displays a message and gets user's answer.
     *
     * @param message the message to display.
     * @return the user's answer or null in case of error.
     */
    public String inputString(String message) {
        System.out.print(message);
        Scanner scan = new Scanner(System.in);
        return scan.next();
    }

    /**
     * activates view closing.
     */
    public void close() {
        this.exit = true;
    }

    public void showCustomerList(List<Customer> result) {
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i).toString());
        }
    }

    /**
     * Method that prints a message and asks the user a confirmation
     *
     * @param message message to show
     * @return true if the user introduces an Y as an answer, false otherwise
     */
    public boolean confirmation(String message) {
        String confirmation = inputString(message);
        confirmation = confirmation.toUpperCase();
        if (confirmation.contains("Y")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method that ask the user all the parameters of a customer
     *
     * @return the passenger selected by user
     */
    public Customer inputCustomer() {
        Customer result = null;
        try {
            String name = inputString("Input name: ");
            String phone = inputString("Input phone: ");
            String sYearDischarge = inputString("Input the year of discharge: ");

            int yearDischarge = Integer.parseInt(sYearDischarge);
            
            result= new Customer(0,name, phone, yearDischarge);
        } catch (NumberFormatException ex) {
            showMessage("The year introduced is not a number");
            result=null;
        }

        return result;
    }

}
