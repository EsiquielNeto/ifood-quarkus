package br.com.studies.cadastro.domain.restaurante.model;

import br.com.studies.cadastro.domain.localizacao.model.Localizacao;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "restaurante")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Restaurante extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String nome;

    String proprietario;

    String cnpj;

    @CreationTimestamp
    Date dataCriacao;

    @UpdateTimestamp
    Date dataAtualizacao;

    @OneToOne(cascade = CascadeType.ALL)
    Localizacao localizacao;
}
