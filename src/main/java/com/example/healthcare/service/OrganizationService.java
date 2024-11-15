package com.example.healthcare.service;

import com.example.healthcare.dto.OrganizationResponseDTO;
import com.example.healthcare.model.Organization;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.example.healthcare.repository.OrganizationRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrganizationService {


    private final OrganizationRepository organizationRepository;

    private  ModelMapper modelMapper = new ModelMapper();

    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
        this.modelMapper = modelMapper;
    }


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
    public Optional<Organization> findById(Long id){
        return organizationRepository.findById(id);
    }

    public OrganizationResponseDTO save (Organization organization){
        Organization savedOrganization = organizationRepository.save(organization);
        OrganizationResponseDTO responseDTO = modelMapper.map(savedOrganization, OrganizationResponseDTO.class);
        return responseDTO;
    }

    @Transactional
    public Optional<Organization>deleteById(Long id){
        organizationRepository.deleteById(id);
        return null;
    }

    public boolean existsByCnpj(String cnpj) {
        return organizationRepository.existsByCnpj(cnpj);
    }

    public Optional<Organization> findByCnpj(String cnpj){
        return organizationRepository.findByCnpj(cnpj);
    }
}
