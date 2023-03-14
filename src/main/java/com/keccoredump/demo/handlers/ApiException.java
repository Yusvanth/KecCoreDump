package com.keccoredump.demo.handlers;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException{
	
	private String code;
	
	private HttpStatus httpStatus;

	public ApiException(String code, HttpStatus httpStatus) {
		super(code);
		this.code = code;
		this.httpStatus = httpStatus;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	
	

}
