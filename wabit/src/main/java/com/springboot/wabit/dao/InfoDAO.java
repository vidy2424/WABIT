package com.springboot.wabit.dao;

import org.springframework.stereotype.Service;

import com.springboot.wabit.dto.Info;

@Service
public interface InfoDAO {

	
	boolean add(Info info);
}
