package br.com.studies.marketplace.domain.restaurante;

import br.com.studies.marketplace.domain.localizacao.Localizacao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Restaurante {

    private Long id;
    private String nome;
    private Localizacao localizacao;
}
