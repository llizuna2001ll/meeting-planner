package com.zenika.meetingplanner.entities;

import com.zenika.meetingplanner.DTOs.SalleRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Salle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salleId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int maxCapacity;

    @Column(nullable = false, name = "screen")
    private boolean hasScreen;

    @Column(nullable = false, name = "whiteboard")
    private boolean hasWhiteboard;

    @Column(nullable = false, name = "webcam")
    private boolean hasWebcam;

    @Column(nullable = false, name = "pieuvre")
    private boolean hasPieuvre;

    public static Salle toSalle(SalleRequest salleRequest){
        return Salle.builder()
                .name(salleRequest.getName())
                .hasPieuvre(salleRequest.isHasPieuvre())
                .hasScreen(salleRequest.isHasScreen())
                .hasWebcam(salleRequest.isHasWebcam())
                .hasWhiteboard(salleRequest.isHasWhiteboard())
                .maxCapacity(salleRequest.getMaxCapacity())
                .build();
    }
}
