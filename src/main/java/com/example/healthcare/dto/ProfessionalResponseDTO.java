package com.example.healthcare.dto;

import com.example.healthcare.enuns.Especialidade;
import com.example.healthcare.enuns.NivelEducacional;
import com.example.healthcare.model.Professional;
import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link Professional} entity
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProfessionalResponseDTO implements Serializable {
    private String nome;
    private  String phone;
    private  String email;
    private  String cnpj;
    private Especialidade especialidade;
    private NivelEducacional nivelEducacional;
}