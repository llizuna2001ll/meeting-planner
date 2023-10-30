package com.zenika.meetingplanner.services;

import com.zenika.meetingplanner.DTOs.ReservationRequest;
import com.zenika.meetingplanner.entities.Reservation;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {
    Reservation findReservation(LocalDateTime startTime, LocalDateTime endTime, Long salleId);
    Reservation addReservation(ReservationRequest reservationRequest);
}
