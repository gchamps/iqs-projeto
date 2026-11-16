package br.edu.iqs.produto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * CRUD REST de produtos.
 *
 * @author Gabriel Champin
 * @version 1.0
 */
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository repository;

    /**
     * Cria o controller.
     *
     * @param repository repositorio de produtos
     */
    public ProdutoController(ProdutoRepository repository) {
        this.repository = repository;
    }

    /**
     * Lista todos os produtos.
     *
     * @return lista de produtos (vazia se nao houver)
     */
    @Operation(summary = "Listar produtos")
    @GetMapping
    public List<Produto> listar() {
        return repository.findAll();
    }

    /**
     * Busca um produto pelo id.
     *
     * @param id identificador
     * @return produto encontrado
     */
    @Operation(summary = "Obter produto por ID")
    @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content)
    @GetMapping("/{id}")
    public Produto buscar(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(ProdutoController::naoEncontrado);
    }

    /**
     * Cria um produto.
     *
     * @param produto dados do produto
     * @return produto salvo, com id
     */
    @Operation(summary = "Criar produto")
    @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto criar(@Valid @RequestBody Produto produto) {
        produto.setId(null);
        return repository.save(produto);
    }

    /**
     * Atualiza um produto existente.
     *
     * @param id identificador
     * @param produto novos dados
     * @return produto atualizado
     */
    @Operation(summary = "Atualizar produto")
    @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content)
    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable Long id, @Valid @RequestBody Produto produto) {
        if (!repository.existsById(id)) {
            throw naoEncontrado();
        }
        produto.setId(id);
        return repository.save(produto);
    }

    /**
     * Remove um produto.
     *
     * @param id identificador
     */
    @Operation(summary = "Remover produto")
    @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw naoEncontrado();
        }
        repository.deleteById(id);
    }

    private static ResponseStatusException naoEncontrado() {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
    }
}
