package cat.proven.customers.views;

import cat.proven.utils.menu.Menu;
import cat.proven.utils.menu.Option;

/**
 * Main menu for flights application.
 * @author ProvenSoft
 */
public class CustomerMenu extends Menu {

    public CustomerMenu() {
        super("Customer application menu");
        //
        addOption( new Option("Exit application", "exit") );
        //
        addOption( new Option("List all customers", "listAllCustomers") );
        addOption( new Option("List customers by name", "listCustomersByName") );
        addOption( new Option("Add a new customer", "addCustomer") );
        addOption( new Option("Modify a customer", "modifyCustomer") );
        addOption( new Option("Remove a customer", "removeCustomer") );
    }
    
}
