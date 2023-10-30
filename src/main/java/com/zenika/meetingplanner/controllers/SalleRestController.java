package com.zenika.meetingplanner.controllers;

import com.zenika.meetingplanner.DTOs.ReunionRequest;
import com.zenika.meetingplanner.DTOs.ReunionResponse;
import com.zenika.meetingplanner.DTOs.SalleResponse;
import com.zenika.meetingplanner.DTOs.SalleRequest;
import com.zenika.meetingplanner.services.SalleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salles")
@Validated
public class SalleRestController {

    private final SalleService salleService;

    @Autowired
    public SalleRestController(SalleService salleService) {
        this.salleService = salleService;
    }


    @GetMapping("/recommend")
    public ResponseEntity<List<SalleResponse>> recommendSalle(@Valid @RequestBody ReunionRequest reunionRequest) {
        List<SalleResponse> salleResponses = salleService.recommendSallesByReunion(reunionRequest);
        return new ResponseEntity<>(salleResponses, HttpStatus.OK);
    }

    @PostMapping("/addSalle")
    public ResponseEntity<SalleResponse> addSalle(@Valid @RequestBody SalleRequest salleRequest) {
        SalleResponse salleResponse = salleService.addSalle(salleRequest);
        return new ResponseEntity<>(salleResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SalleResponse>> getAllSalles() {
        List<SalleResponse> salleResponses = salleService.getAllSalles();
        return new ResponseEntity<>(salleResponses, HttpStatus.OK);
    }
}
