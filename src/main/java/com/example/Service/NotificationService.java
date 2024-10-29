package com.example.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class NotificationService {
	
	@Autowired
	private JavaMailSender mail;
	
	public void sendGoalNotification(String to,String subject,String text)
	{
		try {
			
			SimpleMailMessage message=new SimpleMailMessage();
			message.setTo(to);
			message.setSubject(subject);
			message.setText(text);
			mail.send(message);
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

}
