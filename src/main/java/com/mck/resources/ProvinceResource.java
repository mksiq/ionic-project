package com.mck.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mck.domain.City;
import com.mck.domain.Province;
import com.mck.dto.CityDTO;
import com.mck.dto.ProvinceDTO;
import com.mck.services.CityService;
import com.mck.services.ProvinceService;

@RestController
@RequestMapping(value="/provinces")
public class ProvinceResource {
	
	@Autowired
	private ProvinceService service;
	
	@Autowired
	private CityService cityService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ProvinceDTO>> findAll() {
		List<Province> list = service.findAll();	
		List<ProvinceDTO> listDTOTES = new ArrayList<ProvinceDTO>();
		for (int i = 0; i < list.size() ; i++) {
			listDTOTES.add (new ProvinceDTO(list.get(i)));
		}
		return ResponseEntity.ok().body(listDTOTES);
	}
	
	@RequestMapping(value="/{provinceId}/cities", method=RequestMethod.GET)
	public ResponseEntity<List<CityDTO>> findCities(@PathVariable Integer provinceId){
		List<City> list = cityService.findByProvince(provinceId);
		List<CityDTO> listDto = list.stream().map(obj -> new CityDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
}
