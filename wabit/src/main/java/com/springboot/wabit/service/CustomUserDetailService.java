package com.springboot.wabit.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.springboot.wabit.dao.UserDAO;
import com.springboot.wabit.dto.User;

/**
 * @author William Suane for DevDojo on 6/27/17.
 */
@Component
public class CustomUserDetailService implements UserDetailsService {
    
	private List<GrantedAuthority> role;
   
    @Autowired
    private UserDAO userDAO;
    
/*    @Autowired
    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
*/
    @Override
    public  UserDetails  loadUserByUsername(String username) throws UsernameNotFoundException {
    	
    	
    	User user = Optional.ofNullable(userDAO.getByEmail(username))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList("ROLE_ADMIN");
        List<GrantedAuthority> authorityListUser = AuthorityUtils.createAuthorityList("ROLE_USER");
        System.out.println("CustomUserDetail Role===" +user.getRole());
        System.out.println("CustomUserDetail email===" +user.getEmail());
        if(user.getRole()=="admin") {
        	role=authorityListAdmin;
        }else {
        	role = authorityListUser;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	System.out.println("authentication ===" + authentication);
        return new org.springframework.security.core.userdetails.User
                (user.getEmail(), user.getPassword(),role);
    }
}