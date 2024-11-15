package com.example.healthcare.model;

import com.example.healthcare.enuns.Especialidade;
import com.example.healthcare.enuns.NivelEducacional;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="tb_professional")
@EqualsAndHashCode(callSuper=false)

@NoArgsConstructor
@AllArgsConstructor
public class Professional extends User {


    @Column(nullable = false)
    private String cpf;

    @Column(nullable = true)
    private String cnpj;

    @Column
    private Especialidade especialidade;

    @Column
    private NivelEducacional nivelEducacional;


    @OneToMany(mappedBy = "professional", cascade = CascadeType.ALL)
    private List<Contract> contracts;


}
