package br.com.studies.cadastro.application.dto;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidatorDTO.class})
@Documented
public @interface ValidDTO {

    String message() default "{validto.message.default}";

    Class<?>[] groups() default{};

    Class<? extends Payload>[] payload() default {};
}
