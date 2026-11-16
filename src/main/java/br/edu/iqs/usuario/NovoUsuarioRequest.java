package br.edu.iqs.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Corpo da requisicao de criacao de usuario.
 *
 * @param nome nome completo
 * @param email e-mail de contato
 */
@Schema(description = "Dados para criar um usuário")
public record NovoUsuarioRequest(
        @Schema(description = "Nome completo", example = "Maria Silva")
        @NotBlank String nome,
        @Schema(description = "E-mail válido", example = "maria@exemplo.com")
        @NotBlank @Email String email) {
}
