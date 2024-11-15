package com.example.healthcare.dto;

import com.example.healthcare.enuns.TipoOrganizacao;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignupOrganizationRequestDTO(
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be blank")
    String email,

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    String password,

    @NotBlank
    String razaoSocial,

    @NotBlank(message= "CNPJ is required")
    String cnpj,

    @NotBlank(message = "Tipo de organização is required")
    TipoOrganizacao tipoOrganizacao
    ) {

}
