package com.tams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.tams.utils.DBConnection;

public class PaymentDAO {

    // Method to deposit a specified amount into the user's balance
    public boolean makeDeposit(int userId, double amount) {
    	String updateQuery = "UPDATE balance SET balance = balance + ? WHERE userid = ?";
    	String insertQuery = "INSERT INTO balance(userid,balance) VALUES(?, ?)";
    	String insertBalanceHistoryQuery = "INSERT INTO balance_history(userid,added) VALUES(?, ?)";
    	try (Connection connection = DBConnection.getConnection()) {
    	    // Start a transaction
    	    connection.setAutoCommit(false);

    	    // Update query
    	    try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
    	        updateStmt.setDouble(1, amount);
    	        updateStmt.setInt(2, userId);
    	        int rowsUpdated = updateStmt.executeUpdate();
    	        try(PreparedStatement stmt = connection.prepareStatement(insertBalanceHistoryQuery)){
    	        
    	        stmt.setInt(1, userId);
                stmt.setDouble(2, amount);
                stmt.executeUpdate();
                System.out.print("here it was updated");
                }

    	        if (rowsUpdated == 0) {
    	            // Insert query if no rows were updated
    	            try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
    	            	System.out.print("zhere is the problem");
    	            	
    	            	insertStmt.setInt(1, userId);
    	                insertStmt.setDouble(2, amount);
    	                
    	                
    	                
    	                insertStmt.executeUpdate();
    	                
    	                
    	                System.out.print("zhere is the problem");
    	                	
    	            }
    	        }
    	    }

    	    // Commit the transaction
    	    connection.commit();
    	    System.out.print("Commited added money");
    	    return true;
    	} catch (SQLException e) {
    	    e.printStackTrace();
    	    return false;
    	}

    }

    // Method to deduct a specified amount from the user's balance
    public boolean makePayment(int userId, String place, double price) {
        String checkBalanceQuery = "SELECT balance FROM balance WHERE userid = ?";
        String updateBalanceQuery = "UPDATE balance SET balance = balance - ? WHERE userid = ?";
        String insertBalanceHistoryQuery = "INSERT INTO balance_history(userid,reduced,place) VALUES(?, ?, ?)";
        
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement checkStmt = connection.prepareStatement(checkBalanceQuery)) {

            // Check the current balance
            checkStmt.setInt(1, userId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                double currentBalance = rs.getDouble("balance");

                if (currentBalance >= price) {
                    // Proceed to deduct the amount
                    try (PreparedStatement updateStmt = connection.prepareStatement(updateBalanceQuery)) {
                        updateStmt.setDouble(1, price);
                        updateStmt.setInt(2, userId);
                        int rowsUpdated = updateStmt.executeUpdate();
                        
                        PreparedStatement stmt = connection.prepareStatement(insertBalanceHistoryQuery);
                        stmt.setInt(1, userId);
    	                stmt.setDouble(2, price);
    	                stmt.setString(3, place);
    	                
    	                int row = stmt.executeUpdate();
    	                if(row < 1) {
    	                System.out.print("zhere is the problem");
    	                	}
                        return rowsUpdated > 0; // Return true if the balance was successfully updated
                    }
                } else {
                    System.out.println("Insufficient balance for user ID: " + userId);
                    return false;
                }
            } else {
                System.out.println("User ID not found in balance table: " + userId);
                return false;
            }
        } catch (SQLException e) {
        	System.out.println("User ID sabona \n waktole");
            e.printStackTrace();
            return false;
        }
    }
}
