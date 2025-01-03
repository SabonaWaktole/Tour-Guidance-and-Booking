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
        PreparedStatement stmt1 = null;
        ResultSet rs1 = null;

        // Query to get the most recent history record
		String historyQuery = "SELECT * FROM history WHERE userid = ? ORDER BY id DESC LIMIT 1";

        try {
            // Establish connection
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            
            // Prepare and execute the history query
            stmt1 = conn.prepareStatement(historyQuery);
            stmt1.setInt(1, Id);
            rs1 = stmt1.executeQuery();
    %>
    <h2>History</h2>
    <table>
        <thead>
            <tr>
                <th>Place</th>
                <th>Price</th>
                <th>People</th>
                <th>Updated At</th>
            </tr>
        </thead>
        <tbody>
            <%
                // Check if there's at least one record
                if (rs1.next()) {
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
        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
            e.printStackTrace();
        } finally {
            // Close resources
            if (rs1 != null) rs1.close();
            if (stmt1 != null) stmt1.close();
            if (conn != null) conn.close();
        }
    %>
    <a href="me.jsp" class="changeme">Go to profile profile</a>
    <a href="booking.jsp" class="booking">add another booking</a>
    
</body>
</html>
