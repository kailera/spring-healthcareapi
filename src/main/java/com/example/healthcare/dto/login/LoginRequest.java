package com.example.healthcare.dto.login;

import com.example.healthcare.enuns.Especialidade;
import com.example.healthcare.enuns.NivelEducacional;
import com.example.healthcare.enuns.TipoOrganizacao;
import com.example.healthcare.enuns.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(

        String email,
        String password

) {

}