package com.springboot.wabit.dao;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Service;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.springboot.wabit.dto.Info;
import com.springboot.wabit.dto.SignUpUser;
import com.springboot.wabit.dto.User;
import com.springboot.wabit.security.JWTAuthenticationFilter;
import com.springboot.wabit.security.JWTAuthorizationFilter;

@Service
public interface SignupDAO {

	SignUpUser get(String name);

	Info getid(int id);

	static List<SignUpUser> list() {
		// TODO Auto-generated method stub
		return null;
	}

	boolean add(User user);

	boolean add(Info info);

	boolean update(Info info);

	boolean delete(int id);

	List<User> loginbyName(String username);

	List<User> getAllUser();

	List<Info> getAllInfo();

	// Object getByEmail(String username);
	User getByUsername(String username);

	List<Info> getbyID(int id);
}


