package com.example.healthcare.enuns;

import lombok.Getter;

@Getter
public enum ContractStatus {
    PENDING (1),
    ACCEPTED (2),
    CANCELLED(3);

    public int statusCode;
    ContractStatus(int statusCode){
        this.statusCode = statusCode;
    }

    public static ContractStatus fromCode(int code){
        for(ContractStatus status: ContractStatus.values()){
            if(status.getStatusCode() ==  code) return status;
        }
        throw new IllegalArgumentException("Invalid code for offer status: " + code);
    }
}
