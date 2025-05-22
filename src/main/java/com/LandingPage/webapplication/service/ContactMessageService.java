package com.LandingPage.webapplication.service;

import com.LandingPage.webapplication.dto.ContactMessageDTO;
import com.LandingPage.webapplication.entity.ContactMessage;

import java.util.List;

public interface ContactMessageService {
    ContactMessage saveMessage(ContactMessageDTO messageDTO);
    List<ContactMessage> getAllMessages();
    List<ContactMessage> getUnreadMessages();
    ContactMessage markAsRead(Long id);
    long getUnreadCount();
}