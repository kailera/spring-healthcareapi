package com.example.healthcare.controllers;

import com.example.healthcare.dtos.requests.UserPFDto;
import com.example.healthcare.dtos.responses.ResponseUserPFDto;
import com.example.healthcare.models.UserPF;
import com.example.healthcare.services.UserPFService;
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

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class UserPFController {
    private final UserPFService userPFService;

    @Autowired
    public  UserPFController (UserPFService userPFService){
        this.userPFService = userPFService;
    }

    //CRUD OPERATIONS - ALL OPERATIONS OK

    @PostMapping
    public ResponseEntity<Object>saveUserPF(@RequestBody @Valid UserPFDto userPFDto){
        var userPf = new UserPF();
        if (userPFService.verifyIfCpfExists(userPFDto.getCpf())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This CPF is already save");
        }

        BeanUtils.copyProperties(userPFDto, userPf);
        userPf.setCreateAt(LocalDateTime.now(ZoneId.of("UTC")));
        try{
            userPFService.save(userPf);
            return ResponseEntity.status(HttpStatus.CREATED).body(ResponseUserPFDto.toDto(userPFDto));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<UserPF>>getAllUsersPF(){
        return ResponseEntity.status(HttpStatus.OK).body(userPFService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object>getUserPF(@PathVariable(value = "id")UUID id){
        Optional<UserPF> userPF = userPFService.findById(id);
        if(!userPF.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This id doesn't exists.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userPFService.findById(id));
    }

    @PutMapping("/{id}")
    public void updateUserPF(@PathVariable(value = "id")UUID id, @RequestBody UserPF newUserPF){
        try{
            userPFService.findById(id)
                    .map(userPF -> {
                        userPF.setPhone(newUserPF.getPhone());
                        userPF.setAddress(newUserPF.getAddress());
                        userPF.setName(newUserPF.getName());
                        userPF.setCpf(newUserPF.getCpf());
                        userPF.setEducationStage(newUserPF.getEducationStage());
                        userPF.setNhsRoles(newUserPF.getNhsRoles());

                        UserPF saveUserPF = userPFService.save( userPF);
                        return ResponseEntity.ok().body(userPF);
                    });
        }catch (Exception e){
             ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id")UUID id){
        Optional<UserPF> userPFToDelete = userPFService.findById(id);
        if (!userPFToDelete.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id Not Found");
        }else{
            userPFService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("user deleted: " + userPFToDelete);
        }

    }
}

