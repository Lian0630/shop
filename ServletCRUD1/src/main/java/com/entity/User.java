package com.entity;

public class User {
		
	protected String id;
	protected String name;
	protected int phone;
	protected String address;
	protected String description;
	
	public User() {
		
	}
	
	public User(String name, int phone,String address, String description) {
		super();
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.description = description;
	}

	public User(String id, String name, int phone,String address, String description) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.description = description;		
	}
	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
