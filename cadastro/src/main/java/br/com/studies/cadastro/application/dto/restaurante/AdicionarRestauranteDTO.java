package br.com.studies.cadastro.application.dto.restaurante;

import br.com.studies.cadastro.application.dto.DTO;
import br.com.studies.cadastro.application.dto.ValidDTO;
import br.com.studies.cadastro.application.dto.localizacao.LocalizacaoDTO;
import br.com.studies.cadastro.domain.restaurante.model.Restaurante;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ValidDTO
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdicionarRestauranteDTO implements DTO {

    @NotBlank
    String nome;

    @NotBlank
    String proprietario;

    @CNPJ
    String cnpj;
    LocalizacaoDTO localizacao;

    @Override
    public boolean isValid(ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        if (Restaurante.find("cnpj", cnpj).count() > 0) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("CNPJ já cadastrado")
                    .addPropertyNode("cnpj")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }

}
