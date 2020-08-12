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
@Table(name = "our_products")

public class OurProducts{
	private static final long serialVersionUID = 1681261145191719508L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String product_name;
	private String product_info;
	private String code;

	@Transient
	private MultipartFile file;
			
	// default constructor
		public OurProducts() {
			
			this.code = "PRD" + UUID.randomUUID().toString().substring(26).toUpperCase();
			
		}
	
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_info() {
		return product_info;
	}
	public void setProduct_info(String product_info) {
		this.product_info = product_info;
	}

	public OurProducts(int id, String product_name, String product_info, String code, MultipartFile file) {
		super();
		this.id = id;
		this.product_name = product_name;
		this.product_info = product_info;
		this.code = code;
		this.file = file;
	}

	@Override
	public String toString() {
		return "OurProducts [id=" + id + ", product_name=" + product_name + ", product_info=" + product_info + ", code="
				+ code + ", file=" + file + "]";
	}
	
	
	
    
	
	
}
