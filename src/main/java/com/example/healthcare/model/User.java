package com.example.healthcare.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;

import java.io.Serializable;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@MappedSuperclass
public class User  {


    @Id
    @GeneratedValue(generator =  "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;

    @Column
    private String phone;

    @Column
    private String email;

    @Column
    private LocalDateTime createAt;
}
