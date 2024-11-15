package com.example.healthcare.repository;

import com.example.healthcare.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    List<Contract> findContractByProfessionalId(Long id);

    List<Contract> findContractByOrganizationId(Long id);


}
