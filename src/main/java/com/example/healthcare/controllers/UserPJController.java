package com.example.healthcare.controllers;

import com.example.healthcare.dtos.responses.ResponseUserPJDto;
import com.example.healthcare.dtos.requests.UserPJDto;
import com.example.healthcare.models.UserPJ;
import com.example.healthcare.services.UserPJService;
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
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/organizations")

public class
UserPJController {
    private final UserPJService userPJService;

    // dependency injection
    @Autowired
    public UserPJController(UserPJService userPJService){
        this.userPJService = userPJService;
    }

    // CRUD OPERATIONS - ALL OPERATIONS OK

    @PostMapping
    public ResponseEntity<Object>saveUserPJ(@RequestBody @Valid UserPJDto userPJDto){
        var userPj = new UserPJ();

        BeanUtils.copyProperties(userPJDto, userPj);
        userPj.setCreateAt(LocalDateTime.now(ZoneId.of("UTC")));
        //prepare response
        try{
            userPJService.save(userPj);
            return ResponseEntity.status(HttpStatus.CREATED).body(ResponseUserPJDto.toDTO(userPj));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<UserPJ>>getAllUsersPJ(){
        return ResponseEntity.status(HttpStatus.OK).body(userPJService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object>getUserPJ(@PathVariable(value = "id")UUID id){
        Optional<UserPJ> userPJOptional = userPJService.findById(id);
        if (!userPJOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userPJOptional);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserPJ> updateUserPJ(@PathVariable(value = "id")UUID id, @RequestBody UserPJ newUserPJ){

            return userPJService.findById(id)
                    .map(userPJ -> {
                    userPJ.setBusinessNumber(newUserPJ.getBusinessNumber());
                    userPJ.setOrganizationName(newUserPJ.getOrganizationName());
                    userPJ.setOrganizationType(newUserPJ.getOrganizationType());
                    userPJ.setPhone(newUserPJ.getPhone());
                    userPJ.setAddress(newUserPJ.getAddress());
                    UserPJ userPJToSave = userPJService.save(userPJ);
                    return ResponseEntity.ok().body(userPJToSave);
                    }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Object> deleteById(@PathVariable(value = "id")UUID id){
        Optional<UserPJ> userPJToDelete = userPJService.findById(id);
        if(!userPJToDelete.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id Not Found");
        }else{
            userPJService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("User Deleted: " + userPJToDelete);
        }
    }
}
