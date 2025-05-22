package com.LandingPage.webapplication.repository;

import com.LandingPage.webapplication.entity.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
    List<ContactMessage> findAllByOrderByCreatedAtDesc();
    List<ContactMessage> findByIsReadFalseOrderByCreatedAtDesc();
    long countByIsReadFalse();
} 