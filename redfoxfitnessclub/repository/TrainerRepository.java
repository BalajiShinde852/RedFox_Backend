package com.recruitapp.redfoxfitnessclub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recruitapp.redfoxfitnessclub.entity.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer> {
    Trainer findByName(String name);
}
