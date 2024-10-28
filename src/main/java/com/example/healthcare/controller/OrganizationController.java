package com.example.healthcare.controller;

import com.example.healthcare.dto.OrganizationResponseDTO;
import com.example.healthcare.exception.organizationExceptions.OrganizationAlreadyExists;
import com.example.healthcare.exception.organizationExceptions.OrganizationNotFoundException;
import com.example.healthcare.model.Organization;
import com.example.healthcare.service.OrganizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@Tag(name="Organization", description = "Operações relacionadas a organizações")
public class OrganizationController {
    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController (OrganizationService organizationService){
        this.organizationService = organizationService;
    }

    private ModelMapper modelMapper = new ModelMapper();


    @PostMapping("/org")
    @Operation(summary = "Criar organização",
            description = "Criar organização a partir de cuidado recebido",
            tags ={"Organization"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Organização criada"),
                    @ApiResponse(responseCode = "500", description = "Organização não criada"),
                    @ApiResponse(responseCode = "409", description = "Cnpj já existente")
            }
    )
    public ResponseEntity<OrganizationResponseDTO> saveOrganization (@RequestBody Organization organization){
            if(organizationService.existsByCnpj(organization.getCnpj())){
                throw new OrganizationAlreadyExists("This organization already exists");
            }
            OrganizationResponseDTO response = organizationService.save(organization);
            OrganizationResponseDTO responseDTO = modelMapper.map(response, OrganizationResponseDTO.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/org")
    @Operation(summary = "Buscar todas as organizações",
            description = "Buscar todas as organizações",
            tags ={"Organization"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Operação Concluída"),
                    @ApiResponse(responseCode = "500", description = "Busca não concluída"),
            }
    )

    public ResponseEntity getAllOrganizations() throws Exception {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(organizationService.findAll());
        }catch (Exception e){
         throw  new Exception("A error has occurred. Try later");
        }
    }

    @GetMapping("/org/{id}")
    @Operation(summary = "Buscar organização por id",
            description = "Buscar Organização por id",
            tags ={"Organization"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Operação concluída"),
                    @ApiResponse(responseCode = "500", description = "Busca não concluída"),
            }
    )
    public ResponseEntity <Optional<Organization>> getOrganizationById(@PathVariable UUID id){
        Optional<Organization> response = organizationService.findById(id);
        if(response.isEmpty()){
            throw  new OrganizationNotFoundException("Organização não cadastrada");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("/org/{id}")
    @Operation(summary = "Deletar organização",
            description = "Deletar organização",
            tags ={"Organization"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Organização criada"),
                    @ApiResponse(responseCode = "404", description = "Operação não concluída")
            }
    )
    public ResponseEntity<Optional<Organization>> deleteOrganizationById(@PathVariable UUID id){
        Optional<Organization> response = organizationService.deleteById(id);
        if(response.isEmpty()){
            throw  new OrganizationNotFoundException("Organização não cadastrada");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
