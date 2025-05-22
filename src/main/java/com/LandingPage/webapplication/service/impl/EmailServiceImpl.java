package com.LandingPage.webapplication.service.impl;

import com.LandingPage.webapplication.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Override
    public void sendEmail(String to, String subject, String text) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            
            helper.setFrom("telugumallesh@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
            
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }

    @Override
    public void sendContactFormEmail(String name, String email, String message) {
        Context context = new Context();
        context.setVariable("name", name);
        context.setVariable("email", email);
        context.setVariable("message", message);

        String emailContent = templateEngine.process("contact-email", context);
        
        // Send to admin
        sendEmail("telugumallesh@gmail.com", 
                 "New Contact Form Submission from " + name,
                 emailContent);
        
        // Send confirmation to user
        String confirmationContent = "Thank you for contacting us, " + name + 
                                   ". We have received your message and will get back to you soon.";
        sendEmail(email, 
                 "Thank you for contacting us",
                 confirmationContent);
    }
} 