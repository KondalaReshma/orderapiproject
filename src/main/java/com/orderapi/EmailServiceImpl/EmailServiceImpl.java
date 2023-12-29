package com.orderapi.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.orderapi.service.EmailService;


@Service
public class EmailServiceImpl  implements EmailService{
	
	
	@Autowired 
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}") private String sender;

	
	public String sendSimpleMail(String to) {
	
		
		
		try {
			 
            
            SimpleMailMessage mailMessage = new SimpleMailMessage();
 
            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(to);
            mailMessage.setText("hey welcome to order zone");
            mailMessage.setSubject("order successfullly created");
 
            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }
 
        // Catch block to handle the exceptions
        catch (Exception e) {
           
        	return "Error while Sending Mail";
        	
        	
		
        }}
	}

