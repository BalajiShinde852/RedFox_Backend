package com.recruitapp.redfoxfitnessclub.service;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.recruitapp.redfoxfitnessclub.entity.PlanEnrolled;
import com.recruitapp.redfoxfitnessclub.entity.User;
import com.recruitapp.redfoxfitnessclub.repository.PlanEnrolledRepository;
import com.recruitapp.redfoxfitnessclub.repository.PlanRepository;
import com.recruitapp.redfoxfitnessclub.repository.UserRepository;

@Service
@Transactional
public class PlanEnrolledServiceImpl implements PlanEnrolledService {

    @Autowired
    private PlanEnrolledRepository planEnrolledRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlanRepository planRepository;

    @Override
    public PlanEnrolled enroll(PlanEnrolled planEnrolled) {

	User checkUser = userRepository.findByUsername(planEnrolled.getUsername());

	if (!ObjectUtils.isEmpty(checkUser)) {

	    if (planRepository.findById(planEnrolled.getPlanId()).isPresent()) {

		planEnrolled.setValidTill(LocalDate.now().plusMonths(1));

		return planEnrolledRepository.save(planEnrolled);
	    }
	}

	return null;
    }

}
