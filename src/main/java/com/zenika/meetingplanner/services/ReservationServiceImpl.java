package com.zenika.meetingplanner.services;

import com.zenika.meetingplanner.DTOs.ReservationRequest;
import com.zenika.meetingplanner.entities.Reservation;
import com.zenika.meetingplanner.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation findReservation(LocalDateTime startTime, LocalDateTime endTime, Long salleId) {
        return reservationRepository.findByStartTimeAndEndTimeAndSalleId(startTime, endTime, salleId);
    }

    @Override
    public Reservation addReservation(ReservationRequest reservationRequest) {

        return reservationRepository.save(Reservation.toReservation(reservationRequest));
    }
}
