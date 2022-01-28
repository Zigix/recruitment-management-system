package com.example.recruitmentanagementsystem.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class Address {
    private String street;
    private String homeNumber;
    private String city;
    private String postalCode;
}
