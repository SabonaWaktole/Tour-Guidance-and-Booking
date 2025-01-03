<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page session="true" %>
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
        
        String userQuery = "SELECT * FROM users";

        try {
            // Establish connection
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            stmt = conn.prepareStatement(userQuery);
            

            // Fetch and display user data
            rs = stmt.executeQuery();
    %>
    <table>
        <thead>
            <tr>
            	<th>user id</th>
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
                    out.println("<td>" + rs.getInt("id") + "</td>");
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
            String historyQuery = "SELECT * FROM history";
		    stmt1 = conn.prepareStatement(historyQuery);
		
		    // Fetch and display user data
		    rs1 = stmt1.executeQuery();
    %>
    <h2>History</h2>
    <table>
        <thead>
            <tr>
            	<th>userId</th>
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
                    out.println("<td>" + rs1.getInt("userid") + "</td>");
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
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    %>
    <a href="promotion.jsp" class="promotion">Promote User</a>
    <a href="addadmin.jsp" class="promotion">add new admin</a>
</body>
</html>
