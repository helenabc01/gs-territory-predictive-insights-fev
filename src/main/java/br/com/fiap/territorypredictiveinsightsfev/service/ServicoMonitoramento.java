package br.com.fiap.territorypredictiveinsightsfev.service;

import br.com.fiap.territorypredictiveinsightsfev.domain.RainSensor;
import br.com.fiap.territorypredictiveinsightsfev.domain.RiverLevelSensor;
import br.com.fiap.territorypredictiveinsightsfev.domain.Sensor;
import br.com.fiap.territorypredictiveinsightsfev.domain.SoilMoistureSensor;
import br.com.fiap.territorypredictiveinsightsfev.persistence.AlertaRepository;
import br.com.fiap.territorypredictiveinsightsfev.persistence.MedicaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Regras de negócio: ler sensores, gravar medições, rodar previsões e gerar alertas.
 * O menu só chama este serviço; aqui não tem Scanner nem System.in.
 * Nome exigido pelo enunciado.
 *
 * Os repositories chegam pelo construtor, o que permite mockar com Mockito
 * no ServicoMonitoramentoTest (testar alerta sem banco).
 */
@Service
public class ServicoMonitoramento {

    private final MedicaoRepository medicaoRepository;
    private final AlertaRepository alertaRepository;

    /** Os 3 sensores monitorados. */
    private final List<Sensor> sensors = List.of(
            new RainSensor(),
            new RiverLevelSensor(),
            new SoilMoistureSensor()
    );

    /** Cria as estratégias de previsão (Factory). */
    private final FabricaPrevisao forecastFactory = new FabricaPrevisao();

    public ServicoMonitoramento(MedicaoRepository medicaoRepository, AlertaRepository alertaRepository) {
        this.medicaoRepository = medicaoRepository;
        this.alertaRepository = alertaRepository;
    }

    // ---- Sensores e leituras ----
    // TODO: List<Medicao> readAllSensors()
    //       para cada sensor: readValue() -> new Medicao(sensor, value) -> medicaoRepository.save(...)
    //       propaga DadosInvalidosException / FonteIndisponivelException para o menu tratar

    // ---- Histórico ----
    // TODO: List<Medicao> buscarHistoricoPorSensor(String sensorType)
    // TODO: List<Alerta> listIssuedAlerts()

    // ---- Previsões e alertas ----
    // TODO: double runForecast(String sensorType, EstrategiaPrevisao strategy)
    //       busca histórico no banco -> strategy.prever(history)
    // TODO: Optional<Alerta> evaluateAlert(String sensorType, double predictedValue, String strategyName)
    //       compara com ConfiguracaoSistema.getInstance().getThreshold(...) -> decide NivelAlerta
    //       se estourou: cria Alerta com o PORQUÊ (sensor, valor previsto, limite) -> alertaRepository.save(...)

    // ---- Status completo ----
    // TODO: String buildFullStatus() -> resumo de sensores, últimas previsões e alertas
}
