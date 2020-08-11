package com.springboot.wabit.dto;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Info")
public class Info {

	private static final long serialVersionUID = 1681261145191719508L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String planName;
	private String websiteType;
	//private String info;
	private  ArrayList <Object> info = new ArrayList<Object>();
	private String note;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getWebsiteType() {
		return websiteType;
	}
	public void setWebsiteType(String websiteType) {
		this.websiteType = websiteType;
	}
	  
	public ArrayList<Object> getInfo() {
		return info;
	} 
	public void setInfo(ArrayList<Object> info) {
		this.info = info;
	}
	public String getNote() { 
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}
