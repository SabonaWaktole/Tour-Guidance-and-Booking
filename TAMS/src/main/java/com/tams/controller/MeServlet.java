package com.tams.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;

public class MeServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get userId from session
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Database connection details
        String jdbcURL = "jdbc:postgresql://localhost:5432/tams";
        String dbUser = "postgres";
        String dbPassword = "1234";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Map<String, String>> userInfo = new ArrayList<>();
        try {
            // Establish connection
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            // Query to get user data
            String userQuery = "SELECT * FROM users WHERE id = ?";
            stmt = conn.prepareStatement(userQuery);
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();

            // Process user data
            if (rs.next()) {
                Map<String, String> user = new HashMap<>();
                user.put("firstname", rs.getString("firstname"));
                user.put("lastname", rs.getString("lastname"));
                user.put("email", rs.getString("email"));
                user.put("role", rs.getString("role"));
                userInfo.add(user);
            }

            // Set user data in request attribute to be used in JSP
            request.setAttribute("userInfo", userInfo);

            // Forward to JSP for rendering
            RequestDispatcher dispatcher = request.getRequestDispatcher("user_details.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
