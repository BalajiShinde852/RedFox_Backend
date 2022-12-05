package com.recruitapp.redfoxfitnessclub.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.recruitapp.redfoxfitnessclub.entity.Trainer;
import com.recruitapp.redfoxfitnessclub.exception.TrainerAlreadyExistsException;
import com.recruitapp.redfoxfitnessclub.repository.TrainerRepository;

@Service
@Transactional
public class TrainerServiceImpl implements TrainerService {
    @Autowired
    private TrainerRepository trainerRepository;

    @Override
    public Trainer addTrainer(Trainer trainer) throws TrainerAlreadyExistsException {

	Trainer checkUser = trainerRepository.findByName(trainer.getName());
	if (!ObjectUtils.isEmpty(checkUser)) {
	    throw new TrainerAlreadyExistsException();
	}

	trainer.setName(trainer.getName().toLowerCase());
	return trainerRepository.save(trainer);

    }
}
