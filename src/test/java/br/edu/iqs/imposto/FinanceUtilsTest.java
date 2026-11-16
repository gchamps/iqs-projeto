package br.edu.iqs.imposto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FinanceUtilsTest {

    private final FinanceUtils utils = new FinanceUtils();

    @ParameterizedTest
    @CsvSource({
        "ICMS, 1000, 170",
        "IPI, 10000, 500",
        "IPI, 20000, 2000",
        "ISS, 1000, 50",
        "PIS, 1000, 6.5",
        "COFINS, 1000, 30"
    })
    void calculaImpostoPorTipo(String tipo, String valor, String esperado) {
        BigDecimal resultado = utils.calcularImposto(new BigDecimal(valor), tipo);
        assertEquals(0, new BigDecimal(esperado).compareTo(resultado));
    }

    @Test
    void valorNuloRetornaZero() {
        assertEquals(BigDecimal.ZERO, utils.calcularImposto(null, "ICMS"));
    }

    @Test
    void tipoDesconhecidoRetornaZero() {
        assertEquals(BigDecimal.ZERO, utils.calcularImposto(BigDecimal.TEN, "XYZ"));
    }

    @Test
    void tipoNuloRetornaZero() {
        assertEquals(BigDecimal.ZERO, utils.calcularImposto(BigDecimal.TEN, null));
    }
}
