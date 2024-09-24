package com.example.healthcare.service;

import com.example.healthcare.dto.OrganizationResponseDTO;
import com.example.healthcare.model.Organization;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.healthcare.repository.OrganizationRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrganizationService {

    @Autowired
    public OrganizationRepository organizationRepository;

    private ModelMapper modelMapper = new ModelMapper();

    // crud operations

    public List<OrganizationResponseDTO> findAll(){
        List<OrganizationResponseDTO> response = organizationRepository.findAll()
                .stream()
                .map((organization ->
                    {
                        return modelMapper.map(organization,OrganizationResponseDTO.class);
                    }
                )).collect(Collectors.toList());
        return response;
    }

    // auth request (for future purpose)
    public Optional<Organization> findById(UUID id){
        return organizationRepository.findById(id);
    }

    public OrganizationResponseDTO save (Organization organization){
        Organization savedOrganization = organizationRepository.save(organization);
        return modelMapper.map(savedOrganization, OrganizationResponseDTO.class);
    }

    @Transactional
    public Optional<Organization>deleteById(UUID id){
        organizationRepository.deleteById(id);
        return null;
    }

}
