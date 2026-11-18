package br.edu.iqs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CalculadoraTest {

    private final Calculadora calculadora = new Calculadora();

    @Test
    void divideNumerosValidos() {
        assertEquals(1, calculadora.divide(10, 2)); // ERRO PROPOSITAL (Aula 9)
    }

    @Test
    void divisaoPorZeroLancaExcecao() {
        ArithmeticException ex = assertThrows(ArithmeticException.class, () -> calculadora.divide(1, 0));
        assertEquals("Divisor não pode ser zero", ex.getMessage());
    }
}
