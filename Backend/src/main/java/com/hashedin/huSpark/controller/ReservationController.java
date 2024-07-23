package com.hashedin.huSpark.controller;


import com.hashedin.huSpark.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@PreAuthorize("hasRole('MANAGER')")
@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/confirm/{requestId}")
    public ResponseEntity<String> confirmRequest(@PathVariable Long requestId) {
        String message = reservationService.confirmRequest(requestId);
        return ResponseEntity.ok(message);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/reject/{requestId}")
    public ResponseEntity<String> rejectRequest(@PathVariable Long requestId) {
        String message = reservationService.rejectRequest(requestId);
        return ResponseEntity.ok(message);
    }
}
