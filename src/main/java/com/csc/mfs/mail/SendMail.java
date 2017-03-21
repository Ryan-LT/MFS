package com.csc.mfs.mail;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class SendMail{

    private JavaMailSenderImpl mailSender;

    public void setMailSender(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }
    
    public void sendmailToClient(String from, String mailClient, String subject, String content) {
    	MimeMessage msg = mailSender.createMimeMessage();
    	MimeMessageHelper helper = new MimeMessageHelper(msg);
    	try {
    		helper.setFrom(new InternetAddress(from));
			helper.setTo(new InternetAddress(mailClient));
			helper.setSubject(subject);
			//helper.setText(content);
			msg.setContent(content, "text/html; charset=utf-8");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	mailSender.send(msg);
    }

}

