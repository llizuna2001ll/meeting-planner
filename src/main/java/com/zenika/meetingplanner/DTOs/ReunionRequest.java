package com.zenika.meetingplanner.DTOs;

import com.zenika.meetingplanner.entities.Reunion;
import com.zenika.meetingplanner.enums.ReunionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReunionRequest {

    @NotBlank(message = "Specify conference type")
    private ReunionType type;

    @NotBlank(message = "Specify guests number")
    private int guestsNum;

    @NotBlank(message = "Specify reunion's type")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}", message = "Time format must be yyyy-MM-dd HH:mm")
    private String time;

}