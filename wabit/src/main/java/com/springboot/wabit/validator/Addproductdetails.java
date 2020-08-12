package com.springboot.wabit.validator;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.springboot.wabit.dto.OurProducts;
 
public class Addproductdetails {

	public OurProducts addproduct(Map<String, String> prod , MultipartFile file) {
		
		//System.out.println("prod "+ prod);
		//System.out.println("1");
		OurProducts product = new OurProducts();
		//System.out.println("2");
		//String imageDataBytes = prod.getFile();
		
	//	byte[] imageByte=Base64.decodeBase64(imageDataBytes);

	//	MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
		//System.out.println("3");
 		//product.setCode((String) prod.get("Code"));
 		//System.out.println("4");
		product.setProduct_name(prod.get("product_name"));
		product.setProduct_info(prod.get("product_info"));
		product.setFile(file);
		//product.setCode((String) prod.get("Code"));
 		
//		product.setName(prod.get("name"));
//		//System.out.println("5");
//		product.setBrand(prod.get("brand"));
//		//System.out.println("6" + prod.get("description"));
//		product.setDescription(prod.get("description"));
//		//System.out.println("7" +   prod.get("unitPrice"));
//		product.setUnitPrice( Double.parseDouble( (String) prod.get("unitPrice")));
//		//System.out.println("8");
//		product.setQuantity(Integer.parseInt((String) prod.get("quantity")));
//		//System.out.println("9");
//		product.setCategoryId( Integer.parseInt((String) prod.get("categoryId")));
//		//System.out.println("10");
//		product.setSupplierId( Integer.parseInt((String) prod.get("supplierId")) );
//		//System.out.println("11");
//		product.setPurchases(12);
//		//System.out.println("12");
//		product.setViews( 12 );
		//System.out.println("13");
		//product.setFile(file);
		//System.out.println("14");
		return product;
		

/*		String theString = "{\"name\":\"m\",\"brand\":\"m\",\"description\":\"m\"}";
		List<String> addProduct = Arrays.asList(theString.split("\\s*,\\s*"));
		Map<String, String> finishedMap = new HashMap<>();
 
		for (String str : addProduct) {
			String st = str.replaceAll("[{}\"]", "");
			String[] c = st.split(":");
			System.out.println(Arrays.toString(c));
			finishedMap.put(c[0], c[1]);
		}
 
		System.out.println(finishedMap);*/
		
	
	}
	
}
