package br.com.studies.cadastro.application.controller;

import br.com.studies.cadastro.domain.restaurante.model.Restaurante;
import br.com.studies.cadastro.domain.restaurante.services.RestauranteServices;
import br.com.studies.cadastro.util.TokenUtils;
import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;
import org.approvaltests.Approvals;
import org.codehaus.groovy.syntax.TokenUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
@QuarkusTest
//@QuarkusTestResource(PostgresInitializer.class)
public class RestaurantesTest {

    private String token;

    @BeforeEach
    public void gerarToken() {
        try {
            token = TokenUtils.generateTokenString("/JWTProprietarioClaims.json", null);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar token");
        }
    }

    @Inject
    private RestauranteServices restauranteServices;

    @Test
    @DataSet("dataset/restaurantes.yml")
    public void testeBuscarRestaurantes() {
        String resultado = given()
                .when()
                .get("/restaurantes")
                .then()
                .statusCode(200)
                .extract().asString();
        Approvals.verifyJson(resultado);
    }

    @Test
    @DataSet("dataset/restaurantes.yml")
    public void testeAlterarRestaurantes() {
        var restaurante = new Restaurante();
        restaurante.setNome("novonome");
        Long parameterValue = 123L;

        given()
                .pathParam("id", parameterValue)
                .request()
                .header("Accept", ContentType.ANY)
                .header("Content-type", ContentType.JSON)
                .body(restaurante)
                .when()
                .put("/restaurantes/{id}")
                .then()
                .log().body()
                .and()
                .statusCode(204);
        Restaurante res = restauranteServices.buscarPorId(parameterValue).get();

        Assertions.assertEquals(restaurante.getNome(), res.getNome());
    }

    private RequestSpecification given() {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header(new Header("Authorization", "Bearer " + token));
    }
}
