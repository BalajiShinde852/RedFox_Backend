package com.recruitapp.redfoxfitnessclub.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.recruitapp.redfoxfitnessclub.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    Event findByScheduledDateTime(LocalDateTime dateTime);

    @Modifying
    @Query("UPDATE Event e set e.remainingMembers= ?1 where e.id=?2")
    void updateRemainingMembers(int remain,int id);

    @Query(value = "select * from event where DATE(scheduled_date_time)= ?1 ", nativeQuery = true)
    List<Event> getEventByDate(LocalDate date);
}