package com.springboot.wabit.dto;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "client_products")

public class ClientProducts {
	private static final long serialVersionUID = 1681261145191719508L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String client_product_name;
	private String client_project_name;
	private String client_product_info;

	private String code;

	@Transient
	private MultipartFile file;

	// default constructor
	public ClientProducts() {

		this.code = "PRD" + UUID.randomUUID().toString().substring(26).toUpperCase();

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

 

	public String getClient_product_name() {
		return client_product_name;
	}

	public void setClient_product_name(String client_product_name) {
		this.client_product_name = client_product_name;
	}

	public String getClient_project_name() {
		return client_project_name;
	}

	public void setClient_project_name(String client_project_name) {
		this.client_project_name = client_project_name;
	}

	public String getClient_product_info() {
		return client_product_info;
	}

	public void setClient_product_info(String client_product_info) {
		this.client_product_info = client_product_info;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	 

	public ClientProducts(int id, String client_product_name, String client_project_name, String client_product_info,
			String code, MultipartFile file) {
		super();
		this.id = id;
		this.client_product_name = client_product_name;
		this.client_project_name = client_project_name;
		this.client_product_info = client_product_info;
		this.code = code;
		this.file = file;
	}

	@Override
	public String toString() {
		return "ClientProducts [id=" + id + ", client_product_name=" + client_product_name + ", client_project_name="
				+ client_project_name + ", client_product_info=" + client_product_info + ", code=" + code + ", file="
				+ file + "]";
	}

 

	
	
	
	
}
