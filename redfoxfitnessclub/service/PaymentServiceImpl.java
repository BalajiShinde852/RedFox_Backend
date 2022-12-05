package com.recruitapp.redfoxfitnessclub.service;

import java.time.LocalDate;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recruitapp.redfoxfitnessclub.entity.Event;
import com.recruitapp.redfoxfitnessclub.entity.EventEnrolled;
import com.recruitapp.redfoxfitnessclub.entity.Payment;
import com.recruitapp.redfoxfitnessclub.repository.EventEnrolledRepository;
import com.recruitapp.redfoxfitnessclub.repository.EventRepository;
import com.recruitapp.redfoxfitnessclub.repository.PaymentRepository;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private EventEnrolledRepository eventEnrolledRepository;

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Payment pay(Payment payment) {
	if (payment.getPaidFor().toLowerCase().contains("event")) {
	    if (payment.getAmount() == 5) {
		EventEnrolled eventEnrolled = new EventEnrolled();
		eventEnrolled.setUsername(payment.getUsername().toLowerCase());
		eventEnrolled.setEventId(Integer.parseInt(payment.getPaidFor().split(":")[1].trim()));
		eventEnrolled.setEnrolledOn(LocalDate.now());
		eventEnrolled.setPaidForEvent(true);

		Optional<Event> checkEvent = eventRepository.findById(eventEnrolled.getEventId());
		int remain = checkEvent.get().getRemainingMembers();
		remain--;
		eventRepository.updateRemainingMembers(remain, checkEvent.get().getId());
		eventEnrolledRepository.save(eventEnrolled);
	    }

	}
	return paymentRepository.save(payment);
    }

}
