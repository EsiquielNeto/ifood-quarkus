package br.com.studies.cadastro.application.controller;

import br.com.studies.cadastro.application.dto.restaurante.AdicionarRestauranteDTO;
import br.com.studies.cadastro.application.dto.restaurante.AtualizarRestauranteDTO;
import br.com.studies.cadastro.application.dto.restaurante.RestauranteDto;
import br.com.studies.cadastro.domain.restaurante.services.RestauranteServices;
import br.com.studies.cadastro.infra.exception.ConstraintViolationResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.SimplyTimed;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlow;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlows;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.hibernate.mapping.Collection;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;

@Tag(name = "Restaurentes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
@Path("/restaurantes")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RolesAllowed("proprietario")
@SecurityScheme(
        securitySchemeName = "ifood-oauth",
        type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows(password = @OAuthFlow(tokenUrl = "http://localhost:8180/auth/realms/ifood/protocol/openid-connect/token")))
@SecurityRequirement(name = "ifood-oauth", scopes = {})
public class RestauranteResource {

    RestauranteServices restauranteServices;

    @GET
    @Counted(name = "Quantidade de busca restaurantes")
    @SimplyTimed(name = "Tempo  simples de busca")
    @Timed(name = "Tempo completo de busca")
    public List<RestauranteDto> buscar() {
        List<RestauranteDto> lis = restauranteServices.buscar();
        return lis;
    }

    @POST
    @Operation(summary = "Cria um restaurante")
    @APIResponse(
            responseCode = "201",
            description = "Restaurante criado com sucesso",
            content = @Content(schema = @Schema(implementation = AdicionarRestauranteDTO.class)))
    @APIResponse(
            responseCode = "400",
            description = "Erro ao criar restaurante",
            content = @Content(schema = @Schema(implementation = ConstraintViolationResponse.class)))
    public void adicionar(@Valid AdicionarRestauranteDTO dto) {
        restauranteServices.adicionar(dto);
    }

    @PUT
    @Path("{id}")
    public void atualizar(@PathParam("id") Long id, @Valid AtualizarRestauranteDTO dto) {
        restauranteServices.atualizar(id, dto);
    }

    @DELETE
    @Path("{id}")
    public void deletar(@PathParam("id") Long id) {
        restauranteServices.deletar(id);
    }

}
