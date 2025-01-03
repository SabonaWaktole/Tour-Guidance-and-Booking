package com.tams.service;

import com.tams.dao.BookingDAO;

public class BookingService {
    private final BookingDAO bookingDAO = new BookingDAO();
    public int createBooking(int userId, String place, int level, int days, int people) {
        return bookingDAO.createBooking(userId, place, level, days, people);
    }
}
