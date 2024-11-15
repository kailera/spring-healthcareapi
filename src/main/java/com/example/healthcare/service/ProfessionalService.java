package com.example.healthcare.service;

import com.example.healthcare.dto.ProfessionalResponseDTO;
import com.example.healthcare.model.Professional;
import com.example.healthcare.repository.ProfessionalRepository;
import com.example.healthcare.specifications.ProfessionalSpecifications;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfessionalService {

    @Autowired
    public ProfessionalRepository professionalRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public ProfessionalResponseDTO createProfessional (Professional professional){
        Professional savedProfessional = professionalRepository.save(professional);
        return modelMapper.map(savedProfessional, ProfessionalResponseDTO.class);
    }

    public List<ProfessionalResponseDTO> getAllProfessional(){
        List<ProfessionalResponseDTO> responseDTOList = professionalRepository
                .findAll()
                .stream()
                .map((professional ->
                {
                    return modelMapper.map(professional, ProfessionalResponseDTO.class);
                }
                )).collect(Collectors.toList());
        return  responseDTOList;
    }

    // for further auth
    public Optional<Professional> getProfessionalById(Long id){
        return professionalRepository.findById(id);
    }

    public Optional deleteById (Long id){
        professionalRepository.deleteById(id);
        return null;
    }

    public boolean existsByCpf(String cpf){
        return professionalRepository.existsByCpf(cpf);
    }

    public Optional<Professional> findByCpf(String cpf){
        return professionalRepository.findByCpf(cpf);
    }

    public List<Professional> getAvailableAndRegisteredProfessionals(){
        return professionalRepository.findByIsRegisteredTrueAndIsAvailableTrue();
    }

    public List<Professional>filterProfessionals(String especialidade, String nivelEducacional){
        Specification<Professional> spec = Specification
                .where(ProfessionalSpecifications.typeEspecialidade(especialidade))
                .and(ProfessionalSpecifications.typeNivelEducacional(nivelEducacional));
        return professionalRepository.findAll(spec);
    }
}
