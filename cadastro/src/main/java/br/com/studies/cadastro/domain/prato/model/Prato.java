package br.com.studies.cadastro.domain.prato.model;

import br.com.studies.cadastro.domain.restaurante.model.Restaurante;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "prato")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Prato extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String nome;
    String descricao;
    BigDecimal preco;

    @ManyToOne
    Restaurante restaurante;
}
