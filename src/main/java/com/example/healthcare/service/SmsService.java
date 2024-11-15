package com.example.healthcare.service;

import com.example.healthcare.model.Professional;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    public void sendVerificationCode(Professional professional){
        String code = generateVerificationCode();
        professional.setVerificationCode(code);
        // chamada para api de sms para envio
    }

    private String generateVerificationCode() {
        return String.valueOf((int)(Math.random()*900000)+100000);
    }
}
