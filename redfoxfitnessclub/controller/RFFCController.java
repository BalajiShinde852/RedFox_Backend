package com.recruitapp.redfoxfitnessclub.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recruitapp.redfoxfitnessclub.entity.Event;
import com.recruitapp.redfoxfitnessclub.exception.EventInvalidException;
import com.recruitapp.redfoxfitnessclub.service.EventEnrolledService;
import com.recruitapp.redfoxfitnessclub.service.EventService;

@RestController
@RequestMapping("/rffc")
public class RFFCController {

    @Autowired
    private EventService eventService;
    
    @Autowired
    private EventEnrolledService eventEnrolledService;

    @GetMapping("/getSchedule/{localDate}")
    public ResponseEntity<?> getSchedulesForDate(
	    @PathVariable("localDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate localDate) {

	try {
	    List<Event> events = eventService.getEventByDate(localDate);
	    return new ResponseEntity<>(events, HttpStatus.OK);
	} catch (Exception e) {
	    return new ResponseEntity<>("No Schedules Found", HttpStatus.OK);
	}

    }
    
    @GetMapping("/getUsersEnrolled/{eventId}")
    public ResponseEntity<?> getUsersEnrolledForEvent(int eventId) {
	
	try {
	    return new ResponseEntity<>(eventEnrolledService.getUsersEnrolledForEvent(eventId),HttpStatus.OK);
	} catch (EventInvalidException e) {
	    return new ResponseEntity<>("Invalid Event Id",HttpStatus.OK);
	}
    }
}
