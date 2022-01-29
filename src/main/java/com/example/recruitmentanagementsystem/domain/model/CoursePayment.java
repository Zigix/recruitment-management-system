package com.example.recruitmentanagementsystem.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "candidate_course_payments")
@Getter
@Setter
public class CoursePayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private DegreeCourse degreeCourse;

    @OneToOne
    private Payment payment;

    @ManyToOne
    private Candidate candidate;
}
