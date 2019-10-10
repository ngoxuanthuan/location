package com.demo.location.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Document
@Getter
@Setter
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private double lat;
    private double lng;
    private String locationName;
}
