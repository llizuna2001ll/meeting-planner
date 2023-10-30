package com.zenika.meetingplanner.controllers;

import com.zenika.meetingplanner.DTOs.ReunionRequest;
import com.zenika.meetingplanner.DTOs.ReunionResponse;
import com.zenika.meetingplanner.DTOs.SalleRequest;
import com.zenika.meetingplanner.DTOs.SalleResponse;
import com.zenika.meetingplanner.services.ReunionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reunions")
@Validated
public class ReunionRestController {

    private final ReunionService reunionService;

    @Autowired
    public ReunionRestController(ReunionService reunionService) {
        this.reunionService = reunionService;
    }

    @GetMapping
    public ResponseEntity<List<ReunionResponse>> getAllReunions() {
        List<ReunionResponse> reunions = reunionService.getAllReunions();
        return new ResponseEntity<>(reunions, HttpStatus.OK);
    }

    @GetMapping("/{reunionId}")
    public ResponseEntity<ReunionResponse> getReunionById(@PathVariable Long reunionId) {
        ReunionResponse reunion = reunionService.getReunionById(reunionId);
        return new ResponseEntity<>(reunion, HttpStatus.OK);
    }

    @PostMapping("/addReunion")
    public ResponseEntity<ReunionResponse> addSalle(@Valid @RequestBody ReunionRequest reunionRequest) {
        ReunionResponse reunionResponse = reunionService.addReunion(reunionRequest);
        return new ResponseEntity<>(reunionResponse, HttpStatus.OK);
    }
}

