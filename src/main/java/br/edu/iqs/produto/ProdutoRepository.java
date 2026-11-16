package br.edu.iqs.produto;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio Spring Data JPA de {@link Produto}.
 *
 * @author Gabriel Champin
 * @version 1.0
 */
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
