package cat.proven.customers.model;

import cat.proven.customers.model.persist.CustomerDao;
import java.util.List;

/**
 * Model service for customers application.
 *
 * @author ProvenSoft
 */
public class Model {

    private CustomerDao customerDao;

    public Model() throws ClassNotFoundException {
        this.customerDao = new CustomerDao();
    }

    //TODO add data service methods.
    public List<Customer> searchAllCustomers() {
        return customerDao.selectAll();
    }

    /**
     * Method that calls the dao to search all costumers by a given name
     * @param name given
     * @return a list of costumers or null in case of error
     */
    public List<Customer> seachCustomersByName(String name) {
        return customerDao.selectWhereName(name);
    }

    /**
     * Method that calls the dao to search a costumer id by a give phone
     * @param phone given
     * @return the id of the customer or -1 in case of error
     */
    public long searchCustomerId(String phone) {
        return customerDao.selectCustomerId(phone);
    }

    /**
     * Method that calls the dao to search a dostumer by a given id
     * @param id given
     * @return the costumer searched or null in case of error
     */
    public Customer searchCustomerById(long id) {
        return customerDao.selectWhereId(id);
    }

    /**
     * Method that calls the dao to remove a customer from the database
     * @param customer the customer given
     * @return 1 if succesfully removed, in case of error returns a negative number or 0
     */
    public int removeCustomer(Customer customer) {
       return customerDao.remove(customer);
    }

/**
     * Method that calls the dao to update a customer from the database
     * @param customer the customer given
     * @return 1 if succesfully updated, in case of error returns a negative number or 0
     */
    public int updateCustomer(Customer customer) {
        return customerDao.update(customer);
    }

/**
     * Method that calls the dao to add a customer from the database
     * @param customer the customer given
     * @return 1 if succesfully added, in case of error returns a negative number or 0
     */
    public int addCustomer(Customer customer) {
        return customerDao.insert(customer);
    }

}
