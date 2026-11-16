package br.edu.iqs.imposto;

import java.math.BigDecimal;

/**
 * IPI: 10% acima de 10.000 e 5% caso contrario.
 *
 * @author Douglas Duarte
 * @version 1.0
 */
public class IpiStrategy implements ImpostoStrategy {

    /** Cria uma instancia de IpiStrategy. */
    public IpiStrategy() {
        // sem estado
    }

    private static final BigDecimal LIMITE = new BigDecimal("10000");
    private static final BigDecimal ALIQUOTA_ALTA = new BigDecimal("0.10");
    private static final BigDecimal ALIQUOTA_BAIXA = new BigDecimal("0.05");

    @Override
    public BigDecimal calcular(BigDecimal valor) {
        BigDecimal aliquota = valor.compareTo(LIMITE) > 0 ? ALIQUOTA_ALTA : ALIQUOTA_BAIXA;
        return valor.multiply(aliquota);
    }
}
