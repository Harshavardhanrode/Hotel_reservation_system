package c31;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");

        try (Connection conn = DatabaseConnection.getConnection()) {
            if ("signup".equals(action)) {
            	
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String password = request.getParameter("password");

                PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO customer (name, email, password) VALUES (?, ?, ?)");
                stmt.setString(1, name);
                stmt.setString(2, email);
                stmt.setString(3, password);
                
                int rows = stmt.executeUpdate();
                if(rows>0) {
                	response.getWriter().println("Signup successful!");
                    RequestDispatcher rd=request.getRequestDispatcher("customerLogin.html");  
        	        rd.forward(request, response); 
                }else {
                	response.getWriter().println("Signup failed!");
                }
               
            } else if ("login".equals(action)) {
                String email = request.getParameter("email");
                String password = request.getParameter("password");

                PreparedStatement stmt = conn.prepareStatement(
                        "SELECT * FROM customer WHERE email = ? AND password = ?");
                stmt.setString(1, email);
                stmt.setString(2, password);

                ResultSet rs = stmt.executeQuery();
                response.getWriter().println(rs.next() ? "Login successful!" : "Invalid credentials.");
                
                RequestDispatcher rd=request.getRequestDispatcher("customerhome.html");  
    	        rd.forward(request, response); 
            } 
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error occurred.");
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
	}

}
