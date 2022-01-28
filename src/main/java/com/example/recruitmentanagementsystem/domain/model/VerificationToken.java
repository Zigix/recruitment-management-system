package com.example.recruitmentanagementsystem.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tokens")
@Getter
@Setter
public class VerificationToken implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;

    @OneToOne
    private User user;
}
