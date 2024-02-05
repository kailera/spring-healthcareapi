package com.example.healthcare.enums;


public enum OrganizationType {
    HOSPITAL("hospital"),
    MEDICAL_CLINIC("medical_clinic"),
    EMERGENCY("emergency");

    private String description;
    OrganizationType(String description){
        this.description = description;
    }
}
