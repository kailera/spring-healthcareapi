package com.example.healthcare.enuns;

public enum CargaHoraria {


    DOZE(12),
    VINTE_QUATRO(24),
    TRINTA_SEIS(36);


    public int cargaHoras;
    CargaHoraria(int cargaHoras) {
    this.cargaHoras = cargaHoras;
    }

}
