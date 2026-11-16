package br.edu.iqs.login;

/**
 * Regras de validacao de senha da feature de login (Aula 9, {@code feature/login}).
 *
 * <p>Uma senha valida tem ao menos 8 caracteres, uma letra maiuscula,
 * uma minuscula e um digito.
 *
 * @author Douglas Duarte
 * @version 1.0
 */
public class ValidadorSenha {

    /** Cria uma instancia de ValidadorSenha. */
    public ValidadorSenha() {
        // sem estado
    }

    /** Tamanho minimo aceito para a senha. */
    public static final int TAMANHO_MINIMO = 8;

    /**
     * Verifica se a senha atende as regras.
     *
     * <p>Exemplo: {@code new ValidadorSenha().isValida("Abcdef12")} retorna {@code true}.
     *
     * @param senha senha a validar (pode ser nula)
     * @return {@code true} se a senha e valida
     */
    public boolean isValida(String senha) {
        if (senha == null || senha.length() < TAMANHO_MINIMO) {
            return false;
        }
        boolean maiuscula = false;
        boolean minuscula = false;
        boolean digito = false;
        for (char c : senha.toCharArray()) {
            maiuscula |= Character.isUpperCase(c);
            minuscula |= Character.isLowerCase(c);
            digito |= Character.isDigit(c);
        }
        return maiuscula && minuscula && digito;
    }
}
