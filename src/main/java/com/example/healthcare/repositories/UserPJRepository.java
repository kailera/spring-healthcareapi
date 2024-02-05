package com.example.healthcare.repositories;

import com.example.healthcare.models.UserPJ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserPJRepository extends JpaRepository<UserPJ, UUID> {

}

