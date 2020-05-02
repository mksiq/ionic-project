package com.mck.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mck.services.DBservice;
import com.mck.services.EmailService;
import com.mck.services.MockMailService;

@Configuration
@Profile("prod")
public class ProdConfig {
	
	@Autowired
	private DBservice dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto=create}")
	private String strategy;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		if(!"create".equals(strategy)) {
			return false;
		}
		dbService.instantiateDatabase();		
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		//return new SmtpEmailService();
		return new MockMailService();
	}
}
