package com.example.healthcare.repositories;

import com.example.healthcare.models.HealthCareJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HealthCareJobRepository extends JpaRepository<HealthCareJob, UUID> {
}
