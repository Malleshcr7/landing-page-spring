package com.LandingPage.webapplication.controller;

import com.LandingPage.webapplication.dto.ContactMessageDTO;
import com.LandingPage.webapplication.entity.ContactMessage;
import com.LandingPage.webapplication.service.ContactMessageService;
import com.LandingPage.webapplication.service.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ContactMessageController {

    private final ContactMessageService contactMessageService;
    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<String> createMessage(@Valid @RequestBody ContactMessageDTO messageDTO) {
        try {
            emailService.sendContactFormEmail(
                messageDTO.getName(),
                messageDTO.getEmail(),
                messageDTO.getMessage()
            );
            return ResponseEntity.ok("Message sent successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to send message: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<ContactMessage>> getAllMessages() {
        List<ContactMessage> messages = contactMessageService.getAllMessages();
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/unread")
    public ResponseEntity<List<ContactMessage>> getUnreadMessages() {
        List<ContactMessage> messages = contactMessageService.getUnreadMessages();
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/unread/count")
    public ResponseEntity<Long> getUnreadCount() {
        long count = contactMessageService.getUnreadCount();
        return ResponseEntity.ok(count);
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<ContactMessage> markAsRead(@PathVariable Long id) {
        ContactMessage message = contactMessageService.markAsRead(id);
        return ResponseEntity.ok(message);
    }
} 