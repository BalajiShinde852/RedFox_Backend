package com.recruitapp.redfoxfitnessclub.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recruitapp.redfoxfitnessclub.entity.Plan;
import com.recruitapp.redfoxfitnessclub.exception.PlanAlreadyExistsException;
import com.recruitapp.redfoxfitnessclub.exception.PlanDoesNotExistsException;
import com.recruitapp.redfoxfitnessclub.service.PlanService;

@RestController
@RequestMapping("/plan")
public class PlanController {

    @Autowired
    private PlanService planService;

    @PostMapping("/addPlan")
    public ResponseEntity<?> addPlan(@Valid @RequestBody Plan plan) {
	try {
	    return new ResponseEntity<>(planService.addPlan(plan), HttpStatus.OK);
	} catch (PlanAlreadyExistsException e) {
	    return new ResponseEntity<>("Plan Already Exists", HttpStatus.OK);
	} catch (PlanDoesNotExistsException e) {
	    return new ResponseEntity<>("Plan Does Not Exists", HttpStatus.OK);
	}
    }
}