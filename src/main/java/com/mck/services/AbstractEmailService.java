package com.mck.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.mck.domain.Client;
import com.mck.domain.Invoice;


public abstract class AbstractEmailService implements EmailService{

	
	@Value("${default.sender}")
	private String sender;
	
	@Autowired
	private TemplateEngine template;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendOrderConfirmationEmail(Invoice obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromInvoice(obj);
		sendEmail(sm);
		
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromInvoice(Invoice obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getClient().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Invoice confirmed");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}
	
	protected String htmlFromTemplateInvoice(Invoice obj) {
		Context context = new Context();
		context.setVariable("invoice", obj);
		return template.process("email/invoiceConfirmation", context);
	}
	
	@Override
	public void sendOrderConfirmationHtmlEmail(Invoice obj) {
		
		try {
			MimeMessage mm;
			mm = prepareMimeMessageFromInvoice(obj);
			sendHtmlEmail(mm);
		} catch (MessagingException e) {
			sendOrderConfirmationEmail(obj);
		}
		
	}

	protected MimeMessage prepareMimeMessageFromInvoice(Invoice obj) throws MessagingException {
		MimeMessage mm = javaMailSender.createMimeMessage();
		MimeMessageHelper  mmh = new MimeMessageHelper(mm, true);
		mmh.setTo(obj.getClient().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Invoice confirmed! Code: "+ obj.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplateInvoice(obj), true);		
		
		
		return mm;
	}
	
	@Override
	public void sendNewPasswordEmail(Client client, String newPass) {
		SimpleMailMessage sm = preparedNewPasswordEmail(client, newPass);
		sendEmail(sm);
	}

	protected SimpleMailMessage preparedNewPasswordEmail(Client client, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(client.getEmail());
		sm.setFrom(sender);
		sm.setSubject("New password request");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("New password" + newPass);
		return sm;
	}
}
