package com.example.healthcare.controller;

import com.example.healthcare.dto.ProfessionalResponseDTO;
import com.example.healthcare.exception.professionalExceptions.ProfessionalAlreadyExistsException;
import com.example.healthcare.exception.professionalExceptions.ProfessionalNotFoundException;
import com.example.healthcare.model.Professional;
import com.example.healthcare.repository.ProfessionalRepository;
import com.example.healthcare.service.ProfessionalService;
import com.example.healthcare.service.SmsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/professional")
@Tag(name="Professional", description = "Operações relacionadas a profissionais")

public class ProfessionalController {
    private final ProfessionalService professionalService;
    private final SmsService smsService;

    private ModelMapper modelMapper = new ModelMapper();


    @Autowired
    public ProfessionalController (ProfessionalService professionalService, SmsService smsService){
        this.professionalService = professionalService;
        this.smsService = smsService;
    }

    @PostMapping
    @Operation(summary = "Criar Profissional",
            description = "Criar organização a partir de dados do req body. Opções default: " +
                    "isRegistered: False, por que ainda precisa confirmar via sms." +
                    "isAvailable: False após a criação. Após a confirmação por sms se torna true",
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
        professional.setAvailable(false);
        professional.setRegistered(false);
        ProfessionalResponseDTO createdProfessional = professionalService.createProfessional(professional);
        smsService.sendVerificationCode(professional);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProfessional);
    }

    @PostMapping("/verify")
    public ResponseEntity<String>veriFyProfissional(@RequestParam Long professionalId, @RequestParam String verificationCode) throws Exception {
        Professional professional = professionalService.getProfessionalById(professionalId)
                .orElseThrow(()-> new ProfessionalNotFoundException("Profissional não encontrado"));

        if(professional.getVerificationCode().equals(verificationCode)){
            professional.setRegistered(true);
            professional.setAvailable(true);
            professional.setVerificationCode(null);
            professionalService.createProfessional(professional);
            return ResponseEntity.status(HttpStatus.OK).body("Professional verified and registered successfully");
        }else{
            throw new  Exception( "Invalid verification code");
        }
    }



    @GetMapping("/available")
    @Operation(summary = "Buscar profissionais",
            description = "Buscar todos os profissionais registrados e disponíveis",
            tags ={"Professional"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Operação concluída"),
                    @ApiResponse(responseCode = "500", description = "Operação não concluída")
    }
    )
    public ResponseEntity getAllProfessionals() throws Exception {
        try {
            List<ProfessionalResponseDTO> responseDTOList = professionalService.getAvailableAndRegisteredProfessionals()
                    .stream()
                    .map((professional -> modelMapper.map(professional, ProfessionalResponseDTO.class)))
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(responseDTOList);
        }catch (Exception e) {
            throw new Exception("A error has ocurred. Try later");
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ProfessionalResponseDTO>>filterProfessionals(
            @RequestParam(required = false) String especialidade,
            @RequestParam(required=false)String nivelEducacional
    ){
        List<ProfessionalResponseDTO> professionals = professionalService.filterProfessionals(
                especialidade,
                nivelEducacional
        ).stream()
                .map((professional -> modelMapper.map(professional, ProfessionalResponseDTO.class)))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(professionals);
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
    public ResponseEntity<Optional<Professional>> getProfessionalById (@PathVariable Long id){

        Optional<Professional> response = professionalService.getProfessionalById(id);
        if(response.isEmpty()){
            throw new ProfessionalNotFoundException("Professional not found");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar Profissional",
            description = "Deletar profissional a partir de id recebido",
            tags ={"Professional"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Operação concluída"),
                    @ApiResponse(responseCode = "404", description = "Operação não concluída"),
            }
    )

    public ResponseEntity deleteById (@PathVariable Long id){
        Optional professional = professionalService.deleteById(id);
        if(professional.isEmpty()){
            throw new ProfessionalNotFoundException("Professional not found");
        }
        return  new ResponseEntity<>(professional, HttpStatus.OK);
    }

}
