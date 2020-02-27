package com.mck.resources;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mck.domain.Invoice;
import com.mck.services.InvoiceServices;


@RestController
@RequestMapping(value="/invoices")
public class InvoiceResource {
	@Autowired
	private InvoiceServices service;
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Invoice> find(@PathVariable Integer id) {
		Invoice obj = service.find(id);	
		return ResponseEntity.ok().body(obj);
	}
}
