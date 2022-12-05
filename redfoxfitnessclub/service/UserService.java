package com.recruitapp.redfoxfitnessclub.service;

import com.recruitapp.redfoxfitnessclub.entity.User;
import com.recruitapp.redfoxfitnessclub.exception.UsernameAlreadyExistsException;

public interface UserService {
    User addUser(User user) throws UsernameAlreadyExistsException;
}
