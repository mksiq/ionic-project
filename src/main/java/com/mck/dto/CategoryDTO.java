package com.mck.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.mck.domain.Category;

import com.mck.services.validation.ClientUpdate;

@ClientUpdate
public class CategoryDTO  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	
	@NotEmpty(message="Must be filled")
	@Length(min=5,max=80,message="Must have between 5 and 80 characters")
	private String name;
	public CategoryDTO() {
		super();
	}
	
	public CategoryDTO(Category cat) {
		this.id = cat.getId();
		this.name = cat.getName();
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
	
	
}
