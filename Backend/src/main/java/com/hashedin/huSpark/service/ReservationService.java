package com.hashedin.huSpark.service;


import com.hashedin.huSpark.congfigration.ResourceNotFoundException;
import com.hashedin.huSpark.model.Booking;
import com.hashedin.huSpark.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    BookingRepository bookingRepository;

    public String confirmRequest(Long requestId) {
        Booking request = bookingRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation request not found"));

        if(request.getNumber_of_Guest()>request.getRestaurant().getCapacity()){
            return "Cannot have more guest than capacity";
        }


        if ("pending".equals(request.getStatus())) {
            request.setStatus("confirmed");
            bookingRepository.save(request);
            return "Booking confirmed";
        } else {
            throw new IllegalStateException("Only pending requests can be confirmed");
        }
    }

    public String rejectRequest(Long requestId) {
        Booking request = bookingRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation request not found"));

        if ("pending".equals(request.getStatus())) {
            request.setStatus("rejected");
            bookingRepository.save(request);
            return "Booking rejected";
        } else {
            throw new IllegalStateException("Only pending requests can be rejected");
        }
    }
}

