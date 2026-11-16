package br.edu.iqs.usuario;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Usuario do sistema.
 *
 * @param id identificador unico
 * @param nome nome completo
 * @param email e-mail de contato
 */
@Schema(description = "Usuário do sistema")
public record Usuario(
        @Schema(description = "Identificador único", example = "1") Long id,
        @Schema(description = "Nome completo", example = "Maria Silva") String nome,
        @Schema(description = "E-mail de contato", example = "maria@exemplo.com") String email) {
}
