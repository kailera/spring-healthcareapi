package com.example.healthcare.service;

import com.example.healthcare.model.Organization;
import com.example.healthcare.model.Work;
import com.example.healthcare.repository.OrganizationRepository;
import com.example.healthcare.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkService {

    @Autowired
    private WorkRepository workRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    public Work createWork(Long organizationId, Work work){
        Optional<Organization> organizationOptional = organizationRepository.findById(organizationId);

        if(organizationOptional.isPresent()){
            work.setOrganization(organizationOptional.get());
            return workRepository.save(work);
        }else{
            throw new IllegalArgumentException("Organization Not Found");
        }
    }

    public List<Work> getAllWorksByOrganization (Long organizationId){
        return workRepository.findByOrganizationId(organizationId);
    }

    public Optional<Work> getWorkById(Long workId){
        return workRepository.findById(workId);
    }

    public void deleteWork(Long workId){
        Optional<Work> workOptional = workRepository.findById(workId);
        if(workOptional.isPresent()){
            workRepository.delete(workOptional.get());
        }else{
            throw new IllegalArgumentException("Work not found");
        }
    }

    public List<Work>findByTitle(String title){
        return workRepository.findByTitleContaining(title);
    }
}
