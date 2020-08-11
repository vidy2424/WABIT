package com.springboot.wabit.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.elasticsearch.client.transport.TransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.wabit.dao.UserDAO;
import com.springboot.wabit.dto.User;
import com.springboot.wabit.dto.Userinfo;
import com.springboot.wabit.repository.JwtTokenRepository;
import com.springboot.wabit.userinfo.Userinfosplit;

//@RequestMapping("/")
@RestController
public class PageController { 

	private static final Logger logger = LoggerFactory
			.getLogger(PageController.class);


	@Autowired
	private UserDAO userDAO;

	@Autowired
	private Userinfosplit split;
	
	@Autowired
	private JwtTokenRepository jwtTokenRepository;
	
	TransportClient client;

    public PageController(JwtTokenRepository jwtTokenRepository) {
        this.jwtTokenRepository = jwtTokenRepository;
    }
 	
//	@SuppressWarnings("resource")
//	public PageController() throws UnknownHostException {
//		client = new PreBuiltTransportClient(Settings.EMPTY)
//				.addTransportAddress(new InetSocketTransportAddress(InetAddress
//						.getByName("localhost"), 9300));
//	} 


    @PostMapping(value = "/membershipdetail")
	public String register( @RequestBody Userinfo userinfo) {
			
		//System.out.println("userdetail " + userinfo.getAddressLineOne());
		  
		User user = split.userdetails(userinfo);
		//Address address = split.addressdetails(userinfo);
		split.saveuserAll(user);
		/*RegisterHandler handler = new RegisterHandler();
		handler.addUser(user);
		handler.addBilling(address);
		handler.saveAll();*/
		System.out.println("userdetail " + user);
		
		logger.info("Page Controller membership called!");
 		return "";
	}
    
    
	@RequestMapping(value = "/userinfo")
	public Map<String, Object> userinfo(HttpServletRequest request,
			HttpServletResponse response) {
  
 		Map<String, Object> userdetailMap = new HashMap<String, Object>();
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//System.out.println("authentication ===" + authentication);
		 
		//System.out.println("request ===" + request.getHeader("Authorization"));
		//String blackListToken = request.getHeader("Authorization");
 		//System.out.println("exist ===" + jwtTokenRepository.exist(blackListToken)); 
		
 		//Boolean keyBlacklist = jwtTokenRepository.exist(blackListToken);
 		
 		//if(request.getHeader("Authorization") != null && !keyBlacklist) {
 			//System.out.println(authentication.getName());
 			User user = userDAO.getByEmail(authentication.getName()); 
 			userdetailMap.put("id", user.getId());
 			userdetailMap.put("fullname", user.getFirstName() + " " + user.getLastName());
 			userdetailMap.put("Email", user.getEmail());
 			userdetailMap.put("name", user.getFirstName());
 			userdetailMap.put("role", user.getRole());
			
 			return userdetailMap;
 			 
// 			if(user.getRole().equals("USER")) {
// 				userdetailMap.put("cart", user.getCart());					
// 				System.out.println("cart ===" + user.getCart());
//			}
 			
 		 }
        //System.out.println("userinfo....");
 		  //return userdetailMap;
	



	@RequestMapping(value = "/login")
	public String login(
			@RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout,
			HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("login");
		HttpSession session= request.getSession(false);
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		  
		SecurityContextHolder.clearContext();
         session= request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
         
		System.out.println("auth ===" + auth);
		System.out.println("logout ===" + logout);

		Map<String, Object> mapproducts = new HashMap<String, Object>();
		System.out.println("Login");
		mv.addObject("title", "Login");
		mapproducts.put("title", "Login");
		if (error != null) { 
			mv.addObject("message", "Username and Password is invalid!");
			mapproducts.put("message", "Username and Password is invalid!");
		}
		if (logout != null) {
			mv.addObject("logout", "You have logged out successfully!");
			mapproducts.put("logout", "You have logged out successfully!");
		}
		if(request.getHeader("Authorization")!= null){
			System.out.println("request ===" + request.getHeader("Authorization"));
			String blackListToken = request.getHeader("Authorization");
			jwtTokenRepository.save(blackListToken);
			System.out.println("exist ===" + jwtTokenRepository.exist(blackListToken));
		}
		return "mv"; 
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout(HttpServletRequest request,
			HttpServletResponse response) {
		// Invalidates HTTP Session, then unbinds any objects bound to it.
		// Removes the authentication from securitycontext
		HttpSession session= request.getSession(false);
		SecurityContextHolder.clearContext();
         session= request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        for(Cookie cookie : request.getCookies()) {
            cookie.setMaxAge(0);
        }
        System.out.println("logout....");
    return "logout";
	}

	@RequestMapping(value = "/access-denied")
	public ModelAndView accessDenied() {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle", "Aha! Caught You.");
		mv.addObject("errorDescription",
				"You are not authorized to view this page!");
		mv.addObject("title", "403 Access Denied");
		return mv;
	}

}
