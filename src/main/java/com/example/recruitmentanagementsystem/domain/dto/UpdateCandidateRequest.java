package com.example.recruitmentanagementsystem.domain.dto;

import com.example.recruitmentanagementsystem.domain.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCandidateRequest {
    private String firstName;
    private String lastName;
    private String certificateNumber;
    private Address address;
}
