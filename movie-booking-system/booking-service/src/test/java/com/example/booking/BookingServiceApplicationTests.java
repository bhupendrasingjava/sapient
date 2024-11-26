package com.example.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.bookingservice.entity.Booking;
import com.example.bookingservice.repository.BookingRepository;
import com.example.bookingservice.service.BookingService;

class BookingServiceTests {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBookings() {
        Booking booking1 = new Booking();
        booking1.setId(1L);
        booking1.setUserId(1L);
        booking1.setShowId(1L);

        Booking booking2 = new Booking();
        booking2.setId(2L);
        booking2.setUserId(2L);
        booking2.setShowId(2L);

        List<Booking> bookings = Arrays.asList(booking1, booking2);
        when(bookingRepository.findAll()).thenReturn(bookings);

        List<Booking> result = bookingService.getAllBookings();

        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getUserId());
        assertEquals(2L, result.get(1).getUserId());
    }

    @Test
    void testGetBookingById() {
        Booking booking = new Booking();
        booking.setId(1L);
        booking.setUserId(1L);
        booking.setShowId(1L);

        when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));

        Booking result = bookingService.getBookingById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getUserId());
    }

    @Test
    void testAddBooking() {
        Booking booking = new Booking();
        booking.setUserId(1L);
        booking.setShowId(1L);

        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        Booking result = bookingService.addBooking(booking);

        assertNotNull(result);
        assertEquals(1L, result.getUserId());
    }

    @Test
    void testUpdateBooking() {
        Booking existingBooking = new Booking();
        existingBooking.setId(1L);
        existingBooking.setUserId(1L);
        existingBooking.setShowId(1L);

        Booking updatedBooking = new Booking();
        updatedBooking.setUserId(2L);
        updatedBooking.setShowId(2L);

        when(bookingRepository.findById(1L)).thenReturn(Optional.of(existingBooking));
        when(bookingRepository.save(any(Booking.class))).thenReturn(updatedBooking);

        Booking result = bookingService.updateBooking(1L, updatedBooking);

        assertNotNull(result);
        assertEquals(2L, result.getUserId());
    }

    @Test
    void testDeleteBooking() {
        Booking booking = new Booking();
        booking.setId(1L);
        booking.setUserId(1L);
        booking.setShowId(1L);

        when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));
        doNothing().when(bookingRepository).deleteById(1L);

        bookingService.deleteBooking(1L);

        verify(bookingRepository, times(1)).deleteById(1L);
    }
}
