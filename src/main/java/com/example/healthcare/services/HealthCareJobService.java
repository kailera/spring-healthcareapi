package com.example.healthcare.services;

import com.example.healthcare.models.HealthCareJob;
import com.example.healthcare.repositories.HealthCareJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HealthCareJobService {

    @Autowired
    public HealthCareJobRepository healthCareJobRepository;

    // CRUD OPERATIONS
    @Transactional
    public HealthCareJob save(HealthCareJob healthCareJob) {
            return healthCareJobRepository.save(healthCareJob);
    }

    public List<HealthCareJob> findAll(){
        return healthCareJobRepository.findAll();
    }

    public Optional<HealthCareJob> findById(UUID id){
        return healthCareJobRepository.findById(id);
    }

    @Transactional
    public Optional<HealthCareJob>deleteById(UUID id){
        healthCareJobRepository.deleteById(id);
        return null;
    }
}
