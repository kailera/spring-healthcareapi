package com.example.healthcare.enums;

/*
NHS Roles: Especialidades
 */
public enum NHSRoles {
    NURSING("nursing"),
    PHARMACY("pharmacy"),
    MIDWIFERY("midwifery"),
    AMBULANCETEAM("ambulanceteam"),
    ALLIEDHEALTPROFESSIONAL("alliedhealthprofessional");

    private String description;
    NHSRoles (String descrtiption){this.description = descrtiption;}
}
