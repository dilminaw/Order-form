package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {

    static dbConnection instance;

    private Connection connection;

    private dbConnection() throws SQLException {
        connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade","root","1234");


    }

    public static   dbConnection getInstance() throws SQLException {
        return instance==null?new dbConnection():instance;
    }

    public Connection getConnection(){
        return connection;
    }
}
