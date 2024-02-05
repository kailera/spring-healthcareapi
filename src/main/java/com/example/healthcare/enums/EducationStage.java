package com.example.healthcare.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/*
Educational Stages - Níveis de escolaridade
Associate: Cursos técnologos de 2 anos de duração, oferecidos nas faculdades comunitárias
Bachelor:
Master
 */

public enum EducationStage implements Serializable {

    ASSOCIATE("associate"),
    BACHELOR("bachelor"),
    MASTER("master");

    private String descrription;
    EducationStage(String description) {
        this.descrription = description;
    }


}
