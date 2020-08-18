package com.springboot.wabit.security;

import static com.springboot.wabit.security.SecurityConstants.*;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.wabit.dto.UserDetail;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author William Suane for DevDojo on 8/24/17.
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager; 
    } 

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
        	UserDetail user = new ObjectMapper().readValue(request.getInputStream(), UserDetail.class);
        	System.out.println("JWTAuth request===" +request.getHeader(getUsernameParameter()));
        	System.out.println("JWTAuth Role===" +user.getRole());
        	System.out.println("JWTAuth email===" +user.getEmail());
        	//SecurityContextHolder.getContext().setAuthentication(authResult);
        	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        	System.out.println("authentication ===" + authentication);
        	
        	return this.authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String username = ((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	System.out.println("authentication ===" + authentication);
        String token = Jwts
                .builder()
                .setSubject(username)
                .setExpiration(new Date((long) (System.currentTimeMillis() + EXPIRATION_TIME)))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        String bearerToken = TOKEN_PREFIX + token;
        System.out.println(bearerToken);
        SecurityContextHolder.getContext().setAuthentication(authResult);
        response.getWriter().write(bearerToken);
        response.addHeader(HEADER_STRING, bearerToken);
       
    }
}