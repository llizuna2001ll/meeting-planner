package com.zenika.meetingplanner;

import com.zenika.meetingplanner.DTOs.ReunionRequest;
import com.zenika.meetingplanner.DTOs.SalleResponse;
import com.zenika.meetingplanner.entities.Reservation;
import com.zenika.meetingplanner.entities.Reunion;
import com.zenika.meetingplanner.entities.Salle;
import com.zenika.meetingplanner.enums.ReunionType;
import com.zenika.meetingplanner.repositories.ReservationRepository;
import com.zenika.meetingplanner.repositories.ReunionRepository;
import com.zenika.meetingplanner.repositories.SalleRepository;
import com.zenika.meetingplanner.services.SalleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class MeetingPlannerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeetingPlannerApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(SalleRepository salleRepository, ReunionRepository reunionRepository, SalleService salleService,
                                        ReservationRepository reservationRepository) {
        return args -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            String[] names =  {"E1001", "E1002", "E1003", "E1004", "E2001", "E2002", "E2003", "E2004", "E3001", "E3002", "E3003", "E3004"};
            int[] maxCpacity = {23, 10, 8, 4, 4, 15, 7, 9, 13, 8, 9, 4};
            boolean[] pieuvre = {false, false, true, false, false, false, false, false, true, false, true, false};
            boolean[] screen = {false, true, false, false, false, true, false, false, true, false, true, false};
            boolean[] webcam = {false, false, false, false, false, true, false, false, true, false, false, false};
            boolean[]  whiteboard = {false, false, false, true, false, false, false, true, false, false, false, false};

            for(int i = 0; i <12 ; i++) {
                Salle salle = Salle.builder()
                        .name(names[i])
                        .maxCapacity(maxCpacity[i])
                        .hasPieuvre(pieuvre[i])
                        .hasScreen(screen[i])
                        .hasWebcam(webcam[i])
                        .hasWhiteboard(whiteboard[i])
                        .build();
                salleRepository.save(salle);
            }

            ReunionRequest reunion = ReunionRequest.builder()
                    .type(ReunionType.VC)
                    .guestsNum(3)
                    .time("2023-10-27 18:00")
                    .build();
            System.out.println(salleService.recommendSallesByReunion(reunion));
            reunionRepository.save(Reunion.toReunion(reunion));
            Reservation reservation = Reservation.builder()
                    .startTime(LocalDateTime.parse("2023-10-27 18:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                    .endTime(LocalDateTime.parse("2023-10-27 20:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                    .salleId(9L)
                    .reunionId(1L)
                    .build();
            reservationRepository.save(reservation);

        };

    }
}