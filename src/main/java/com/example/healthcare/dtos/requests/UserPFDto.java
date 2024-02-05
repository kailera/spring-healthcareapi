package com.example.healthcare.dtos.requests;


/*
    NotNull falha se o objeto é nulo; NotEmpty falha se é nulo ou vazia;
    NotBlank falha se for nulo e ainda se for vazia, após remover os espaços nas extremidades da String com trim
 */

import com.example.healthcare.enums.EducationStage;
import com.example.healthcare.enums.NHSRoles;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserPFDto {

    @NotBlank
    @CPF
    private String cpf;

    @NotBlank
    private String name;

    @NotBlank
    @Size(max = 50)
    private String address;

    @NotBlank
    private String phone;

    @NotNull
    private EducationStage educationStage;

    @NotNull
    private NHSRoles nhsRoles;



}
