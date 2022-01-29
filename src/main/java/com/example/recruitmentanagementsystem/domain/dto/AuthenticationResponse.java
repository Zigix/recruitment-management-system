package com.example.recruitmentanagementsystem.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.file.attribute.UserDefinedFileAttributeView;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String JwtToken;
    private UserView userView;
}
