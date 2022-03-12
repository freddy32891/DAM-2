package cat.proven.customers.controllers;

import cat.proven.customers.model.Customer;
import cat.proven.customers.model.Model;
import cat.proven.customers.views.CustomerView;
import java.util.List;

/**
 * Main controller for customers application.
 *
 * @author ProvenSoft
 */
public class CustomerController {

    private final Model model;
    private final CustomerView view;

    public CustomerController(Model model) {
        this.model = model;
        this.view = new CustomerView(this);
    }

    public Model getModel() {
        return model;
    }

    /**
     * starts running controller.
     */
    public void start() {
        view.show();
    }

    /**
     * processes actions received from view.
     *
     * @param action the action to process.
     */
    public void processAction(String action) {
        if (action != null) {
            switch (action) {
                case "exit": //exit application.
                    exitApplication();
                    break;
                case "listAllCustomers"://list all customers
                    doListAllCustomers();
                    break;
                case "listCustomersByName": //lists all customers by name
                    doListCustomersByName();
                    break;
                case "addCustomer": //add a customer
                    addCustomer();
                    break;
                case "removeCustomer": //removes a customer
                    removeCustomer();
                    break;
                case "modifyCustomer": //modifies a customer
                    modifyCustomer();
                    break;
                default:
                    view.showMessage("Uknown option");
                    break;
            }
        }
    }

    /**
     * MEthod that lists all the costumers in the database
     */
    private void doListAllCustomers() {
        List<Customer> result = model.searchAllCustomers();
        if (result != null) {
            view.showCustomerList(result);
        } else {
            view.showMessage("Error retrieving data");
        }
    }

    /**
     * Method that lists all costumers searched bya a given name, shows error messages in case of error
     */
    private void doListCustomersByName() {
        String name = view.inputString("Input the name of the customer you want to search: ");
        if (name != null) {
            List<Customer> customers = model.seachCustomersByName(name);
            if (customers != null) {
                view.showCustomerList(customers);
            } else {
                view.showMessage("Error retrieving data");
            }
        } else {
            view.showMessage("The name given is not allowed");
        }
    }

    /**
     * MEthod that removes a customer by asking the phone to the user, sends error messages in case of error
     */
    private void removeCustomer() {

        String phone = view.inputString("Input phone: ");
        if (phone != null) {
            long id = model.searchCustomerId(phone);
            Customer result = model.searchCustomerById(id);
            if (result != null) {
                System.out.println("Customer founded");
                view.showMessage(result.toString());
                boolean answer = view.confirmation("Is this the Customer you want to delete? (Y/N)");
                if (answer) {
                    int remove = model.removeCustomer(result);
                    if (remove == 1) {
                        view.showMessage("Customer succesfully removed");
                    } else {
                        if (remove == 0) {
                            view.showMessage("There is a problem when executing the sql query");
                        } else if (remove == -1) {
                            view.showMessage("There is a problem in the consult");
                        } else if (remove == -2) {
                            view.showMessage("There is an error in the connection with the database");
                        } else if (remove == -3) {
                            view.showMessage("The customer given is not allowed");
                        }
                        view.showMessage("Customer not removed");
                    }

                } else {
                    view.showMessage("The passenger was not removed");
                }
            } else {
                view.showMessage("Passenger not founded");
            }
        } else {
            view.showMessage("The phone number given is not allowed");
        }
    }

    /**
     * MEthod that modifies a customer by asking the user to input the customer
     */
    private void modifyCustomer() {
        String phone = view.inputString("Input phone number: ");
        if (phone != null) {
            long id = model.searchCustomerId(phone);
            Customer result = model.searchCustomerById(id);
            if (result != null) {
                System.out.println("Customer founded");
                view.showMessage(result.toString());
                boolean answer = view.confirmation("Is this the Customer you want to update? (Y/N)");
                if (answer) {
                    Customer newCustomer = view.inputCustomer();
                    if (newCustomer != null) {
                        newCustomer.setId(result.getId()); //giving to the new customer the value of the lastId
                        //     * Modify passenger
                        int update = model.updateCustomer(newCustomer);
                        if (update == 1) {
                            view.showMessage("Customer succesfully modified");
                        } else {
                            if (update == 0) {
                                view.showMessage("There is a problem when executing the query");
                            } else if (update == -1) {
                                view.showMessage("There is a problem in the consult");
                            } else if (update == -2) {
                                view.showMessage("There is an error in the connection with the database");
                            } else if (update == -3) {
                                view.showMessage("There is a problem in the customers modification ");
                            } else if (update == -11) {
                                view.showMessage("The phone number given, already exists");
                            }
                            view.showMessage("Customer not modified");
                        }
                    } else {
                        view.showMessage("There is a problem in the customers modification");
                    }
                } else {
                    view.showMessage("The customer was not modified");
                }
            } else {
                view.showMessage("Customer not founded");
            }
        } else {
            view.showMessage("The phone number given is not allowed");
        }
    }

    /**
     * Method that adds a customer in to the database by asking the phone
     */
    private void addCustomer() {
        Customer customer = view.inputCustomer();
        if (customer != null) {
            int result = model.addCustomer(customer);
            if (result == 1) {
                view.showMessage("Customer succesfully added");
            } else if (result == 0) {
                view.showMessage("Error at executing query");
            } else if (result == -1) {
                view.showMessage("There is a problem in the consult");
            } else if (result == -11) {
                view.showMessage("The phone number given already exists");
            } else if (result == -2) {
                view.showMessage("The given customer is not allowed");
            }
        } else {
            view.showMessage("The given flight is not allowed");
        }
    }

    /**
     * Exits application
     */
    private void exitApplication() {
        boolean answer = view.confirmation("Exit. Are you sure (Y/N): ");
        if (answer) {
            view.close();
        }
    }

}
