package com.mck.resources;



import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mck.domain.Category;
import com.mck.domain.Invoice;
import com.mck.dto.CategoryDTO;
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
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Invoice obj){
		
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}


