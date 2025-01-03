<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page session="true" %>
<%
    // Get the session object (it will not create a new session if one doesn't exist)
    HttpSession existingSession = session;
	int Id = -1;

    if (existingSession == null) {
        // No existing session
        out.println("<p>No active session found. Please log in again.</p>");
    } else {
        // Retrieve session attributes
        Integer userId = (Integer) session.getAttribute("userId");
        String userRole = (String) session.getAttribute("role");
        Id = userId;

        if (userId != null) {
            out.println("<p>Welcome back, user ID: " + userId + "</p>");
        } else {
            out.println("<p>User ID not found in session.</p>");
        }

        if (userRole != null) {
            out.println("<p>Your role: " + userRole + "</p>");
        } else {
            out.println("<p>Role not found in session.</p>");
        }
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>User Details</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
            margin-bottom: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        h2 {
            text-align: center;
        }
    </style>
</head>
<body>
    <h2>User Information</h2>

    <%
        // Database connection details
        String jdbcURL = "jdbc:postgresql://localhost:5432/tams";
        String dbUser = "postgres";
        String dbPassword = "1234";

        Connection conn = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        PreparedStatement stmt3 = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        
        String userQuery = "SELECT * FROM users WHERE id = ?";

        try {
            // Establish connection
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            stmt = conn.prepareStatement(userQuery);
            stmt.setInt(1, Id);

            // Fetch and display user data
            rs = stmt.executeQuery();
    %>
    <table>
        <thead>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Role</th>
            </tr>
        </thead>
        <tbody>
            <%
                while (rs.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rs.getString("firstname") + "</td>");
                    out.println("<td>" + rs.getString("lastname") + "</td>");
                    out.println("<td>" + rs.getString("email") + "</td>");
                    out.println("<td>" + rs.getString("role") + "</td>");
                    out.println("</tr>");
                }
            %>
        </tbody>
    </table>
    <%
            // Fetch and display history data
            String historyQuery = "SELECT * FROM history where userid = ?";
		    stmt1 = conn.prepareStatement(historyQuery);
		    stmt1.setInt(1, Id);
		
		    // Fetch and display user data
		    rs1 = stmt1.executeQuery();
    %>
    <h2>History</h2>
    <table>
        <thead>
            <tr>
                <th>Place</th>
                <th>Price</th>
                <th>People</th>
                <th>updated at</th>
            </tr>
        </thead>
        <tbody>
            <%
                while (rs1.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rs1.getString("place") + "</td>");
                    out.println("<td>" + rs1.getInt("price") + "</td>");
                    out.println("<td>" + rs1.getInt("people") + "</td>");
                    out.println("<td>" + rs1.getTimestamp("updated_at") + "</td>");
                    out.println("</tr>");
                }
            %>
        </tbody>
    </table>
    <%
            // Fetch and display balance data
            Integer userId = (Integer) session.getAttribute("userId");
            String balanceQuery = "SELECT * FROM balance where userid=?";
		    stmt2 = conn.prepareStatement(balanceQuery);
		    stmt2.setInt(1, userId);
		
		    // Fetch and display user data
		    rs2 = stmt2.executeQuery();
    %>
    <h2>Balance</h2>
    <table>
        <thead>
            <tr>
                <th>Balance Amount</th>
                <th>Last Updated</th>
            </tr>
        </thead>
        <tbody>
            <%
                while (rs2.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rs2.getDouble("balance") + "</td>");
                    out.println("<td>" + rs2.getTimestamp("updated_at") + "</td>");
                    
                    out.println("</tr>");
                }
            %>
        </tbody>
    </table>
    <%
            // Fetch and display balance history data
            userId = (Integer) session.getAttribute("userId");
            String balanceHistoryQuery = "SELECT * FROM balance_history where userid=?";
		    stmt3 = conn.prepareStatement(balanceHistoryQuery);
		    stmt3.setInt(1, userId);
		
		    // Fetch and display user data
		    rs3 = stmt3.executeQuery();
    %>
    <h2>Balance History</h2>
    <table>
        <thead>
            <tr>
                <th>Last update</th>
                <th>Added amount</th>
                <th>reduced amount</th>
                <th>place visited</th>
            </tr>
        </thead>
        <tbody>
            <%
                while (rs3.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rs3.getTimestamp("updated_at") + "</td>");
                    out.println("<td>" + rs3.getDouble("added") + "</td>");
                    out.println("<td>" + rs3.getDouble("reduced") + "</td>");
                    out.println("<td>" + rs3.getString("place") + "</td>");
                    out.println("</tr>");
                }
            %>
        </tbody>
    </table>
    <%
        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
            e.printStackTrace();
        } finally {
            // Close resources
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    %>
    <a href="changeme.jsp" class="changeme">Edit profile</a>
    <a href="booking.jsp" class="booking">Start booking</a>
    <a href="payment.jsp" class="booking">add money</a>
</body>
</html>
