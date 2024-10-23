package com.example.healthcare.repository;

import com.example.healthcare.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

public interface OrganizationRepository extends JpaRepository<Organization, UUID> {
    boolean existsByCnpj(String cnpj);

    Optional<Organization> findByCnpj(String cnpj);

}
