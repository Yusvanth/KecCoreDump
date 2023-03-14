package com.keccoredump.demo.dto;

public class UserSignUpDTO {

	private String name;
	
	private String email;
	
	private String password;
	
	private String rollNo;
	
	private int year;

	String role;
	
	public UserSignUpDTO() {
		
	}
	
	public UserSignUpDTO(String name, String email, String password, String rollNo, int year) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.rollNo = rollNo;
		this.year = year;
		this.role = "USER";
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	
	
}
