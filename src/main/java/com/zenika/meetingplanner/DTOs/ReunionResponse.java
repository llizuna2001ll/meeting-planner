package com.zenika.meetingplanner.DTOs;

import com.zenika.meetingplanner.entities.Reunion;
import com.zenika.meetingplanner.enums.ReunionType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReunionResponse {

    private Long reunionId;
    private ReunionType type;
    private int guestsNum;
    private LocalDateTime time;

    public static ReunionResponse toReunionResponse(Reunion reunionResponse){
        return ReunionResponse.builder()
                .guestsNum(reunionResponse.getGuestsNum())
                .type(reunionResponse.getType())
                .time(reunionResponse.getTime())
                .build();
    }
}
