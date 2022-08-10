package br.com.studies.marketplace.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PratoDTO {
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;

}
