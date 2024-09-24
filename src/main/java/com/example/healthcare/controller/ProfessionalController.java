package com.example.healthcare.controller;

import com.example.healthcare.dto.ProfessionalResponseDTO;
import com.example.healthcare.model.Professional;
import com.example.healthcare.repository.ProfessionalRepository;
import com.example.healthcare.service.ProfessionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/professional")
public class ProfessionalController {
    private final ProfessionalService professionalService;

    @Autowired
    public ProfessionalController (ProfessionalService professionalService){
        this.professionalService = professionalService;
    }

    @PostMapping
    public ResponseEntity<Object> createProfessional(@RequestBody Professional professional){
        professional.setCreateAt(LocalDateTime.now(ZoneId.of("UTC")));
        try{
            ProfessionalResponseDTO responseDTO = professionalService.createProfessional(professional);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getAllProfessionals(){
        try {

            List<ProfessionalResponseDTO> responseDTOList = professionalService.getAllProfessional();
            return ResponseEntity.status(HttpStatus.OK).body(responseDTOList);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Professional>> getProfessionalById (@PathVariable UUID id){

        Optional<Professional> response = professionalService.getProfessionalById(id);
        HttpStatus status = (response.isEmpty()) ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(response, status);
    }

    @DeleteMapping
    public ResponseEntity deleteById (@PathVariable UUID id){
        Optional professional = professionalService.deleteById(id);
        HttpStatus status = (professional.isEmpty()) ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return  new ResponseEntity<>(professional, status);
    }

}
