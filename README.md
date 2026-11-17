# Territory Predictive Insights — Clima Local (chuva e alagamento)

> TODO: 1–2 frases sobre o sistema.

## Integrantes e divisão de tarefas

| Integrante | RM | Responsabilidade |
|---|---|---|
| TODO | TODO | Domínio e exceções (`dominio/`, `excecao/`, sensores) |
| TODO | TODO | Persistência (`Medicao`, `Alerta`, `persistencia/`, gravação/listagem no serviço) |
| TODO | TODO | Previsões, alertas e testes (`EstrategiaPrevisao` e filhas, `servico/`, `src/test/`) |

## Público beneficiado

> TODO: quem sofre o problema (ex.: moradores de áreas de risco de alagamento, defesa civil, associação de bairro)
> e qual decisão o sistema apoia (ex.: acionar alerta à comunidade, desobstruir bueiros, preparar abrigo).

## Como rodar

Requisitos: Java 17+.

```bash
./mvnw spring-boot:run      # Linux/Mac
mvnw.cmd spring-boot:run    # Windows
```

O banco é H2 gravado em arquivo (`./data/territorio`), então o histórico sobrevive ao reiniciar.
Para zerar o banco, apague a pasta `data/`.

Testes:

```bash
./mvnw test
```

## Estrutura

| Pacote | Conteúdo |
|---|---|
| `domain/` | ComponenteTerritorial, RiskArea, Sensor (+ RainSensor, RiverLevelSensor, SoilMoistureSensor), DadosTerritorio, EstrategiaPrevisao, PrevisaoMediaMovel, PrevisaoComparacao, Medicao, Alerta, NivelAlerta |
| `exception/` | DadosInvalidosException, FonteIndisponivelException |
| `persistence/` | MedicaoRepository, AlertaRepository (Spring Data JPA) |
| `service/` | ServicoMonitoramento, FabricaPrevisao, ConfiguracaoSistema |
| raiz | SistemaMonitoramento (menu de console) |

Padrão de código: inglês. As classes e métodos que o enunciado exige pelo nome
(ex.: `ComponenteTerritorial`, `EstrategiaPrevisao.prever()`, `MedicaoRepository`) foram mantidos em português.

## Decisões da equipe

> TODO: por que Spring Boot + JPA, faixas de cada sensor, limiares escolhidos, exceção checked x unchecked etc.

## Padrões criacionais (justificativa)

> TODO: por que Factory na FabricaPrevisao (e não em outro lugar)?
> TODO: por que Singleton no ConfiguracaoSistema (e não em outro lugar)?

## Reflexão crítica

> TODO (meia página):
> - Quando o sistema deve se calar? (ex.: menos de 5 leituras no banco)
> - O que os dados não representam? (ex.: 1 sensor por região inteira)
> - Por que o alerta apoia a decisão da pessoa, em vez de decidir por ela?

## Uso de IA

> TODO: onde usaram IA e como revisaram.
> Ex.: o esqueleto de pacotes/classes com TODOs foi gerado com auxílio do Claude Code; a implementação foi feita pela equipe.
