package com.example.healthcare.enuns;

public enum TipoOrganizacao {
    HOSPITAL("hospital"),
    CLINICA_MEDICA("clinica medica"),
    EMERGENCIAL("emergencial");

    private String tipoOrganizacao;

    TipoOrganizacao(String tipoOrganizacao){
        this.tipoOrganizacao = tipoOrganizacao;
    }
}
