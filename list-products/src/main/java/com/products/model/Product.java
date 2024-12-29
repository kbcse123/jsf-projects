package com.products.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "product")
public class Product implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2552890499810593970L;

	@Id
	@GeneratedValue
	private Long id;	
	private String productName;	
	private String productCode;
	private Long availableQty;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public Long getAvailableQty() {
		return availableQty;
	}
	public void setAvailableQty(Long availableQty) {
		this.availableQty = availableQty;
	}
	
	
}
