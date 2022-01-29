package com.example.recruitmentanagementsystem.domain.dto;

import com.example.recruitmentanagementsystem.domain.model.Priority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAnnouncementRequest {
    private String title;
    private Priority priority;
    private String text;
}
