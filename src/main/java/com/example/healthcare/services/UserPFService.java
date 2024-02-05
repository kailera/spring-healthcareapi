package com.example.healthcare.services;

import com.example.healthcare.models.UserPF;
import com.example.healthcare.repositories.UserPFRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserPFService {

    @Autowired
    public UserPFRepository userPFRepository;

    public boolean verifyIfCpfExists(String cpf){
        return userPFRepository.existsByCpf(cpf);
    }

    @Transactional
    public UserPF save(UserPF userPf) {
        return userPFRepository.save(userPf);
    }

    public List<UserPF> findAll(){
        return userPFRepository.findAll();
    }

    public Optional<UserPF>findById(UUID id){
        return userPFRepository.findById(id);
    }

    @Transactional
    public Optional<UserPF> deleteById(UUID id){
        userPFRepository.deleteById(id);
        return null;
    }


}
