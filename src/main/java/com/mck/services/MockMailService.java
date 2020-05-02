package com.mck.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import com.mck.domain.Invoice;



public class MockMailService extends AbstractEmailService {

	private static final Logger LOG = LoggerFactory.getLogger(MockMailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Simulating sending an email...");
		LOG.info(msg.toString());
		LOG.info("Email sent");
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOG.info("Simulating sending an HTML email...");
		LOG.info(msg.toString());
		LOG.info("Html email sent");
	}

	@Override
	public void sendOrderConfirmationHtmlEmail(Invoice obj) {
		// TODO Auto-generated method stub
		
	}

	
}
