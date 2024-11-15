package com.example.healthcare.controller;

import com.example.healthcare.dto.WorkResponseDTO;
import com.example.healthcare.exception.organizationExceptions.OrganizationNotFoundException;
import com.example.healthcare.exception.workExceptions.WorkNotFoundException;
import com.example.healthcare.model.Work;
import com.example.healthcare.service.OrganizationService;
import com.example.healthcare.service.WorkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/works")
@Tag(name="Work", description = "Operações relacionadas a trabalhos criados")

public class WorkController {

    private final WorkService workService;
    private final OrganizationService organizationService;
    private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    public WorkController (WorkService workService, OrganizationService organizationService1){

        this.workService = workService;
        this.organizationService = organizationService1;

    }

    @PostMapping("/org/{id}")
    @Operation(summary = "Criar Trabalho",
            description = "Criar Trabalho a partir de item de url",
            tags ={"Work"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Trabalho criado"),
                    @ApiResponse(responseCode = "404", description = "Organização não encontrada"),
                    @ApiResponse(responseCode = "500", description = "Erro interno")
            }
    )

    public ResponseEntity<WorkResponseDTO> createWork(@PathVariable Long id, @RequestBody Work work){

        // verificar se a organizacao existe
        if(organizationService.findById(id).isEmpty()){
            throw new OrganizationNotFoundException("Organization not found");
        }
            Work createdWork = workService.createWork(id, work);
            WorkResponseDTO workResponseDTO = modelMapper.map(createdWork,WorkResponseDTO.class );
            return new ResponseEntity<>(workResponseDTO, HttpStatus.CREATED);

    }

    @GetMapping("/org/{id}")

    @Operation(summary = "Buscar todos os trabalhos",
            description = "Busca todos os trabalhos de uma organização a partir do id dela",
            tags ={"Work"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Operação concluída"),
                    @ApiResponse(responseCode = "500", description = "Erro interno"),
            }
    )

    public ResponseEntity<List<WorkResponseDTO>>getAllWorkByOrganization(@PathVariable Long id) throws Exception{
        try{
            List<WorkResponseDTO> workList = workService.getAllWorksByOrganization(id)
                    .stream()
                    .map( work -> modelMapper.map(work, WorkResponseDTO.class))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(workList, HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("A error has ocurred. Try later");
        }
    }

    @GetMapping("/{workId}")
    @Operation(summary = "Busca trabalho por id",
            description = "Criar organização a partir de cuidado recebido",
            tags ={"Work"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Operação concluída"),
                    @ApiResponse(responseCode = "404", description = "Trabalho nao encontrado"),
            }
    )

    public ResponseEntity<WorkResponseDTO>getWorkById(@PathVariable Long workId){
        Optional<Work> workOptional = workService.getWorkById(workId);
        return workOptional.map(work -> {
            WorkResponseDTO workResponseDTO = modelMapper.map(work, WorkResponseDTO.class);
            return new ResponseEntity<>(workResponseDTO, HttpStatus.OK);
        }).orElseThrow(()-> new WorkNotFoundException("Work Not Found"));
    }

    @GetMapping("/search")
    @Operation(summary = "Busca um trabalho por título",
            description = "Busca um trabalho a partir de uma palavra contida no título",
            tags ={"Work"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Operação concluída"),
                    @ApiResponse(responseCode = "404", description = "Trabalho não encontrado")
            }
    )
    public ResponseEntity<List<WorkResponseDTO>>searchWorkByTitle(@RequestParam(required = false) String text){
        try {
            List<WorkResponseDTO> workList = workService.findByTitle(text)
                    .stream()
                    .map(work -> modelMapper.map(work, WorkResponseDTO.class))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(workList, HttpStatus.OK);
        }catch (Exception e){
            throw new WorkNotFoundException("Work Not found");
        }
    }

    @DeleteMapping("/{workId}")
    @Operation(summary = "Deleta um trabalho",
            description = "Deleta um tabalho a partir do id recebido",
            tags ={"Work"},
            responses = {
                    @ApiResponse(responseCode = "204", description = "No content"),
                    @ApiResponse(responseCode = "404", description = "Trabalho não encontrado")
            }
    )
    public ResponseEntity<Void> deleteWork(@PathVariable Long workId) {
            if ((workService.getWorkById(workId)).isEmpty()) throw  new WorkNotFoundException("Work not found");
            workService.deleteWork(workId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
