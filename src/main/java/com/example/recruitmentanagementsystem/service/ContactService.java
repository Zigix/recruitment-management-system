package com.example.recruitmentanagementsystem.service;

import com.example.recruitmentanagementsystem.domain.dto.ContactMessageRequest;
import com.example.recruitmentanagementsystem.domain.model.NotificationEmail;
import com.example.recruitmentanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactService {
    private final MailService mailService;
    private final UserRepository userRepository;
    private final AuthService authService;


    public void sendMessage(ContactMessageRequest request) {
        if (!userRepository.existsByEmail(request.getRecipient())) {
            throw new IllegalStateException("Invalid recipient email");
        }
        String loggedUserEmail = authService.getLoggedUser().getEmail();
        mailService.sendMail(new NotificationEmail(
                loggedUserEmail,
                request.getRecipient(),
                request.getSubject(),
                request.getMessage()));
    }
}
