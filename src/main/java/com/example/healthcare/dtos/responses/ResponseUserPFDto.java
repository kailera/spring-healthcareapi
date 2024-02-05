package com.example.healthcare.dtos.responses;

import com.example.healthcare.dtos.requests.UserPFDto;
import com.example.healthcare.enums.EducationStage;
import com.example.healthcare.enums.NHSRoles;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)

public class ResponseUserPFDto {

    @NotBlank
    @Size(max = 50)
    private String address;

    @NotBlank
    private String phone;

    @NotBlank
    @Size(max = 30)
    private String name;

    @NotBlank
    @CPF
    private String cpf;

    @NotNull
    private EducationStage educationStage;

    @NotNull
    private NHSRoles nhsRoles;

    public static ResponseUserPFDto toDto(@Valid UserPFDto userPF){
        return new ResponseUserPFDto(
                userPF.getAddress(),
                userPF.getPhone(),
                userPF.getName(),
                userPF.getCpf(),
                userPF.getEducationStage(),
                userPF.getNhsRoles()
        );
    }
}
