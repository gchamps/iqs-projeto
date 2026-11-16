package br.edu.iqs.produto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

/**
 * Corpo das requisicoes de criacao e atualizacao de produto (DTO).
 *
 * <p>Evita expor a entidade JPA diretamente na API (regra Sonar S4684).
 *
 * @param nome nome do produto
 * @param preco preco em reais
 */
@Schema(description = "Dados de um produto")
public record ProdutoRequest(
        @Schema(description = "Nome do produto", example = "Teclado mecânico")
        @NotBlank String nome,
        @Schema(description = "Preço em reais", example = "299.90")
        @PositiveOrZero BigDecimal preco) {

    /**
     * Converte o DTO em entidade.
     *
     * @return nova entidade sem id
     */
    public Produto toEntity() {
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setPreco(preco);
        return produto;
    }
}
