package c31;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/hotelreservation";
    private static final String USER = "root";
    private static final String PASSWORD = "Harsh.0027";

    public static Connection getConnection() {
    	 Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn=  DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return conn;
    }
}
