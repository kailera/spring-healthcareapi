package com.example.healthcare.dto;

import com.example.healthcare.enuns.TipoOrganizacao;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrganizationResponseDTO {

    private UUID id;
    private String razaoSocial;
    private TipoOrganizacao tipoOrganizacao;

}
