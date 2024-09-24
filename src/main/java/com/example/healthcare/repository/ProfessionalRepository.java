package com.example.healthcare.repository;

import com.example.healthcare.model.Professional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfessionalRepository extends JpaRepository<Professional, UUID> {
}
