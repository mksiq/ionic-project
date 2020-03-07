package com.mck.domain;

import java.io.Serializable;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.mck.domain.enums.PaymentStatus;
@Entity
@JsonTypeName("paymentCard")
public class PaymentCard extends Payment implements Serializable {

	private static final long serialVersionUID = 1L;
	private String cardInfo;

	public PaymentCard() {
		super();
	}

	public PaymentCard(Integer id, PaymentStatus paymentStatus, Invoice invoice, String cardInfo) {
		super(id, paymentStatus, invoice);
		this.cardInfo = cardInfo;
	}

	public String getCardInfo() {
		return cardInfo;
	}

	public void setCardInfo(String cardInfo) {
		this.cardInfo = cardInfo;
	}
	
	
	
	
}
