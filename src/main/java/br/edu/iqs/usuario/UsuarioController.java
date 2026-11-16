package br.edu.iqs.usuario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Endpoints REST de usuarios, documentados com OpenAPI (Aula 12).
 *
 * @author Douglas Duarte
 * @version 1.0
 */
@RestController
public class UsuarioController {

    private final UsuarioService service;

    /**
     * Cria o controller.
     *
     * @param service servico de usuarios
     */
    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    /**
     * Obtem um usuario pelo id.
     *
     * @param id identificador do usuario
     * @return usuario encontrado
     */
    @Operation(summary = "Obter usuário por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    })
    @GetMapping("/usuarios/{id}")
    public Usuario getUsuario(@Parameter(description = "ID do usuário", example = "1") @PathVariable Long id) {
        return service.findById(id);
    }

    /**
     * Cria um novo usuario.
     *
     * @param request dados do usuario
     * @return usuario criado
     */
    @Operation(summary = "Criar usuário",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados do novo usuário",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = NovoUsuarioRequest.class),
                            examples = @ExampleObject(name = "Exemplo",
                                    value = "{\"nome\":\"Maria Silva\",\"email\":\"maria@exemplo.com\"}"))))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuário criado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    @PostMapping("/usuarios")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario criarUsuario(@Valid @RequestBody NovoUsuarioRequest request) {
        return service.criar(request);
    }
}
