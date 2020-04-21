package com.mck.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mck.domain.City;
import com.mck.repositories.CityRepository;

@Service
public class CityService {
	@Autowired
	private CityRepository repo;
	
	public List<City> findByProvince(Integer provinceId){
		return repo.findCities(provinceId);
	}
}
