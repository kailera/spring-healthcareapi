package com.example.healthcare.controller;

import com.example.healthcare.auth.RecoveryJwtTokenDto;
import com.example.healthcare.dto.LoginRequest;
import com.example.healthcare.dto.SignupOrganizationRequestDTO;
import com.example.healthcare.dto.SignupProfessionalRequestDTO;
import com.example.healthcare.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;


    @PostMapping("/signup/professional")
    public ResponseEntity<ResponseEntity> signupProfessional (@Valid @RequestBody SignupProfessionalRequestDTO professionalRequest) {
        userService.createUserProfessional(professionalRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/signup/organization")
    public ResponseEntity<ResponseEntity> signupOrganization (@Valid @RequestBody SignupOrganizationRequestDTO organizationRequestDTO) {
        userService.createUserOrganization(organizationRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<RecoveryJwtTokenDto> authenticateUser (@Valid @RequestBody LoginRequest loginRequest){
        RecoveryJwtTokenDto token = userService.authenticateUser(loginRequest);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
