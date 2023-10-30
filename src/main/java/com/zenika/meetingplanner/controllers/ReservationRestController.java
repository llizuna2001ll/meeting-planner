package com.zenika.meetingplanner.controllers;

import com.zenika.meetingplanner.DTOs.ReservationRequest;
import com.zenika.meetingplanner.entities.Reservation;
import com.zenika.meetingplanner.services.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/reservations")
@Validated
public class ReservationRestController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationRestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/addReservation")
    public ResponseEntity<Reservation> addReservation(@Valid @RequestBody ReservationRequest reservationRequest) {
        Reservation addedReservation = reservationService.addReservation(reservationRequest);
        return new ResponseEntity<>(addedReservation, HttpStatus.CREATED);
    }
}
