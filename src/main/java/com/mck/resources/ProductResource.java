package com.mck.resources;





import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mck.domain.Category;
import com.mck.domain.Invoice;
import com.mck.domain.Product;
import com.mck.dto.CategoryDTO;
import com.mck.dto.ProductDTO;
import com.mck.resources.util.URLutil;
import com.mck.services.InvoiceServices;
import com.mck.services.ProductServices;


@RestController
@RequestMapping(value="/products")
public class ProductResource {
	@Autowired
	private ProductServices service;
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Product> find(@PathVariable Integer id) {
		Product obj = service.find(id);	
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<ProductDTO>> findPage(
			@RequestParam(value="name", defaultValue = "") String name,
			@RequestParam(value="categories", defaultValue = "") String categories,
			@RequestParam(value="page", defaultValue = "0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue = "24")Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue = "name")String orderBy,
			@RequestParam(value="direction", defaultValue = "ASC")String direction) {
		String decodadeName = URLutil.decodeParam(name);
		List<Integer> ids = URLutil.decodeIntList(categories);
		Page<Product> list = service.search(decodadeName, ids, page, linesPerPage, orderBy, direction);	
		
		Page<ProductDTO> listDTO = list.map(obj -> new ProductDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}
}
