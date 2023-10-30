package com.zenika.meetingplanner.services;

import com.zenika.meetingplanner.DTOs.ReunionRequest;
import com.zenika.meetingplanner.DTOs.SalleResponse;
import com.zenika.meetingplanner.DTOs.SalleRequest;

import java.util.List;

public interface SalleService {
    List<SalleResponse> recommendSallesByReunion(ReunionRequest reunionRequest);
    SalleResponse addSalle(SalleRequest salleRequest);
    List<SalleResponse> getAllSalles();
}
