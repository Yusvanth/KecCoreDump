package com.keccoredump.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.keccoredump.demo.dto.EmailDto;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmailServiceImpl implements EmailService{

	@Autowired
	OtpService otpService;

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public boolean send(EmailDto emailDto) {
		
		boolean isSent=false;
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(emailDto.getTo());
		mailMessage.setSubject(emailDto.getSubject());
		mailMessage.setText(emailDto.getBody());
		
		try {
			javaMailSender.send(mailMessage);
			isSent=true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return isSent;
	}

	@Override
	public boolean sendMailForOtp(String email, boolean forLogin) {
		int generatedOtp=0;
		if(forLogin)
			generatedOtp = otpService.generateOtp(email);
		else
			generatedOtp = otpService.generateOtpForForgotPassword(email);

		EmailDto emailDto = new EmailDto();
		emailDto.setTo(email);
		emailDto.setSubject("One Time Password for sign up - KEC CORE DUMP");
		emailDto.setBody("OTP - " + generatedOtp);

		System.out.println("HI"+emailDto.toString());
		return(send(emailDto));

	}

	@Override
	public boolean sendMailForAnswerNotification(String email, String answeredUser, String answer) {
		EmailDto emailDto = new EmailDto();
		emailDto.setTo(email);
		emailDto.setSubject("KEC CORE DUMP - You got answer to your question!!!");
		emailDto.setBody("Answered by : "+answeredUser+"\n\nAnswer\n\t"+answer);

		//System.out.println("HI"+emailDto.toString());
		return(send(emailDto));
	}
}
