# IQS Projeto

Projeto prático das **Aulas 9 a 12** de Qualidade de Software: Git Flow, CI/CD, análise
estática (SonarQube) e documentação. É uma API REST pequena (Spring Boot) com classes de apoio
usadas nos exercícios.

## Requisitos
- Java 17+
- Maven 3.9+
- (opcional) Docker, para SonarQube local e Testcontainers

## Como Executar
```bash
mvn spring-boot:run
```
A API sobe em `http://localhost:8080` e o Swagger UI em `http://localhost:8080/swagger-ui.html`.

## Rotas Principais
| Endpoint          | Método | Descrição                 |
|-------------------|--------|---------------------------|
| `/usuarios`       | POST   | Cria um novo usuário      |
| `/usuarios/{id}`  | GET    | Obtém usuário por ID      |

Exemplo:
```bash
curl -X POST http://localhost:8080/usuarios -H "Content-Type: application/json" \
  -d '{"nome":"Maria Silva","email":"maria@exemplo.com"}'
```

## Comandos úteis
| Objetivo                         | Comando                                   |
|----------------------------------|-------------------------------------------|
| Testes unitários                 | `mvn test`                                |
| Testes de integração             | `mvn verify -Pintegration-tests`          |
| Cobertura (JaCoCo, mínimo 85%)   | `mvn verify` → `target/site/jacoco/`      |
| Estilo (Checkstyle)              | `mvn checkstyle:check`                    |
| PMD                              | `mvn pmd:check`                           |
| Javadoc (sem warnings)           | `mvn javadoc:javadoc` → `target/site/apidocs/index.html` |
| Site de documentação             | `mvn site`                                |
| OWASP Dependency-Check           | `mvn verify -Psecurity`                   |
| SonarQube                        | ver `docs/aula-11-sonarqube.md`           |

## Mapa das aulas
| Aula | Tema | Onde está |
|------|------|-----------|
| 9  | Git Flow, GitHub Actions, code review | `docs/aula-09-git-flow.md`, `.github/workflows/pr-validation.yml`, `Calculadora`, `ValidadorSenha`, `NotificacaoService` |
| 10 | CI/CD | `.github/workflows/ci.yml`, `quality-gate.yml`, `pipeline-completo.yml`, `Jenkinsfile` |
| 11 | Análise estática | `docs/aula-11-sonarqube.md`, pacote `imposto` (Strategy Pattern) |
| 12 | Documentação | Javadoc nas classes, `UsuarioController` (OpenAPI), `ARCHITECTURE.md`, `docs/confluence-template.md` |

Veja também [ARCHITECTURE.md](ARCHITECTURE.md).
