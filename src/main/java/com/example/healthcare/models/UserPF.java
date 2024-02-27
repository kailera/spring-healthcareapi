package com.example.healthcare.models;

import com.example.healthcare.enums.EducationStage;
import com.example.healthcare.enums.NHSRoles;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserPF userPF = (UserPF) o;
        return getId() != null && Objects.equals(getId(), userPF.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
