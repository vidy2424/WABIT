package com.springboot.wabit.validator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.wabit.dto.ClientProducts;
import com.springboot.wabit.dto.OurProducts;

public class Addproductdetails {

	public OurProducts addproduct(Map<String, String> prod, MultipartFile file) {

		System.out.println("prod " + prod);

		OurProducts product = new OurProducts();

		System.out.println("product === " + product.getId());
		int id = prod.containsKey("id") && !prod.get("id").isEmpty() ? Integer.parseInt((String) prod.get("id")) : 0;
		product.setId(id);

		product.setProduct_name(prod.get("product_name"));
		product.setProduct_info(prod.get("product_info"));
		product.setFile(file);
		return product;

	}

	public ClientProducts addclientproduct(Map<String, String> prod, MultipartFile file) {

		System.out.println("prod " + prod);

		ClientProducts product = new ClientProducts();

		System.out.println("product === " + product.getId());
		int id = prod.containsKey("id") && !prod.get("id").isEmpty() ? Integer.parseInt((String) prod.get("id")) : 0;
		product.setId(id);

		product.setClient_product_name(prod.get("client_product_name"));
		product.setClient_project_name(prod.get("client_project_name"));
		product.setClient_product_info(prod.get("client_product_info"));
		product.setFile(file);
		return product;

	}

}
 