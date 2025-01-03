package com.tams.service;

import com.tams.dao.PaymentDAO;

/**
 * 
 */
public class PaymentService {
	private final PaymentDAO paymentDAO = new PaymentDAO();
	public boolean makePayment(int userId,String place, float amount) {
		return paymentDAO.makePayment(userId, place, amount);
	}
	
	public boolean makeDeposit(int userId, float amount) {
		return paymentDAO.makeDeposit(userId, amount);
		
	}
}
