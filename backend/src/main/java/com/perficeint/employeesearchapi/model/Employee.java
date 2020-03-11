package com.perficeint.employeesearchapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document("employees")
public class Employee implements Serializable {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private Address address;
    private String contactEmail;
    private String companyEmail;
    private String birthDate;
    private String hireDate;
    private RoleEnum businessUnit;
    private List<Skill> skills;
    private Employee assignedTo;

}
