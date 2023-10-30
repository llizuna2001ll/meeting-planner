package com.zenika.meetingplanner.repositories;

import com.zenika.meetingplanner.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Reservation findByStartTimeAndEndTimeAndSalleId(LocalDateTime startTime, LocalDateTime endTime, Long salleId);
}