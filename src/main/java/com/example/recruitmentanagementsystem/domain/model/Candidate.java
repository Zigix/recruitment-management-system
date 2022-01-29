package com.example.recruitmentanagementsystem.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "candidate_data")
@Getter
@Setter
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @PESEL @NotNull
    private String pesel;
    private String certificateNumber;

    @Embedded
    private Address address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "candidate")
    private List<ExamResult> examResults;

    @NotNull
    @OneToOne
    private User user;
}
