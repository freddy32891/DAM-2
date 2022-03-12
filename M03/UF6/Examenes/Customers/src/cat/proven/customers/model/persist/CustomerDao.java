package cat.proven.customers.model.persist;

import cat.proven.customers.model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DAO for Customer entity.
 *
 * @author ProvenSoft
 */
public class CustomerDao {

    protected Map<String, String> queries;
    protected DbConnect dbConnect;
    private String TABLE_NAME;

    public CustomerDao() throws ClassNotFoundException {
        queries = new HashMap<>();
        dbConnect = DbConnect.getInstance();
        TABLE_NAME = "customers";
        initQueries();
    }

    /**
     * converts resultset entry to entity object.
     *
     * @param rs resultset to get data from.
     * @return object with data in current position of resultset.
     */
    private Customer fromResultSet(ResultSet rs) throws SQLException {
        Customer p = null;
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String telephone = rs.getString("phone");
        int yearDischarge = rs.getInt("year_discharge");
        p = new Customer(id, name, telephone, yearDischarge);
        return p;
    }

    /**
     * Method that selects a customer by a given id
     *
     * @param id given
     * @return customer selected or null in case of error
     */
    public Customer selectWhereId(long id) {
        Customer result = null;
        try {
            Connection conn = dbConnect.getConnection();
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement(queries.get("sId"));
                st.setLong(1, id);
                ResultSet rs = st.executeQuery();
                boolean success = rs.next();
                if (!success) {
                    result = null;
                }
                result = fromResultSet(rs);
            } else {
                result = null;
            }

        } catch (SQLException ex) {
            result = null;
        }
        return result;
    }

    /**
     * Selects all entities from database
     *
     * @return list of entities or null in case of errror
     */
    public List<Customer> selectAll() {
        List<Customer> result = new ArrayList<>();
        try {
            Connection connection = dbConnect.getConnection();
            if (connection != null) {
                String query = queries.get("sAll");
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    Customer obj = fromResultSet(rs);
                    if (obj != null) {
                        result.add(obj);
                    }
                }
            } else {
                result = null;
            }
        } catch (SQLException ex) {
            result = null;
        }
        return result;
    }

    /**
     * Method that selects all the costumers by the given name
     *
     * @param name given
     * @return an arrayLIst with all the costumers with the same name given or
     * null in case of error
     */
    public List<Customer> selectWhereName(String name) {
        List<Customer> result = new ArrayList<>();
        try {
            Connection conn = dbConnect.getConnection();
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement(queries.get("sName"));
                st.setString(1, name);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Customer obj = fromResultSet(rs);
                    if (obj != null) {
                        result.add(obj);
                    }
                }
            } else {
                result = null;
            }

        } catch (SQLException ex) {
            result = null;
        }
        return result;
    }

    /**
     * Method that searchs the id of a customer by the given phone
     *
     * @param phone given
     * @return the id or -1 in case of error
     */
    public long selectCustomerId(String phone) {
        long id = -1;
        Customer result = null;
        try {
            Connection conn = dbConnect.getConnection();
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement(queries.get("sPhone"));
                st.setString(1, phone);
                ResultSet rs = st.executeQuery();
                boolean success = rs.next();
                if (!success) {
                    id = -1;
                }
                result = fromResultSet(rs);
                id = result.getId();
            } else {
                id = -1;
            }

        } catch (SQLException ex) {
            id = -1;
        }
        return id;
    }

    /**
     * Method that updates parameters of a customer in the database
     *
     * @param customer with the modifications and also with the id of the
     * oldCustomer
     * @return 1 if succesfully updated, a negative number or 0 in case of error
     */
    public int update(Customer customer) {
        int result = 0;
        if (customer != null) {
            try {
                Connection connection = dbConnect.getConnection();
                if (connection != null) {
                    PreparedStatement st = connection.prepareStatement(queries.get("update"));
                    st.setString(1, customer.getName());
                    st.setString(2, customer.getPhone());
                    st.setInt(3, customer.getYearDischarge());
                    st.setLong(4, customer.getId());
                    result = st.executeUpdate();
                } else {
                    result = -2;
                }
            } catch (SQLIntegrityConstraintViolationException ex) {
                result = -11;
            } catch (SQLException ex) {
                result = -1;
            } catch (RuntimeException e) {
                result = -4;
            }
        } else {
            result = -3;
        }
        return result;
    }

    /**
     * Method that deletes a customer in to the database
     *
     * @param customer to remove
     * @return 1 if succesfully removed, negative numbers or 0 in case of error
     */
    public int remove(Customer customer) {
        int result = 0;
        if (customer != null) {
            try {
                Connection conn = dbConnect.getConnection();
                if (conn != null) {
                    PreparedStatement st = conn.prepareStatement(queries.get("delete"));
                    st.setString(1, customer.getPhone());
                    result = st.executeUpdate();
                } else {
                    result = -2;
                }
            } catch (SQLException ex) {
                result = -1;
            }
        } else {
            result = -3;
        }
        return result;
    }

    /**
     * Method that inserts a customer in to the database
     *
     * @param customer to add
     * @return 1 if succesfully added, negative numbers or 0 in case of error
     */
    public int insert(Customer customer) {
        int result = 0;
        if (customer != null) {
            try {
                Connection conn = dbConnect.getConnection();
                if (conn != null) {
                    PreparedStatement st = conn.prepareStatement(queries.get("insert"));
                    st.setString(1, customer.getName());
                    st.setString(2, customer.getPhone());
                    st.setInt(3, customer.getYearDischarge());
                    result = st.executeUpdate();
                }
            } catch (SQLIntegrityConstraintViolationException ex) {
                result = -11;
            } catch (SQLException ex) {
                result = -1;
            }
        } else {
            result = -2;
        }
        return result;
    }

    /**
     * Method that inits all the queries that we are going to use in a hash map
     */
    private void initQueries() {
        queries.put("sAll", String.format("select * from %s", TABLE_NAME));
        queries.put("insert", String.format("INSERT INTO %s VALUES (0,?,?,?)", TABLE_NAME));
        queries.put("delete", String.format("DELETE FROM %s WHERE PHONE = ?", TABLE_NAME));
        queries.put("update", String.format("UPDATE %s SET NAME = ?, PHONE = ?, YEAR_DISCHARGE = ? WHERE ID = ?", TABLE_NAME));
        queries.put("sName", String.format("select * from %s where name = ?", TABLE_NAME));
        queries.put("sId", String.format("SELECT * FROM %s where id = ?", TABLE_NAME));
        queries.put("sPhone", String.format("select * from %s where phone = ?", TABLE_NAME));

    }

}
