package br.edu.iqs.notificacao;

import java.util.regex.Pattern;

/**
 * Servico de notificacoes por e-mail (desafio colaborativo da Aula 9).
 *
 * @author Gabriel Champin
 * @version 1.0
 */
public class NotificacaoService {

    /** Cria uma instancia de NotificacaoService. */
    public NotificacaoService() {
        // sem estado
    }

    private static final Pattern EMAIL =
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*\\.[A-Za-z]{2,}$");

    /**
     * Valida o formato de um e-mail.
     *
     * @param email endereco a validar (pode ser nulo)
     * @return {@code true} se o formato e valido
     */
    public boolean emailValido(String email) {
        return email != null && EMAIL.matcher(email).matches();
    }

    /**
     * Envia um e-mail (simulado) apos validar o destinatario.
     *
     * @param destinatario endereco de destino
     * @param mensagem texto da mensagem
     * @return {@code true} quando o envio simulado foi concluido
     * @throws IllegalArgumentException se o e-mail for invalido ou a mensagem estiver vazia
     */
    public boolean enviarEmail(String destinatario, String mensagem) {
        if (!emailValido(destinatario)) {
            throw new IllegalArgumentException("E-mail inválido: " + destinatario);
        }
        if (mensagem == null || mensagem.isBlank()) {
            throw new IllegalArgumentException("Mensagem não pode ser vazia");
        }
        return true;
    }
}
