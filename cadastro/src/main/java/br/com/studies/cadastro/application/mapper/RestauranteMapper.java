package br.com.studies.cadastro.application.mapper;

import br.com.studies.cadastro.application.dto.restaurante.AdicionarRestauranteDTO;
import br.com.studies.cadastro.application.dto.restaurante.AtualizarRestauranteDTO;
import br.com.studies.cadastro.application.dto.restaurante.RestauranteDto;
import br.com.studies.cadastro.domain.restaurante.model.Restaurante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface RestauranteMapper {

    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "dataAtualizacao", ignore = true)
    @Mapping(target = "localizacao.id", ignore = true)
    Restaurante toRestaurante(AdicionarRestauranteDTO dto);

    @Mapping(target = "nome", source = "nome")
    Restaurante toRestaurante(AtualizarRestauranteDTO dto, @MappingTarget Restaurante restaurante);

    @Mapping(target = "dataCriacao", dateFormat = "dd/MM/yyyy HH:mm:ss")
    RestauranteDto toRestauranteDto(Restaurante dto);
}
