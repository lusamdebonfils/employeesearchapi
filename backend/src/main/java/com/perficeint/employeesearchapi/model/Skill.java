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
public class Skill implements Serializable {
    //UUID uuid = UUID.randomUUID();
    private UUID _id;
    //private String id;
    private Field field;
    private Integer experience;
    private String summary;

}
