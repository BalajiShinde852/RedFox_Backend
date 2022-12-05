package com.recruitapp.redfoxfitnessclub.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.recruitapp.redfoxfitnessclub.entity.Event;
import com.recruitapp.redfoxfitnessclub.entity.EventEnrolled;
import com.recruitapp.redfoxfitnessclub.entity.PlanEnrolled;
import com.recruitapp.redfoxfitnessclub.exception.EventAlreadyRegisteredException;
import com.recruitapp.redfoxfitnessclub.exception.EventInvalidException;
import com.recruitapp.redfoxfitnessclub.exception.NoSchedulesFoundException;
import com.recruitapp.redfoxfitnessclub.exception.NoSeatsLeftException;
import com.recruitapp.redfoxfitnessclub.exception.PlanExpiredException;
import com.recruitapp.redfoxfitnessclub.exception.PlanUpgradeException;
import com.recruitapp.redfoxfitnessclub.repository.EventEnrolledRepository;
import com.recruitapp.redfoxfitnessclub.repository.EventRepository;
import com.recruitapp.redfoxfitnessclub.repository.PlanEnrolledRepository;

@Service
@Transactional
public class EventEnrolledServiceImpl implements EventEnrolledService {

    @Autowired
    private EventEnrolledRepository eventEnrolledRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PlanEnrolledRepository planEnrolledRepository;

    @Override
    public EventEnrolled enrollForEvent(EventEnrolled eventEnrolled) throws NoSchedulesFoundException,
	    NoSeatsLeftException, EventAlreadyRegisteredException, PlanUpgradeException, PlanExpiredException {

	Optional<Event> checkEvent = eventRepository.findById(eventEnrolled.getEventId());

	if (checkEvent.isEmpty()) {
	    throw new NoSchedulesFoundException();
	}

	if (checkEvent.get().getRemainingMembers() == 0) {
	    throw new NoSeatsLeftException();
	}

	EventEnrolled checkEventEnrolled = eventEnrolledRepository.findByEventIdAndUsername(eventEnrolled.getEventId(),
		eventEnrolled.getUsername());
	if (!ObjectUtils.isEmpty(checkEventEnrolled)) {
	    throw new EventAlreadyRegisteredException();
	}

	PlanEnrolled planEnrolled = planEnrolledRepository.findByUsername(eventEnrolled.getUsername().toLowerCase());
	// planId=1 for basic and 2 for fox
	if (planEnrolled.getPlanId() != 2 && Boolean.FALSE.equals(eventEnrolled.getPaidForEvent())) {
	    throw new PlanUpgradeException();
	}

	if (planEnrolled.getValidTill().isAfter(LocalDate.from(checkEvent.get().getScheduledDateTime()))) {
	    throw new PlanExpiredException();
	}

	eventEnrolled.setEventName(checkEvent.get().getEventName());
	eventEnrolled.setUsername(eventEnrolled.getUsername().toLowerCase());

	int remain = checkEvent.get().getRemainingMembers();
	remain--;
	eventRepository.updateRemainingMembers(remain,checkEvent.get().getId());
	return eventEnrolledRepository.save(eventEnrolled);

    }

    @Override
    public List<EventEnrolled> getUsersEnrolledForEvent(int eventId) throws EventInvalidException {
	List<EventEnrolled> listt = eventEnrolledRepository.findByEventId(eventId);
	if(listt.size()==0)
	    throw new EventInvalidException();
	return listt;
    }

}
