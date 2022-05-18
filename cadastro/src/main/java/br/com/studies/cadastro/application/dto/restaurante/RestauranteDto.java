package br.com.studies.cadastro.application.dto.restaurante;

import br.com.studies.cadastro.application.dto.localizacao.LocalizacaoDTO;

import java.util.Date;

public class RestauranteDto {
    public Long id;
    public String nome;
    public String cnpj;
    public String proprietario;
    public Date dataCriacao;
    public LocalizacaoDTO localizacaoDto;
}
