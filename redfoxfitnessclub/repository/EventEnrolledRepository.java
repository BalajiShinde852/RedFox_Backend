package com.recruitapp.redfoxfitnessclub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recruitapp.redfoxfitnessclub.entity.EventEnrolled;

@Repository
public interface EventEnrolledRepository extends JpaRepository<EventEnrolled, Integer> {

    EventEnrolled findByEventIdAndUsername(int id, String username);
    
    List<EventEnrolled> findByEventId(int eventId);
}