package com.example.recruitmentanagementsystem.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoursePaymentView {
    private String name;
    private String department;
    private Long id;
    private String accountNumber;
    private Double amount;
    private boolean paid;
}
