package com.example.healthcare.controller;


import com.example.healthcare.enuns.ContractStatus;
import com.example.healthcare.model.Contract;
import com.example.healthcare.service.ContractService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/contracts")
public class ContractController {

    @Autowired
    private ContractService contractService;

    @PutMapping("/{id}/sign")
    public ResponseEntity<String> signContract (
            @PathVariable Long id,
            @RequestParam("userType") String userType
    ) throws BadRequestException {
        Contract contract = contractService.getContractById(id).orElseThrow(BadRequestException::new);

        if("professional".equalsIgnoreCase(userType)){
            contract.setProfessionalSigned(true);
        }else if ("organization".equalsIgnoreCase(userType)){
            contract.setOrganizationSigned(true);
        }else{
            return ResponseEntity.badRequest().body("Tipo de usuário inválido");
        }

        if(contract.isProfessionalSigned() && contract.isOrganizationSigned()){
            contract.setContractStatus(ContractStatus.SIGNED);
        }

        contractService.save(contract);

        return ResponseEntity.ok("Contrato criado com sucesso");
    }


}
