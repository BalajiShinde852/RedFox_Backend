package com.recruitapp.redfoxfitnessclub.service;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.recruitapp.redfoxfitnessclub.entity.Plan;
import com.recruitapp.redfoxfitnessclub.exception.PlanAlreadyExistsException;
import com.recruitapp.redfoxfitnessclub.exception.PlanDoesNotExistsException;
import com.recruitapp.redfoxfitnessclub.repository.PlanRepository;

@Service
@Transactional
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanRepository planRepository;

    private List<String> planTypes = Arrays.asList("basic", "fox");

    @Override
    public Plan addPlan(Plan plan) throws PlanAlreadyExistsException, PlanDoesNotExistsException {

	Plan checkPlan = planRepository.findByType(plan.getType());
	if (!ObjectUtils.isEmpty(checkPlan)) {
	    throw new PlanAlreadyExistsException();
	}
	if (!planTypes.contains(plan.getType())) {
	    throw new PlanDoesNotExistsException();
	}
	return planRepository.save(plan);
    }

}
