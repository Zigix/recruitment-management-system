package com.example.recruitmentanagementsystem.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "announcements")
@Getter
@Setter
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @Enumerated(value = EnumType.STRING)
    private Priority priority;
    private String text;
    private Instant createdDate;

    @ManyToOne
    private User user;
}
