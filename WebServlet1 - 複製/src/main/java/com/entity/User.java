package com.entity;

public class User {
		private String id;
		private String name;
		private int phone;
		private String address;
		private String description;
		
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
		@Override
		public String toString() {
			return "User [id=" + id + ", name=" + name + ", phone=" + phone + ", address=" + address + ", description="
					+ description + "]";
		}
		
		public User(String id, String name,int phone ,String address, String description) {
			this.id = id;
			this.name = name;
			this.phone = phone;
			this.address = address;
			this.description = description;
		}
		
		
		
}
