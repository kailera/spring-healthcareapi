package com.example.healthcare.controller;

import com.example.healthcare.dto.WorkResponseDTO;
import com.example.healthcare.exception.organizationExceptions.OrganizationNotFoundException;
import com.example.healthcare.exception.workExceptions.WorkNotFoundException;
import com.example.healthcare.model.Work;
import com.example.healthcare.service.OrganizationService;
import com.example.healthcare.service.WorkService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/works")
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
    public ResponseEntity<WorkResponseDTO> createWork(@PathVariable UUID id, @RequestBody Work work){

        // verificar se a organizacao existe
        if(organizationService.findById(id).isEmpty()){
            throw new OrganizationNotFoundException("Organization not found");
        }
            Work createdWork = workService.createWork(id, work);
            WorkResponseDTO workResponseDTO = modelMapper.map(createdWork,WorkResponseDTO.class );
            return new ResponseEntity<>(workResponseDTO, HttpStatus.CREATED);

    }

    @GetMapping("/org/{id}")
    public ResponseEntity<List<WorkResponseDTO>>getAllWorkByOrganization(@PathVariable UUID id) throws Exception{
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
    public ResponseEntity<WorkResponseDTO>getWorkById(@PathVariable Long workId){
        Optional<Work> workOptional = workService.getWorkById(workId);
        return workOptional.map(work -> {
            WorkResponseDTO workResponseDTO = modelMapper.map(work, WorkResponseDTO.class);
            return new ResponseEntity<>(workResponseDTO, HttpStatus.OK);
        }).orElseThrow(()-> new WorkNotFoundException("Work Not Found"));
    }

    @GetMapping("/search")
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
    public ResponseEntity<Void> deleteWork(@PathVariable Long workId) {
            if ((workService.getWorkById(workId)).isEmpty()) throw  new WorkNotFoundException("Work not found");
            workService.deleteWork(workId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
