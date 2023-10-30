package com.zenika.meetingplanner.controllers;

import com.zenika.meetingplanner.DTOs.ReunionRequest;
import com.zenika.meetingplanner.DTOs.SalleRequest;
import com.zenika.meetingplanner.DTOs.SalleResponse;
import com.zenika.meetingplanner.entities.Salle;
import com.zenika.meetingplanner.enums.ReunionType;
import com.zenika.meetingplanner.services.SalleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SalleRestControllerTest {

    @Mock
    private SalleService salleService;

    @InjectMocks
    private SalleRestController salleRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRecommendSalle() {
        ReunionRequest reunion = ReunionRequest.builder()
                .type(ReunionType.VC)
                .guestsNum(3)
                .time("2023-10-27 18:00")
                .build();
        when(salleService.recommendSallesByReunion(reunion))
                .thenReturn(Collections.singletonList(new SalleResponse(9L,"E3001", 13, true, false, true, true)));


        ResponseEntity<List<SalleResponse>> responseEntity = salleRestController.recommendSalle(reunion);


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, responseEntity.getBody().size());
        verify(salleService, times(1)).recommendSallesByReunion(reunion);
    }

    @Test
    void testAddSalle() {
        SalleRequest salleRequest = SalleRequest.builder()
                .name("E1001")
                .maxCapacity(23)
                .hasPieuvre(false)
                .hasScreen(false)
                .hasWebcam(false)
                .hasWhiteboard(false)
                .build();
        SalleResponse expectedSalleResponse = new SalleResponse(1L,"E1001", 13, false, false, false, false);
        when(salleService.addSalle(salleRequest)).thenReturn(expectedSalleResponse);


        ResponseEntity<SalleResponse> responseEntity = salleRestController.addSalle(salleRequest);


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedSalleResponse, responseEntity.getBody());
        verify(salleService, times(1)).addSalle(salleRequest);
    }

    @Test
    void testGetAllSalles() {
        SalleRequest salleRequest = SalleRequest.builder()
                .name("E1001")
                .maxCapacity(23)
                .hasPieuvre(false)
                .hasScreen(false)
                .hasWebcam(false)
                .hasWhiteboard(false)
                .build();
        SalleResponse expectedSalleResponse = new SalleResponse(1L,"E1001", 13, false, false, false, false);
        when(salleService.addSalle(salleRequest)).thenReturn(expectedSalleResponse);
        List<SalleResponse> expectedSalleResponses = List.of(new SalleResponse(1L, "E1001", 13, false, false, false, false));
        when(salleService.getAllSalles()).thenReturn(expectedSalleResponses);


        ResponseEntity<List<SalleResponse>> responseEntity = salleRestController.getAllSalles();


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedSalleResponses, responseEntity.getBody());
        verify(salleService, times(1)).getAllSalles();
    }
}
