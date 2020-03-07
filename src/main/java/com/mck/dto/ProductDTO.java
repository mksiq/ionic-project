package com.mck.dto;

import java.io.Serializable;

import com.mck.domain.Product;

public class ProductDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private double price;
	public ProductDTO() {
		super();
	}
	
	public ProductDTO(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.price = product.getPrice();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	
}
