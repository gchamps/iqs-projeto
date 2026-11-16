package br.edu.iqs.usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

class UsuarioServiceTest {

    private final UsuarioService service = new UsuarioService();

    @Test
    void criaEBuscaUsuario() {
        Usuario criado = service.criar(new NovoUsuarioRequest("Maria", "maria@exemplo.com"));
        assertEquals(1L, criado.id());
        assertEquals(criado, service.findById(1L));
    }

    @Test
    void usuarioInexistenteLanca404() {
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> service.findById(99L));
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
    }
}
