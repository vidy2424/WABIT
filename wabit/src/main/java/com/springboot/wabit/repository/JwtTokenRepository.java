package com.springboot.wabit.repository;

import java.util.Map;

import com.springboot.wabit.dto.JwtInvalidToken;
  
public interface JwtTokenRepository {

    void save(String token);
    Map<String, String> findAll();
    String findById(String id);
    void update(JwtInvalidToken token);
    void delete(String id);
    boolean exist (String token);
}
