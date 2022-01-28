package com.example.recruitmentanagementsystem.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotificationEmail {
    private String from;
    private String to;
    private String subject;
    private String text;
}
