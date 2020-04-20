package com.mck.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mck.domain.enums.ClientType;
import com.mck.domain.enums.UserProfile;
@Entity
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	
//	@Column(unique=true) for unique email restricted by db
	
	@Column(unique=true)
	private String email;
	private String sinOrBn;
	private Integer type;
	
	@JsonIgnore
	private String password;
	

	@OneToMany(mappedBy="client", cascade=CascadeType.ALL)
	private List<Address> adress = new ArrayList<>();
	
	@ElementCollection
	@CollectionTable(name="PHONE")
	private Set<String>  phones = new HashSet<>();
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="USER_PROFILE")
	private Set<Integer> userProfile = new HashSet<>();
	
	
	@JsonIgnore
	@OneToMany(mappedBy="client")
	private List<Invoice> invoices = new ArrayList<>();
	
//	private String imageUrl;
	
	public Client() {
		addProfile(UserProfile.CLIENT);
	
	}

	public Client(Integer id, String name, String email, String sinOrBn, ClientType type, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.sinOrBn = sinOrBn;
		this.type = (type == null )? null : type.getCod();
		this.password = password;
		addProfile(UserProfile.CLIENT);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSinOrBn() {
		return sinOrBn;
	}

	public void setSinOrBn(String sinOrBn) {
		this.sinOrBn = sinOrBn;
	}

	public ClientType getType() {
		return ClientType.toEnum(type);
	}

	public void setType(ClientType type) {
		this.type = type.getCod();
	}

	public List<Address> getAdress() {
		return adress;
	}

	public void setAdress(List<Address> adress) {
		this.adress = adress;
	}

	public Set<String> getPhones() {
		return phones;
	}

	public void setPhones(Set<String> phones) {
		this.phones = phones;
	}
	
	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}
	
	

	public Set<UserProfile> getProfiles() {
		
		return userProfile.stream().map(x -> UserProfile.toEnum(x)).collect(Collectors.toSet());
	}

	public void addProfile(UserProfile profile) {
		userProfile.add(profile.getCod());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

//	public String getImageUrl() {
//		return imageUrl;
//	}
//
//	public void setImageUrl(String imageUrl) {
//		this.imageUrl = imageUrl;
//	}


	
	
}
