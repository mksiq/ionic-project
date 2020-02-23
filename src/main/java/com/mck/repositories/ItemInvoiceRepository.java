package com.mck.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mck.domain.ItemInvoice;

@Repository
public interface ItemInvoiceRepository extends JpaRepository<ItemInvoice, Integer> {
	
}