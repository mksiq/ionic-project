package com.mck.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
	S3Service s3Service;
	
	@Autowired
	private ClientRepository repo;
	
	@Autowired
	private AddressRepository addRepo;
	
	@Autowired
	private BCryptPasswordEncoder bp;
	
	@Autowired
	private ImageService imageService;
	
	@Value("${img.prefix.client.profile}")
	private String prefix;
	
	@Value("${img.profile.size}")
	private Integer size = 200;
	
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
	
	public Client findByEmail(String email) {
		UserSS user = UserService.authenticated();
		if(user == null || user.hasRole(UserProfile.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Access denied");
		}
		
		Client obj = find(user.getId());
		
		if(obj == null) {
			throw new ObjectNotFoundException("Object not found. Id " + user.getId() + ", type: " + Client.class.getName());
		}
		return obj;
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

	public URI uploadProfilePicture(MultipartFile multipartFile) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Access denied");
		}
		
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);        
//		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);
		String fileName = prefix + user.getId() +".jpg";
		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
//		URI uri = s3Service.uploadFile(multipartFile);
//		Client cli = find(user.getId());
//		cli.setImageUrl(uri.toString());
//		repo.save(cli);
//		return s3Service.uploadFile(multipartFile);
	}
	
	
	
}
