package com.mck.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemInvoice implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private ItemInvoicePK id = new ItemInvoicePK();
	
	
	private Double discount;
	private Integer quantity;
	private Double price;
	
	public ItemInvoice() {
	}

	public ItemInvoice(Invoice invoice, Product product, Double discount, Integer quantity, Double price) {
		super();
		id.setInvoice(invoice);
		id.setProduct(product);
		this.discount = discount;
		this.quantity = quantity;
		this.price = price;
	}
	

	public ItemInvoicePK getId() {
		return id;
	}

	public void setId(ItemInvoicePK id) {
		this.id = id;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public double getSubTotal() {		
		return (price - discount) * quantity;
	}
	
	
	
	@JsonIgnore
	public Invoice getInvoice() {
		return id.getInvoice();
	}
	
	public void setInvoice(Invoice invoice) {
		id.setInvoice(invoice);
	}
	public Product getProduct() {
		return id.getProduct();
	}

	public void setProduct(Product product) {
		id.setProduct(product);
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
		ItemInvoice other = (ItemInvoice) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.CANADA);
		
		StringBuilder builder = new StringBuilder();
		builder.append(getProduct().getName());
		builder.append(", qty: ");
		builder.append(getQuantity());
		builder.append(", pricer per unit: ");
		builder.append(nf.format(getPrice()));
		builder.append(", sub total: ");
		builder.append(nf.format(getSubTotal()));
		builder.append("\n");
		return builder.toString();
	}
	
	
	
	
}
