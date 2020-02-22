package com.mck.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mck.domain.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	
}