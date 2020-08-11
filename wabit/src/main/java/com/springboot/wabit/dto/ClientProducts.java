package com.springboot.wabit.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "client_products")

public class ClientProducts {
	private static final long serialVersionUID = 1681261145191719508L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String client_product;
	private String client_product_info;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getClient_product() {
		return client_product;
	}
	public void setClient_product(String client_product) {
		this.client_product = client_product;
	}
	public String getClient_product_info() {
		return client_product_info;
	}
	public void setClient_product_info(String client_product_info) {
		this.client_product_info = client_product_info;
	}
	
	
	
}
