package com.tams.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.tams.dao.PaymentDAO;
import com.tams.service.PaymentService;
/**
 * Servlet implementation class PaymentsServlet
 */
@WebServlet("/PaymentsServlet")
public class PaymentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final PaymentService paymentService = new PaymentService();
	

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false); // Get the existing session (don't create a new one)
		//float amount = (Float) session.getAttribute("amount");
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp"); // Redirect to login if session is invalid
            return;
        }

        int userId = (Integer) session.getAttribute("userId"); // Retrieve the userId from session
        String action = request.getParameter("action");
        String place = request.getParameter("place");
    	int amount = Integer.parseInt(request.getParameter("amount"));
        if("pay".equals(action)) {
        	boolean success = paymentService.makePayment(userId, place, amount);
        	if(success) {
        		response.getWriter().write("Booking successful");        		
        	}
        	else 
        	{
        		response.getWriter().write("Booking failed");
        	}
        	
        }
        else if("deposit".equals(action)) {
        
        	
        	boolean success = paymentService.makeDeposit(userId, amount);
        	if (success) {
        		response.sendRedirect("booking.jsp");
        		
        	}else {
        		response.sendRedirect("me.jsp?error=Invalid");
        	}
        	}
        //String paymentDetails = request.getParameter("paymentDetails");
        // Payment logic goes here
        response.getWriter().write("Payment processed successfully");
    }
}
