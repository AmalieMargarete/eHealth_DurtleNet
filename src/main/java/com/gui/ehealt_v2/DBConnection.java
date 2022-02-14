package com.gui.ehealt_v2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class to connect to MYSQL Database as well as close it
 * DB connection achieved with JDBC driver
 * DB: MYSQL, local host
 * @author Amalie Wilke; StudentID: 1304925
 */

public class DBConnection {

    private static final String Host="127.0.0.1:3306";
    private static final int Port=3306;
    private static final String DB_Name="ehealth_db";
    private static final String Username="root";
    private static final String Password="hells";
    private static Connection connection;

    /**
     * Method returns the connection to the database after attempting it.
     * @return
     */
    public static Connection getConnection(){
        try{
            connection= DriverManager.getConnection(String.format("jdbc:mysql://localhost:%i/%s", "%s", "%s", Port, DB_Name, Username, Password));
        }
        catch(SQLException exception){
            System.out.println("Connection failure");
        }
        return connection;

    }

}
