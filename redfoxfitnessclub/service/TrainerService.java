package com.recruitapp.redfoxfitnessclub.service;

import com.recruitapp.redfoxfitnessclub.entity.Trainer;
import com.recruitapp.redfoxfitnessclub.exception.TrainerAlreadyExistsException;

public interface TrainerService {
    Trainer addTrainer(Trainer trainer) throws TrainerAlreadyExistsException;
}
