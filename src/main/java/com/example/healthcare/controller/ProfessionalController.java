package com.example.healthcare.controller;

import com.example.healthcare.dto.ProfessionalResponseDTO;
import com.example.healthcare.exception.professionalExceptions.ProfessionalAlreadyExistsException;
import com.example.healthcare.exception.professionalExceptions.ProfessionalNotFoundException;
import com.example.healthcare.model.Professional;
import com.example.healthcare.repository.ProfessionalRepository;
import com.example.healthcare.service.ProfessionalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="Professional", description = "Operações relacionadas a profissionais")

public class ProfessionalController {
    private final ProfessionalService professionalService;

    @Autowired
    public ProfessionalController (ProfessionalService professionalService){
        this.professionalService = professionalService;
    }

    @PostMapping
    @Operation(summary = "Criar Profissional",
            description = "Criar organização a partir de dados do req body",
            tags ={"Professional"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Profissional Criado"),
                    @ApiResponse(responseCode = "500", description = "Problema interno"),
                    @ApiResponse(responseCode = "409", description = "CPF já existente")
            }
    )

    public ResponseEntity<ProfessionalResponseDTO> createProfessional(@RequestBody Professional professional) throws Exception {
        if(professionalService.existsByCpf(professional.getCpf())){
            throw new ProfessionalAlreadyExistsException("Profissional já cadastrado");
        }
        ProfessionalResponseDTO createdProfessional = professionalService.createProfessional(professional);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProfessional);
    }

    @GetMapping
    @Operation(summary = "Buscar todos os profissionais",
            description = "Buscar todos os profissionais",
            tags ={"Professional"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Operação concluída"),
                    @ApiResponse(responseCode = "500", description = "Operação não concluída")
    }
    )
    public ResponseEntity getAllProfessionals() throws Exception {
        try {
            List<ProfessionalResponseDTO> responseDTOList = professionalService.getAllProfessional();
            return ResponseEntity.status(HttpStatus.OK).body(responseDTOList);
        }catch (Exception e) {
            throw new Exception("A error has ocurred. Try later");
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar profissional por id",
            description = "Buscar profissional por id passado na url",
            tags ={"Professional"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Busca concluída"),
                    @ApiResponse(responseCode = "500", description = "Busca não concluída")
            }
    )
    public ResponseEntity<Optional<Professional>> getProfessionalById (@PathVariable UUID id){

        Optional<Professional> response = professionalService.getProfessionalById(id);
        if(response.isEmpty()){
            throw new ProfessionalNotFoundException("Professional not found");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping
    @Operation(summary = "Deletar Profissional",
            description = "Deletar profissional a partir de id recebido",
            tags ={"Professional"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Operação concluída"),
                    @ApiResponse(responseCode = "404", description = "Operação não concluída"),
            }
    )

    public ResponseEntity deleteById (@PathVariable UUID id){
        Optional professional = professionalService.deleteById(id);
        if(professional.isEmpty()){
            throw new ProfessionalNotFoundException("Professional not found");
        }
        return  new ResponseEntity<>(professional, HttpStatus.OK);
    }

}
