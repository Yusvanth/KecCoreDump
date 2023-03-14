package com.keccoredump.demo.service;

import com.keccoredump.demo.dto.OtpDto;

public interface OtpService {

	public int generateOtp(String email);
	
	public boolean verifyOtp(OtpDto otpDto);

	public int generateOtpForForgotPassword(String email);

	public boolean verifyOtpForForgotPassword(OtpDto otpDto);
}
