package com.keccoredump.demo.dto;

public class LoginResponceDto {

	private String jwtToken;

	public LoginResponceDto(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	
	
}
