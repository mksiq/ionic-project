package com.mck.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mck.services.DBservice;
import com.mck.services.EmailService;
import com.mck.services.MockMailService;
import com.mck.services.SmtpEmailService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBservice dbService;
	
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbService.instantiateDatabase();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		// return new SmtpEmailService();
		return new MockMailService();
	}
}
