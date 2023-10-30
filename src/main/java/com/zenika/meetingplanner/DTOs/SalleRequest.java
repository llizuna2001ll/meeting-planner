package com.zenika.meetingplanner.DTOs;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;


@Data
@Builder
public class SalleRequest {

    @NotEmpty(message = "Chose a name for the room")
    private String name;
    private int maxCapacity;
    private boolean hasScreen;
    private boolean hasWhiteboard;
    private boolean hasWebcam;
    private boolean hasPieuvre;
}