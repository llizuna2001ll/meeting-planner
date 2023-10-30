package com.zenika.meetingplanner.services;

import com.zenika.meetingplanner.DTOs.ReunionRequest;
import com.zenika.meetingplanner.DTOs.SalleRequest;
import com.zenika.meetingplanner.DTOs.SalleResponse;
import com.zenika.meetingplanner.entities.Salle;
import com.zenika.meetingplanner.enums.ReunionType;
import com.zenika.meetingplanner.repositories.SalleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SalleServiceImplTest {

    @Mock private SalleRepository salleRepository;
    @Mock private ReservationService reservationService;
    private SalleService underTest;

    @BeforeEach
    void setUp() {
        underTest = new SalleServiceImpl(salleRepository, reservationService);
    }

    @Test
    void testRecommendSallesByReunion() {

        ReunionRequest reunion = ReunionRequest.builder()
                .type(ReunionType.VC)
                .guestsNum(3)
                .time("2023-10-27 18:00")
                .build();

        Salle salle = Salle.builder()
                .salleId(1L)
                .name("E3001")
                .maxCapacity(13)
                .hasScreen(true)
                .hasWebcam(true)
                .hasWhiteboard(false)
                .hasPieuvre(true)
                .build();
        when(salleRepository.findByMaxCapacityGreaterThan(any(Integer.class))).thenReturn(Collections.singletonList(salle));

        List<SalleResponse> salleResponses  = underTest.recommendSallesByReunion(reunion);

        assertNotNull(salleResponses);


        verify(salleRepository, times(1)).findByMaxCapacityGreaterThan(any(Integer.class));
        verify(reservationService, times(1)).findReservation(any(LocalDateTime.class), any(LocalDateTime.class),any(Long.class));
    }

    @Test
    public void testAddSalle() {

        SalleRequest salleRequest = SalleRequest.builder()
                .name("E1001")
                .maxCapacity(23)
                .hasPieuvre(false)
                .hasScreen(false)
                .hasWebcam(false)
                .hasWhiteboard(false)
                .build();

        Salle salleEntity = Salle.toSalle(salleRequest);
        salleEntity.setSalleId(1L);

        when(salleRepository.save(any(Salle.class))).thenReturn(salleEntity);

        SalleResponse salleResponse = underTest.addSalle(salleRequest);


        assertNotNull(salleResponse);
        assertEquals(1L, salleResponse.getSalleId().longValue());
        assertEquals("E1001", salleResponse.getName());
        assertEquals(23, salleResponse.getMaxCapacity());
        assertFalse(salleResponse.isHasPieuvre());
        assertFalse(salleResponse.isHasScreen());
        assertFalse(salleResponse.isHasWebcam());
        assertFalse(salleResponse.isHasWhiteboard());

        verify(salleRepository, times(1)).save(any(Salle.class));
    }


    @Test
    void getAllSalles() {
        underTest.getAllSalles();
        verify(salleRepository).findAll();
    }
}