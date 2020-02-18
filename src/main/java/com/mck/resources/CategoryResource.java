package com.mck.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mck.domain.Category;


@RestController
@RequestMapping(value="/categories")
public class CategoryResource {
	@RequestMapping(method=RequestMethod.GET)
	public List<Category> list() {
		
		Category cat1 = new Category(1, "IT");
		Category cat2 = new Category(2, "Office");
		
		List<Category> catList = new ArrayList<>();
		catList.add(cat1);
		catList.add(cat2);
		
		
		return catList;
	}
}
