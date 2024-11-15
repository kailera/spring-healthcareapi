package com.example.healthcare.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Types;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="tb_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED) // Estratégia JOINED para dividir as tabelas
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;


    /**informações comuns **/
    @Column(unique = true)
    private String email;

    @Column (nullable = true, unique = true)
    private String phone;

    private String password;


    /**informações de controle e verificação **/

    @Column(nullable = true)
    private String verificationCode;

    @Column
    @Builder.Default
    private boolean isRegistered = false;

    @Column
    @Builder.Default
    private boolean isAvailable = false;

    @CreatedDate
    private Instant createAt;


    /** Relacionamentos **/

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="tb_user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private List<Role> roles;


    public User(String email, String hashedPassword) {
    }


}
