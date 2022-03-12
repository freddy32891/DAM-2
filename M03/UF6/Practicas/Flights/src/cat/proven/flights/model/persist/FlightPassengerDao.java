package cat.proven.flights.model.persist;

import cat.proven.flights.model.Flight;
import cat.proven.flights.model.FlightPassenger;
import cat.proven.flights.model.Passenger;
import cat.proven.flights.model.persist.DbConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DAO for flight-passsenger relationship persistence.
 *
 * @author ProvenSoft
 */
public class FlightPassengerDao {

    protected final Map<String, String> queries;
    protected final DbConnect dbConnect;
    private final String TABLE_NAME;

    public FlightPassengerDao() throws ClassNotFoundException {
        this.TABLE_NAME = "flightpassenger";
        this.dbConnect = new DbConnect();
        this.queries = new HashMap<>();
        initQueries();
    }

    /**
     * converts resultset entry to entity object.
     *
     * @param rs resultset to get data from.
     * @return object with data in current position of resultset.
     */
    private FlightPassenger fromResultSet(ResultSet rs) throws SQLException {
        FlightPassenger p = null;
        long flightId = rs.getLong("flight_id");
        long passengerId = rs.getLong("passenger_id");
        p = new FlightPassenger(flightId, passengerId);
        return p;
    }

    /**
     * selects all entities from database.
     *
     * @return list of entities of null in case of error.
     */
    public List<FlightPassenger> selectAll() {
        List<FlightPassenger> result = new ArrayList<>();
        try {
            Connection conn = dbConnect.getConnection();
            if (conn != null) {
                String query = queries.get("sAll");
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    FlightPassenger obj = fromResultSet(rs);
                    if (obj != null) {
                        result.add(obj);
                    }
                }
            }
        } catch (SQLException ex) {
            result = null;
        }
        return result;
    }

    /**
     * inserts a flightpassenger in database.
     *
     * @param flightpassenger the flightpassenger to insert.
     * @return number of rows affected.
     */
    public int insert(FlightPassenger flightpassenger) {
        int result = 0;
        try {
            Connection conn = dbConnect.getConnection();
            if (conn != null) {
                String query = queries.get("insert");
                PreparedStatement st = conn.prepareStatement(query);
                st.setLong(1, flightpassenger.getFlightId());
                st.setLong(2, flightpassenger.getPassengerId());
                result = st.executeUpdate();
            }
        } catch (SQLException ex) {
            result = -1;
        }
        return result;
    }

    /**
     * Method that tells if a passenger is already in a specific flight
     *
     * @param fp flight passenger
     * @return 1 if is duplicated, 0 if not
     */
    public int duplicated(FlightPassenger fp) {
        int result = 0;
        try {
            Connection conn = dbConnect.getConnection();
            if (conn != null) {
                String query = queries.get("sExist");
                PreparedStatement st = conn.prepareStatement(query);
                st.setLong(1, fp.getFlightId());
                st.setLong(2, fp.getPassengerId());
                ResultSet rs=st.executeQuery();
                if(rs.next()){
                result=1;
                }
            }
        } catch (SQLException ex) {
            result = 0;
        }
        return result;
    }

    public List<FlightPassenger> selectWhereFlightId(long id) {
        List<FlightPassenger> result = new ArrayList<>();
        try {
            Connection conn = dbConnect.getConnection();
            if (conn != null) {
                String query = queries.get("sWhereFlightId");
                PreparedStatement st = conn.prepareStatement(query);
                st.setLong(1, id);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    FlightPassenger obj = fromResultSet(rs);
                    if (obj != null) {
                        result.add(obj);
                    }
                }
            }
        } catch (SQLException ex) {
            result = null;
        }
        return result;
    }

    public List<FlightPassenger> selectWherePassengerId(long id) {
        List<FlightPassenger> result = new ArrayList<>();
        try {
            Connection conn = dbConnect.getConnection();
            if (conn != null) {
                String query = queries.get("sWherePassengerId");
                PreparedStatement st = conn.prepareStatement(query);
                st.setLong(1, id);
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    FlightPassenger obj = fromResultSet(rs);
                    if (obj != null) {
                        result.add(obj);
                    }
                }
            }
        } catch (SQLException ex) {
            result = null;
        }
        return result;
    }

    private void initQueries() {
        queries.put("sAll", String.format("select * from %s", TABLE_NAME));
        queries.put("insert", String.format("insert into %s values (?, ?)", TABLE_NAME));
        queries.put("sWhereFlightId", String.format("select * from %s where flight_id = ?", TABLE_NAME));
        queries.put("sWherePassengerId", String.format("select * from %s where passenger_id = ?", TABLE_NAME));
        queries.put("sExist", String.format("Select * from %s where flight_id = ? and passenger_id = ?", TABLE_NAME));
        queries.put("delete", String.format("DELETE FROM %s WHERE  flight_id = ? and passenger_id = ?", TABLE_NAME));
    }

    public int delete(FlightPassenger fp) {
        int result = 0;
        try {
            Connection conn = dbConnect.getConnection();
            if (conn != null) {
                String query = queries.get("delete");
                PreparedStatement st = conn.prepareStatement(query);
                st.setLong(1, fp.getFlightId());
                st.setLong(2, fp.getPassengerId());
                result = st.executeUpdate();
            }
        } catch (SQLException ex) {
            result = -1;
        }
        return result;
    }

}
