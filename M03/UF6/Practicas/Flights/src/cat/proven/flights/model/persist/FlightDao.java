/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.flights.model.persist;

import cat.proven.flights.model.Flight;
import java.sql.Connection;
import java.sql.Date; //sql because of the database
import java.sql.PreparedStatement;
import java.sql.Time;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fredd
 */
public class FlightDao {

    protected Map<String, String> queries;
    protected DbConnect dbConnect;
    private final String TABLE_NAME;

    public FlightDao() {
        this.TABLE_NAME = "flights";
        this.dbConnect = new DbConnect();
        this.queries = new HashMap<>();
        initQueries();
    }

    /**
     * Selects all entities from database
     *
     * @return list of entities or null in case of errror
     */
    public List<Flight> selectAll() {
        List<Flight> result = new ArrayList<>();
        try {
            Connection connection = dbConnect.getConnection();
            if (connection != null) {
                String query = queries.get("sAll");
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    Flight obj = fromResultSet(rs);
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
     * Method that updates parameters of a flight in the database
     * @param flight with me modifications ans also with the id of the oldFligth
     * @return 1 if succesfully updated or a negative number or 0 in case of error
     */
    public int update(Flight flight) {
        int result = 0;
        if (flight != null) {
            try {
                Connection connection = dbConnect.getConnection();
                if (connection != null) {
                    PreparedStatement st = connection.prepareStatement(queries.get("update"));
                    st.setString(1, flight.getCode());
                    st.setInt(2, flight.getCapacity());
                    st.setDate(3, new java.sql.Date(flight.getDate().getTime()));
                    st.setTime(4, new java.sql.Time(flight.getTime().getTime()));
                    st.setLong(5,flight.getId()); //the id of the flight 
                    result = st.executeUpdate();
                } else {
                    result = -2;
                }
            } catch (SQLException ex) {
                result = -1;
            }
        } else {
            result = 0;
        }
        return result;
    }

    /**
     * Method that deletes a flight in to the database
     * @param flight to remove
     * @return 1 if succesfully added, negative numbers or 0 in case of error
     */
    public int delete(Flight flight) {
        int result = 0;
        if (flight != null) {
            try {
                Connection conn = dbConnect.getConnection();
                if (conn != null) {
                    PreparedStatement st = conn.prepareStatement(queries.get("delete"));
                    st.setLong(1, flight.getId());
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
     *Method that inserts a flight in to the database
     * @param flight the flight to add
     * @return 1 if succesfully added, negative numbers or 0 in case of error
     */
    public int insert(Flight flight) {
        int result;
        if (flight != null) {
            try {
                Connection conn = dbConnect.getConnection();
                if (conn != null) {
                    PreparedStatement st = conn.prepareStatement(queries.get("insert"));
                    st.setString(1, flight.getCode());
                    st.setInt(2, flight.getCapacity());
                    st.setDate(3, new java.sql.Date(flight.getDate().getTime()));
                    st.setTime(4, new java.sql.Time(flight.getTime().getTime()));
                    result = st.executeUpdate();
                } else {
                    result = -3;
                }
            } catch (SQLIntegrityConstraintViolationException eX) {
                result = -11;
            } catch (SQLException ex) {
                result = -2;
            }
        } else {
            result = -1;
        }
        return result;
    }

    /**
     * Method that searchs a Flight in to de database
     *
     * @param code of the flight
     * @return flight with the given code or null in case of error
     */
    public Flight selectWhereCode(String code) {
        Flight result = null;
        try {
            Connection conn = dbConnect.getConnection();
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement(queries.get("sCode"));
                st.setString(1, code);
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
     * Method that searchs a Flight in to de database
     *
     * @param id of the flight
     * @return flight with the given code or null in case of error
     */
    public Flight selectWhereId(long id){
        Flight result = null;
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

    public void initQueries() {
        queries.put("sAll", String.format("select * from %s", TABLE_NAME));
        queries.put("insert", String.format("INSERT INTO %s VALUES (0,?,?,?,?)", TABLE_NAME));
        queries.put("delete", String.format("DELETE FROM %s WHERE ID = ?", TABLE_NAME));
        queries.put("update", String.format("UPDATE %s SET CODE = ?, capacity = ?, date=?, time=? WHERE ID = ?", TABLE_NAME));
        queries.put("sCode", String.format("select * from %s where code = ?", TABLE_NAME));
        queries.put("sId", String.format("select * from %s where id = ?", TABLE_NAME));

    }

    /**
     * Method that converts a ResultSet entry into a flight
     *
     * @param rs
     * @return
     */
    private Flight fromResultSet(ResultSet rs) throws SQLException {
        Flight flight = null;
        int id = rs.getInt("id");
        String code = rs.getString("code");
        int capacity = rs.getInt("capacity");
        Date sqlDate = rs.getDate("date");
        Time sqlTime = rs.getTime("time");
        Date date = new Date(sqlDate.getTime());
        Time time = new Time(sqlTime.getTime());
        flight = new Flight(id, code, capacity, date, time);
        return flight;
    }

    /**
     * Method that seachs the id of a flight by code
     * @param code given
     * @return id or -1 in case of error
     */
    public long selectFlightId(String code) {
        long id = -1;
        Flight result = null;
        try {
            Connection conn = dbConnect.getConnection();
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement(queries.get("sCode"));
                st.setString(1, code);
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
