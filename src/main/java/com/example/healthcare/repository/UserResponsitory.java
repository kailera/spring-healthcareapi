package com.example.healthcare.repository;

import com.example.healthcare.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserResponsitory extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
