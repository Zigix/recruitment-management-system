package com.example.recruitmentanagementsystem.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRecruiterRequest {
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
}
