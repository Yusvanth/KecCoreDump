package com.keccoredump.demo.dto;

public class AuthenticationResponseDto {

	private String jwt;

	private String refreshToken;

	public AuthenticationResponseDto(String jwt, String refreshToken) {
		this.jwt = jwt;
		this.refreshToken = refreshToken;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}
