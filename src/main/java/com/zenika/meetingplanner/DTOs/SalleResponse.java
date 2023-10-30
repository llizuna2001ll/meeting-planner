package com.zenika.meetingplanner.DTOs;

import com.zenika.meetingplanner.entities.Salle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@AllArgsConstructor
@Builder
@Data
public class SalleResponse {
    private Long salleId;
    private String name;
    private int maxCapacity;
    private boolean hasScreen;
    private boolean hasWhiteboard;
    private boolean hasWebcam;
    private boolean hasPieuvre;

    public static SalleResponse toSalleResponse(Salle salle){
        return SalleResponse.builder()
                .salleId(salle.getSalleId())
                .name(salle.getName())
                .hasScreen(salle.isHasScreen())
                .hasWebcam(salle.isHasWebcam())
                .hasWhiteboard(salle.isHasWhiteboard())
                .hasPieuvre(salle.isHasPieuvre())
                .maxCapacity(salle.getMaxCapacity())
                .build();
    }
}