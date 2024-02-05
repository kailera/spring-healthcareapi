package com.example.healthcare.models;

import com.example.healthcare.enums.EducationStage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@ToString
@Table(name="HEALTHCARE_JOB")
public class HealthCareJob implements Serializable {
    private static final long serialversionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 30)
    private String  titleJob;

    @Column(nullable = false, length = 75)
    private String descriptionJob;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EducationStage requiredLevel;

    @Column(nullable = false)
    private double salary;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_uuid", nullable = false)
    @JsonIgnore
    private UserPJ UserPJ;

    @Column(nullable = false)
    private LocalDateTime createAt;

}
