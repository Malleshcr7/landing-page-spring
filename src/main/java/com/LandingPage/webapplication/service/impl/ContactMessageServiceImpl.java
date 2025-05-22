package com.LandingPage.webapplication.service.impl;

import com.LandingPage.webapplication.dto.ContactMessageDTO;
import com.LandingPage.webapplication.entity.ContactMessage;
import com.LandingPage.webapplication.repository.ContactMessageRepository;
import com.LandingPage.webapplication.service.ContactMessageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ContactMessageServiceImpl implements ContactMessageService {

    private final ContactMessageRepository contactMessageRepository;

    @Override
    public ContactMessage saveMessage(ContactMessageDTO messageDTO) {
        ContactMessage message = new ContactMessage();
        message.setName(messageDTO.getName());
        message.setEmail(messageDTO.getEmail());
        message.setMessage(messageDTO.getMessage());
        return contactMessageRepository.save(message);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContactMessage> getAllMessages() {
        return contactMessageRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContactMessage> getUnreadMessages() {
        return contactMessageRepository.findByIsReadFalseOrderByCreatedAtDesc();
    }

    @Override
    public ContactMessage markAsRead(Long id) {
        ContactMessage message = contactMessageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Message not found with id: " + id));
        message.setRead(true);
        return contactMessageRepository.save(message);
    }

    @Override
    @Transactional(readOnly = true)
    public long getUnreadCount() {
        return contactMessageRepository.countByIsReadFalse();
    }
} 