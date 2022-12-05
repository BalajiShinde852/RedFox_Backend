package com.recruitapp.redfoxfitnessclub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recruitapp.redfoxfitnessclub.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}