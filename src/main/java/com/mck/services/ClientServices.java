package com.mck.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mck.domain.Client;
import com.mck.repositories.ClientRepository;
import com.mck.services.exceptions.ObjectNotFoundException;

@Service
public class ClientServices {

	@Autowired
	private ClientRepository repo;
	
	public Client find(Integer id) {
		Optional<Client> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found. Id : " + id + ", Type: " + Client.class.getName()));
	}
	
	


	
}
