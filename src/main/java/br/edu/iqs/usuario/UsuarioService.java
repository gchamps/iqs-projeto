package br.edu.iqs.usuario;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Servico de usuarios com armazenamento em memoria.
 *
 * @author Douglas Duarte
 * @version 1.0
 */
@Service
public class UsuarioService {

    /** Cria uma instancia de UsuarioService. */
    public UsuarioService() {
        // sem estado
    }

    private final Map<Long, Usuario> usuarios = new ConcurrentHashMap<>();
    private final AtomicLong sequencia = new AtomicLong();

    /**
     * Busca um usuario pelo id.
     *
     * @param id identificador
     * @return usuario encontrado
     * @throws ResponseStatusException com 404 se nao existir
     */
    public Usuario findById(Long id) {
        Usuario usuario = usuarios.get(id);
        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
        return usuario;
    }

    /**
     * Cria e armazena um usuario.
     *
     * @param request dados do novo usuario
     * @return usuario criado, com id gerado
     */
    public Usuario criar(NovoUsuarioRequest request) {
        Usuario usuario = new Usuario(sequencia.incrementAndGet(), request.nome(), request.email());
        usuarios.put(usuario.id(), usuario);
        return usuario;
    }
}
