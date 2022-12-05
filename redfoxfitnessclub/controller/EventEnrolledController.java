package com.recruitapp.redfoxfitnessclub.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recruitapp.redfoxfitnessclub.entity.EventEnrolled;
import com.recruitapp.redfoxfitnessclub.exception.EventAlreadyRegisteredException;
import com.recruitapp.redfoxfitnessclub.exception.NoSchedulesFoundException;
import com.recruitapp.redfoxfitnessclub.exception.NoSeatsLeftException;
import com.recruitapp.redfoxfitnessclub.exception.PlanExpiredException;
import com.recruitapp.redfoxfitnessclub.exception.PlanUpgradeException;
import com.recruitapp.redfoxfitnessclub.service.EventEnrolledService;

@RestController
@RequestMapping("/eventEnrolled")
public class EventEnrolledController {

    @Autowired
    private EventEnrolledService eventEnrolledService;

    @PostMapping("/enroll")
    public ResponseEntity<?> enrollForEvent(@Valid @RequestBody EventEnrolled eventEnrolled) {
	try {
	    return new ResponseEntity<>(eventEnrolledService.enrollForEvent(eventEnrolled), HttpStatus.OK);
	} catch (NoSchedulesFoundException e) {
	    return new ResponseEntity<>("No Events Scheduled for given Event Id/ Invalid Event Id",
		    HttpStatus.BAD_REQUEST);
	} catch (NoSeatsLeftException e) {
	    return new ResponseEntity<>("Class is Full/ No Seats Available", HttpStatus.BAD_REQUEST);

	} catch (EventAlreadyRegisteredException e) {
	    return new ResponseEntity<>("You Have Already Registered for the Class/Event", HttpStatus.BAD_REQUEST);

	} catch (PlanUpgradeException e) {
	    return new ResponseEntity<>("Please upgrade your plan to Fox or Pay $5 for each group class",
		    HttpStatus.BAD_REQUEST);

	} catch (PlanExpiredException e) {
	    return new ResponseEntity<>("Your Plan is/will be Expired", HttpStatus.BAD_REQUEST);

	}
    }
}