package com.mck.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mck.domain.Category;
import com.mck.dto.CategoryDTO;
import com.mck.repositories.CategoryRepository;
import com.mck.services.exceptions.DataIntegrityException;
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
	
	public Category insert(Category obj) {
		obj.setId(null);
		return repo.save(obj);
		
		
	}

	public Category update(Category obj) {
		//check if there is this id
		find(obj.getId());
		return repo.save(obj);
	}

	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
			
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Not allowed to delete a Category with Products binded");
		}
	}
	// repo.deleteById(id);

	public List<Category> findAll() {
		
		return repo.findAll();
	}

	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Category fromDTO(CategoryDTO objDTO) {
		return new Category(objDTO.getId(), objDTO.getName());
	}
	
}
