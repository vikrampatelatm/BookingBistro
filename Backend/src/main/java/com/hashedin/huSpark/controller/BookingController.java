package com.hashedin.huSpark.controller;

import com.hashedin.huSpark.dao.BookingDTO;
import com.hashedin.huSpark.dao.BookingResponseDTO;
import com.hashedin.huSpark.model.Booking;
import com.hashedin.huSpark.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping("/create")
    public ResponseEntity<Booking> createBooking(@RequestBody BookingDTO bookingDTO) {
        Booking savedBooking = bookingService.createBooking(bookingDTO);
        return ResponseEntity.ok(savedBooking );
    }

    @GetMapping("/All")
    public ResponseEntity<List<BookingResponseDTO>> getAllBookings() {
        List<BookingResponseDTO> savedBooking = bookingService.getAllBooking();
        return ResponseEntity.ok(savedBooking);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<BookingResponseDTO>> getBookingsById(@PathVariable Long id) {
        List<BookingResponseDTO> savedBooking = bookingService.getBookingByUserId(id);
        return ResponseEntity.ok(savedBooking);
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long id) {
        System.out.println("in cancel controller");
        bookingService.cancelBooking(id);
        return ResponseEntity.ok().build();
    }

}
