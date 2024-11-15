package com.example.healthcare.repository;

import com.example.healthcare.model.Professional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfessionalRepository extends JpaRepository<Professional, Long>, JpaSpecificationExecutor<Professional> {

    boolean existsByCpf(String cpf);

    Optional<Professional> findByCpf(String cpf);

    List<Professional> findByIsRegisteredTrue();
    List<Professional> findByIsAvailableTrue();

    List<Professional> findByIsRegisteredTrueAndIsAvailableTrue();
}
