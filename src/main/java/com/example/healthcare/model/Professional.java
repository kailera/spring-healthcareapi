package com.example.healthcare.model;

import com.example.healthcare.enuns.Especialidade;
import com.example.healthcare.enuns.NivelEducacional;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "tb_professional")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "professional")
public class Professional extends User {

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String cnpj;

    @Column
    private String nome;

    @Column
    private Especialidade especialidade;

    @Column
    private NivelEducacional nivelEducacional;

    @Column
    private boolean isRegistered;

    @Column
    private boolean isAvailable;
}
