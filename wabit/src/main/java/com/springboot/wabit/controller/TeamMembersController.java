package com.springboot.wabit.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.springboot.wabit.dao.ProductDAO;
import com.springboot.wabit.dao.TeamMemberDAO;
import com.springboot.wabit.dto.ClientProducts;
import com.springboot.wabit.dto.OurProducts;
import com.springboot.wabit.dto.TeamMembers;
import com.springboot.wabit.util.FileUploadUtility;
import com.springboot.wabit.validator.AddMemberDetails;
import com.springboot.wabit.validator.Addproductdetails;
import com.springboot.wabit.validator.ProductValidator;

@RestController
public class TeamMembersController {

	@Autowired
	private TeamMemberDAO teamMemberDAO;

//	@CrossOrigin(origins = "http://localhost:4200")
	//@CrossOrigin(origins = "*", allowedHeaders = "*")


	// Our Products //

	@PostMapping(value = "/teamMembers")
	public List<TeamMembers> addTeamMembers(@RequestBody TeamMembers teamMembers) {
		// System.out.println(user);

		teamMemberDAO.addTeamMember(teamMembers);
		// System.out.println(info.getPlanName().toString());
		// System.out.println(info.getInfo().toString());

		// productDAO.get(ourProducts.getProduct_name());
		// System.out.println(info.getPlanName().toString());
		List<TeamMembers> sign2 = teamMemberDAO.getAllTeamMembers();
		// List<SignUpUser> sign15 = signupDAO.loginbyName(signUpUser.getName());

		// List<User> sign1 = new ArrayList<>();

		System.out.println(sign2.toString());
		return sign2;
	}

	@RequestMapping(value = "/team", method=RequestMethod.POST)
	public String manageTeamMember(
			@RequestParam(name = "data", required = false) String add,
			//@RequestBody AddProduct addProduct,
			@RequestParam(name="file") MultipartFile file,
			//@RequestParam(name = "file", required = false) MultipartFile file ,
		  HttpServletRequest request) {
 		
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String> map1 = new HashMap<String, String>();
		//	addProduct.replaceAll("[{}\"]", "").split(",").forEach(string -> { String[] c = string.split(":"); map.put(c[0], c[1]); });
		String theString = "{\"name\":\"m\",\"brand\":\"m\",\"description\":\"m\"}";
		List<String> addProduct = Arrays.asList(add.split("\\s*,\\s*"));
		Map<String, String> finishedMap = new HashMap<>();
 
		for (String str : addProduct) {
			String st = str.replaceAll("[{}\"]", "");
			String[] c = st.split(":");
			//System.out.println(Arrays.toString(c));
			map.put(c[0], c[1]);
		}
 
		//System.out.println("finishedMap "+ map);
		
/*		map1.put("vinay", "1");
 		map1.put("v", "2");
		for(String str: addProduct){ 
			System.out.println(str); // {"name":"m"
			String st = str.replaceAll("[{}]", "");
	 		String[] c = st.split(":"); 
			map.put(c[0], c[1]);	 
		 }
*/		
		// {"name":"m", "brand":"m", "description":"m"}
		// {"name" = "m", "brand" = "m", "description" = "m"}
			
		//System.out.println("addProduct "+ add); 
 		//System.out.println("addProduct "+ addProduct);
		//System.out.println("addProduct "+ file.getOriginalFilename());
		//System.out.println("map "+ theOtherString);
	
  
		AddMemberDetails detail = new AddMemberDetails();
		TeamMembers mMember = detail.addmember(map, file);
		//System.out.println("mProduct "+ mProduct);
	 
		// mandatory file upload check
		if(mMember.getId() == 0) {
			new ProductValidator().validateTeamImage(mMember);
		}
		else {
			// edit check only when the file has been selected
			if(!mMember.getFile().getOriginalFilename().equals("")) {
				new ProductValidator().validateTeamImage(mMember);
			}			
		}
		  
		
		if(mMember.getId() == 0 ) {
			System.out.println("product id: "+mMember.getId());

			teamMemberDAO.addTeamMember(mMember);
			
		}
		else {
			teamMemberDAO.update(mMember);
		}
	
		 //upload the file
//		 if(!mProduct.getFile().getOriginalFilename().equals("") ){
//			FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode()); 
//		 }
 	 
		 if(!mMember.getFile().getOriginalFilename().equals("")) {
			 FileUploadUtility.uploadFile(request, mMember.getFile(), mMember.getCode());
		 }
		 
		return "redirect:/manage/product?success=product";
	}
 	
	@RequestMapping(value = "/teamMembers/info")
	public List<TeamMembers> getAllOurproduct() {

		List<TeamMembers> planinfo = teamMemberDAO.getAllTeamMembers();
		// System.out.println(planinfo.toString());
		return planinfo;
	}

 
	@DeleteMapping("/DeleteteamMember/{id}")
	public void deleteOurproduct(@PathVariable int id) {
		teamMemberDAO.deleteTeamMembers(id);
		System.out.println("this is delete");
	}

 }
