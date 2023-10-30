package com.zenika.meetingplanner.DTOs;

import com.zenika.meetingplanner.entities.Reunion;
import com.zenika.meetingplanner.enums.ReunionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReunionRequest {

    private ReunionType type;

    @NotNull(message = "Specify guests number")
    private Integer guestsNum;

    @NotBlank(message = "Specify reunion's type")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}", message = "Time format must be yyyy-MM-dd HH:mm")
    private String time;

}