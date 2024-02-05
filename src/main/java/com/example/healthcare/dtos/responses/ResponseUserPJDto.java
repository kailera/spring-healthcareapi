package com.example.healthcare.dtos.responses;

import com.example.healthcare.enums.OrganizationType;
import com.example.healthcare.models.UserPJ;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseUserPJDto {

    private String OrganizationName;

    private String address;

    private String phone;

    private OrganizationType organizationType;

    public static ResponseUserPJDto toDTO (UserPJ userPJ){
        return new ResponseUserPJDto(
                userPJ.getOrganizationName(),
                userPJ.getAddress(),
                userPJ.getPhone(),
                userPJ.getOrganizationType()
        );
    }

}
