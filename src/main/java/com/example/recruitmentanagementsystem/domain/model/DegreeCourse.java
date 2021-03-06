package com.example.recruitmentanagementsystem.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "degree_courses")
@Getter
@Setter
public class DegreeCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String department;
    private Integer numberOfPlaces;
}
