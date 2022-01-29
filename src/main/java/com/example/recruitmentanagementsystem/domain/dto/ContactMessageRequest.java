package com.example.recruitmentanagementsystem.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactMessageRequest {
    private String recipient;
    private String subject;
    private String message;
}
