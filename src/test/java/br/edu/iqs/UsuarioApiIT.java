package br.edu.iqs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Teste de integracao (executado pelo Failsafe com {@code -Pintegration-tests}).
 * Sobe a aplicacao completa em porta aleatoria e valida o OpenAPI.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UsuarioApiIT {

    @Autowired
    private TestRestTemplate rest;

    @Test
    void openApiExpoeEndpointsDeUsuario() {
        ResponseEntity<String> resposta = rest.getForEntity("/v3/api-docs", String.class);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertTrue(resposta.getBody().contains("/usuarios/{id}"));
        assertTrue(resposta.getBody().contains("Criar usuário"));
    }
}
