package com.mck.resources;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mck.domain.Category;
import com.mck.services.CategoryServices;


@RestController
@RequestMapping(value="/categories")
public class CategoryResource {
	@Autowired
	private CategoryServices service;
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Category obj = service.find(id);	
		return ResponseEntity.ok().body(obj);
	}
}
