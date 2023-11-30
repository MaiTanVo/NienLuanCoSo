package b2013574.nienluancar;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

    public static Connection connectDB(){
        try{
            return DriverManager.getConnection(mysqlJdbcUrl, mysqlUsername, mysqlPassword);
    }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    private static final String mysqlJdbcUrl = "jdbc:mysql://localhost:3306/nienluan";
    private static final String mysqlUsername = "maitanvo";
    private static final String mysqlPassword = "maitanvo";
}
