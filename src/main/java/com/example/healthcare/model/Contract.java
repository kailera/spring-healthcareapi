package com.example.healthcare.model;

import com.example.healthcare.enuns.ContractStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_contract")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Work work;

    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false )
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "professional_id", nullable = false )
    private Professional professional;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL)
    private List<Payment> paymentList = new java.util.ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ContractStatus contractStatus = ContractStatus.PENDING;

    @Column(nullable = false)
    private boolean professionalSigned = false;

    @Column(nullable = false)
    private boolean organizationSigned = false;

    @Column(nullable = false)
    private String contractDocumentPath ;

}
