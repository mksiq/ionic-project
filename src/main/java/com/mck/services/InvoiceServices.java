package com.mck.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mck.domain.Invoice;
import com.mck.repositories.InvoiceRepository;
import com.mck.services.exceptions.ObjectNotFoundException;

@Service
public class InvoiceServices {

	@Autowired
	private InvoiceRepository repo;
	
	public Invoice find(Integer id) {
		Optional<Invoice> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found. Id : " + id + ", Type: " + Invoice.class.getName()));
	}
	
	


	
}
