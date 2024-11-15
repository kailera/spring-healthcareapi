package com.example.healthcare.model;

import com.example.healthcare.enuns.ContractStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "contract")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(EnumType.STRING)
    private ContractStatus contractStatus = ContractStatus.PENDING;

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



}
