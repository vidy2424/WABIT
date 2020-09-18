package com.springboot.wabit.controller;

import java.io.PushbackReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.servlet.http.HttpServletRequest;

import org.elasticsearch.cluster.metadata.AliasAction.Add;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.wabit.dao.ProductDAO;
import com.springboot.wabit.dto.ClientProducts;
import com.springboot.wabit.dto.OurProducts;
import com.springboot.wabit.util.FileUploadUtility;
import com.springboot.wabit.validator.Addproductdetails;
import com.springboot.wabit.validator.ProductValidator;

 
@RestController
public class ProductsController {

	@Autowired
	private ProductDAO productDAO;

	// add and edit product
	@RequestMapping(value = "/product", method = RequestMethod.POST)
	public String managePostProduct(@RequestParam(name = "data", required = false) String add,
			@RequestParam(name = "file") MultipartFile file, HttpServletRequest request) {

		Map<String, String> map2 = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();

		try {
			// convert JSON string to Map
			map2 = mapper.readValue(add, new TypeReference<HashMap<String, String>>() {
			});
			System.out.println(map2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Addproductdetails detail = new Addproductdetails();
		OurProducts mProduct = detail.addproduct(map2, file);

		// mandatory file upload check
		if (mProduct.getId() == 0) {
			new ProductValidator().validate(mProduct);
		} else {
			// edit check only when the file has been selected
			if (!mProduct.getFile().getOriginalFilename().equals("")) {
				new ProductValidator().validate(mProduct);
			}
		}

		if (mProduct.getId() == 0) {
			System.out.println("product id: " + mProduct.getId());

			productDAO.addOurProducts(mProduct);

		} else {
			productDAO.update(mProduct);
		}

		// upload the file
		if (!mProduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
		}

		return "redirect:/manage/product?success=product";
	}

	@RequestMapping(value = "/ourProducts/products/{start}")
	public Stack<List> getAllOurproduct(@PathVariable int start) {
	 	List<OurProducts> planinfo = productDAO.getAllOurProducts(start);
		long planinfo1 = productDAO.getOurProductCount();
		System.out.println("this is" +planinfo);
		System.out.println("this is" +planinfo1);
		List<List<OurProducts>> strings = Arrays.asList(planinfo);
        String str = String.valueOf(planinfo1);

        Stack<List> STACK = new Stack<List>();
        final List<Long> longArray = Arrays.asList(planinfo1);

        STACK.push( planinfo);
        STACK.push( longArray);

 		return STACK;
	}
 
	@DeleteMapping("/infoDeleteprod/{id}")
	public void deleteOurproduct(@PathVariable int id) {
		productDAO.deleteOurProducts(id);
		System.out.println("this is delete");
	}

	// Client Products //

	// add and edit product
	@RequestMapping(value = "/clientproduct", method = RequestMethod.POST)
	public String manageClientProduct(@RequestParam(name = "data", required = false) String add,
			@RequestParam(name = "file") MultipartFile file, HttpServletRequest request) {

		Map<String, String> map = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();

		try {
			// convert JSON string to Map
			map = mapper.readValue(add, new TypeReference<HashMap<String, String>>() {
			});
			System.out.println(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Addproductdetails detail = new Addproductdetails();
		ClientProducts mProduct = detail.addclientproduct(map, file);

		// mandatory file upload check
		if (mProduct.getId() == 0) {
			new ProductValidator().validateclientproductimage(mProduct);
		} else {
			// edit check only when the file has been selected
			if (!mProduct.getFile().getOriginalFilename().equals("")) {
				new ProductValidator().validateclientproductimage(mProduct);
			}
		}

		if (mProduct.getId() == 0) {
			System.out.println("product id: " + mProduct.getId());

			productDAO.addClientProducts(mProduct); 

		} else {
			productDAO.updateClientProducts(mProduct);
		}

		// upload the file
		if (!mProduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
		}

		return "redirect:/manage/product?success=product";
	}
	
	@RequestMapping(value = "/clientProducts/products/{start}")
	public Stack<List> getAllCLientproduct(@PathVariable int start) {

		List<ClientProducts> clientProductsinfo = productDAO.getAllCLientproducts(start);
		long clientProductsCount = productDAO.getCLientproductsCount();
		System.out.println("this is" +clientProductsinfo);
		System.out.println("this is" +clientProductsCount);
		List<List<ClientProducts>> strings = Arrays.asList(clientProductsinfo);
        String str = String.valueOf(clientProductsCount);

        Stack<List> STACK = new Stack<List>();
        final List<Long> longArray = Arrays.asList(clientProductsCount);

        STACK.push( clientProductsinfo);
        STACK.push( longArray);

 		return STACK;
	}
 	
	@DeleteMapping("/clientProducts/{id}")
	public void deleteClientproduct(@PathVariable int id) {
		productDAO.deleteClientProducts(id);
		System.out.println("this is delete");
	}
	
	 

}
