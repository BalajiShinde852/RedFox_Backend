package com.recruitapp.redfoxfitnessclub.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.recruitapp.redfoxfitnessclub.entity.Event;
import com.recruitapp.redfoxfitnessclub.entity.Trainer;
import com.recruitapp.redfoxfitnessclub.exception.EventAlreadyExistsException;
import com.recruitapp.redfoxfitnessclub.exception.EventDateInvalidException;
import com.recruitapp.redfoxfitnessclub.exception.EventInvalidException;
import com.recruitapp.redfoxfitnessclub.exception.NoSchedulesFoundException;
import com.recruitapp.redfoxfitnessclub.exception.TrainerDoesNotExistsException;
import com.recruitapp.redfoxfitnessclub.repository.EventRepository;
import com.recruitapp.redfoxfitnessclub.repository.TrainerRepository;

@Service
@Transactional
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    private List<String> eventNames = Arrays.asList("zumba", "yoga", "cardio");

    @Override
    public Event addEvent(Event event) throws EventDateInvalidException, EventAlreadyExistsException,
	    TrainerDoesNotExistsException, EventInvalidException {

	if (!eventNames.contains(event.getEventName().toLowerCase())) {
	    throw new EventInvalidException();
	}

	LocalDateTime scheduledTime = event.getScheduledDateTime();

	if (scheduledTime.isBefore(LocalDateTime.now(ZoneId.of("America/New_York")))) {
	    throw new EventDateInvalidException("Event Date Can't be Past");
	}

	event.setScheduledDateTime(LocalDateTime.of(scheduledTime.getYear(), scheduledTime.getMonth(),
		scheduledTime.getDayOfMonth(), scheduledTime.get(ChronoField.HOUR_OF_DAY), 0));

	Event checkEvent = eventRepository.findByScheduledDateTime(event.getScheduledDateTime());

	if (!ObjectUtils.isEmpty(checkEvent)) {
	    throw new EventAlreadyExistsException();
	}

	Trainer trainer = trainerRepository.findByName(event.getTrainerName().toLowerCase());

	if (ObjectUtils.isEmpty(trainer)) {
	    throw new TrainerDoesNotExistsException();
	}

	event.setTrainerName(event.getTrainerName().toLowerCase());
	event.setMaxMembers(25);
	event.setRemainingMembers(25);
	event.setStatus("ACTIVE");
	return eventRepository.save(event);

    }

    @Override
    public List<Event> getEventByDate(LocalDate date) throws NoSchedulesFoundException {
	List<Event> events = eventRepository.getEventByDate(date);
	if (events.size() == 0)
	    throw new NoSchedulesFoundException();
	return events;
    }
}