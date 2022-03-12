package cat.proven.customers.model.persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * encapsulates data for database connection.
 *
 * @author Jose
 */
public final class DbConnect {
    
    private static DbConnect instance;

    static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String PROTOCOL = "jdbc:mysql:";
    static final String HOST = "127.0.0.1";
    static final String BD_NAME = "customersdb";
    static final String USER = "customerssusr";
    static final String PASSWORD = "customerspwd";
    static final String PARAMS = "useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    private DbConnect() throws ClassNotFoundException {
        loadDriver();
    }

    public static DbConnect getInstance() throws ClassNotFoundException {
        if (instance == null) {
            instance = new DbConnect();
        }
        return instance;
    }
    
    public void loadDriver() throws ClassNotFoundException {
        Class.forName(DRIVER);
    }

    /**
     * gets and returns a connection to database
     *
     * @return connection
     * @throws java.sql.SQLException
     */
    public Connection getConnection() throws SQLException {
        final String BD_URL = String.format("%s//%s/%s?%s", PROTOCOL, HOST, BD_NAME, PARAMS);
        Connection conn = null;
        conn = DriverManager.getConnection(BD_URL, USER, PASSWORD);
        return conn;
    }
}


