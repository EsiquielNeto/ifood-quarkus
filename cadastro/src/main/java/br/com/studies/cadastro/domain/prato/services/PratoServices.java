package br.com.studies.cadastro.domain.prato.services;

import br.com.studies.cadastro.application.dto.prato.AdicionarPratoDTO;
import br.com.studies.cadastro.application.dto.prato.AtualizarPratoDTO;
import br.com.studies.cadastro.application.mapper.PratoMapper;
import br.com.studies.cadastro.domain.prato.model.Prato;
import br.com.studies.cadastro.domain.restaurante.model.Restaurante;
import br.com.studies.cadastro.domain.restaurante.services.RestauranteServices;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PratoServices {

    RestauranteServices restauranteServices;
    PratoMapper pratoMapper;

    public List<Prato> buscarPratos(Long id) {
        Optional<Restaurante> restauranteOp = restauranteServices.buscarPorId(id);

        if (restauranteOp.isPresent()) {
            throw new NotFoundException();
        }

        Prato.stream("restaurante", restauranteOp.get());
        return Prato.list("restaurante", restauranteOp.get());
    }

    @Transactional
    public void adicionarPrato(Long id, AdicionarPratoDTO dto) {
        Optional<Restaurante> restauranteOp = restauranteServices.buscarPorId(id);

        if (restauranteOp.isPresent()) {
            throw new NotFoundException();
        }

        Prato prato = pratoMapper.toPrato(dto);
        prato.setRestaurante(restauranteOp.get());

        Prato.persist(prato);
    }

    @Transactional
    public void atualizar(Long idRestaurante, Long id, AtualizarPratoDTO dto) {

        Optional<Restaurante> restauranteOp = restauranteServices.buscarPorId(idRestaurante);
        if (restauranteOp.isPresent()) {
            throw new NotFoundException("Restaurante não existe");
        }

        Optional<Prato> pratoOp = Prato.findByIdOptional(id);
        if (pratoOp.isPresent()) {
            throw new NotFoundException("Prato não existe");
        }

        Prato prato = pratoOp.get();
        pratoMapper.toPrato(dto, prato);
        prato.persist();
    }


    @Transactional
    public void deletar(Long idRestaurante, Long id) {

        Optional<Restaurante> restauranteOp = restauranteServices.buscarPorId(idRestaurante);
        if (restauranteOp.isPresent()) {
            throw new NotFoundException("Restaurante não existe");
        }

        Optional<Prato> pratoOp = Prato.findByIdOptional(id);
        pratoOp.ifPresentOrElse(Prato::delete, () -> {
                    throw new NotFoundException("Prato não existe");
                }
        );
    }
}
