package com.mck.services;

import org.springframework.mail.SimpleMailMessage;

import com.mck.domain.Invoice;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Invoice obj);
	
	void sendEmail(SimpleMailMessage msg);
}
