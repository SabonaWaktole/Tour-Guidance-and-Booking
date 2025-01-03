package com.tams.dao;

import java.sql.*;
import com.tams.utils.DBConnection;

public class UserDAO {
	public int validateLogin(String firstName,String lastName, String password) {
	    try (Connection connection = DBConnection.getConnection()) {
	        String query = "SELECT id FROM users WHERE firstname = ? AND lastname = ? AND password = ?";
	        PreparedStatement stmt = connection.prepareStatement(query);
	        stmt.setString(1, firstName);
	        stmt.setString(2, lastName);
	        stmt.setString(3, password);
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            return rs.getInt("id");  // Return the user ID
	        } else {
	            return -1;  // Return -1 if no user is found
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return -1;  // Return -1 in case of an error
	    }
	}
	
	public int retriveAdminId(String firstName, String lastName, String password) {
		try (Connection connection = DBConnection.getConnection()) {
			String query = "SELECT id FROM users WHERE firstname = ? AND lastname = ? AND password = ?";
	        PreparedStatement stmt = connection.prepareStatement(query);
	        stmt.setString(1, firstName);
	        stmt.setString(2, lastName);
	        stmt.setString(3, password);
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            return rs.getInt("id"); // Return the user ID
	        } else {
	            return -1;  // Return -1 if no user is found
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	
	public String validateAdmin(String firstName, String lastName, String password) {
		try (Connection connection = DBConnection.getConnection()) {
	        String query = "SELECT role FROM users WHERE firstname = ? AND lastname = ? AND password = ?";
	        PreparedStatement stmt = connection.prepareStatement(query);
	        stmt.setString(1, firstName);
	        stmt.setString(2, lastName);
	        stmt.setString(3, password);
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            return rs.getString("role");  // Return the user ID
	        } else {
	            return "user";  // Return -1 if no user is found
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return "user";  // Return -1 in case of an error
	    }
	}
		
		
		
	

    
    
    public int registerUser(String firstName, String lastName, String password, String email) {
        String sql = "INSERT INTO users (firstname,lastname, password, email) VALUES (?, ?, ?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, password);
            pstmt.setString(4, email);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);  // Return the generated user ID
                    }
                }
            }
            return -1;  // Return -1 if no user is registered or error occurs
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;  // Return -1 in case of an error
        }
    }
    
    public boolean addAdmin(String firstName, String lastName, String email){
    	String sql = "INSERT INTO Users (firstname, lastname, email, password, role) VALUES (?, ?,?,'Admin', 'admin')";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            int row = pstmt.executeUpdate();
            if (row > 0) {
            	return true;
            }else {
            	return false;
            }
    } catch (SQLException e) {
		// TODO Auto-generated catch block
    	System.out.print("cant add user to admin");
		e.printStackTrace();
		return false;
	}
    	
    }
    
    public boolean promoteToAdmin(String firstName, String lastName, String email) {
    	String updateQuery = "UPDATE  users SET role = 'admin' WHERE firstname = ? AND lastname = ? AND email = ?";
    	
    	try (Connection conn = DBConnection.getConnection()){
                PreparedStatement pstmt = conn.prepareStatement(updateQuery);
                pstmt.setString(1, firstName);
                pstmt.setString(2, lastName);
                pstmt.setString(3, email);
                int row = pstmt.executeUpdate();
                if (row > 0) {
                	return true;
                }else {
                	return false;
                }
                
        } catch (SQLException e) {
    		// TODO Auto-generated catch block
        	System.out.print("cant promote user to admin: ");
    		e.printStackTrace();
    		return false;
    	}
		
    	
    	
    }
    
    public boolean changeCredential(int id, String newFirstname, String newLastname, String newPassword, String newEmail) {
        // Base query
        StringBuilder updateQuery = new StringBuilder("UPDATE users SET ");
        boolean hasUpdate = false;

        // Dynamically build query
        if (newFirstname != null && !newFirstname.isEmpty()) {
            updateQuery.append("firstname = ?");
            hasUpdate = true;
        }
        if (newLastname != null && !newLastname.isEmpty()) {
            if (hasUpdate) updateQuery.append(", "); // Add comma if there are previous updates
            updateQuery.append("lastname = ?");
            hasUpdate = true;
        }
        if (newPassword != null && !newPassword.isEmpty()) {
            if (hasUpdate) updateQuery.append(", ");
            updateQuery.append("password = ?");
            hasUpdate = true;
        }
        if (newEmail != null && !newEmail.isEmpty()) {
            if (hasUpdate) updateQuery.append(", ");
            updateQuery.append("email = ?");
            hasUpdate = true;
        }
        updateQuery.append(" WHERE id = ?");

        // Exit if no updates
        if (!hasUpdate) {
            System.out.println("No changes provided. Nothing to update.");
            return false;
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateQuery.toString())) {

            // Set parameters dynamically
            int paramIndex = 1;
            if (newFirstname != null && !newFirstname.isEmpty()) {
                pstmt.setString(paramIndex++, newFirstname);
            }
            if (newLastname != null && !newLastname.isEmpty()) {
                pstmt.setString(paramIndex++, newLastname);
            }
            if (newPassword != null && !newPassword.isEmpty()) {
                pstmt.setString(paramIndex++, newPassword);
            }
            if (newEmail != null && !newEmail.isEmpty()) {
                pstmt.setString(paramIndex++, newEmail);
            }
            pstmt.setInt(paramIndex, id); // Original email to identify the user

            // Execute the update
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
            	
                System.out.println("User credentials updated successfully!");
                return true;
            } else {
                System.out.println("No user found with the provided email.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Unable to update user credentials.");
            e.printStackTrace();
            return false;
        }
    }

    
    
    //public 
   
}
