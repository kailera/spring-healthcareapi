package com.example.healthcare.dtos.requests;

import com.example.healthcare.enums.EducationStage;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
public class HealthCareJobDto {

    @NotBlank
    @Size(max = 30)
    private String titleJob;

    @NotBlank
    @Size(max = 75)
    private String descriptionJob;

    @NotNull
    private EducationStage requiredLevel;

    @NotNull
    private double salary;

    @Valid
    private UUID userPJId;

}