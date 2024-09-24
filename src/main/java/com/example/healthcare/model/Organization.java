package com.example.healthcare.model;

import com.example.healthcare.enuns.TipoOrganizacao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="tb_organization")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "organization")
public class Organization extends User {

    @Column
    private String cnpj;

    @Column
    private String razaoSocial;

    @Column
    private TipoOrganizacao tipoOrganizacao;
}
