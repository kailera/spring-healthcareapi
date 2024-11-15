package com.example.healthcare.specifications;

import com.example.healthcare.enuns.Especialidade;
import com.example.healthcare.model.Professional;
import org.springframework.data.jpa.domain.Specification;

public class ProfessionalSpecifications {
    public static Specification<Professional> typeEspecialidade (String especialidade){
        return (root, query, criteriaBuilder) ->
                especialidade == null ? null : criteriaBuilder.equal(root.get("especialidade"), especialidade);
    }

    public static Specification<Professional> typeNivelEducacional (String nivelEducacional){
        return (root, query, criteriaBuilder) ->
            nivelEducacional == null?null:criteriaBuilder.equal(root.get("nivelEducacional"), nivelEducacional);
    }
}
