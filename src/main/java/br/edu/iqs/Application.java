package br.edu.iqs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Ponto de entrada da aplicacao.
 *
 * @author Douglas Duarte
 * @version 1.0
 */
@SpringBootApplication
public class Application {

    /** Cria uma instancia de Application. */
    public Application() {
        // sem estado
    }

    /**
     * Inicia a aplicacao Spring Boot.
     *
     * @param args argumentos de linha de comando
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
