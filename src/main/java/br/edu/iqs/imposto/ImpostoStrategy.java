package br.edu.iqs.imposto;

import java.math.BigDecimal;

/**
 * Estrategia de calculo de um tipo de imposto (Strategy Pattern, Aula 11).
 *
 * @author Douglas Duarte
 * @version 1.0
 */
public interface ImpostoStrategy {

    /**
     * Calcula o imposto sobre um valor.
     *
     * @param valor base de calculo (nao nula)
     * @return imposto devido
     */
    BigDecimal calcular(BigDecimal valor);
}
