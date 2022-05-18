package br.com.studies.cadastro.application.dto.prato;

import br.com.studies.cadastro.application.dto.restaurante.RestauranteDto;

import java.math.BigDecimal;

public class PratoDTO {
    public Long id;

    public String nome;
    public String descricao;
    public RestauranteDto restauranteDto;
    public BigDecimal preco;
}
