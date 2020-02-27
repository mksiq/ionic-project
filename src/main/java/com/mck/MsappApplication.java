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
	@Autowired
	private CategoryRepository catRepository;
	@Autowired
	private ProductRepository prodRepository;
	@Autowired
	private ProvinceRepository provRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private AddressRepository addrRepository;
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private InvoiceRepository invoiceRepository;
	@Autowired
	private ItemInvoiceRepository itemInvoiceRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(MsappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "IT");
		Category cat2 = new Category(null, "Office");
		Category cat3 = new Category(null, "HR");
		Category cat4 = new Category(null, "Bed and Bath");
		Category cat5 = new Category(null, "Eletronics");
		Category cat6 = new Category(null, "Gardening");
		Category cat7 = new Category(null, "Pharmacy");
		
		Product p1 = new Product(null, "Computer", 1495.00);
		Product p2 = new Product(null, "Printer", 400.00);
		Product p3 = new Product(null, "Mouse", 40.00);
		

		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
		

		
		catRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
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
		
		Client cli1 = new Client(null, "Mary", "mary@gmail.com", "987654321", ClientType.PERSON);
		cli1.getPhones().addAll(Arrays.asList("6471234567", "6472135656"));
		
		Address a1 = new Address(null, "123", "Keale st", "0", "M1M M1M", cli1, c1);
		Address a2 = new Address(null, "234", "Finch Ave", "0", "M2M M1M", cli1, c1);
		
		cli1.getAdress().addAll(Arrays.asList(a1,a2));
		
		clientRepository.saveAll(Arrays.asList(cli1));
		
		addrRepository.saveAll(Arrays.asList(a1, a2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Invoice inv1 = new Invoice(null, sdf.parse("22/02/2020 3:04"), cli1, a1);
		Invoice inv2 = new Invoice(null, sdf.parse("15/01/2020 2:01"), cli1, a2);
		
		Payment pay1 = new PaymentCard(null, PaymentStatus.PAID, inv1, "Visa");
		inv1.setPayment(pay1);
		Payment pay2 = new PaymentSlip(null, PaymentStatus.PENDING, inv2, sdf.parse("23/03/2020 00:00"),  null);
		inv2.setPayment(pay2);
		
		cli1.getInvoices().addAll(Arrays.asList(inv1, inv2));
		
		invoiceRepository.saveAll(Arrays.asList(inv1, inv2));
		paymentRepository.saveAll(Arrays.asList(pay1, pay2));
		
		ItemInvoice ii1 = new ItemInvoice(inv1, p1, 0.0, 1, 2000.0);
		ItemInvoice ii2 = new ItemInvoice(inv1, p3, 0.0, 2, 80.0);
		ItemInvoice ii3 = new ItemInvoice(inv2, p2, 100.00, 1, 800.00);
		
		inv1.getItems().addAll(Arrays.asList(ii1, ii2));
		inv2.getItems().addAll(Arrays.asList(ii3));
		
		p1.getItems().addAll(Arrays.asList(ii1));
		p2.getItems().addAll(Arrays.asList(ii3));
		p3.getItems().addAll(Arrays.asList(ii2));
		
		itemInvoiceRepository.saveAll(Arrays.asList(ii1,ii2,ii3));
		
		
		// import javax.validation.constraints.noempty;
	}

}
