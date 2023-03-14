package com.keccoredump.demo.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.keccoredump.demo.entity.VerifiedUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keccoredump.demo.dto.OtpDto;

@Service
public class OtpServiceImpl implements OtpService{

	@Autowired
	VerifiedUsersService verifiedUsersService;
	
	Map<String, Integer> loginOtp = new HashMap<>();

	Map<String, Integer> forgotPasswordOtp = new HashMap<>();

	@Override
	public int generateOtp(String email) {
		Random random = new Random();
        int OTP = 100000 + random.nextInt(900000);
        loginOtp.put(email, OTP);
        return OTP;
	}

	@Override
	public boolean verifyOtp(OtpDto otpDto) {
		
		int receivedOtp = otpDto.getOtp();
		
		int generatedOtp = loginOtp.get(otpDto.getEmail());
		
		if(receivedOtp == generatedOtp) {
			verifiedUsersService.saveVerifiedUser(new VerifiedUsers(otpDto.getEmail()));
			loginOtp.remove(otpDto.getEmail());
			return true;
		}
		return false;
	}

	@Override
	public int generateOtpForForgotPassword(String email) {
		Random random = new Random();
		int OTP = 100000 + random.nextInt(900000);
		forgotPasswordOtp.put(email, OTP);
		return OTP;
	}

	@Override
	public boolean verifyOtpForForgotPassword(OtpDto otpDto) {
		int receivedOtp = otpDto.getOtp();

		int generatedOtp = forgotPasswordOtp.get(otpDto.getEmail());

		if(receivedOtp == generatedOtp) {
			forgotPasswordOtp.remove(otpDto.getEmail());
			return true;
		}
		return false;
	}

}
