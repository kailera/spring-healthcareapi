package com.example.healthcare.models;

import com.example.healthcare.enums.EducationStage;
import com.example.healthcare.enums.NHSRoles;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@Table(name = "USER_PF")
@ToString
public class UserPF extends User  {

    @CPF
    private String cpf;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EducationStage educationStage;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NHSRoles nhsRoles;
}
