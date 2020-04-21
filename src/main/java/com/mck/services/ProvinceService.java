package com.mck.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mck.domain.Province;
import com.mck.repositories.ProvinceRepository;

@Service
public class ProvinceService {
	@Autowired
	private ProvinceRepository repo;
	
	public List<Province> findAll(){
		return repo.findAllByOrderByName();
	}
}
