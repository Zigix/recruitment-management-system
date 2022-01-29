package com.example.recruitmentanagementsystem.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamResultView {
    private Long id;
    private String subject;
    private boolean extended;
    private Integer percentResult;
    private Long candidateId;
}
