package com.recruitapp.redfoxfitnessclub.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recruitapp.redfoxfitnessclub.entity.User;
import com.recruitapp.redfoxfitnessclub.exception.UsernameAlreadyExistsException;
import com.recruitapp.redfoxfitnessclub.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@Valid @RequestBody User user) {
	try {
	    return new ResponseEntity<>(userService.addUser(user), HttpStatus.OK);
	} catch (UsernameAlreadyExistsException e) {
	    return new ResponseEntity<>("Username Already Exists", HttpStatus.OK);
	}
    }
}