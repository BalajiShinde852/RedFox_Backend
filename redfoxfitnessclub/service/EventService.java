package com.recruitapp.redfoxfitnessclub.service;

import java.time.LocalDate;
import java.util.List;

import com.recruitapp.redfoxfitnessclub.entity.Event;
import com.recruitapp.redfoxfitnessclub.exception.EventAlreadyExistsException;
import com.recruitapp.redfoxfitnessclub.exception.EventDateInvalidException;
import com.recruitapp.redfoxfitnessclub.exception.EventInvalidException;
import com.recruitapp.redfoxfitnessclub.exception.NoSchedulesFoundException;
import com.recruitapp.redfoxfitnessclub.exception.TrainerDoesNotExistsException;

public interface EventService {

    Event addEvent(Event event) throws EventDateInvalidException, EventAlreadyExistsException,
	    TrainerDoesNotExistsException, EventInvalidException;

    List<Event> getEventByDate(LocalDate date) throws NoSchedulesFoundException;
}
