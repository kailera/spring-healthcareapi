package com.example.healthcare.repository;

import com.example.healthcare.model.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface WorkRepository extends JpaRepository<Work, Long> {
    List<Work> findByOrganizationId(UUID organizationId);

    @Query("SELECT t FROM Work t WHERE t.title LIKE %:title%")
    List<Work> findByTitleContaining(@Param("title") String title);
}
