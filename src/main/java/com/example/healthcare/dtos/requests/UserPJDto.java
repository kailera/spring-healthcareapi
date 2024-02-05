package com.example.healthcare.dtos.requests;

import com.example.healthcare.enums.OrganizationType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserPJDto {
    @NotBlank
    @Size(max = 30)
    private String organizationName;

    @NotBlank
    @Size(max = 50)
    private String address;

    @NotBlank
    private String phone;

    @NotBlank
    @Size(max = 20)
    private String businessNumber;

    @NotNull
    private OrganizationType organizationType;

}

