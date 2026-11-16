package br.edu.iqs.imposto;

/**
 * ICMS: aliquota fixa de 17%.
 *
 * @author Douglas Duarte
 * @version 1.0
 */
public class IcmsStrategy extends AliquotaFixaStrategy {

    /** Cria a estrategia de ICMS. */
    public IcmsStrategy() {
        super("0.17");
    }
}
