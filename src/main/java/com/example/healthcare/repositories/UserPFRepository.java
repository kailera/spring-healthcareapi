package com.example.healthcare.repositories;

import com.example.healthcare.models.UserPF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserPFRepository extends JpaRepository<UserPF, UUID> {


    boolean existsByCpf(String cpf);


}
