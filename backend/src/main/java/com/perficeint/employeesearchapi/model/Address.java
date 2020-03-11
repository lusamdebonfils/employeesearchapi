package com.perficeint.employeesearchapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Address implements Serializable {
    //UUID.randomUUID()
    private UUID _id;
    private String street;
    private String suite;
    private String city;
    private String region;
    private String postal;
    private String country;

}
