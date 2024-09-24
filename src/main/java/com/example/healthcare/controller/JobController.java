package com.example.healthcare.controller;

import com.example.healthcare.dto.OrganizationResponseDTO;
import com.example.healthcare.model.Job;
import com.example.healthcare.model.Organization;
import com.example.healthcare.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController("/job")
public class JobController {

    @Autowired
    private final JobService jobService;

    public JobController(JobService jobService){
        this.jobService = jobService;
    }


    @PostMapping
    public ResponseEntity<Object> saveJob (@RequestBody Job job){
        job.setCreateAt(LocalDateTime.now(ZoneId.of("UTC")));
        try{
            Job response = jobService.save(job);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception e ){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getAllOrganizations() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(jobService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity <Optional<Job>> getJobById(@PathVariable UUID id){
        Optional<Job> response = jobService.findById(id);
        HttpStatus status = (response != null) ? HttpStatus.OK :  HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Job>> deleteJobById(@PathVariable UUID id){
        Optional<Job> response = jobService.deleteById(id);
        HttpStatus status = (response!= null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }

}
