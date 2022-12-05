package com.recruitapp.redfoxfitnessclub.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recruitapp.redfoxfitnessclub.entity.PlanEnrolled;
import com.recruitapp.redfoxfitnessclub.service.PlanEnrolledService;

@RestController
@RequestMapping("/planEnrolled")
public class PlanEnrolledController {

    @Autowired
    private PlanEnrolledService planEnrolledService;

    @PostMapping("/add")
    public ResponseEntity<?> addPlan(@Valid @RequestBody PlanEnrolled planEnrolled) {
	return new ResponseEntity<>(planEnrolledService.enroll(planEnrolled), HttpStatus.OK);
    }
}