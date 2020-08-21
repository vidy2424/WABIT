package com.springboot.wabit.validator;

import java.util.HashMap;
import java.util.Map;

import com.springboot.wabit.dto.ClientProducts;
import com.springboot.wabit.dto.OurProducts;
import com.springboot.wabit.dto.TeamMembers;

public class ProductValidator {

	public Map<String, Object> validate(Object target) {

		Map<String, Object> errors = new HashMap<String, Object>();
		OurProducts product = (OurProducts) target;
		if (product.getFile() == null || product.getFile().getOriginalFilename().equals("")) {
			errors.put("file", "Please select a file to upload!");
		}
		if (!(product.getFile().getContentType().equals("image/jpeg")
				|| product.getFile().getContentType().equals("image/png"))
				|| product.getFile().getContentType().equals("image/gif")) {
			errors.put("file", "Please select an image file to upload!");
		}
		return errors;
	}
	
	public Map<String, Object> validateclientproductimage(Object target) {

		Map<String, Object> errors = new HashMap<String, Object>();
		ClientProducts product = (ClientProducts) target;
		if (product.getFile() == null || product.getFile().getOriginalFilename().equals("")) {
			errors.put("file", "Please select a file to upload!");
		}
		if (!(product.getFile().getContentType().equals("image/jpeg")
				|| product.getFile().getContentType().equals("image/png"))
				|| product.getFile().getContentType().equals("image/gif")) {
			errors.put("file", "Please select an image file to upload!");
		}
		return errors;
	}

	public Map<String, Object> validateTeamImage(Object target) {

		Map<String, Object> errors = new HashMap<String, Object>();
		TeamMembers members = (TeamMembers) target;
		if (members.getFile() == null || members.getFile().getOriginalFilename().equals("")) {
			errors.put("file", "Please select a file to upload!");
		}
		if (!(members.getFile().getContentType().equals("image/jpeg")
				|| members.getFile().getContentType().equals("image/png"))
				|| members.getFile().getContentType().equals("image/gif")) {
			errors.put("file", "Please select an image file to upload!");
		}
		return errors;
	}

}
