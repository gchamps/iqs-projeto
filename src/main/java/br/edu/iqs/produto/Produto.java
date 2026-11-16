package br.edu.iqs.produto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import lombok.Data;

/**
 * Entidade JPA de produto (MVP da Aula 13).
 *
 * @author Gabriel Champin
 * @version 1.0
 */
@Entity
@Data
@Schema(description = "Produto do catálogo")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador gerado", accessMode = Schema.AccessMode.READ_ONLY, example = "1")
    private Long id;

    @NotBlank
    @Column(nullable = false)
    @Schema(description = "Nome do produto", example = "Teclado mecânico")
    private String nome;

    @PositiveOrZero
    @Column(precision = 10, scale = 2)
    @Schema(description = "Preço em reais", example = "299.90")
    private BigDecimal preco;

    /** Cria um produto vazio (exigido pelo JPA). */
    public Produto() {
        // construtor exigido pelo JPA
    }
}
