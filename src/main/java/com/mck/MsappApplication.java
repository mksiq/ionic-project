package com.mck;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mck.domain.Category;
import com.mck.domain.City;
import com.mck.domain.Product;
import com.mck.domain.Province;
import com.mck.repositories.CategoryRepository;
import com.mck.repositories.CityRepository;
import com.mck.repositories.ProductRepository;
import com.mck.repositories.ProvinceRepository;

@SpringBootApplication
public class MsappApplication implements CommandLineRunner {
	@Autowired
	private CategoryRepository catRepository;
	@Autowired
	private ProductRepository prodRepository;
	@Autowired
	private ProvinceRepository provRepository;
	@Autowired
	private CityRepository cityRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(MsappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "IT");
		Category cat2 = new Category(null, "Office");
		Category cat3 = new Category(null, "HR");
		
		Product p1 = new Product(null, "Computer", 1495.00);
		Product p2 = new Product(null, "Printer", 400.00);
		Product p3 = new Product(null, "Mouse", 40.00);
		

		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
		

		
		catRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		prodRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		
		Province prov1 = new Province(null, "Ontario");
		Province prov2 = new Province(null, "British Columbia");
		
		City c1 = new City(null, "Toronto", prov1);
		City c2 = new City(null, "Vancouver", prov2);
		City c3 = new City(null, "Niagara", prov1);
		
		prov1.getCities().addAll(Arrays.asList(c1,c3));
		prov2.getCities().addAll(Arrays.asList(c2));
		
		provRepository.saveAll(Arrays.asList(prov1,prov2));
		cityRepository.saveAll(Arrays.asList(c1,c2,c3));
	}

}
