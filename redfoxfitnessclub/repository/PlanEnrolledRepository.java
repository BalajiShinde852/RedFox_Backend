package com.recruitapp.redfoxfitnessclub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recruitapp.redfoxfitnessclub.entity.PlanEnrolled;

@Repository
public interface PlanEnrolledRepository extends JpaRepository<PlanEnrolled, Integer> {

    PlanEnrolled findByUsername(String username);

}