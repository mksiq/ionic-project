package com.mck.services;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mck.domain.Address;

import com.mck.domain.City;
import com.mck.domain.Client;
import com.mck.domain.enums.ClientType;
import com.mck.domain.enums.UserProfile;
import com.mck.dto.ClientDTO;
import com.mck.dto.ClientNewDTO;
import com.mck.repositories.AddressRepository;
import com.mck.repositories.ClientRepository;
import com.mck.security.UserSS;
import com.mck.services.exceptions.AuthorizationException;
import com.mck.services.exceptions.DataIntegrityException;
import com.mck.services.exceptions.ObjectNotFoundException;

@Service
public class ClientServices {

	@Autowired
	private ClientRepository repo;
	
	@Autowired
	private AddressRepository addRepo;
	
	@Autowired
	private BCryptPasswordEncoder bp;
	
	public Client find(Integer id) {
		
		UserSS user = UserService.authenticated();
		if(user == null || !user.hasRole(UserProfile.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Access denied");
		}
		Optional<Client> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found. Id : " + id + ", Type: " + Client.class.getName()));
	}
	
	@Transactional
	public Client insert(Client obj) {
		obj.setId(null);
		obj = repo.save(obj);
		addRepo.saveAll(obj.getAdress());
		return obj;
				
	}

	public Client update(Client obj) {
		//check if there is this id
		//find(obj.getId());
		Client newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	
	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
		
	}


	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
			
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Not allowed to delete a Client with Invoices binded");
		}
	}
	

	public List<Client> findAll() {
		
		return repo.findAll();
	}

	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Client fromDTO(ClientDTO objDto) {
		return new Client(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null, null);
	}
	
	public Client fromDTO(ClientNewDTO objDto) {
		Client cli = new Client(null, objDto.getName(), objDto.getEmail(), objDto.getSinOrBn(), ClientType.toEnum(objDto.getType()), bp.encode(objDto.getPassword()));
		City city = new City(objDto.getCityId(), null, null);
		Address address = new Address(null, objDto.getNumber(), objDto.getStreetName(), objDto.getUnitNumber(), objDto.getPostalCode(), cli, city  );
		cli.getAdress().add(address);
		cli.getPhones().add(objDto.getPhone1());
		
		
		if (objDto.getPhone2() != null) {
			cli.getPhones().add(objDto.getPhone2());
		}
		if (objDto.getPhone3() != null) {
			cli.getPhones().add(objDto.getPhone3());
		}
		return cli;
	}

	
}
