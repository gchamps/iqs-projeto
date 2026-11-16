package br.edu.iqs.financeiro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class FinanceiraTest {

    private final Financeira financeira = new Financeira();

    @Test
    void calculaJurosCompostos() {
        assertEquals(1210.0, financeira.jurosCompostos(1000.0, 0.1, 2), 0.0001);
    }

    @Test
    void semMesesRetornaPrincipal() {
        assertEquals(500.0, financeira.jurosCompostos(500.0, 0.1, 0), 0.0001);
    }

    @Test
    void taxaNegativaLancaExcecao() {
        assertThrows(IllegalArgumentException.class, () -> financeira.jurosCompostos(1000, -0.1, 2));
    }

    @Test
    void mesesNegativosLancaExcecao() {
        assertThrows(IllegalArgumentException.class, () -> financeira.jurosCompostos(1000, 0.1, -1));
    }
}
