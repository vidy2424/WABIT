package com.springboot.wabit.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.wabit.dto.ClientProducts;
import com.springboot.wabit.dto.OurProducts;

@Service
public interface ProductDAO {

	// Our Product 
	
	OurProducts getid(int id);

	boolean addOurProducts(OurProducts ourProducts);

	boolean update(OurProducts ourProducts);

	 
	boolean deleteOurProducts(int id);
	
	List<OurProducts> getAllOurProducts();

	List<OurProducts> getOurProductbyID(int id);
 
 
	// Client Products
	
	ClientProducts getbyId(int id);

	boolean addClientProducts(ClientProducts clientProducts);

	boolean updateClientProducts(ClientProducts clientProducts);

	boolean deleteClientProducts(ClientProducts clientProducts);

	List<ClientProducts> getAllCLientproducts();

	List<ClientProducts> getClientproductbyID(int id);

 	
}
