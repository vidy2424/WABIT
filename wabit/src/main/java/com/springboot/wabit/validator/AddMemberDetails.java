package com.springboot.wabit.validator;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.springboot.wabit.dto.TeamMembers;

public class AddMemberDetails {
	
	public TeamMembers addmember(Map<String, String> member , MultipartFile file) {
		
		System.out.println("member "+ member);
		
 		TeamMembers team = new TeamMembers();
		
 
		System.out.println("product === " +team.getId());
		int id = member.containsKey("id") && !member.get("id").isEmpty() ? Integer.parseInt((String) member.get("id")) : 0;
		team.setId(id);
		team.setMember_name(member.get("member_name"));
		team.setProfile_name(member.get("profile_name"));
		team.setFile(file);
 		return team;	
	}
}
