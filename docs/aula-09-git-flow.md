# Aula 9 — Git Flow, GitHub Actions e Code Review

## Atividade 1: Git Flow na prática
O histórico deste repositório já foi montado seguindo o Git Flow. Para visualizar:

```bash
git log --graph --oneline --all
```

Branches criadas: `main`, `develop`, `feature/login`, `feature/notificacoes`,
`release/v1.0` (tag `v1.0`) e `hotfix/header-null` (exercício) — esta última partiu de `main`.

## Atividade 2: GitHub Actions + branch protection
O workflow está em `.github/workflows/pr-validation.yml`.

Branch protection (feito na interface do GitHub, não pode ser versionado):
Settings → Branches → Add rule (`main` e `develop`):
- Require status checks to pass → selecione `build`
- Require approvals → 2

Teste: abra um PR com um teste quebrando (`assertEquals(1, 2)`) e veja o check falhar.

## Atividade 3: Code review simulado (PR #123)

Código original:
```java
public int divide(int a, int b) {
    return a / b;
}
```

Comentários de revisão (específicos, com snippet):

> **Calculadora.java, linha 3 — bloqueante**
> `b == 0` lança `ArithmeticException` sem mensagem útil e, em um serviço, vira HTTP 500.
> Sugiro falhar cedo com mensagem clara:
> ```java
> public int divide(int a, int b) {
>     if (b == 0) {
>         throw new ArithmeticException("Divisor não pode ser zero");
>     }
>     return a / b;
> }
> ```
> Também falta um teste para o caso `b == 0`.

Checklist aplicado: funcionalidade ✔ · legibilidade ✔ · testes ✔ (`CalculadoraTest`) · documentação ✔ (Javadoc).

## Desafio colaborativo: `feature/notificacoes`
- Classe `NotificacaoService` com `enviarEmail()` e validação de e-mail por regex.
- `NotificacaoServiceTest` cobre cenários válidos/inválidos, nulos e mensagem vazia.
- Critérios: build passando, comentários resolvidos, 100% de cobertura da classe (verifique em `target/site/jacoco`).

## Tarefa
- Workflow de Javadoc: `.github/workflows/javadoc.yml`.
- Conventional Commits: `tipo(escopo): descrição`, com tipos `feat`, `fix`, `docs`, `test`, `refactor`, `ci`, `chore`.
  Ex.: `feat(login): implementa validação de senha`. `BREAKING CHANGE:` no rodapé indica major.
