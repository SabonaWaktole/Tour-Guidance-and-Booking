package com.tams.service;

import com.tams.dao.UserDAO;

public class UserService {
    private final UserDAO userDAO = new UserDAO();

    public int validateLogin(String firstName,String lastName, String password) {
        return userDAO.validateLogin( firstName, lastName,  password);
    }
    public int validatesignin(String firstName, String lastName, String password, String email) {
        return userDAO.registerUser(firstName,lastName, password,email);
        
    }
    
    public String validteAdmin(String firstName, String lastName, String password) {
    	return userDAO.validateAdmin(firstName, lastName, password);
    }
    public int retriveAdminId(String firstName, String lastName, String password) {
    	return userDAO.retriveAdminId(firstName, lastName, password);
    }
    
    public boolean promoteToAdmin(String firstName, String lastName, String email) {
    	return userDAO.promoteToAdmin(firstName, lastName, email);
    }
    
    public boolean addToAdmin(String firstName, String lastName, String email) {
    	return userDAO.addAdmin(firstName, lastName, email);
    }
    
    public boolean changeCredentails(int id, String newFirstname, String newLastname, String newPassword, String newEmail) {
    	return userDAO.changeCredential(id, newFirstname, newLastname, newPassword, newEmail);
    }
}