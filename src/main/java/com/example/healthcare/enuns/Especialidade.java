package com.example.healthcare.enuns;

public enum Especialidade {
    NUTRICIONISTA ("nutricionista"),
    FISIOTERAPEUTA("fisioterapeuta"),
    CUIDADOR_DE_IDOSOS("cuidador de idosos"),
    ENFERMEIRO ("enfermeiro"),
    FARMACEUTICO("farmaceutico"),
    TECNICO_ENFERMAGEM("tecnico em enfermagem"),
    TECNICO_AMBULANCIA("tecnico em ambulancia"),
    MEDICO_GERIATRICO("medico geriatrico"),
    PEDIATRA("pediatra"),
    CARDIOLOGISTA("cardiologista"),
    ORTOPEDISTA("ortopedista"),
    TECNICO_RADIOLOGISTA("tecnico radiologista");
    private String especialidade;

    Especialidade (String especialidade){
        this.especialidade = especialidade;
    }
}
