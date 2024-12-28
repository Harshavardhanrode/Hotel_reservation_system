/*package c31;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestConnection {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn != null) {
                System.out.println("Connection successful!");
                Statement adminStmt = conn.createStatement();
                ResultSet adminRs = adminStmt.executeQuery("SELECT * FROM admin");

                while (adminRs.next()) {
                    // Adjust column types to match the table schema
                    System.out.println("Admin ID: " + adminRs.getInt("id") +
                                       ", Username: " + adminRs.getString("username") +
                                       ", Password: " + adminRs.getString("password"));
                }
            } else {
                System.out.println("Connection failed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}*/
package c31;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestConnection {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn != null) {
                System.out.println("Connection successful!");
                Statement adminStmt = conn.createStatement();
                ResultSet adminRs = adminStmt.executeQuery("SELECT reservations.customer_id,customer.name ,customer.email,reservations.room_type, reservations.reservation_date FROM reservations INNER JOIN customer ON reservations.customer_id = customer.id;");

                while (adminRs.next()) {
                    // Adjust column types to match the table schema
                    System.out.println("Admin ID: " + adminRs.getInt("customer_id")+adminRs.getString("name")+adminRs.getString("email")+
                                       ", Username: " + adminRs.getString("room_type") +
                                       ", Password: " + adminRs.getString("reservation_date"));
                }
            } else {
                System.out.println("Connection failed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}/*
<td><%= rs.getInt("reservation_id") %></td>
<td><%= rs.getString("customer_name") %></td>
<td><%= rs.getString("room_type") %></td>
<td><%= rs.getDate("check_in_date") %></td>
<td><%= rs.getDate("check_out_date") %></td>*/