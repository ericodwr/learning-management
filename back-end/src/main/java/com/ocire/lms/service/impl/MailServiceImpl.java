package com.ocire.lms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ocire.lms.service.MailService;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendEmail(String to, String subject, String text) {

		final SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(to);

		msg.setSubject(subject);
		msg.setText(text);

		javaMailSender.send(msg);

	}

}
