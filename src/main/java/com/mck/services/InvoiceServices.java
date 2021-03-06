package com.mck.services;

import java.util.Date;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mck.domain.Client;
import com.mck.domain.Invoice;
import com.mck.domain.ItemInvoice;
import com.mck.domain.PaymentSlip;
import com.mck.domain.enums.PaymentStatus;
import com.mck.repositories.InvoiceRepository;
import com.mck.repositories.ItemInvoiceRepository;
import com.mck.repositories.PaymentRepository;
import com.mck.security.UserSS;
import com.mck.services.exceptions.AuthorizationException;
import com.mck.services.exceptions.ObjectNotFoundException;

@Service
public class InvoiceServices {

	@Autowired
	private InvoiceRepository repo;
	
	@Autowired
	private SlipService slipService;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ProductServices productServices;
	
	@Autowired
	private ItemInvoiceRepository itemInvoiceRepository;
	
	@Autowired
	private ClientServices clientServices;
	
	@Autowired
	private EmailService emailService;
	
	public Invoice find(Integer id) {
		Optional<Invoice> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found. Id : " + id + ", Type: " + Invoice.class.getName()));
	}
	
	@Transactional
	public Invoice insert(Invoice obj) {
		obj.setId(null);
		obj.setRequestDate(new Date());;
		obj.setClient(clientServices.find(obj.getClient().getId()));
		obj.getPayment().setPaymentStatus(PaymentStatus.PENDING);
		obj.getPayment().setInvoice(obj);
		
		if (obj.getPayment() instanceof PaymentSlip) {
			PaymentSlip payment = (PaymentSlip) obj.getPayment();
			slipService.fillPaymentSlip(payment, obj.getRequestDate());
		}
		obj = repo.save(obj);
		paymentRepository.save(obj.getPayment());
		for(ItemInvoice item : obj.getItems()) {
			item.setDiscount(0.0);
			item.setProduct(productServices.find(item.getProduct().getId()));
			item.setPrice(item.getProduct().getPrice());
			item.setInvoice(obj);
		}
		itemInvoiceRepository.saveAll(obj.getItems());
		//emailService.sendOrderConfirmationHtmlEmail(obj);
		emailService.sendOrderConfirmationEmail(obj);
		return obj;
	}
	
	public Page<Invoice> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Access denied");
		}
		
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Client client = clientServices.find(user.getId());
		
		return repo.findByClient(client, pageRequest);
	}
	//@Transactional
	//public void insert
	
	//productService.find(ip.getProduct().getId()).getPrice());
	
	
	
	//PageRequest.of and ClientService (Not CliRep)

}
