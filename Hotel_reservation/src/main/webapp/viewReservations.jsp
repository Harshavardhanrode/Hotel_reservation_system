<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>


<!DOCTYPE html>
<html>
<head>
    <title>View Reservations</title>
    <style>
        *{
            margin: 0px;
            padding: 0px;
        }
        .main_container{
	background-color:rgb(236, 181, 119);
    height: 100vh;
    width: 100vw;
    }
    nav{
        height: 60px;
        width: 100%;
        display: flex;
        align-items: center;
        background-color: white;
    }
    .div1{
        display: flex;
        justify-content: center;
        font-size: x-large;
        letter-spacing: 1px;
        width: 70%;
    }
    .div2{
        height: 50px;
        width: 30%;
        /*background-color: blueviolet;*/
        display: flex;
        justify-content: space-around;
        align-items: center;
    }
    .nav_container{
        height: 25px;
        /*background-color: aqua;*/
        display: flex;
        align-items: center;
        padding: 2px 4px 2px 4px;
        border-radius: 5%;
        font-size: larger;
    }
    .nav_container:hover{
        background-color: rgb(191, 197, 201);

    }
    .container{
        height: 80vh;  
    }
    label{
    letter-spacing: 2px;
    margin-top: 25px;
    padding-left: 13px;
    color: rgb(7, 0, 0);
    
	}
	button{
	    padding: 3px;
	}
    table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
    }
    table, th, td {
        border: 1px solid black;
    }
    th, td {
     padding: 10px;
       text-align: left;
    }
    th {
       background-color: #f2f2f2;
    }
    </style>
</head>
<body>
    <div class="main_container">
        <nav>
            <div class="div1">
                <div id="hotel_name"> Hotel Bliss Dining </div>
            </div>
            <div class="div2">
                <div class="nav_container" id="home">Home</div>
                
                <div class="nav_container" id="booking">Booking</div>
                <div class="nav_container" id="customer_login">login</div>
                <div  class="nav_container" id="about">About</div>
            </div>   
        </nav>
        <div class="container">
            <h2 style="margin: 20px;">welcome admin..</h2>
            <div class="subcontainer">
                <label for="">To view all reservations-></label>
                <form action="viewReservations.jsp" method="get" style="display: inline;">
                	<button> View </button>
                </form>	
            </div>
            <div class=table_container>
            	<table>
			        <tr>
			            <th>Customer ID</th>
			            <th>Customer Name</th>
			            <th>Email</th>
			            <th>Room Type</th>
			            <th>reservation_date</th>
			            
			        </tr>
			        <% 
                        try{
                        	Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelreservation", "root", "Harsh.0027");
                            Statement stmt = con.createStatement();
                            String query = "SELECT reservations.customer_id,customer.name ,customer.email,reservations.room_type, reservations.reservation_date FROM reservations INNER JOIN customer ON reservations.customer_id = customer.id;";
                            ResultSet rs = stmt.executeQuery(query);
                            
                            // Fetch and display data
                            while (rs.next()) {
                    %>
                    <tr>
                        <td><%= rs.getInt("customer_id") %></td>
                        <td><%= rs.getString("name") %></td>
                        <td><%= rs.getString("email") %></td>
                        <td><%= rs.getString("room_type") %></td>
                        <td><%= rs.getString("reservation_date") %></td>
                        
                    </tr>
                    <% 
                            }
                            con.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                    %>
                    <tr>
                        <td colspan="5" style="color: red; text-align: center;">Error fetching data</td>
                    </tr>
                    <% } %>
			        </table>
            </div>
            
            
            
            
            
        </div> 
    </div>    
    <script>
let home_button = document.getElementById("home");
let booking_button = document.getElementById("booking");
let login_button = document.getElementById("customer_login");
let about_button = document.getElementById("about");
home_button.addEventListener('click',()=>{
    window.location.href = 'index.html';
})
login_button.addEventListener('click',()=>{
            window.location.href = 'customerLogin.html';
})

    </script>
</body>
</html>
