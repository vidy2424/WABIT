package com.springboot.wabit.dao;

import java.util.List;

import org.apache.tomcat.jni.Address;

import com.springboot.wabit.dto.User;
  
public interface UserDAO{

	// user related operation
	User getByEmail(String email);
	User get(int id);

	boolean add(User user);
	
	// adding and updating a new address
	Address getAddress(int addressId);
	boolean addAddress(Address address);
	boolean updateAddress(Address address);
	Address getBillingAddress(int userId);
	List<Address> listShippingAddresses(int userId);
	List<User> getdata();
	//Address getBillingAddress(User user);
	//List<Address> listShippingAddresses(User user);
	 
}
