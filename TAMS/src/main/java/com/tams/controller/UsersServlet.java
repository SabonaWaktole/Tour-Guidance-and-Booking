package com.tams.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import com.tams.service.UserService;

/**
 * Servlet implementation class UsersServlet
 */
@WebServlet("/UsersServlet")
public class UsersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("login".equals(action)) {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String password = request.getParameter("password");
            int userId = userService.validateLogin(firstName,lastName, password);
            if (userId > 0) {
                HttpSession session = request.getSession(); // Create a new session or get the existing one
                session.setAttribute("userId", userId); // Store the userId in the session
                session.setAttribute("role", "user");
                response.sendRedirect("me.jsp");
            } else {
                response.sendRedirect("login.jsp?error=invalid"); // Redirect back to login on failure
            }
        } else if ("signup".equals(action)) {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            int userId = userService.validatesignin(firstName, lastName, password, email);
            if (userId > 0) {
                HttpSession session = request.getSession(); // Create a new session or get the existing one
                session.setAttribute("userId", userId); // Store the userId in the session
                response.sendRedirect("me.jsp");
            } else {
                response.sendRedirect("signup.jsp?error=invalid"); // Redirect back to signup on failure
            }
        } else if ("adminlogin".equals(action)) {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String password = request.getParameter("password");
            String role = userService.validteAdmin(firstName,lastName, password); // Validate admin credentials
            if ("admin".equals(role)) {
            	int id = userService.retriveAdminId(firstName, lastName, password);
                if(id > 0) {
                	HttpSession session = request.getSession(); // Create a new session or get the existing one
                    session.setAttribute("id", id); // Set access_id as a flag in the session
                    response.sendRedirect("adminDashboard.jsp"); // Redirect to the admin dashboard
                } else {
                	response.sendRedirect("adminLogin.jsp?error=invalid");
                	System.out.print(id);
                }
            } else {
                response.sendRedirect("adminLogin.jsp?error=invalid"); // Redirect back to admin login on failure
                System.out.print(role + "admin".equals(role));
            }
        }else if("change".equals(action)) {
        	String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            
         // Normalize empty strings to null
            firstname = (firstname == null || firstname.trim().isEmpty()) ? null : firstname;
            lastname = (lastname == null || lastname.trim().isEmpty()) ? null : lastname;
            email = (email == null || email.trim().isEmpty()) ? null : email;
            password = (password == null || password.trim().isEmpty()) ? null : password;
            
            HttpSession session = request.getSession(false); // Get the existing session (don't create a new one)
            if (session == null || session.getAttribute("userId") == null) {
                response.sendRedirect("login.jsp"); // Redirect to login if session is invalid
                return;
            }

            int userId = (Integer) session.getAttribute("userId"); // Retrieve the userId from session
            boolean success = userService.changeCredentails(userId, firstname, lastname, password, email);
            if(success) {
            	response.sendRedirect("me.jsp"); // Redirect to login if session is invalid
                System.out.print(success);
            	
            }else {
            	response.sendRedirect("changeme.jsp"); // Redirect to login if session is invalid
            
            }
         
        }else if("promote".equals(action)) {
        	String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String email = request.getParameter("email");
            boolean success = userService.promoteToAdmin(firstname, lastname, email);
            if(success) {
            	response.sendRedirect("adminDashboard.jsp");
            }
            else {
            	response.sendRedirect("promotion.jsp");
            }
        	
        }else if("addadmin".equals(action)) {
        	String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String email = request.getParameter("email");
            boolean success = userService.addToAdmin(firstname, lastname, email);
            if(success) {
            	response.sendRedirect("adminDashboard.jsp");
            }
            else {
            	response.sendRedirect("promotion.jsp");
            }
        }
        
    }
}
