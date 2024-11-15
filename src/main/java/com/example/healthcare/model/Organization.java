package com.example.healthcare.model;

import com.example.healthcare.enuns.TipoOrganizacao;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_organization")
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
public class Organization extends User {
    @Column
    private String cnpj;

    @Column
    private String razaoSocial;

    @Column
    private TipoOrganizacao tipoOrganizacao;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Work> works;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<Contract> contracts;


    public Organization(UUID uuid, String cpnj, String razaoSocial, TipoOrganizacao tipoOrganizacao) {
    }


}
