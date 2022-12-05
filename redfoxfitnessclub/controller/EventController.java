package com.recruitapp.redfoxfitnessclub.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recruitapp.redfoxfitnessclub.entity.Event;
import com.recruitapp.redfoxfitnessclub.exception.EventAlreadyExistsException;
import com.recruitapp.redfoxfitnessclub.exception.EventDateInvalidException;
import com.recruitapp.redfoxfitnessclub.exception.EventInvalidException;
import com.recruitapp.redfoxfitnessclub.exception.TrainerDoesNotExistsException;
import com.recruitapp.redfoxfitnessclub.service.EventService;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/addEvent")
    public ResponseEntity<?> addEvent(@Valid @RequestBody Event event) {
	try {
	    return new ResponseEntity<>(eventService.addEvent(event), HttpStatus.OK);
	} catch (EventDateInvalidException e) {
	    return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
	} catch (EventAlreadyExistsException e) {
	    return new ResponseEntity<>("Event Already Exists for the given Time", HttpStatus.BAD_REQUEST);
	} catch (TrainerDoesNotExistsException e) {
	    return new ResponseEntity<>("Trainer Name is Invalid/ Trainer Doesn't Exists", HttpStatus.BAD_REQUEST);
	} catch (EventInvalidException e) {
	    return new ResponseEntity<>("Event (Group Fitness Classes) can be Zumba,Yoga and cardio only ",
		    HttpStatus.BAD_REQUEST);
	}
    }
}