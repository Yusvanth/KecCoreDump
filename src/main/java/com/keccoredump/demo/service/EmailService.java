package com.keccoredump.demo.service;

import com.keccoredump.demo.dto.EmailDto;

public interface EmailService {

	public boolean send(EmailDto emailDto);

	public boolean sendMailForOtp(String email, boolean forLogin);

	public boolean sendMailForAnswerNotification(String email,String answeredUser,String answer);


}
