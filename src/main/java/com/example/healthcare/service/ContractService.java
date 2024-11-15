package com.example.healthcare.service;

import com.example.healthcare.model.Contract;
import com.example.healthcare.repository.ContractRepository;
import org.springframework.stereotype.Service;

/**
 * getContractsByProfessional: busca contratos de um profissional específico
 *
 * getContractsBy
 */

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContractService {

    private final ContractRepository contractRepository;

    public ContractService(ContractRepository contractRepository){
        this.contractRepository = contractRepository;
    }

    public Contract save (Contract contract){
        Contract savedContract = contractRepository.save(contract);
        return savedContract;
    }

    public List<Contract>getContractsByProfessional(Long professionalId){
        return contractRepository.findContractByProfessionalId(professionalId);
    }

    public List<Contract>getContractsByOrganizationId(Long organizationId){
        return contractRepository.findContractByOrganizationId(organizationId);
    }

    public Optional<Contract> getContractById(Long id){
        return contractRepository.findById(id);
    }

    public Contract updateContract(Long id, Contract contractDetails){
        Contract existedContract = getContractById(id).orElseThrow(()-> new RuntimeException("Contract not found"));
        existedContract.setOrganization(contractDetails.getOrganization());
        existedContract.setProfessional(contractDetails.getProfessional());
        existedContract.setWork(contractDetails.getWork());
        existedContract.setPaymentList(contractDetails.getPaymentList());
        existedContract.setContractStatus(contractDetails.getContractStatus());
        contractRepository.save(existedContract);
        return existedContract;
    }

    public Contract deleteContract (long contractId){
        Contract contractToDelete = getContractById(contractId)
                .orElseThrow(()-> new RuntimeException("Contract Not Found"));
        contractRepository.deleteById(contractId);
        return contractToDelete;
    }

}
