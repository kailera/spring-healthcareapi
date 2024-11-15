package com.example.healthcare.model;

import com.example.healthcare.enuns.NivelEducacional;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/*
Work é feito por uma organização que oferta um trabalho a ser preenchido por um profissional
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
public class Work {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private double salary;

    @Column
    private NivelEducacional nivelEducacional;

    @Column(nullable = true)
    private LocalDate initialDate;

    @Column(nullable = true)
    private LocalDate finalDate;

    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;



}
