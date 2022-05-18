package br.com.studies.cadastro.domain.restaurante.services;

import br.com.studies.cadastro.application.dto.restaurante.AdicionarRestauranteDTO;
import br.com.studies.cadastro.application.dto.restaurante.AtualizarRestauranteDTO;
import br.com.studies.cadastro.application.dto.restaurante.RestauranteDto;
import br.com.studies.cadastro.application.mapper.RestauranteMapper;
import br.com.studies.cadastro.domain.restaurante.model.Restaurante;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RestauranteServices {

    RestauranteMapper restauranteMapper;

    public List<RestauranteDto> buscar() {
        Stream<Restaurante> restaurantes = Restaurante.streamAll();
        return restaurantes.map(restauranteMapper::toRestauranteDto).collect(Collectors.toList());
    }

    public Optional<Restaurante> buscarPorId(long id) {
        return Restaurante.findByIdOptional(id);
    }

    @Transactional
    public void adicionar(AdicionarRestauranteDTO dto) {
        Restaurante restaurante = restauranteMapper.toRestaurante(dto);
        Restaurante.persist(restaurante);
    }

    @Transactional
    public void atualizar(Long id, AtualizarRestauranteDTO dto) {
        Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(id);

        if (restauranteOp.isEmpty()) {
            throw new IllegalArgumentException("Restaurante não existe");
        }

        Restaurante restaurante = restauranteMapper.toRestaurante(dto, restauranteOp.get());
        Restaurante.persist(restaurante);
    }

    @Transactional
    public void deletar(Long id) {
        Optional<Restaurante> restaurante = Restaurante.findByIdOptional(id);

        restaurante.ifPresentOrElse(
                Restaurante::delete,
                () -> {
                    throw new IllegalArgumentException();
                }
        );
    }
}
