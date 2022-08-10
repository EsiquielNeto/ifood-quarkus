package br.com.studies.marketplace.domain.prato;

import br.com.studies.marketplace.application.dto.PratoDTO;
import br.com.studies.marketplace.domain.restaurante.Restaurante;
import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.pgclient.PgPool;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Prato {
    private Long id;
    private String nome;
    private String descricao;
    private Restaurante restaurante;
    private BigDecimal preco;

    //TODO - Refactor
    public static Multi<PratoDTO> findAll(PgPool pgPool) {
        return null;
    }
}
