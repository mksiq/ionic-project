package com.mck.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mck.domain.Category;
import com.mck.repositories.CategoryRepository;
import com.mck.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryServices {

	@Autowired
	private CategoryRepository repo;
	
	public Category find(Integer id) {
		Optional<Category> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found. Id : " + id + ", Type: " + Category.class.getName()));
	}
	
	
	// return obj.orElseThrow(() -> new ObjectNotFoundException(
	//					"Object not found. Id : " + id + ", Type: " + Category.class.getName()));
	

	
}
