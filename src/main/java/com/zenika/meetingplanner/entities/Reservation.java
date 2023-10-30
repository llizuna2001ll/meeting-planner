package com.zenika.meetingplanner.entities;

import com.zenika.meetingplanner.DTOs.ReservationRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;
    private Long salleId;
    private Long reunionId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    
    public static Reservation toReservation(ReservationRequest reservationRequest){
        return Reservation.builder()
                .startTime(LocalDateTime.parse(reservationRequest.getStartTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .endTime(LocalDateTime.parse(reservationRequest.getEndTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .salleId(reservationRequest.getSalleId())
                .reunionId(reservationRequest.getReunionId())
                .build();
    }
}
