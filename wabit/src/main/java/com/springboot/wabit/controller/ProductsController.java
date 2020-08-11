package com.springboot.wabit.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.springboot.wabit.dao.ProductDAO;
import com.springboot.wabit.dto.ClientProducts;
import com.springboot.wabit.dto.FileInfo;
import com.springboot.wabit.dto.OurProducts;
import com.springboot.wabit.dto.ResponseMessage;
import com.springboot.wabit.util.FileUploadUtility;
import com.springboot.wabit.validator.Addproductdetails;
import com.springboot.wabit.validator.ProductValidator;


@RestController
public class ProductsController {

	@Autowired
	private ProductDAO productDAO;

//	@CrossOrigin(origins = "http://localhost:4200")
	//@CrossOrigin(origins = "*", allowedHeaders = "*")


	// Our Products //

	@PostMapping(value = "/ourProducts")
	public List<OurProducts> addOurproduct(@RequestBody OurProducts ourProducts) {
		// System.out.println(user);

		productDAO.addOurProducts(ourProducts);
		// System.out.println(info.getPlanName().toString());
		// System.out.println(info.getInfo().toString());

		// productDAO.get(ourProducts.getProduct_name());
		// System.out.println(info.getPlanName().toString());
		List<OurProducts> sign2 = productDAO.getAllOurProducts();
		// List<SignUpUser> sign15 = signupDAO.loginbyName(signUpUser.getName());

		// List<User> sign1 = new ArrayList<>();

		System.out.println(sign2.toString());
		return sign2;
	}

	@RequestMapping(value = "/product", method=RequestMethod.POST)
	public String managePostProduct(
			//@RequestParam(name = "data", required = false) String add,
			//@RequestBody AddProduct addProduct,
			@RequestParam(name="file") MultipartFile file,
			//@RequestParam(name = "file", required = false) MultipartFile file ,
		  HttpServletRequest request) {
 		
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String> map1 = new HashMap<String, String>();
		//	addProduct.replaceAll("[{}\"]", "").split(",").forEach(string -> { String[] c = string.split(":"); map.put(c[0], c[1]); });
 		System.out.println("file : " + file);
		String theString = "{\"name\":\"m\",\"brand\":\"m\",\"description\":\"m\"}";
//		List<String> addProduct = Arrays.asList(add.split("\\s*,\\s*"));
		Map<String, String> finishedMap = new HashMap<>();
 
//		for (String str : addProduct) {
//			String st = str.replaceAll("[{}\"]", "");
//			String[] c = st.split(":");
//			//System.out.println(Arrays.toString(c));
//			map.put(c[0], c[1]);
//		}
 
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
	
  
		Addproductdetails detail = new Addproductdetails();
		OurProducts mProduct = detail.addproduct(map, file);
		//System.out.println("mProduct "+ mProduct);
	 
		// mandatory file upload check
		if(mProduct.getId() == 0) {
			new ProductValidator().validate(mProduct);
		}
		else {
			// edit check only when the file has been selected
			if(!mProduct.getFile().getOriginalFilename().equals("")) {
				new ProductValidator().validate(mProduct);
			}			
		}
		  
		
		if(mProduct.getId() == 0 ) {
			productDAO.addOurProducts(mProduct);
		}
		else {
	productDAO.update(mProduct);
		}
	
		 //upload the file
//		 if(!mProduct.getFile().getOriginalFilename().equals("") ){
//			FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode()); 
//		 }
 	 
		 if(!mProduct.getFile().getOriginalFilename().equals("")) {
			 FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
		 }
		 
		return "redirect:/manage/product?success=product";
	}

	
	@RequestMapping(value = "/ourProducts/info")
	public List<OurProducts> getAllOurproduct() {

		List<OurProducts> planinfo = productDAO.getAllOurProducts();
		// System.out.println(planinfo.toString());
		return planinfo;
	}

	@PutMapping("/ourProducts/{id}")
	public String updateOurproducts(@RequestBody OurProducts ourProducts, @PathVariable int id) {

		// List<Info> information = signupDAO.getbyID(id);

		System.out.println("this is id");

		ourProducts.setId(id);

		productDAO.update(ourProducts);

		// info1.update(info1);

		return "";
	}

	@DeleteMapping("/infoDeleteprod/{id}")
	public void deleteOurproduct(@RequestBody OurProducts ourProducts, @PathVariable int id) {
		productDAO.deleteOurProducts(ourProducts);
		System.out.println("this is delete");
	}

	// Client Products //

	@PostMapping(value = "/clientProducts")
	public List<ClientProducts> addClientproduct(@RequestBody ClientProducts clientProducts) {
		// System.out.println(user);

		productDAO.addClientProducts(clientProducts);
		// System.out.println(info.getPlanName().toString());
		// System.out.println(info.getInfo().toString());

		// productDAO.get(ourProducts.getProduct_name());
		// System.out.println(info.getPlanName().toString());
		List<ClientProducts> client = productDAO.getAllCLientproducts();
		// List<SignUpUser> sign15 = signupDAO.loginbyName(signUpUser.getName());

		// List<User> sign1 = new ArrayList<>();

		System.out.println(client.toString());
		return client;
	}

	@RequestMapping(value = "/clientProducts/products")
	public List<ClientProducts> getAllCLientproduct() {

		List<ClientProducts> clientProductsinfo = productDAO.getAllCLientproducts();
		// System.out.println(planinfo.toString());
		return clientProductsinfo;
	}

	@PutMapping("/clientProducts/{id}")
	public String updateClientproduct(@RequestBody ClientProducts clientProducts, @PathVariable int id) {

		// List<Info> information = signupDAO.getbyID(id);

		System.out.println("this is id");

		clientProducts.setId(id);

		productDAO.updateClientProducts(clientProducts);

		// info1.update(info1);

		return "";
	}

	@DeleteMapping("/clientProducts/{id}")
	public void deleteClientproduct(@RequestBody ClientProducts clientProducts, @PathVariable int id) {
		productDAO.deleteClientProducts(clientProducts);
		System.out.println("this is delete");
	}

 	  @PostMapping("/upload")
	  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
	    String message = "";
	    try {
	    	productDAO.save(file);

	      message = "Uploaded the file successfully: " + file.getOriginalFilename();
	      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	    } catch (Exception e) {
	      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	    }
	  }

	  @GetMapping("/files")
	  public ResponseEntity<List<FileInfo>> getListFiles() {
	    List<FileInfo> fileInfos = productDAO.loadAll().map(path -> {
	      String filename = path.getFileName().toString();
	      String url = MvcUriComponentsBuilder
	          .fromMethodName(ProductsController.class, "getFile", path.getFileName().toString()).build().toString();

	      return new FileInfo(filename, url);
	    }).collect(Collectors.toList());

	    return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
	  }

	  @GetMapping("/files/{filename:.+}")
	  @ResponseBody
	  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
	    Resource file = productDAO.load(filename);
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	  }
	
}
