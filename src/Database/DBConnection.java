package Database;

import com.mysql.cj.jdbc.Driver;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String userName = "sqlUser"; // Username
    private static final String password = "Passw0rd!"; // Password
    public static Connection connection;  // Connection Interface


    /**
     * Helps connect application to database
     *
     *
     */
    public static Connection openConnection() {
        try {
            DriverManager.registerDriver(new Driver());
            if (connection == null || connection.isClosed())
                connection = DriverManager.getConnection(jdbcUrl, userName, password);
            System.out.println("Connection successful");
          //  return connection;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return connection;
        //  throw new RuntimeException("Unable to connect to Database", exception);
    }


    public static Connection getConnection(){
        return connection;
    }


    public static void terminateConnection() {
        try {
            if (connection != null && !connection.isClosed())
                connection.close();
        } catch (SQLException exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }
}
