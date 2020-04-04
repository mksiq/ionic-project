package com.mck.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.mck.domain.Client;
import com.mck.domain.Invoice;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Invoice obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Invoice obj);
	
	void sendHtmlEmail(MimeMessage msg);

	void sendNewPasswordEmail(Client client, String newPass);
}
