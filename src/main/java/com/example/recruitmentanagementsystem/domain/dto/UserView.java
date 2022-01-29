package com.example.recruitmentanagementsystem.domain.dto;

import com.example.recruitmentanagementsystem.domain.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserView {
    private Long id;
    private String email;
    private String username;
    private Instant createdDate;
    private Role role;
    private boolean enabled;
    private Long candidateId;
}
