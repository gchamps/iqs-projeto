package br.edu.iqs;

/**
 * Calculadora basica usada no exercicio de code review (Aula 9).
 *
 * <p>A versao original de {@code divide} nao tratava divisao por zero.
 * Esta e a versao revisada, com fail fast.
 *
 * @author Douglas Duarte
 * @version 1.1
 */
public class Calculadora {

    /** Cria uma instancia de Calculadora. */
    public Calculadora() {
        // sem estado
    }

    /**
     * Divide dois inteiros.
     *
     * @param a dividendo
     * @param b divisor
     * @return resultado da divisao inteira
     * @throws ArithmeticException se {@code b} for zero
     */
    public int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Divisor não pode ser zero");
        }
        return a / b; // hotfix: divisao validada acima
    }
}
