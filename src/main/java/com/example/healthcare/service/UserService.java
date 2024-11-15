package com.example.healthcare.service;

import com.example.healthcare.auth.JwtTokenService;
import com.example.healthcare.auth.RecoveryJwtTokenDto;
import com.example.healthcare.auth.UserDetailsImpl;
import com.example.healthcare.configuration.SecurityConfig;
import com.example.healthcare.dto.LoginRequest;
import com.example.healthcare.dto.SignupOrganizationRequestDTO;
import com.example.healthcare.dto.SignupProfessionalRequestDTO;
import com.example.healthcare.enuns.RoleNames;
import com.example.healthcare.model.Organization;
import com.example.healthcare.model.Professional;
import com.example.healthcare.model.Role;
import com.example.healthcare.model.User;
import com.example.healthcare.repository.UserResponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserResponsitory userResponsitory;

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private ProfessionalService professionalService;

    public RecoveryJwtTokenDto authenticateUser (LoginRequest loginRequest){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
    }

    // create user deve dar as roles
    public void createUserProfessional (SignupProfessionalRequestDTO signupProfessionalRequestDTO) {
        User newUser = User.builder()
                .email(signupProfessionalRequestDTO.email())
                .password(securityConfig.passwordEncoder().encode(signupProfessionalRequestDTO.password()))
                .roles(List.of(Role.builder().roleNames(RoleNames.ROLE_PROFESSIONAL).build()))
                .build();

        userResponsitory.save(newUser);

        Professional newProfessional = new Professional(
                signupProfessionalRequestDTO.cpf(),
                signupProfessionalRequestDTO.cnpj(),
                signupProfessionalRequestDTO.especialidade(),
                signupProfessionalRequestDTO.nivelEducacional(),
                null
        );

        professionalService.createProfessional(newProfessional);
    }

    public void createUserOrganization(SignupOrganizationRequestDTO organizationRequestDTO) {
        User newUser = User.builder()
                .email(organizationRequestDTO.email())
                .password(securityConfig.passwordEncoder().encode(organizationRequestDTO.password()))
                .roles(List.of(Role.builder().roleNames(RoleNames.ROLE_ORGANIZATION).build()))
                .build();
        userResponsitory.save(newUser);

        Organization newOrganization = new Organization(
                organizationRequestDTO.cnpj(),
                organizationRequestDTO.razaoSocial(),
organizationRequestDTO.tipoOrganizacao(),null,null

        );

        organizationService.save(newOrganization);
    }
}
