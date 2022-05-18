package br.com.studies.cadastro.application.controller;

import br.com.studies.cadastro.application.dto.prato.AdicionarPratoDTO;
import br.com.studies.cadastro.application.dto.prato.AtualizarPratoDTO;
import br.com.studies.cadastro.domain.prato.model.Prato;
import br.com.studies.cadastro.domain.prato.services.PratoServices;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlow;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlows;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.List;

@Tag(name = "Pratos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
@Path("/restaurantes/{idRestaurante}/pratos")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RolesAllowed("proprietario")
@SecurityScheme(
        securitySchemeName = "ifood-oauth",
        type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows(password = @OAuthFlow(tokenUrl = "http://localhost:8180/auth/realms/ifood/protocol/openid-connect/token")))
@SecurityRequirement(name = "ifood-oauth", scopes = {})
public class PratosResource {

    PratoServices pratoServices;

    @GET
    public List<Prato> buscarPratos(@PathParam("idRestaurante") Long id) {
        return pratoServices.buscarPratos(id);
    }

    @POST
    public Response adicionarPrato(@PathParam("idRestaurante") Long id, AdicionarPratoDTO dto) {
        pratoServices.adicionarPrato(id, dto);
        return Response.status(Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    public void atualizar(@PathParam("idRestaurante") Long idRestaurante, @PathParam("id") Long id, AtualizarPratoDTO dto) {
        pratoServices.atualizar(idRestaurante, id, dto);
    }


    @DELETE
    @Path("{id}")
    public void deletar(@PathParam("idRestaurante") Long idRestaurante, @PathParam("id") Long id) {
        pratoServices.deletar(idRestaurante, id);
    }
}
