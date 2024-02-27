package com.example.healthcare.services;

import com.example.healthcare.models.UserPJ;
import com.example.healthcare.repositories.UserPJRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserPJService {

    @Autowired
    public UserPJRepository userPJRepository;

    //CRUD OPERATIONS
    @Transactional
    public UserPJ save(UserPJ userPJ){
        return userPJRepository.save(userPJ);
    }
    public List<UserPJ> findAll(){
        return userPJRepository.findAll();
    }
    public Optional<UserPJ> findById(UUID id) {
        return userPJRepository.findById(id);
    }



    @Transactional
    public Optional<UserPJ> deleteById(UUID id) {
        userPJRepository.deleteById(id);
        return null;
    }
}
