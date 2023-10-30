package com.zenika.meetingplanner.services;

import com.zenika.meetingplanner.DTOs.ReunionRequest;
import com.zenika.meetingplanner.DTOs.ReunionResponse;

import java.util.List;

public interface ReunionService {

    List<ReunionResponse> getAllReunions();
    ReunionResponse getReunionById(Long reunionId);
    ReunionResponse addReunion(ReunionRequest reunionRequest);
}
