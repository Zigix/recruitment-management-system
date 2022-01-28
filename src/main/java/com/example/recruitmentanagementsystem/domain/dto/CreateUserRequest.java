package com.example.recruitmentanagementsystem.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    @PESEL @NotNull
    private String pesel;
    @Email @NotNull
    private String email;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
}
