package com.example.healthcare.model;

import com.example.healthcare.enuns.TipoOrganizacao;
import com.example.healthcare.enuns.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@PrimaryKeyJoinColumn(name = "organization_id")
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

    @Enumerated(EnumType.STRING)
    private TipoOrganizacao tipoOrganizacao;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Work> works;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<Contract> contracts;

    // pesquise por contrecte page hibernate inheritance joined
    public Organization (Long id, String email, String password, String phone, UserRole role,String cnpj, String razaoSocial, TipoOrganizacao tipoOrganizacao){
        super(id, email, password, phone, role);
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.tipoOrganizacao = tipoOrganizacao;
    }

}
