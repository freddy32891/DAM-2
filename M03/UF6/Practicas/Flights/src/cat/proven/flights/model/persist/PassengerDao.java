/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.flights.model.persist;

import cat.proven.flights.model.Passenger;
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
 *
 * @author fredd
 */
public class PassengerDao {

    protected Map<String, String> queries;
    protected DbConnect dbConnect;
    private String TABLE_NAME;

    public PassengerDao() {
        queries = new HashMap<>();
        dbConnect = new DbConnect();
        TABLE_NAME = "passengers";
        initQueries();
    }

    /**
     * Selects all entities from database
     *
     * @return list of entities or null in case of errror
     */
    public List<Passenger> selectAll() {
        List<Passenger> result = new ArrayList<>();
        try {
            Connection connection = dbConnect.getConnection();
            if (connection != null) {
                String query = queries.get("sAll");
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    Passenger obj = fromResultSet(rs);
                    if (obj != null) {
                        result.add(obj);
                    }
                }
            } else {
                result = null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            result = null;
        }
        return result;
    }

    /**
     * Method that inserts a passenger in to the database
     *
     * @param passenger to add
     * @return 1 if succesfully added, negative numbers or 0 in case of error
     */
    public int insert(Passenger passenger) {
        int result = 0;
        if (passenger != null) {
            try {
                Connection conn = dbConnect.getConnection();
                if (conn != null) {
                    PreparedStatement st = conn.prepareStatement(queries.get("insert"));
                    st.setString(1, passenger.getName());
                    st.setString(2, passenger.getTelephone());
                    st.setBoolean(3, passenger.isMinor());
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
     * Method that deletes a passenger in to the database
     *
     * @param passenger to remove
     * @return 1 if succesfully added, negative numbers or 0 in case of error
     */
    public int remove(Passenger passenger) {
        int result = 0;
        if (passenger != null) {
            try {
                Connection conn = dbConnect.getConnection();
                if (conn != null) {
                    PreparedStatement st = conn.prepareStatement(queries.get("delete"));
                    st.setString(1, passenger.getTelephone());
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
     * Method that updates parameters of a passenger in the database
     *
     * @param passenger with thee modifications and also with the id of the
     * oldPassenger
     * @return 1 if succesfully updated, a negative number or 0 in case of error
     */
    public int update(Passenger passenger) {
        int result = 0;
        if (passenger != null) {
            try {
                Connection connection = dbConnect.getConnection();
                if (connection != null) {
                    PreparedStatement st = connection.prepareStatement(queries.get("update"));
                    st.setString(1, passenger.getName());
                    st.setString(2, passenger.getTelephone());
                    st.setBoolean(3, passenger.isMinor());
                    st.setLong(4, passenger.getId());//because it has the same id that the oldPassenger
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
     * Method selects a passenger by a given phone
     *
     * @param phone given
     * @return passenger if founded, null in case of error
     * @throws RuntimeException
     */
    public Passenger selectWherePhone(String phone){
        Passenger result = null;
        try {
            Connection conn = dbConnect.getConnection();
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement(queries.get("sPhone"));
                st.setString(1, phone);
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
     * Method selects a passenger by a given id
     *
     * @param id given
     * @return passenger if founded, null in case of error
     * @throws RuntimeException
     */
    public Passenger selectWhereId(long id) throws RuntimeException {
        Passenger result = null;
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
     * Method that inits all the queries that we are going to use in a hash map
     */
    private void initQueries() {
        queries.put("sAll", String.format("select * from %s", TABLE_NAME));
        queries.put("insert", String.format("INSERT INTO %s VALUES (0,?,?,?)", TABLE_NAME));
        queries.put("delete", String.format("DELETE FROM %s WHERE PHONE = ?", TABLE_NAME));
        queries.put("update", String.format("UPDATE %s SET NAME = ?, PHONE = ?, MINOR = ? WHERE ID = ?", TABLE_NAME));
        queries.put("sPhone", String.format("select * from %s where phone = ?", TABLE_NAME));
        queries.put("sId", String.format("SELECT * FROM %s where id = ?", TABLE_NAME));
    }

    /**
     * Method that converts a result of a query into a passenger
     *
     * @param rs result set
     * @return the passenger or null in case of error
     * @throws SQLException
     */
    private Passenger fromResultSet(ResultSet rs) throws SQLException {
        Passenger flight = null;
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String telephone = rs.getString("phone");
        boolean minor = rs.getBoolean("minor");
        flight = new Passenger(id, name, telephone, minor);
        return flight;
    }

    /**
     * Method that searchs the id of a passenger by the given phnoe
     *
     * @param phone given
     * @return the id or -1 in case of error
     */
    public long searchPassengerId(String phone) {
        long id = -1;
        Passenger result = null;
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

}
