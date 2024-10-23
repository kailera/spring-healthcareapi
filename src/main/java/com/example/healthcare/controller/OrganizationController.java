package com.example.healthcare.controller;

import com.example.healthcare.dto.OrganizationResponseDTO;
import com.example.healthcare.exception.organizationExceptions.OrganizationAlreadyExists;
import com.example.healthcare.exception.organizationExceptions.OrganizationNotFoundException;
import com.example.healthcare.model.Organization;
import com.example.healthcare.service.OrganizationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController

public class OrganizationController {
    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController (OrganizationService organizationService){
        this.organizationService = organizationService;
    }

    private ModelMapper modelMapper = new ModelMapper();


    @PostMapping("/org")

    // verificar se cnpj já nao foi inscrito
    public ResponseEntity<OrganizationResponseDTO> saveOrganization (@RequestBody Organization organization){
            if(organizationService.existsByCnpj(organization.getCnpj())){
                throw new OrganizationAlreadyExists("This organization already exists");
            }
            OrganizationResponseDTO response = organizationService.save(organization);
            OrganizationResponseDTO responseDTO = modelMapper.map(response, OrganizationResponseDTO.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/org")
    public ResponseEntity getAllOrganizations() throws Exception {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(organizationService.findAll());
        }catch (Exception e){
         throw  new Exception("A error has occurred. Try later");
        }
    }

    @GetMapping("/org/{id}")
    public ResponseEntity <Optional<Organization>> getOrganizationById(@PathVariable UUID id){
        Optional<Organization> response = organizationService.findById(id);
        if(response.isEmpty()){
            throw  new OrganizationNotFoundException("Organização não cadastrada");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/org/{id}")
    public ResponseEntity<Optional<Organization>> deleteOrganizationById(@PathVariable UUID id){
        Optional<Organization> response = organizationService.deleteById(id);
        if(response.isEmpty()){
            throw  new OrganizationNotFoundException("Organização não cadastrada");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
