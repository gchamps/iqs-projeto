package br.edu.iqs.imposto;

import java.math.BigDecimal;

/**
 * Estrategia com aliquota fixa (ex.: ICMS 17%, ISS 5%).
 *
 * @author Gabriel Champin
 * @version 1.0
 */
public class AliquotaFixaStrategy implements ImpostoStrategy {

    private final BigDecimal aliquota;

    /**
     * Cria a estrategia.
     *
     * @param aliquota aliquota em fracao (0.17 = 17%)
     */
    public AliquotaFixaStrategy(String aliquota) {
        this.aliquota = new BigDecimal(aliquota);
    }

    @Override
    public BigDecimal calcular(BigDecimal valor) {
        return valor.multiply(aliquota);
    }
}
