package com.example.healthcare.models;

import com.example.healthcare.enums.OrganizationType;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
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
