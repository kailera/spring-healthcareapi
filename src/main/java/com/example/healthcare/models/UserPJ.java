package com.example.healthcare.models;

import com.example.healthcare.enums.OrganizationType;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Table(name="USER_PJ")
public class UserPJ extends User {


    @Column(nullable = false, length = 30)
    private String organizationName;

    @Column(nullable = false, length = 20)
    private String BusinessNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrganizationType organizationType;

}
