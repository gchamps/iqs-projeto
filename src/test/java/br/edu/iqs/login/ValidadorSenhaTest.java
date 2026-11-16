package br.edu.iqs.login;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ValidadorSenhaTest {

    private final ValidadorSenha validador = new ValidadorSenha();

    @Test
    void senhaValida() {
        assertTrue(validador.isValida("Abcdef12"));
    }

    @Test
    void senhaNulaEInvalida() {
        assertFalse(validador.isValida(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Ab1", "abcdefg1", "ABCDEFG1", "Abcdefgh"})
    void senhasInvalidas(String senha) {
        assertFalse(validador.isValida(senha));
    }
}
