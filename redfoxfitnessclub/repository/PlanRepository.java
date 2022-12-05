package com.recruitapp.redfoxfitnessclub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recruitapp.redfoxfitnessclub.entity.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {

    Plan findByType(String type);

}