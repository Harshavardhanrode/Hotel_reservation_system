package c31;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RoomReservation
 */
@WebServlet("/RoomReservation")
public class RoomReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomReservation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String action = request.getParameter("action");

        try (Connection conn = DatabaseConnection.getConnection()) {
             if ("register".equals(action)) {
                String email = request.getParameter("email");
                String roomType = request.getParameter("roomType");

                PreparedStatement stmt = conn.prepareStatement(
                        "SELECT id FROM customer WHERE email = ?");
                stmt.setString(1, email);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    int customerId = rs.getInt("id");

                    PreparedStatement reservationStmt = conn.prepareStatement(
                            "INSERT INTO reservations (customer_id, room_type) VALUES (?, ?)");
                    reservationStmt.setInt(1, customerId);
                    reservationStmt.setString(2, roomType);

                    int rows = reservationStmt.executeUpdate();
                    if(rows>0) {
                    	response.getWriter().println("Room reserved successfully!");
                    	RequestDispatcher rd=request.getRequestDispatcher("customerhome.html");  
         	        	rd.forward(request, response); 
                    }else {
                    	response.getWriter().println("<script>alert('Failed to add room.');</script>");	
                    }
               
                } else {
                	response.getWriter().println("<script>alert('Customer not found.');</script>");
                	
                }
            } else if ("cancel".equals(action)) {
                String email = request.getParameter("email");

                PreparedStatement stmt = conn.prepareStatement(
                        "DELETE FROM reservations WHERE customer_id = (SELECT id FROM customer WHERE email = ?)");
                stmt.setString(1, email);

                int rows = stmt.executeUpdate();
                if(rows>0) {
                	response.getWriter().println("Room cancelled successfully!");
                	RequestDispatcher rd=request.getRequestDispatcher("customerhome.html");  
     	        	rd.forward(request, response); 
                }else {
                	response.getWriter().println("<script>alert('Customer not found.');</script>");	
                    response.getWriter().println("Go back");
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error occurred.");
        }
	}

}
