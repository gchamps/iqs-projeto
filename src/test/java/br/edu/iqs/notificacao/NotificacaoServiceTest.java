package br.edu.iqs.notificacao;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NotificacaoServiceTest {

    private final NotificacaoService service = new NotificacaoService();

    @ParameterizedTest
    @ValueSource(strings = {"a@b.com", "maria.silva+tag@exemplo.com.br", "x_y@sub.dominio.org"})
    void emailsValidos(String email) {
        assertTrue(service.emailValido(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "semarroba.com", "@sem-usuario.com", "a@b", "a b@c.com", "a@b..com"})
    void emailsInvalidos(String email) {
        assertFalse(service.emailValido(email));
    }

    @Test
    void emailNuloEInvalido() {
        assertFalse(service.emailValido(null));
    }

    @Test
    void enviaEmailValido() {
        assertTrue(service.enviarEmail("a@b.com", "Olá"));
    }

    @Test
    void enviarParaEmailInvalidoLancaExcecao() {
        assertThrows(IllegalArgumentException.class, () -> service.enviarEmail("invalido", "Olá"));
    }

    @Test
    void enviarMensagemVaziaLancaExcecao() {
        assertThrows(IllegalArgumentException.class, () -> service.enviarEmail("a@b.com", " "));
        assertThrows(IllegalArgumentException.class, () -> service.enviarEmail("a@b.com", null));
    }
}
