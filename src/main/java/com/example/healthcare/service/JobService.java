package com.example.healthcare.service;

import com.example.healthcare.dto.OrganizationResponseDTO;
import com.example.healthcare.model.Job;
import com.example.healthcare.model.Organization;
import com.example.healthcare.repository.JobRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class JobService {

    @Autowired
    public JobRepository jobRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public List<Job> findAll(){
        List<Job> response = jobRepository.findAll()
                .stream()
                .collect(Collectors.toList());
        return response;
    }

    public Optional<Job> findById(UUID id){
        return jobRepository.findById(id);
    }

    public Job save (Job job){
        Job createdJob = jobRepository.save(job);
        return createdJob;
    }

    @Transactional
    public Optional<Job>deleteById(UUID id){
        jobRepository.deleteById(id);
        return null;
    }


}
