package com.keccoredump.demo.handlers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

	public static ResponseEntity<Object> generateResponse(String msg, HttpStatus status){
		Map<String, Object> map = new HashMap<>();
		map.put("status", status.value());
		map.put("message", msg);
		
		return new ResponseEntity<Object>(map,status);
		
	}
	
	public static ResponseEntity<Object> generateResponse(String msg, HttpStatus status,Object object){
		Map<String, Object> map = new HashMap<>();
		map.put("status", status.value());
		map.put("message", msg);
		map.put("data", object);
		return new ResponseEntity<Object>(map,status);
		
	}
}
