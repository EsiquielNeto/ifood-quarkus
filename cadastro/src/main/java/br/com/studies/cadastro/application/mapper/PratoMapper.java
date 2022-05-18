package br.com.studies.cadastro.application.mapper;

import br.com.studies.cadastro.application.dto.prato.AdicionarPratoDTO;
import br.com.studies.cadastro.application.dto.prato.AtualizarPratoDTO;
import br.com.studies.cadastro.application.dto.prato.PratoDTO;
import br.com.studies.cadastro.domain.prato.model.Prato;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface PratoMapper {

    Prato toPrato(PratoDTO dto);
    Prato toPrato(AdicionarPratoDTO dto);
    void toPrato(AtualizarPratoDTO dto, @MappingTarget Prato prato);
}
