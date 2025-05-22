package com.LandingPage.webapplication.service;

public interface EmailService {
    void sendEmail(String to, String subject, String text);
    void sendContactFormEmail(String name, String email, String message);
} 