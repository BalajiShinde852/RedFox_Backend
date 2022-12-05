package com.recruitapp.redfoxfitnessclub.service;

import java.util.List;

import com.recruitapp.redfoxfitnessclub.entity.EventEnrolled;
import com.recruitapp.redfoxfitnessclub.exception.EventAlreadyRegisteredException;
import com.recruitapp.redfoxfitnessclub.exception.EventInvalidException;
import com.recruitapp.redfoxfitnessclub.exception.NoSchedulesFoundException;
import com.recruitapp.redfoxfitnessclub.exception.NoSeatsLeftException;
import com.recruitapp.redfoxfitnessclub.exception.PlanExpiredException;
import com.recruitapp.redfoxfitnessclub.exception.PlanUpgradeException;

public interface EventEnrolledService {

    EventEnrolled enrollForEvent(EventEnrolled eventEnrolled) throws NoSchedulesFoundException, NoSeatsLeftException,
	    EventAlreadyRegisteredException, PlanUpgradeException, PlanExpiredException;
    
    List<EventEnrolled> getUsersEnrolledForEvent(int eventId) throws EventInvalidException;

}
