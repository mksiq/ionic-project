package com.mck;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mck.domain.Address;
import com.mck.domain.Category;
import com.mck.domain.City;
import com.mck.domain.Client;
import com.mck.domain.Invoice;
import com.mck.domain.ItemInvoice;
import com.mck.domain.Payment;
import com.mck.domain.PaymentCard;
import com.mck.domain.PaymentSlip;
import com.mck.domain.Product;
import com.mck.domain.Province;
import com.mck.domain.enums.ClientType;
import com.mck.domain.enums.PaymentStatus;
import com.mck.repositories.AddressRepository;
import com.mck.repositories.CategoryRepository;
import com.mck.repositories.CityRepository;
import com.mck.repositories.ClientRepository;
import com.mck.repositories.InvoiceRepository;
import com.mck.repositories.ItemInvoiceRepository;
import com.mck.repositories.PaymentRepository;
import com.mck.repositories.ProductRepository;
import com.mck.repositories.ProvinceRepository;

@SpringBootApplication
public class MsappApplication implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(MsappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		
	}

}
