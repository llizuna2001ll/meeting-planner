package com.zenika.meetingplanner.services;

import com.zenika.meetingplanner.DTOs.ReunionRequest;
import com.zenika.meetingplanner.DTOs.ReunionResponse;
import com.zenika.meetingplanner.entities.Reunion;
import com.zenika.meetingplanner.exceptions.WeekendMeetingException;
import com.zenika.meetingplanner.repositories.ReunionRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReunionServiceImpl implements ReunionService {

    private final ReunionRepository reunionRepository;

    public ReunionServiceImpl(ReunionRepository reunionRepository) {
        this.reunionRepository = reunionRepository;
    }

    @Override
    public List<ReunionResponse> getAllReunions() {
        List<ReunionResponse> reunionResponses = new ArrayList<>();
        List<Reunion> reunions = reunionRepository.findAll();
        for (Reunion reunion : reunions) {
            reunionResponses.add(ReunionResponse.toReunionResponse(reunion));
        }
        return reunionResponses;
    }

    @Override
    public ReunionResponse getReunionById(Long reunionId) {
        return ReunionResponse.toReunionResponse(reunionRepository.findById(reunionId).orElseThrow(()
                -> new RuntimeException("Reunion Not found")));
    }

    @Override
    public ReunionResponse addReunion(ReunionRequest reunionRequest) {
        LocalDateTime time = (LocalDateTime.parse(reunionRequest.getTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

        if (time.getDayOfWeek() == DayOfWeek.SUNDAY || time.getDayOfWeek() == DayOfWeek.SATURDAY)
            throw new WeekendMeetingException("Meeting cannot be scheduled on a weekend");

        return ReunionResponse.toReunionResponse(reunionRepository.save(Reunion.toReunion(reunionRequest)));
    }


}
