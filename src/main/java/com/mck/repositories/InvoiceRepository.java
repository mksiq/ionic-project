package com.mck.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mck.domain.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
	
}