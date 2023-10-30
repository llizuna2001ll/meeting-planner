package com.zenika.meetingplanner.entities;

import com.zenika.meetingplanner.DTOs.ReunionRequest;
import com.zenika.meetingplanner.DTOs.ReunionResponse;
import com.zenika.meetingplanner.enums.ReunionType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Reunion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reunionId;

    @Column(nullable = false)
    private LocalDateTime time;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReunionType type;

    @Column(nullable = false)
    private int guestsNum;

    public static Reunion toReunion(ReunionRequest reunionRequest){
       return Reunion.builder()
                .guestsNum(reunionRequest.getGuestsNum())
                .type(reunionRequest.getType())
                .time(LocalDateTime.parse(reunionRequest.getTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .build();
    }
}
