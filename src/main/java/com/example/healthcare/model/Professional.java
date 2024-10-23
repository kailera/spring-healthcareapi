package com.example.healthcare.model;

import com.example.healthcare.enuns.Especialidade;
import com.example.healthcare.enuns.NivelEducacional;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;

@Entity(name = "tb_professional")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "professional")
public class Professional {

    @Id
    @GeneratedValue(generator =  "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;

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
