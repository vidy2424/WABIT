package com.springboot.wabit.dao;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.wabit.dto.ClientProducts;
import com.springboot.wabit.dto.OurProducts;

@Service
public interface ProductDAO {

	// Our Product 
	
	OurProducts getid(int id);

	boolean addOurProducts(OurProducts ourProducts);

	boolean update(OurProducts ourProducts);

	boolean deleteOurProducts(OurProducts ourProducts);

	List<OurProducts> getAllOurProducts();

	List<OurProducts> getOurProductbyID(int id);
	 public void init();

	  public void save(MultipartFile file);

	  public Resource load(String filename);

	  public void deleteAll();

	  public Stream<Path> loadAll();

	// Client Products
	
	ClientProducts getbyId(int id);

	boolean addClientProducts(ClientProducts clientProducts);

	boolean updateClientProducts(ClientProducts clientProducts);

	boolean deleteClientProducts(ClientProducts clientProducts);

	List<ClientProducts> getAllCLientproducts();

	List<ClientProducts> getClientproductbyID(int id);
 	
}
