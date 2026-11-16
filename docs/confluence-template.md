# Template Confluence (wiki markup)

```
h2. Visão Geral
*Data de Criação*: {{data}}
*Responsável*: {{owner}}

h3. Fluxo Principal
{code:java}
public void processar() { ... }
{code}

h3. Decision Records
||Data||Decisão||Razão||
|12/05|Usar JWT|Mais stateless|
```

Exemplo preenchido para este projeto:

```
h2. Visão Geral
*Data de Criação*: 16/11/2026
*Responsável*: Douglas Duarte

h3. Fluxo Principal
{code:java}
public BigDecimal calcularImposto(BigDecimal valor, String tipo) {
    ImpostoStrategy estrategia = tipo == null ? null : estrategias.get(tipo);
    if (valor == null || estrategia == null) {
        return BigDecimal.ZERO;
    }
    return estrategia.calcular(valor);
}
{code}

h3. Decision Records
||Data||Decisão||Razão||
|16/11|Strategy Pattern para impostos|Menor complexidade ciclomática|
|16/11|Javadoc com failOnWarnings|Documentação sempre válida|
```
