package com.mck.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.mck.domain.PaymentSlip;

@Service
public class SlipService {
	public void fillPaymentSlip(PaymentSlip payment, Date paymentRequestDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(paymentRequestDate);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		payment.setDueDate(cal.getTime());
		
	}
}
