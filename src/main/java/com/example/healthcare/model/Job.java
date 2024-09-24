package com.example.healthcare.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.*;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name="tb_job")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Job {

    private static final long serialversionUID = 1L;

    @Id
    @GeneratedValue(generator =  "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String salary;

    @Column
    private LocalDateTime createAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "organization_id", nullable = false)
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Organization organization;


}
