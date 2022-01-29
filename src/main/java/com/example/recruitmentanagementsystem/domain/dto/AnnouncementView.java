package com.example.recruitmentanagementsystem.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementView {
    private Long id;
    private String title;
    private String priority;
    private String text;
    private Instant createdDate;
    private String creator;
}
