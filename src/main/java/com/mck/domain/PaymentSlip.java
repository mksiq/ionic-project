package com.mck.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mck.domain.enums.PaymentStatus;
@Entity
public class PaymentSlip extends Payment implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dueDate;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date paymentDate;
	public PaymentSlip() {
		super();
	}
	public PaymentSlip(Integer id, PaymentStatus paymentStatus, Invoice invoice, Date dueDate, Date paymentDate) {
		super(id, paymentStatus, invoice);
		this.dueDate = dueDate;
		this.paymentDate = paymentDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	
	
	
	
}
