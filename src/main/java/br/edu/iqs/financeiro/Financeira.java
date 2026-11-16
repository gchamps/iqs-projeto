package br.edu.iqs.financeiro;

/**
 * Calculadora de operações financeiras básicas.
 *
 * <p>Exemplo de uso:
 * <pre>{@code
 * Financeira f = new Financeira();
 * double futuro = f.jurosCompostos(1000.0, 0.01, 12);
 * }</pre>
 *
 * @author Gabriel Champin
 * @version 1.1
 * @see #jurosCompostos(double, double, int)
 */
public class Financeira {

    /** Cria uma instancia de Financeira. */
    public Financeira() {
        // sem estado
    }

    /**
     * Calcula juros compostos.
     *
     * <p>Equivale a {@code principal * (1 + taxa)^meses}.
     *
     * @param principal Valor inicial
     * @param taxa Taxa mensal (0.1 = 10%)
     * @param meses Período
     * @return Valor futuro
     * @throws IllegalArgumentException Se taxa ou meses forem negativos
     * @see Math#pow(double, double)
     */
    public double jurosCompostos(double principal, double taxa, int meses) {
        if (taxa < 0 || meses < 0) {
            throw new IllegalArgumentException("Parâmetros não podem ser negativos");
        }
        return principal * Math.pow(1 + taxa, meses);
    }
}
