package com.example.healthcare.dto;

import com.example.healthcare.enuns.TipoOrganizacao;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrganizationResponseDTO {

    private String razaoSocial;
    private TipoOrganizacao tipoOrganizacao;

}
