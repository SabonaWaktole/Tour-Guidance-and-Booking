package com.tams.dao;

import java.sql.*;

import com.tams.service.PaymentService;
import com.tams.utils.DBConnection;

public class BookingDAO {

    // Modified to return both booking result and amount (price)
    public int createBooking(int userId, String place, int level, int days, int people) {
        try (Connection connection = DBConnection.getConnection()) {

            // Determine the price per level
            int levelPrice;
            switch (level) {
                case 1:
                    levelPrice = 3000;
                    break;
                case 2:
                    levelPrice = 2500;
                    break;
                case 3:
                    levelPrice = 2200;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid level: " + level);
            }
            int price = levelPrice * days * people;
            PaymentService paymentService = new PaymentService();
            boolean success = paymentService.makePayment(userId, place, price);
            
            
            if(success) {

            // Insert into `travel_history` table and retrieve the generated ID
            String travelHistoryQuery = "INSERT INTO history (userid, place, people,price) VALUES (?, ?, ?,?) RETURNING id";
            try (PreparedStatement travelHistoryStmt = connection.prepareStatement(travelHistoryQuery)) {
                travelHistoryStmt.setInt(1, userId);
                travelHistoryStmt.setString(2, place);
                travelHistoryStmt.setInt(3, people);
                travelHistoryStmt.setFloat(4, price);
                System.out.print("as bira");
                travelHistoryStmt.executeQuery();
                System.out.print("as gubbaa");
                
                return price;
                
                
            }
            
            
            }else {
            	System.out.print("Something" + success);
            	return -1;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print("Here ");
            return -1; // Return -1 in case of a database error
            
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.print("There");
            return -1; // Return -1 in case of invalid input
        }
    }
}
