# Territory Predictive Insights — Clima local

Projeto acadêmico da Global Solution de Programação Orientada a Objetos — Tema: **CitizenTech**.

Aplicação Java de console para simular o monitoramento de chuva, nível de rio e umidade do solo em um território. O sistema valida medições, mantém o histórico em banco de dados, aplica duas estratégias simples de previsão e registra alertas com justificativas e sugestões de ação.

## 1. Estado atual do projeto

**Esta versão ainda está em conclusão.** O sensor de nível do rio foi implementado por Pedro e os testes automatizados da suíte foram ampliados. A validação manual completa do fluxo e da persistência após reinício continua pendente.

Marco validado em 19/11/2026 na branch `fix/sensor-chuva-readme`:

- Sensor de chuva implementado e cinco testes específicos adicionados por Bruna.
- Compilação com alvo Java 17 (`release 17`).
- Execução de `bash mvnw test`: **44 testes, 0 falhas, 0 erros e 0 ignorados; BUILD SUCCESS**.
- Sensor de nível do rio implementado, com cinco testes específicos adicionados por Pedro.
- Commit `9a42249`: `Implementa sensor de chuva e adiciona testes unitarios`, enviado ao GitHub.
- Sensor de umidade já implementado na base recebida.
- Persistência e menu implementados; validação manual completa, incluindo reinício da aplicação, pendente.
- Integração das branches à `main` pendente de confirmação.

O resultado de 44 testes corresponde ao estado atual após a implementação dos sensores de chuva e rio. O roteiro de validação final está na seção 12.

## 2. Equipe e divisão de tarefas

| Integrante | RM | Participação e situação |
|---|---|---|
| Bruna Marques e Queiroz | 565648 | Implementação do `RainSensor`, inclusão do `RainSensorTest`, execução da primeira suíte com 39 testes aprovados e preparação desta documentação. |
| Pedro Lisboa | PREENCHER | Implementação do `RiverLevelSensor`, inclusão do `RiverLevelSensorTest` e execução da suíte atual com 44 testes aprovados. |
| Helena Barbosa | PREENCHER | O repositório está hospedado na conta `helenabc01`. Confirmar e registrar sua contribuição técnica efetiva antes da entrega. |

As responsabilidades planejadas não devem ser apresentadas como trabalho já realizado. Cada integrante deve registrar as próprias alterações com nome e e-mail corretos no Git e compreender o código apresentado.

## 3. Problema, público beneficiado e recorte

O recorte escolhido é **chuva intensa e risco de alagamento em pontos vulneráveis**. O território de demonstração é identificado no código como “Zona Leste — Bacia do Córrego Verde”, com população exposta e pontos vulneráveis usados como parâmetros didáticos. Esses valores não representam um levantamento oficial.

O público beneficiado proposto inclui moradores de áreas suscetíveis a alagamento, associações de bairro, equipes municipais e Defesa Civil. A aplicação apoia decisões como acompanhar o aumento das leituras, vistoriar a drenagem, preparar equipes e avaliar a necessidade de avisos à comunidade.

O protótipo usa leituras simuladas e não está integrado a equipamentos físicos, serviços meteorológicos ou canais externos de notificação. Os alertas aparecem no console e são registrados no banco. Nenhum dado pessoal de moradores é necessário.

## 4. Tecnologias e organização

| Tecnologia | Uso |
|---|---|
| Java 17 | Linguagem e alvo de compilação. |
| Maven Wrapper | Execução do build e dos testes com a versão definida no projeto. |
| Spring Boot | Inicialização da aplicação e gerenciamento dos componentes. |
| Spring Data JPA | Persistência e consultas de medições e alertas. |
| H2 | Banco local em arquivo; banco em memória no perfil de testes. |
| JUnit 5 | Testes de domínio e regras de negócio. |
| Mockito | Controle do gerador aleatório e simulação de repositórios nos testes. |

O pacote-base é `br.com.fiap.territorypredictiveinsightsfev`.

| Caminho relativo | Conteúdo |
|---|---|
| `src/main/java/br/com/fiap/territorypredictiveinsightsfev/domain/` | Componentes territoriais, sensores, estratégias, medições, alertas e tipos auxiliares. |
| `src/main/java/br/com/fiap/territorypredictiveinsightsfev/exception/` | `DadosInvalidosException` e `FonteIndisponivelException`. |
| `src/main/java/br/com/fiap/territorypredictiveinsightsfev/persistence/` | `MedicaoRepository` e `AlertaRepository`. |
| `src/main/java/br/com/fiap/territorypredictiveinsightsfev/service/` | `ServicoMonitoramento`, `FabricaPrevisao` e `ConfiguracaoSistema`. |
| `src/main/java/br/com/fiap/territorypredictiveinsightsfev/SistemaMonitoramento.java` | Menu de console implementado com `Scanner` e `CommandLineRunner`. |
| `src/main/java/br/com/fiap/territorypredictiveinsightsfev/TerritoryPredictiveInsightsFevApplication.java` | Ponto de entrada do Spring Boot. |
| `src/main/resources/application.properties` | Configuração da aplicação e do banco. |
| `src/test/java/br/com/fiap/territorypredictiveinsightsfev/` | Testes, organizados em pacotes equivalentes aos do código principal. |
| `src/test/resources/application-test.properties` | Banco em memória para os testes. |

## 5. Como executar

### Pré-requisitos

- JDK 17 instalado e configurado em `JAVA_HOME`.
- Acesso à internet na primeira execução para baixar o Maven e as dependências.
- Terminal aberto na raiz do projeto, onde estão `pom.xml`, `mvnw` e `mvnw.cmd`.

Não é necessário instalar um banco externo nem o Maven separadamente. O projeto é uma aplicação de console; não exige API REST ou interface gráfica.

### Baixar o projeto

```bash
git clone https://github.com/helenabc01/gs-territory-predictive-insights-fev.git
cd gs-territory-predictive-insights-fev
```

Enquanto as correções não estiverem integradas à `main`, consulte o estado das branches. O roteiro do Pedro explica como partir da branch que contém a implementação de chuva e este README.

### Linux e macOS

```bash
bash mvnw -v
bash mvnw test
bash mvnw spring-boot:run
```

O primeiro comando deve indicar Java 17. `bash mvnw` permite executar o wrapper sem depender da permissão de execução do arquivo.

Se `JAVA_HOME` estiver incorreto, ajuste-o para a pasta real do JDK no seu computador, sem acrescentar `/bin` ao final. Exemplo, a ser adaptado:

```bash
export JAVA_HOME="/caminho/real/do/jdk-17"
export PATH="$JAVA_HOME/bin:$PATH"
bash mvnw -v
```

Esses `export` valem para a sessão atual do terminal. O caminho do JDK varia entre computadores.

### Windows — PowerShell

```powershell
.\mvnw.cmd -v
.\mvnw.cmd test
.\mvnw.cmd spring-boot:run
```

### IntelliJ IDEA

Abra a pasta que contém o `pom.xml`, carregue o projeto Maven e selecione JDK 17 e language level 17. O SDK da IDE e o Java usado no terminal podem ser diferentes; confirme o terminal com o comando de versão acima.

Se o terminal integrado ficar vazio, use o terminal do sistema na raiz do projeto. Como diagnóstico no IntelliJ, pode-se testar o terminal `Classic` com shell `/bin/bash --noprofile --norc` e integração de shell desativada. Essa alternativa ignora os arquivos pessoais de inicialização; pode ser necessário configurar `JAVA_HOME` nessa sessão.

### Menu

| Opção | Comportamento |
|---|---|
| `1` — Verificar sensores | Solicita de 1 a 50 rodadas; tenta ler cada sensor, validar e gravar cada medição válida. |
| `2` — Rodar previsões | Executa a estratégia configurada ou ambas, usando o histórico de cada tipo de sensor. |
| `3` — Ver histórico | Consulta medições por sensor ou alertas emitidos. A tela de medições mostra até 20 registros mais recentes. |
| `4` — Gerenciar dados do território | Altera população exposta, pontos vulneráveis, limiares e estratégia padrão. |
| `5` — Status completo | Exibe território, contagens e últimas leituras, limiares, áreas de risco, estratégia e últimos alertas. |
| `0` — Sair | Encerra o menu. |

O código de acesso didático para alterar limiares é **`GS2026`**, definido em `ServicoMonitoramento`. Ele demonstra a restrição da alteração pela classe `DadosTerritorio`; não é um mecanismo de autenticação adequado para uso real.

## 6. Sensores, validação e exceções

| Sensor | Tipo | Unidade | Faixa aceita por `Medicao` | Simulação | Situação |
|---|---|---|---|---|---|
| `RainSensor` | `RAIN` | mm/h | 0 a 150, inclusive | 0 a 80, uma casa decimal; 5% de chance de falha por verificação | Implementado e com cinco testes específicos. |
| `RiverLevelSensor` | `RIVER_LEVEL` | cm | 0 a 800, inclusive | 100 a 700, uma casa decimal; 5% de chance de falha | Implementado e com cinco testes específicos. |
| `SoilMoistureSensor` | `SOIL_MOISTURE` | % | 0 a 100, inclusive | 40 a 100; 5% de chance de falha. Após passar pela verificação de funcionamento, há 3% de chance de retornar 105 para simular leitura inválida. | Implementado na base recebida. |

As faixas e probabilidades são parâmetros didáticos. A faixa de validação pode ser maior que a faixa usada para gerar as leituras de demonstração.

`ServicoMonitoramento.readAndSave()` obtém a leitura, cria uma `Medicao` validada e só então chama o repositório. Medições fora de faixa ou `NaN` lançam `DadosInvalidosException`. Leituras inválidas e falhas de sensor não devem produzir um registro de medição.

- **`DadosInvalidosException`**: representa erros de validação, como medição fora da faixa, população negativa, alteração indevida de limiar ou histórico insuficiente para prever.
- **`FonteIndisponivelException`**: representa indisponibilidade do sensor ou fonte de dados.

As duas exceções são checked, tornando explícita a necessidade de propagação ou tratamento. O menu as captura e apresenta mensagens compreensíveis. Erros de digitação numérica também recebem mensagens. O construtor de `DadosTerritorio` e a fábrica usam `IllegalArgumentException` para argumentos de criação/configuração inválidos.

O sensor de rio funciona como os outros sensores simulados: falhas são representadas por `FonteIndisponivelException`, enquanto leituras fora da faixa são rejeitadas pela validação de `Medicao`.

## 7. Previsões e alertas

As estratégias recebem o histórico consultado no banco, ordenado do registro mais recente para o mais antigo. A escolha é centralizada na fábrica; o serviço chama `prever()` de forma polimórfica.

| Estratégia | Cálculo | Histórico mínimo |
|---|---|---|
| `PrevisaoMediaMovel` | Média das cinco últimas medições válidas. | 5 medições do sensor. |
| `PrevisaoComparacao` | Média das três últimas leituras + diferença entre essa média e a das três anteriores; resultado limitado inferiormente a zero. | 6 medições do sensor. |

Exemplo: se a média recente é 40 e a anterior é 30, a comparação projeta `40 + (40 - 30) = 50`. Se faltar histórico, a previsão é suspensa com explicação, sem emitir alerta para aquela previsão.

Não há intervalo fixo entre leituras nem horizonte calibrado em dias. As projeções resumem a sequência de medições; não demonstram antecipação de um evento real para amanhã ou para a próxima semana.

### Limiares padrão

| Tipo de sensor | Atenção | Alerta | Crítico |
|---|---|---|---|
| Chuva — mm/h | 10 | 30 | 50 |
| Nível do rio — cm | 300 | 450 | 600 |
| Umidade do solo — % | 70 | 85 | 95 |

Os limiares vêm de `ConfiguracaoSistema`. Para cada previsão, o sistema seleciona o nível mais grave cujo limite tenha sido atingido ou ultrapassado (`>=`). Abaixo de Atenção, informa que não há alerta para aquela previsão.

O alerta gravado identifica sensor, nível, valor previsto, limite atingido, estratégia, mensagem e data/hora. A mensagem recomenda acompanhar/vistoriar no nível Atenção, avisar/preparar equipes no nível Alerta e acionar apoio no nível Crítico. Essas ações são sugestões no console; não são executadas automaticamente.

As previsões são iniciadas pelo usuário na opção 2. A comparação com limiares e a gravação dos alertas ocorrem automaticamente dentro dessa execução. Repetir uma previsão pode gravar novos alertas equivalentes; não há deduplicação implementada.

## 8. Persistência e decisões técnicas

Foi adotado o caminho **Spring Boot + Spring Data JPA**, permitido no enunciado. `Medicao` e `Alerta` são entidades; os repositórios concentram as operações de acesso ao banco. O Spring gerencia os recursos de persistência, sem necessidade de abrir e fechar conexões manualmente no serviço.

Configuração principal:

```properties
spring.datasource.url=jdbc:h2:file:./data/territorio
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
```

O H2 em arquivo reduz a configuração necessária para a correção e permite preservar medições e alertas após encerrar o programa. As tabelas são criadas/atualizadas a partir das entidades. Não é necessário fornecer um script SQL separado para esse caminho.

A pasta `data/` é relativa ao diretório de execução: reinicie a aplicação na mesma raiz do projeto para consultar o mesmo histórico. Essa pasta está no `.gitignore`, assim como `target/` e os arquivos locais da IDE.

**Somente medições e alertas são persistidos.** População, pontos vulneráveis, limiares alterados, estratégia padrão e estado das áreas de risco ficam em memória e voltam aos valores iniciais após reiniciar. O status das áreas de risco representa a execução atual e retém o maior nível registrado nela.

Os testes de contexto usam o perfil `test`, H2 em memória e `create-drop`, com o menu desativado pelo perfil. Os testes de serviço usam Mockito para verificar a lógica sem banco real.

Outras decisões:

- Console com `Scanner`: atende ao escopo e concentra o esforço em POO, persistência, exceções e testes.
- Sensores simulados: permitem demonstrar leituras, indisponibilidade e validação sem hardware.
- Duas previsões simples: permitem demonstrar comportamentos diferentes por polimorfismo, sem depender de modelos estatísticos complexos.
- `Random` injetável no sensor de chuva: mantém o construtor padrão para a aplicação e permite controlar os valores nos testes, evitando resultados que dependam da sorte.
- Uma casa decimal na leitura: facilita a apresentação e fornece um comportamento de arredondamento verificável.

## 9. POO e padrões criacionais

### Domínio orientado a objetos

- `ComponenteTerritorial` é abstrata e define `verificarStatus()`; `RiskArea` herda dela e implementa o comportamento.
- `Sensor` define o contrato comum dos três sensores. `RainSensor`, `RiverLevelSensor` e `SoilMoistureSensor` implementam esse contrato.
- `DadosTerritorio` mantém atributos privados e exige código de acesso para alterar limiares pelo seu método de negócio.
- `EstrategiaPrevisao` é abstrata. `PrevisaoMediaMovel` e `PrevisaoComparacao` usam `super`, compartilham validação e sobrescrevem `prever()`.

### Factory — `FabricaPrevisao`

A fábrica centraliza a associação entre os identificadores de estratégia e seus construtores, usando um mapa de `Supplier`. Ela fica na criação de previsões porque é ali que existe a variação entre algoritmos. O menu pede a estratégia configurada ou todas as disponíveis, e o serviço trabalha com a abstração, sem espalhar verificações de classe ou `instanceof` para decidir qual cálculo executar.

### Singleton — `ConfiguracaoSistema`

O construtor privado e `getInstance()` fornecem uma instância compartilhada de configuração. O padrão foi colocado nessa classe porque limiares e estratégia padrão precisam ser consultados de modo consistente pelo menu e pelo serviço durante a sessão. Isso não substitui persistência: as mudanças permanecem apenas em memória. Os testes relevantes restauram os valores padrão para reduzir interferência entre cenários.

## 10. Testes automatizados

Execute toda a suíte:

```bash
bash mvnw test
```

Execute somente os testes do sensor de chuva:

```bash
bash mvnw -Dtest=RainSensorTest test
```

Resultado confirmado no marco atual: **44 testes aprovados**, incluindo cinco em `RainSensorTest` e cinco em `RiverLevelSensorTest`:

1. Geração e arredondamento da leitura para uma casa decimal.
2. Lançamento de `FonteIndisponivelException` na falha simulada, sem gerar a leitura em seguida.
3. Funcionamento no limite de probabilidade de falha (`0.05`).
4. Identificação do tipo, unidade e faixa do sensor de chuva.
5. Aceitação dos limites válidos e rejeição de medições fora da faixa pela classe `Medicao`, usando o sensor de chuva.

Os testes seguem Arrange, Act e Assert. `assertThrows` verifica exceções customizadas. `ServicoMonitoramentoTest` usa repositórios simulados com Mockito para conferir emissão de alerta, cálculo e gravação sem depender de banco ou digitação no console. Outras classes cobrem estratégias, território, configuração, fábrica, medições e áreas de risco; há também um teste de carregamento do contexto Spring.

Os testes específicos do sensor de rio estão em `RiverLevelSensorTest`. A suíte automatizada não substitui a validação manual completa do fluxo e da recuperação do histórico após reinício.

## 11. Reflexão crítica

O sistema deve suspender a previsão quando não houver histórico suficiente. A média móvel exige cinco medições válidas e a comparação exige seis, porque compara dois grupos de três. Esse mínimo evita calcular uma projeção sem a base definida pelo algoritmo, mas não comprova confiabilidade estatística. Mesmo com muitas leituras, dados antigos, concentrados em um único ponto ou coletados em condições diferentes podem produzir interpretações inadequadas. A versão atual conta registros; ela ainda não verifica automaticamente se o histórico é recente ou se os intervalos de coleta são regulares.

Uma falha de sensor não equivale a chuva zero, rio baixo ou solo seco. Por isso, leituras indisponíveis ou inválidas devem ser informadas e não incorporadas como valores normais. A ausência de um alerta também não deve ser interpretada como garantia de segurança: pode haver dados insuficientes, atraso de atualização ou um fenômeno fora do alcance dos sensores. O sistema informa essas limitações por mensagens, mas não substitui acompanhamento local.

Os dados simulados não representam uma comunidade real. Um sensor por tipo não descreve todas as ruas, a capacidade de drenagem, o relevo, a impermeabilização do solo ou as diferenças entre pontos de uma região. Uma leitura alta de umidade, isoladamente, não demonstra que haverá alagamento. Da mesma forma, os limiares didáticos escolhidos não são limites oficiais de emergência. A extrapolação da estratégia de comparação também pode produzir projeções acima de uma faixa física plausível, como umidade maior que 100%; essa limitação precisa ser considerada na interpretação e em uma futura evolução do algoritmo.

O alerta deve apoiar uma pessoa responsável, fornecendo sensor, valor, limite, estratégia e justificativa. A decisão de avisar moradores, interditar uma via ou abrir um ponto de apoio exige avaliação humana, protocolos oficiais e outras fontes de informação. O protótipo demonstra como organizar e testar esse apoio com POO; não demonstra capacidade operacional de prever desastres. Uma evolução exigiria sensores reais, calibração local, avaliação de falsos positivos e falsos negativos, controle de qualidade temporal e validação com os responsáveis pelo território.

## 12. Continuidade — roteiro para Pedro

**Responsável pela implementação: Pedro. Esta seção conserva o roteiro usado para concluir o sensor de rio e orientar a validação final da entrega.**

### 12.1. Começar pela versão correta

Bruna publicou o sensor de chuva na branch `fix/sensor-chuva-readme`. Este README também deverá estar enviado nessa branch antes de Pedro começar. Criar a branch de Pedro a partir dela garante que os dois arquivos de chuva e esta documentação estarão disponíveis, mesmo antes do merge na `main`.

Se ainda não tiver o repositório, clone-o pelo comando da seção 5. Se já tiver, abra o terminal na raiz e confira `git status`; conclua ou preserve alterações locais antes de trocar de branch.

```bash
git fetch origin
git switch -c fix/sensor-rio-validacao origin/fix/sensor-chuva-readme
```

O comando acima é para criar a branch uma única vez. Se ela já existir, use `git switch fix/sensor-rio-validacao` e confira se contém o trabalho de Bruna antes de prosseguir. Não recrie a branch nem descarte alterações locais.

Configure sua própria identidade somente neste repositório, substituindo os exemplos pelos seus dados:

```bash
git config --local user.name "NOME COMPLETO DO PEDRO"
git config --local user.email "EMAIL DO PEDRO VINCULADO AO GITHUB"
git branch --show-current
git config user.name
git config user.email
```

Use sua própria conta para enviar os commits. O e-mail configura a autoria; ele não faz login no GitHub.

### 12.2. Implementar `RiverLevelSensor`

Editar este arquivo existente:

```text
src/main/java/br/com/fiap/territorypredictiveinsightsfev/domain/RiverLevelSensor.java
```

Substituir os seis métodos pendentes por comportamentos funcionais:

| Método | Comportamento esperado |
|---|---|
| `readValue()` | Verificar funcionamento; se falhar, lançar `FonteIndisponivelException` com mensagem clara. Caso contrário, simular valor de 100 a 700 cm e arredondar para uma casa decimal. |
| `isWorking()` | Simular 5% de chance de falha por verificação. |
| `getType()` | Retornar `SensorType.RIVER_LEVEL`. |
| `getUnit()` | Retornar `"cm"`. |
| `getMinValue()` | Retornar `0`. |
| `getMaxValue()` | Retornar `800`. |

Usar constantes privadas para limites, faixa simulada e taxa de falha. Preservar o construtor sem argumentos, pois o serviço já instancia `new RiverLevelSensor()`. Seguir o desenho do sensor de chuva com um segundo construtor que receba `Random` para testes controlados. O serviço já inclui o sensor de rio na lista; não é necessário criar outro menu ou outro repository.

O gerador deverá usar a faixa de rio, não a faixa de chuva: `100 + random.nextDouble() * (700 - 100)`. As medições continuam sendo validadas e gravadas pelo fluxo existente, sem acesso ao repository dentro do sensor. Após a implementação, nenhum método desse sensor deverá lançar `UnsupportedOperationException` por falta de código.

### 12.3. Criar testes do sensor de rio

Criar o arquivo **dentro de `src/test/java`**:

```text
src/test/java/br/com/fiap/territorypredictiveinsightsfev/domain/RiverLevelSensorTest.java
```

Não colocar esse teste em `src/main/java`: JUnit e Mockito são dependências de teste.

Usar `RainSensorTest` como referência e adaptar tipo, unidade, limites e cálculo. Cobrir pelo menos:

1. Leitura e arredondamento com valores controlados. Exemplo: `0.50` para funcionamento e `0.5356` para leitura devem produzir `421.4 cm`.
2. Falha com valor aleatório `0.04`, verificando `FonteIndisponivelException` e que a geração da medição não ocorre depois.
3. Funcionamento no limite `0.05`.
4. Tipo `RIVER_LEVEL`, unidade `cm` e faixa de 0 a 800.
5. `Medicao` aceitando 0 e 800, e rejeitando -0.1 e 800.1 com `DadosInvalidosException`.

Executar:

```bash
bash mvnw -Dtest=RiverLevelSensorTest test
bash mvnw test
```

Se forem adicionados exatamente cinco testes e nenhum outro for alterado, o total esperado será **44**. Registrar o total efetivamente executado e o resultado real; não atualizar o README apenas pelo número esperado.

### 12.4. Validar a aplicação e o banco

Executar `bash mvnw spring-boot:run` e seguir este roteiro. Registrar o resultado observado de cada item:

| Etapa | Ação | Resultado esperado |
|---|---|---|
| Histórico insuficiente | Com um banco de demonstração vazio, usar opção 2 e escolher ambas as estratégias. | Previsões suspensas com explicação, sem novos alertas. |
| Coleta | Opção 1, inicialmente 10 rodadas. | Leituras válidas dos três sensores gravadas; possíveis falhas simuladas informadas. Nenhum `[PENDENTE]` por falta de implementação. |
| Quantidade | Opção 5. | Pelo menos seis leituras válidas por sensor. Se faltar, repetir a coleta: rodadas com falha não contam como medição válida. |
| Histórico | Opção 3 → medições; consultar cada sensor. | Tipo, valor, unidade e data/hora coerentes. |
| Previsão | Opção 2 → todas as estratégias. | Dois resultados por sensor, com valores e significados. |
| Alertas | Opção 3 → alertas. | Alertas gerados anteriormente aparecem com justificativas. Se nenhuma previsão ultrapassar um limite, coletar mais e repetir; a ausência de alerta não é necessariamente erro. |
| Validação territorial | Opção 4; tentar população negativa e código incorreto ao alterar limiar. | Mensagem clara e alteração recusada. |
| Configuração | Opção 4; mudar um limiar com `GS2026`, preservando Atenção < Alerta < Crítico; mudar a estratégia padrão. | Novos valores aparecem no status e a opção de estratégia configurada usa a escolha realizada. |
| Reinício | Anotar contagens/últimas medições e um alerta, sair com 0 e iniciar novamente na mesma pasta. | Medições e alertas permanecem no banco antes de novas leituras. Configurações em memória voltam aos padrões, conforme documentado. |

Para testar com um banco vazio sem apagar o histórico existente, é possível iniciar a aplicação com um caminho separado para demonstração:

```bash
bash mvnw spring-boot:run -Dspring-boot.run.arguments="--spring.datasource.url=jdbc:h2:file:./data/validacao-pedro"
```

Esse caminho estará vazio apenas se ainda não tiver sido usado. Para testar o reinício, repita exatamente esse comando e consulte os registros anteriores. Não rode duas instâncias simultâneas contra o mesmo arquivo H2.

### 12.5. Atualizar o README e registrar commits

Após concluir e validar:

- Trocar o estado do sensor de rio para implementado, informando os testes realmente executados.
- Atualizar o total final de testes e a data da verificação.
- Registrar o resultado do teste manual de persistência após reinício.
- Completar nomes, RMs e contribuições reais da equipe.
- Ajustar a seção de uso de IA para incluir o auxílio realmente utilizado por Pedro e como foi revisado.
- Converter este roteiro em registro de conclusão ou removê-lo da versão final, preservando as instruções úteis de execução e validação.

Conferir as alterações e registrar primeiro a implementação e seus testes:

```bash
git diff --check
git status --short
git add src/main/java/br/com/fiap/territorypredictiveinsightsfev/domain/RiverLevelSensor.java src/test/java/br/com/fiap/territorypredictiveinsightsfev/domain/RiverLevelSensorTest.java
git diff --cached
git commit -m "Implementa sensor de nivel do rio e adiciona testes"
```

Registrar a documentação atualizada:

```bash
git add README.md
git diff --cached
git commit -m "Documenta validacao final e contribuicoes da equipe"
git push -u origin fix/sensor-rio-validacao
```

Se o GitHub exigir autenticação HTTPS, a senha da conta/Google não serve no campo Password do Git. Com GitHub CLI instalado, usar `gh auth login --hostname github.com --git-protocol https --web`, entrar na própria conta e concluir a autorização no navegador. Para não configurar o helper de autenticação permanentemente, responder `n` à pergunta de autenticar o Git e enviar com:

```bash
git -c credential.helper= -c 'credential.helper=!gh auth git-credential' push -u origin fix/sensor-rio-validacao
```

É necessário ter permissão de escrita no repositório da Helena. Se houver erro de permissão, confirmar o convite de colaborador; não usar a conta de outra pessoa. Nunca colocar tokens no README ou nos commits.

### 12.6. Integrar e entregar

1. Abrir pull request da branch de Bruna para `main` e revisar os arquivos incluídos.
2. Integrar essa alteração e abrir/revisar o pull request da branch de Pedro para `main`. A branch de Pedro já deve conter a base de Bruna por ter sido criada a partir dela.
3. Para preservar os commits individuais e sua autoria, preferir **Create a merge commit** se essa opção estiver disponível no repositório. Se surgirem conflitos no README, manter a documentação final coerente com o código integrado.
4. Depois de integrar, com o diretório de trabalho limpo, atualizar a versão final e executar a suíte:

```bash
git switch main
git pull --ff-only origin main
bash mvnw test
```

5. Conferir que a `main` contém os três sensores funcionais, testes, README final e contribuições identificáveis dos integrantes.
6. Entregar o link do repositório exigido pelo professor. Publicar uma branch não a integra automaticamente à `main`.

## 13. Uso de inteligência artificial

O ChatGPT foi utilizado como ferramenta de apoio à ideação, à revisão ortográfica e à organização da documentação, além de auxiliar na elaboração e revisão de trechos de código e testes. As sugestões aplicadas foram verificadas por meio da execução local da suíte, com 44 testes aprovados nesta etapa. A equipe permanece responsável pelas decisões, pela compreensão do código e pela validação final do projeto.

## 14. Checklist antes da entrega

- [x] Domínio com classe abstrata, herança e estratégias polimórficas implementadas.
- [x] Exceções customizadas e tratamento no menu presentes.
- [x] Sensor de chuva implementado, com cinco testes específicos aprovados.
- [x] Sensor de umidade implementado na base recebida.
- [x] Sensor de nível do rio implementado e testado por Pedro.
- [x] Entidades, repositories e configuração H2 em arquivo presentes.
- [ ] Fluxo completo e recuperação de medições/alertas após reinício validados manualmente.
- [x] Factory e Singleton descritos e justificados neste README.
- [x] Marcos de 39 testes para chuva e 44 testes após o sensor de rio, incluindo `assertThrows` e Mockito.
- [x] Suíte atual executada após a implementação do sensor de rio: 44 testes aprovados.
- [x] Público beneficiado, decisões técnicas, reflexão crítica e uso de IA documentados com as informações confirmadas.
- [ ] Nomes completos, RMs, contribuições e uso de IA de todos os integrantes confirmados.
- [ ] Commits dos integrantes e integração final à `main` conferidos.
- [ ] Execução seguindo o README conferida na versão final de entrega.

O bônus do enunciado — exportação CSV, simulação de 24 horas ou dados públicos reais como base — não está confirmado nesta versão e não é necessário para cumprir os requisitos obrigatórios. Várias rodadas manuais de leitura, sem modelagem temporal, não equivalem por si só à simulação de 24 horas.