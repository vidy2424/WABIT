package com.springboot.wabit.dto;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "team_members")

public class TeamMembers {

	private static final long serialVersionUID = 1681261145191719508L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String member_name;
	private String profile_name;
	private String code;

	@Transient
	private MultipartFile file;
			
	// default constructor
		public TeamMembers() {
			
			this.code = "PRD" + UUID.randomUUID().toString().substring(26).toUpperCase();
			
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getMember_name() {
			return member_name;
		}

		public void setMember_name(String member_name) {
			this.member_name = member_name;
		}

		public String getProfile_name() {
			return profile_name;
		}

		public void setProfile_name(String profile_name) {
			this.profile_name = profile_name;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public MultipartFile getFile() {
			return file;
		}

		public void setFile(MultipartFile file) {
			this.file = file;
		}
	
}
