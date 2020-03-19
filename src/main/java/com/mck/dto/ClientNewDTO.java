package com.mck.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.mck.services.validation.ClientInsert;


@ClientInsert
public class ClientNewDTO implements Serializable{


	private static final long serialVersionUID = 1L;
	@NotEmpty(message="Must be filled")
	@Length(min=5,max=120,message="Must have between 5 and 120 characters")
	private String name;
	@NotEmpty(message="Must be filled")
	@Email(message="Invalid email")
	private String email;
	
	@NotEmpty(message="Must be filled")
	private String sinOrBn;
	private Integer type;
	@NotEmpty(message="Must be filled")
	private String password;
	
	private String number;
	
	
	@NotEmpty(message="Must be filled")
	private String streetName;
	private String unitNumber;
	
	@NotEmpty(message="Must be filled")
	private String postalCode;
	
	@NotEmpty(message="Must be filled")
	private String phone1;
	private String phone2;
	private String phone3;
	
	private Integer cityId;

	public ClientNewDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSinOrBn() {
		return sinOrBn;
	}

	public void setSinOrBn(String sinOrBn) {
		this.sinOrBn = sinOrBn;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getUnitNumber() {
		return unitNumber;
	}

	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
}
