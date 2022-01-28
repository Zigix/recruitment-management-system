package com.example.recruitmentanagementsystem.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "candidate_data")
@Getter
@Setter
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PESEL @NotNull
    private String pesel;
    private String certificateNumber;

    @Embedded
    private Address address;

    @NotNull
    @OneToOne
    private User user;
}
