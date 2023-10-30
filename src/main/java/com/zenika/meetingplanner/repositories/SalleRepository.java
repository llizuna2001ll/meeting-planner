package com.zenika.meetingplanner.repositories;

import com.zenika.meetingplanner.entities.Salle;
import com.zenika.meetingplanner.enums.Equipment;
import org.apache.coyote.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SalleRepository extends JpaRepository<Salle, Long> {

    boolean existsByName(String name);
    List<Salle> findByMaxCapacityGreaterThan(int maxCapacity);

}
