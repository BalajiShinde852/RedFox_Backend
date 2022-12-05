package com.recruitapp.redfoxfitnessclub.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recruitapp.redfoxfitnessclub.entity.Trainer;
import com.recruitapp.redfoxfitnessclub.exception.TrainerAlreadyExistsException;
import com.recruitapp.redfoxfitnessclub.service.TrainerService;

@RestController
@RequestMapping("/trainer")
public class TrainerController {
    @Autowired
    private TrainerService trainerService;

    @PostMapping("/addTrainer")
    public ResponseEntity<?> addTrainer(@Valid @RequestBody Trainer trainer) {
	try {
	    return new ResponseEntity<>(trainerService.addTrainer(trainer), HttpStatus.OK);
	} catch (TrainerAlreadyExistsException e) {
	    return new ResponseEntity<>("Trainer Already Exists", HttpStatus.OK);
	}
    }
}
