package com.example.healthcare.repository;

import com.example.healthcare.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserResponsitory extends JpaRepository<User, Long> {

    UserDetails findByEmail(String email);


}

