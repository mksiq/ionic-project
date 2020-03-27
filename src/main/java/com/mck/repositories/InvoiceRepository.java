package com.mck.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mck.domain.Client;
import com.mck.domain.Invoice;


@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
	
	@Transactional(readOnly=true)
	Page<Invoice> findByClient(Client client, Pageable pageRequest);
}