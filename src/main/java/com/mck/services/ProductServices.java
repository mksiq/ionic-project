package com.mck.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mck.domain.Product;
import com.mck.repositories.ProductRepository;
import com.mck.services.exceptions.ObjectNotFoundException;

@Service
public class ProductServices {

	@Autowired
	private ProductRepository repo;
	
	public Product find(Integer id) {
		Optional<Product> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found. Id : " + id + ", Type: " + Product.class.getName()));
	}
	
	


	
}
