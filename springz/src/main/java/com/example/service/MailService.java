package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.controller.LoginController;

@Service
public class MailService {

	@Autowired
	private JavaMailSender mailSender;
	
	public void registerMail(String mail, String key) {
			
		SimpleMailMessage email = new SimpleMailMessage();
		email.setFrom("hacerozturk9781@gmail.com");
		email.setTo(mail);
		email.setSubject("Kaydı Tamamla");
		email.setText("Kaydinizi tamamlmak icin asagidaki baglantiya tıklyınız.\n\n" + LoginController.url + "/reg/" + key);
		mailSender.send(email);
	}
	
}
