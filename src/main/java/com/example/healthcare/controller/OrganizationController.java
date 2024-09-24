package com.example.healthcare.controller;

import com.example.healthcare.dto.OrganizationResponseDTO;
import com.example.healthcare.model.Organization;
import com.example.healthcare.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController

@RequestMapping("/org")
public class OrganizationController {
    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController (OrganizationService organizationService){
        this.organizationService = organizationService;
    }

    @PostMapping
    public ResponseEntity<Object> saveOrganization (@RequestBody Organization organization){
        organization.setCreateAt(LocalDateTime.now(ZoneId.of("UTC")));
        try{
            OrganizationResponseDTO responseDTO = organizationService.save(organization);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        }catch (Exception e ){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getAllOrganizations() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(organizationService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity <Optional<Organization>> getOrganizationById(@PathVariable UUID id){
        Optional<Organization> response = organizationService.findById(id);
        HttpStatus status = (response != null) ? HttpStatus.OK :  HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Organization>> deleteOrganizationById(@PathVariable UUID id){
        Optional<Organization> response = organizationService.deleteById(id);
        HttpStatus status = (response!= null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }


}
