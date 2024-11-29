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
@PrimaryKeyJoinColumn(name = "professional_id")
@Table(name="tb_professional")
@EqualsAndHashCode(callSuper=false)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Professional extends User {
    
    @Column(nullable = false)
    private String cpf;

    @Column(nullable = true)
    private String cnpj;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Enumerated(EnumType.STRING)
    private NivelEducacional nivelEducacional;


    @OneToMany(mappedBy = "professional", cascade = CascadeType.ALL)
    private List<Contract> contracts;


    public Professional(String cpf, String cnpj, Especialidade especialidade, NivelEducacional nivelEducacional) {
    }
}
