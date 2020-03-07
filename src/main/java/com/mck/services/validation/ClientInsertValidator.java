package com.mck.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.mck.domain.Client;
import com.mck.dto.ClientNewDTO;
import com.mck.repositories.ClientRepository;
import com.mck.resources.exceptions.FieldMessage;


public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO>{

	@Autowired
	private ClientRepository repo;
	
	@Override
	public void initialize(ClientInsert ann) {
		
	}
	
	@Override
	public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
//		if(objDto.getType() == null) {
//			list.add(new FieldMessage("type", "Type can't be null."));
//		}
		
//		if(objDto.getType)
		
		// looks for a user with the same email
		Client aux = repo.findByEmail(objDto.getEmail());
		if(aux != null) {
			list.add(new FieldMessage("email", "This is email alredy exists."));
		}
		
		for(FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).
			addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}



}
