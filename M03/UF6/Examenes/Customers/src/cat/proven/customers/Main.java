package cat.proven.customers;

import cat.proven.customers.controllers.CustomerController;
import cat.proven.customers.model.Model;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main class for Customers application.
 * @author ProvenSoft
 */
public class Main {

    public static void main(String[] args) {

        try {
            Model model = new Model();
            CustomerController controller = new CustomerController(model);
            controller.start();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
