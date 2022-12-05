package com.recruitapp.redfoxfitnessclub.service;

import com.recruitapp.redfoxfitnessclub.entity.Plan;
import com.recruitapp.redfoxfitnessclub.exception.PlanAlreadyExistsException;
import com.recruitapp.redfoxfitnessclub.exception.PlanDoesNotExistsException;

public interface PlanService {
    Plan addPlan(Plan plan) throws PlanAlreadyExistsException, PlanDoesNotExistsException;
}
