package com.zenika.meetingplanner.repositories;

import com.zenika.meetingplanner.entities.Reunion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReunionRepository extends JpaRepository<Reunion, Long> {

}