package br.edu.iqs.produto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
class ProdutoControllerTest {

    @Mock
    ProdutoRepository repository;

    @InjectMocks
    ProdutoController controller;

    private Produto produto(Long id) {
        Produto p = new Produto();
        p.setId(id);
        p.setNome("Teclado");
        p.setPreco(new BigDecimal("10.00"));
        return p;
    }

    @Test
    void deveChamarRepositoryAoListar() {
        when(repository.findAll()).thenReturn(List.of(produto(1L)));
        assertEquals(1, controller.listar().size());
        verify(repository).findAll();
    }

    @Test
    void buscaProdutoExistente() {
        Produto p = produto(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(p));
        assertSame(p, controller.buscar(1L));
    }

    @Test
    void buscaProdutoInexistenteLanca404() {
        when(repository.findById(9L)).thenReturn(Optional.empty());
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> controller.buscar(9L));
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
    }

    @Test
    void criaProdutoSemId() {
        ArgumentCaptor<Produto> captor = ArgumentCaptor.forClass(Produto.class);
        when(repository.save(captor.capture())).thenAnswer(inv -> inv.getArgument(0));
        controller.criar(new ProdutoRequest("Teclado", new BigDecimal("10.00")));
        assertNull(captor.getValue().getId());
        assertEquals("Teclado", captor.getValue().getNome());
    }

    @Test
    void atualizaProdutoExistente() {
        ArgumentCaptor<Produto> captor = ArgumentCaptor.forClass(Produto.class);
        when(repository.existsById(5L)).thenReturn(true);
        when(repository.save(captor.capture())).thenAnswer(inv -> inv.getArgument(0));
        controller.atualizar(5L, new ProdutoRequest("Mouse", BigDecimal.ONE));
        assertEquals(5L, captor.getValue().getId());
    }

    @Test
    void atualizarInexistenteLanca404() {
        when(repository.existsById(5L)).thenReturn(false);
        ProdutoRequest entrada = new ProdutoRequest("Mouse", BigDecimal.ONE);
        assertThrows(ResponseStatusException.class, () -> controller.atualizar(5L, entrada));
        verify(repository, never()).save(org.mockito.ArgumentMatchers.any());
    }

    @Test
    void removeProdutoExistente() {
        when(repository.existsById(3L)).thenReturn(true);
        controller.remover(3L);
        verify(repository).deleteById(3L);
    }

    @Test
    void removerInexistenteLanca404() {
        when(repository.existsById(3L)).thenReturn(false);
        assertThrows(ResponseStatusException.class, () -> controller.remover(3L));
        verify(repository, never()).deleteById(3L);
    }
}
