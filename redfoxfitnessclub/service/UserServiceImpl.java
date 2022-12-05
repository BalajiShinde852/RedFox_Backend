package com.recruitapp.redfoxfitnessclub.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.recruitapp.redfoxfitnessclub.entity.User;
import com.recruitapp.redfoxfitnessclub.exception.UsernameAlreadyExistsException;
import com.recruitapp.redfoxfitnessclub.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) throws UsernameAlreadyExistsException {

	User checkUser = userRepository.findByUsername(user.getUsername());
	if (!ObjectUtils.isEmpty(checkUser)) {
	    throw new UsernameAlreadyExistsException();
	}
	user.setUsername(user.getUsername().toLowerCase());
	return userRepository.save(user);
    }

}
