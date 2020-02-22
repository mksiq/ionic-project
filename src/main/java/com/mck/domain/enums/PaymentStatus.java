package com.mck.domain.enums;

public enum PaymentStatus {

	PENDING(1,"Pending"),
	PAID(2,"Paid"),
	CANCELLED(3,"Cancelled");
	
	private int cod;
	private String description;
	
	private PaymentStatus(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public int getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}
	
	public static PaymentStatus toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for(PaymentStatus ps : PaymentStatus.values()) {
			if(cod.equals(ps.getCod())) {
				return ps;
			}
		}
		throw new IllegalArgumentException("Invalid code: "+cod);
	}
	
	
}
