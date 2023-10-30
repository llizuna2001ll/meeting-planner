package com.zenika.meetingplanner.controllers;

import com.zenika.meetingplanner.DTOs.ReunionResponse;
import com.zenika.meetingplanner.services.ReunionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reunions")
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
}

