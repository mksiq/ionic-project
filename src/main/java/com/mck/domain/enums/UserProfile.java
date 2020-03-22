package com.mck.domain.enums;

public enum UserProfile {

	ADMIN(1, "ROLE_ADMIN"),
	CLIENT(2, "ROLE_CLIENT");
		
	private int cod;
	private String description;
	
	private UserProfile(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public int getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}
	
	public static UserProfile toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for(UserProfile ps : UserProfile.values()) {
			if(cod.equals(ps.getCod())) {
				return ps;
			}
		}
		throw new IllegalArgumentException("Invalid code: "+cod);
	}
	
	
}
