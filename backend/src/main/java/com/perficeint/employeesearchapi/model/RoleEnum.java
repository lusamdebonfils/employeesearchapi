package com.perficeint.employeesearchapi.model;

public enum RoleEnum {
    TECHNICAL_CONSULTANT("Technical Consultant"),
    PROJECT_MANAGER("Project Manager"),
    DIRECTOR("Director"),
    CHIEF("Chief");

    private String value;

    RoleEnum(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }

}
