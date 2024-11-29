package com.example.healthcare.model;

import com.example.healthcare.enuns.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Table(name="tb_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED) // Estratégia JOINED para dividir as tabelas
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;


    /**informações comuns **/
    @Column(unique = true)
    private String email;

    @Column (nullable = true, unique = true)
    private String phone;

    @Column
    private String password;


    @Enumerated(EnumType.STRING)
    private UserRole role;


    /**informações de controle e verificação **/

    @Column
    private String verificationCode;

    @Column
    @Builder.Default
    private boolean isRegistered = false;

    @Column
    @Builder.Default
    private boolean isAvailable = false;

    @CreatedDate
    private Instant createAt;


    public User (Long id, String email, String password, String phone, UserRole role){
        this.id = id;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.PROFESSIONAL){
            return List.of(new SimpleGrantedAuthority("ROLE_PROFESSIONAL"), new SimpleGrantedAuthority("ROLE_USER"));
        } else if ( this.role == UserRole.ORGANIZATION) {
            return List.of(new SimpleGrantedAuthority("ROLE_ORGANIZATION"), new SimpleGrantedAuthority("ROLE_USER"));
        } else if (this.role == UserRole.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_USER"),
                    new SimpleGrantedAuthority("ROLE_PROFESSIONAL"),
                    new SimpleGrantedAuthority("ROLE_ORGANIZATION"));
        }else{
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }

    }

    @Override
    public String getUsername() {
        return email ;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
