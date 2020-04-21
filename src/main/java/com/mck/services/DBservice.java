package com.mck.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
import com.mck.domain.enums.UserProfile;
import com.mck.repositories.AddressRepository;
import com.mck.repositories.CategoryRepository;
import com.mck.repositories.CityRepository;
import com.mck.repositories.ClientRepository;
import com.mck.repositories.InvoiceRepository;
import com.mck.repositories.ItemInvoiceRepository;
import com.mck.repositories.PaymentRepository;
import com.mck.repositories.ProductRepository;
import com.mck.repositories.ProvinceRepository;

@Service
public class DBservice {
	
	@Autowired
	private BCryptPasswordEncoder bp;
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
	

	public void instantiateDatabase() throws ParseException {

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
		Product p4 = new Product(null, "Table", 1495.00);
		Product p5 = new Product(null, "Pillow", 12.00);
		Product p6 = new Product(null, "Matress", 140.00);
		Product p7 = new Product(null, "TV OLED", 1495.00);
		Product p8 = new Product(null, "Shears", 16.00);
		Product p9 = new Product(null, "Tulip", 11.00);
		Product p10 = new Product(null, "Shampoo", 14.95);



		Product p12 = new Product(null, "Product 12", 10.00);
		Product p13 = new Product(null, "Product 13", 10.00);
		Product p14 = new Product(null, "Product 14", 10.00);
		Product p15 = new Product(null, "Product 15", 10.00);
		Product p16 = new Product(null, "Product 16", 10.00);
		Product p17 = new Product(null, "Product 17", 10.00);
		Product p18 = new Product(null, "Product 18", 10.00);
		Product p19 = new Product(null, "Product 19", 10.00);
		Product p20 = new Product(null, "Product 20", 10.00);
		Product p21 = new Product(null, "Product 21", 10.00);
		Product p22 = new Product(null, "Product 22", 10.00);
		Product p23 = new Product(null, "Product 23", 10.00);
		Product p24 = new Product(null, "Product 24", 10.00);
		Product p25 = new Product(null, "Product 25", 10.00);
		Product p26 = new Product(null, "Product 26", 10.00);
		Product p27 = new Product(null, "Product 27", 10.00);
		Product p28 = new Product(null, "Product 28", 10.00);
		Product p29 = new Product(null, "Product 29", 10.00);
		Product p30 = new Product(null, "Product 30", 10.00);
		Product p31 = new Product(null, "Product 31", 10.00);
		Product p32 = new Product(null, "Product 32", 10.00);
		Product p33 = new Product(null, "Product 33", 10.00);
		Product p34 = new Product(null, "Product 34", 10.00);
		Product p35 = new Product(null, "Product 35", 10.00);
		Product p36 = new Product(null, "Product 36", 10.00);
		Product p37 = new Product(null, "Product 37", 10.00);
		Product p38 = new Product(null, "Product 38", 10.00);
		Product p39 = new Product(null, "Product 39", 10.00);
		Product p40 = new Product(null, "Product 40", 10.00);
		Product p41 = new Product(null, "Product 41", 10.00);
		Product p42 = new Product(null, "Product 42", 10.00);
		Product p43 = new Product(null, "Product 43", 10.00);
		Product p44 = new Product(null, "Product 44", 10.00);
		Product p45 = new Product(null, "Product 45", 10.00);
		Product p46 = new Product(null, "Product 46", 10.00);
		Product p47 = new Product(null, "Product 47", 10.00);
		Product p48 = new Product(null, "Product 48", 10.00);
		Product p49 = new Product(null, "Product 49", 10.00);
		Product p50 = new Product(null, "Product 50", 10.00);
		

		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2, p4));
		cat3.getProducts().addAll(Arrays.asList(p5));
		cat4.getProducts().addAll(Arrays.asList(p5,p6));
		cat5.getProducts().addAll(Arrays.asList(p7, p1));
		cat6.getProducts().addAll(Arrays.asList(p8, p9));
		cat7.getProducts().addAll(Arrays.asList(p10));
		
		
		
		p1.getCategories().addAll(Arrays.asList(cat1, cat5));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
		p4.getCategories().addAll(Arrays.asList(cat2));
		p5.getCategories().addAll(Arrays.asList(cat3, cat4));
		p6.getCategories().addAll(Arrays.asList(cat4));
		p7.getCategories().addAll(Arrays.asList(cat5));
		p8.getCategories().addAll(Arrays.asList(cat6));
		p9.getCategories().addAll(Arrays.asList(cat6));
		p10.getCategories().addAll(Arrays.asList(cat7));
		

		
		cat1.getProducts().addAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
		p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
		p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
		
		
		
		p12.getCategories().add(cat1);
		p13.getCategories().add(cat1);
		p14.getCategories().add(cat1);
		p15.getCategories().add(cat1);
		p16.getCategories().add(cat1);
		p17.getCategories().add(cat1);
		p18.getCategories().add(cat1);
		p19.getCategories().add(cat1);
		p20.getCategories().add(cat1);
		p21.getCategories().add(cat1);
		p22.getCategories().add(cat1);
		p23.getCategories().add(cat1);
		p24.getCategories().add(cat1);
		p25.getCategories().add(cat1);
		p26.getCategories().add(cat1);
		p27.getCategories().add(cat1);
		p28.getCategories().add(cat1);
		p29.getCategories().add(cat1);
		p30.getCategories().add(cat1);
		p31.getCategories().add(cat1);
		p32.getCategories().add(cat1);
		p33.getCategories().add(cat1);
		p34.getCategories().add(cat1);
		p35.getCategories().add(cat1);
		p36.getCategories().add(cat1);
		p37.getCategories().add(cat1);
		p38.getCategories().add(cat1);
		p39.getCategories().add(cat1);
		p40.getCategories().add(cat1);
		p41.getCategories().add(cat1);
		p42.getCategories().add(cat1);
		p43.getCategories().add(cat1);
		p44.getCategories().add(cat1);
		p45.getCategories().add(cat1);
		p46.getCategories().add(cat1);
		p47.getCategories().add(cat1);
		p48.getCategories().add(cat1);
		p49.getCategories().add(cat1);
		p50.getCategories().add(cat1);

		
		catRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		prodRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10));
		prodRepository.saveAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
				p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
				p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
		
		Province prov1 = new Province(null, "Ontario");
		Province prov2 = new Province(null, "British Columbia");
		
		City c1 = new City(null, "Toronto", prov1);
		City c2 = new City(null, "Vancouver", prov2);
		City c3 = new City(null, "Niagara", prov1);
		
		prov1.getCities().addAll(Arrays.asList(c1,c3));
		prov2.getCities().addAll(Arrays.asList(c2));
		
		provRepository.saveAll(Arrays.asList(prov1,prov2));
		cityRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Client cli1 = new Client(null, "Mary", "mcksiq@gmail.com", "987654321", ClientType.PERSON, bp.encode("123"));
		cli1.getPhones().addAll(Arrays.asList("6471234567", "6472135656"));
		
		Client cli2 = new Client(null, "Maickel", "maickelsiqueira@gmail.com", "18238123", ClientType.PERSON, bp.encode("123"));
		cli2.addProfile(UserProfile.ADMIN);
		cli2.getPhones().addAll(Arrays.asList("189329381", "81234923"));
		
		
		Address a1 = new Address(null, "123", "Keele st", "0", "M1M M1M", cli1, c1);
		Address a2 = new Address(null, "234", "Finch Ave", "0", "M2M M1M", cli1, c1);
		
		cli1.getAdress().addAll(Arrays.asList(a1,a2));
		cli2.getAdress().addAll(Arrays.asList(a2));
		
		clientRepository.saveAll(Arrays.asList(cli1, cli2));
		
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
		
	}

}
