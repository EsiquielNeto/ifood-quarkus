package br.com.studies.cadastro.application.dto;

import javax.validation.ConstraintValidatorContext;

public interface DTO {

    default boolean isValid(ConstraintValidatorContext constraintValidatorContext) {
        return true;
    }

}
