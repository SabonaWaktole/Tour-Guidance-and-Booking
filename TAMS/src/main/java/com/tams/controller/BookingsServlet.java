package com.tams.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.tams.dao.BookingDAO;
import com.tams.service.BookingService;

/**
 * Servlet implementation class BookingsServlet
 */
@WebServlet("/BookingsServlet")
public class BookingsServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final BookingService bookingService = new BookingService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false); // Get the existing session (don't create a new one)
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp"); // Redirect to login if session is invalid
            return;
        }

        int userId = (Integer) session.getAttribute("userId"); // Retrieve the userId from session

        String action = request.getParameter("action");
        if ("create".equals(action)) {
            String place = request.getParameter("place");
            String daysString = request.getParameter("days");  // Get the "days" parameter as a String
            int days = 0;
            try {
                days = Integer.parseInt(daysString);  // Convert the String to an integer
            } catch (NumberFormatException e) {
                // Handle invalid number format if necessary
                e.printStackTrace();
                // You can set an error message or return an error response
            }
            String levelString = request.getParameter("level");  // Get the "days" parameter as a String
            int level = 0;
            try {
            	level = Integer.parseInt(levelString);  // Convert the String to an integer
            } catch (NumberFormatException e) {
                // Handle invalid number format if necessary
                e.printStackTrace();
                // You can set an error message or return an error response
            }
            int people = 1;
            String peopleString = request.getParameter("people");
            try {
            	people = Integer.parseInt(peopleString);  // Convert the String to an integer
            } catch (NumberFormatException e) {
                // Handle invalid number format if necessary
                e.printStackTrace();
                // You can set an error message or return an error response
            }
            
            int price = bookingService.createBooking(userId, place, level, days, people);
            if (price >= 0) {
            	session.setAttribute("amount", price);
                System.out.print("Your booking is success ful");;
                response.sendRedirect("report.jsp");
            } else {
            	System.out.print("error with your booking"+price);
                response.getWriter().write("Booking failed");
                response.sendRedirect("payment.jsp");
            }
        }
    }
}
