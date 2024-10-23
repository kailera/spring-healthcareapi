package com.example.healthcare.model;

import com.example.healthcare.enuns.TipoOrganizacao;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.List;
import java.util.UUID;

@Entity(name="tb_organization")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
@Table(name = "organization")
public class Organization {

    @Id
    @GeneratedValue(generator =  "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;

    @Column
    private String cnpj;

    @Column
    private String razaoSocial;

    @Column
    private TipoOrganizacao tipoOrganizacao;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Work> works;


    public Organization(UUID uuid, String cpnj, String razaoSocial, TipoOrganizacao tipoOrganizacao) {
    }
}
