package com.example.healthcare.controllers;

import com.example.healthcare.dtos.requests.HealthCareJobDto;
import com.example.healthcare.models.HealthCareJob;
import com.example.healthcare.models.UserPF;
import com.example.healthcare.repositories.UserPJRepository;
import com.example.healthcare.services.HealthCareJobService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/*
200 - ok (put)
201 - created
400 - bad request
404 - not found
422 - json unformatted
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
// uri a nivel de classe
@RequestMapping("/healthcare")
public class HealthCareJobController {

    private final HealthCareJobService healthCareJobService;

    @Autowired
    private UserPJRepository userPJRepository;

    public HealthCareJobController( HealthCareJobService healthCareJobService) {
       this.healthCareJobService = healthCareJobService;
    }

    // healthcare job add

    //@Valid enable the validations on dto
    @PostMapping
    public ResponseEntity<Object>saveHealthCareJob(@RequestBody @Valid HealthCareJobDto healthCareJobDto){

        var healthCareJob = new HealthCareJob();
        //dto to model copy data
        BeanUtils.copyProperties(healthCareJobDto, healthCareJob);

        //updates
        healthCareJob.setCreateAt(LocalDateTime.now(ZoneId.of("UTC")));

        // find user
        try {
            userPJRepository.findById(healthCareJobDto.getUserPJId()).map(user -> {
                healthCareJob.setUserPJ(user);
                return null;
            });

            return ResponseEntity.status(HttpStatus.CREATED).body(healthCareJobService.save(healthCareJob));

        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping
    public ResponseEntity<List<HealthCareJob>>getHealtCareJob(){
        if (healthCareJobService.findAll().isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(healthCareJobService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object>getHealthCareJobById(@PathVariable(value = "id")UUID id){
        Optional<HealthCareJob>healthCareJob = healthCareJobService.findById(id);
        if(healthCareJob.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This id doesn't exists.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(healthCareJobService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HealthCareJob> updateHealthCareJob(@PathVariable(value = "id") UUID id, @RequestBody HealthCareJob newHealthCareJob){
        return healthCareJobService.findById(id)
                .map(healthCareJob -> {
                    healthCareJob.setTitleJob(newHealthCareJob.getTitleJob());
                    healthCareJob.setDescriptionJob(newHealthCareJob.getDescriptionJob());
                    healthCareJob.setRequiredLevel(newHealthCareJob.getRequiredLevel());
                    healthCareJob.setSalary(newHealthCareJob.getSalary());
                    healthCareJob.setUserPJ(newHealthCareJob.getUserPJ());

                    HealthCareJob healthCareJobToSave = healthCareJobService.save(healthCareJob);
                    return ResponseEntity.ok().body(healthCareJobToSave);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object>deleteById(@PathVariable(value = "id")UUID id){
        Optional <HealthCareJob> healthCareJobToDelete = healthCareJobService.findById(id);
        if(healthCareJobToDelete.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Healthcare job id not found");
        }else{
            healthCareJobService.deleteById(healthCareJobToDelete.get().getId());
            return ResponseEntity.status(HttpStatus.OK).body("id " + id + " deleted successfully!");
        }

    }
}
