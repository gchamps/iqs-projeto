# Aula 11 — SonarQube e refatoração

## Atividade 1: SonarQube local
```bash
docker run -d --name sonarqube -p 9000:9000 sonarqube:community
```
1. Acesse http://localhost:9000 (login `admin` / `admin`, troque a senha).
2. My Account → Security → **Generate token** (guarde o token).
3. Crie o projeto manualmente com a chave `iqs-projeto`.

## Atividade 2: análise do projeto
O plugin `sonar-maven-plugin` 3.9.1.2184 já está no `pom.xml`.

```bash
mvn clean verify sonar:sonar \
  -Dsonar.projectKey=iqs-projeto \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=SEU_TOKEN
```
(O relatório JaCoCo é lido automaticamente de `target/site/jacoco/jacoco.xml`.)

No dashboard identifique: maior code smell, arquivo com maior dívida técnica e método com maior complexidade ciclomática.

## Atividade 3: refatoração guiada
Antes — `FinanceUtils.calcularImposto` com cadeia de `if/else` (complexidade ciclomática 8).
Depois — Strategy Pattern (pacote `br.edu.iqs.imposto`):

| Classe | Papel |
|--------|-------|
| `ImpostoStrategy` | interface comum |
| `AliquotaFixaStrategy` | base para ICMS, ISS, PIS, COFINS |
| `IcmsStrategy` | 17% |
| `IpiStrategy` | 10% acima de 10.000, senão 5% |
| `FinanceUtils` | mapa tipo → estratégia; método principal com complexidade baixa (≈4, sem crescer a cada novo imposto) |

Passos aplicados: (1) extrair a lógica de cada imposto, (2) eliminar `if/else` com mapa de estratégias,
(3) testes em `FinanceUtilsTest` (valor/tipo nulos, tipo desconhecido, limite do IPI em 10.000).

Para adicionar um imposto novo basta registrar uma estratégia no mapa — `FinanceUtils` não muda de lógica.

## Quality Gate mínimo (tarefa)
Quality Gates → Create → condições em *New Code* e *Overall*:
- Coverage ≥ 80%
- Vulnerabilidades críticas = 0 (Security Rating = A)
- Bugs críticos = 0 (Reliability Rating = A)
- Technical Debt Ratio / Maintainability Rating ≥ A
Associe o gate ao projeto em Project Settings → Quality Gate.
