package com.example.bookingservice.service;

import com.example.bookingservice.entity.Booking;
import com.example.bookingservice.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    public Booking addBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking updateBooking(Long id, Booking bookingDetails) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setUserId(bookingDetails.getUserId());
        booking.setShowId(bookingDetails.getShowId());
        booking.setBookingDate(bookingDetails.getBookingDate());
        booking.setSeats(bookingDetails.getSeats());
        booking.setTotalAmount(bookingDetails.getTotalAmount());
        booking.setDiscountApplied(bookingDetails.isDiscountApplied());
        return bookingRepository.save(booking);
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}