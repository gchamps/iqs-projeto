# Arquitetura

## Diagrama de componentes

```mermaid
graph LR
    Cliente[Cliente / Swagger UI] -->|HTTP JSON| Controller[UsuarioController]
    Controller --> Service[UsuarioService]
    Service --> Store[(Mapa em memória)]
    Cliente -->|HTTP JSON| ProdCtl[ProdutoController]
    ProdCtl --> Repo[ProdutoRepository]
    Repo --> H2[(H2 / JPA)]
    Controller -.->|anotações| OpenAPI[springdoc-openapi]

    subgraph Biblioteca
        Financeira
        FinanceUtils --> Strategy[ImpostoStrategy]
        Strategy --> Icms[IcmsStrategy]
        Strategy --> Ipi[IpiStrategy]
        Strategy --> Fixa[AliquotaFixaStrategy]
        ValidadorSenha
        NotificacaoService
        Calculadora
    end
```

## Pipeline de qualidade

```mermaid
graph LR
    A[Commit] --> B[Build]
    B --> C[Testes Unitários]
    C --> D[Análise Estática]
    D --> E{Passou?}
    E -->|Sim| F[Deploy Staging]
    E -->|Não| G[Notificação de Falha]
```

## Fluxo de branches (Git Flow)

```mermaid
gitGraph
    commit
    branch develop
    checkout develop
    commit
    branch feature/login
    commit
    checkout develop
    merge feature/login
    branch release/v1.0
    commit
    checkout main
    merge release/v1.0
    branch hotfix/issue-123
    commit
    checkout main
    merge hotfix/issue-123
    checkout develop
    merge hotfix/issue-123
```

## Principais decisões técnicas

| Data       | Decisão                                   | Razão |
|------------|-------------------------------------------|-------|
| 2026-11-16 | Spring Boot 2.7 + springdoc-openapi 1.7.0 | Versão do springdoc usada na Aula 12 exige `javax.*` (Boot 2.x) |
| 2026-11-16 | Strategy Pattern para impostos            | Reduz a complexidade ciclomática (de 8+ para ≈4, constante) e permite novos impostos sem alterar `FinanceUtils` |
| 2026-11-16 | JaCoCo com mínimo de 85% de linhas        | Gate do desafio da Aula 10 aplicado no `mvn verify` |
| 2026-11-16 | Javadoc com `failOnWarnings`              | Critério do desafio da Aula 12: HTML sem warnings |
| 2026-11-16 | Armazenamento em memória                  | Foco do projeto é qualidade/pipeline, não persistência |
| 2026-11-16 | Perfis Maven `integration-tests` e `security` | Mantêm `mvn test` rápido; CI roda cada perfil em job próprio |
| 2026-11-16 | Monolito com Spring Data JPA + H2 (Aula 13) | MVP simples; H2 em desenvolvimento e TestContainers/PostgreSQL recomendado em CI futuro |
| 2026-11-16 | Manter Spring Boot 2.7 (aula cita 3.x) | springdoc-openapi-ui 1.7.0 (Aula 12) exige `javax.*`; migrar para Boot 3 exige springdoc 2.x |
