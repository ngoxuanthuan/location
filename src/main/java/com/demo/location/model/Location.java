package com.demo.location.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class Location {
    @Id
    private long id;
    private double lat;
    private double lng;
    private String locationName;
}
