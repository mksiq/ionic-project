package com.mck.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mck.domain.Category;
import com.mck.domain.Product;
import com.mck.repositories.CategoryRepository;
import com.mck.repositories.ProductRepository;
import com.mck.services.exceptions.ObjectNotFoundException;

@Service
public class ProductServices {

	@Autowired
	private ProductRepository repo;
	@Autowired
	CategoryRepository categoryRepository;
	

	public Product find(Integer id) {
		Optional<Product> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found. Id : " + id + ", Type: " + Product.class.getName()));
	}
	
	public Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Category> categories = categoryRepository.findAllById(ids);
		
		
		return repo.findDistinctByNameContainingAndCategoriesIn(name, categories, pageRequest);
	}
// List<Category> categories = categoryRepository.findAllById(ids);

	
}
