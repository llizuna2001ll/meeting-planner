package com.zenika.meetingplanner.DTOs;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationRequest {
    private Long salleId;
    private Long reunionId;
    private String startTime;
    private String endTime;
}
