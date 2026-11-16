package br.edu.iqs.imposto;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Calculo de impostos refatorado com Strategy Pattern.
 *
 * <p>A versao original tinha complexidade ciclomatica 8 (cadeia de
 * {@code if/else}). Agora cada imposto e uma {@link ImpostoStrategy} e o metodo
 * principal tem complexidade baixa.
 *
 * @author Douglas Duarte
 * @version 2.0
 */
public class FinanceUtils {

    /** Cria uma instancia de FinanceUtils. */
    public FinanceUtils() {
        // sem estado
    }

    private final Map<String, ImpostoStrategy> estrategias = Map.of(
            "ICMS", new IcmsStrategy(),
            "IPI", new IpiStrategy(),
            "ISS", new AliquotaFixaStrategy("0.05"),
            "PIS", new AliquotaFixaStrategy("0.0065"),
            "COFINS", new AliquotaFixaStrategy("0.03"));

    /**
     * Calcula o imposto de um valor.
     *
     * @param valor base de calculo
     * @param tipo tipo do imposto (ICMS, IPI, ISS, PIS, COFINS)
     * @return imposto devido, ou {@link BigDecimal#ZERO} se valor/tipo forem nulos ou desconhecidos
     */
    public BigDecimal calcularImposto(BigDecimal valor, String tipo) {
        ImpostoStrategy estrategia = tipo == null ? null : estrategias.get(tipo);
        if (valor == null || estrategia == null) {
            return BigDecimal.ZERO;
        }
        return estrategia.calcular(valor);
    }
}
