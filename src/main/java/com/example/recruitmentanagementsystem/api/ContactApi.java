package com.example.recruitmentanagementsystem.api;

import com.example.recruitmentanagementsystem.domain.dto.ContactMessageRequest;
import com.example.recruitmentanagementsystem.service.ContactService;
import com.example.recruitmentanagementsystem.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
public class ContactApi {
    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<Void> sendMessage(@RequestBody ContactMessageRequest request) {
        contactService.sendMessage(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
