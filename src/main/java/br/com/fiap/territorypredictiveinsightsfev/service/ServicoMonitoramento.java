package br.com.fiap.territorypredictiveinsightsfev.service;

import br.com.fiap.territorypredictiveinsightsfev.domain.Alerta;
import br.com.fiap.territorypredictiveinsightsfev.domain.DadosTerritorio;
import br.com.fiap.territorypredictiveinsightsfev.domain.EstrategiaPrevisao;
import br.com.fiap.territorypredictiveinsightsfev.domain.ForecastResult;
import br.com.fiap.territorypredictiveinsightsfev.domain.Medicao;
import br.com.fiap.territorypredictiveinsightsfev.domain.NivelAlerta;
import br.com.fiap.territorypredictiveinsightsfev.domain.RainSensor;
import br.com.fiap.territorypredictiveinsightsfev.domain.RiskArea;
import br.com.fiap.territorypredictiveinsightsfev.domain.RiverLevelSensor;
import br.com.fiap.territorypredictiveinsightsfev.domain.Sensor;
import br.com.fiap.territorypredictiveinsightsfev.domain.SensorType;
import br.com.fiap.territorypredictiveinsightsfev.domain.SoilMoistureSensor;
import br.com.fiap.territorypredictiveinsightsfev.exception.DadosInvalidosException;
import br.com.fiap.territorypredictiveinsightsfev.exception.FonteIndisponivelException;
import br.com.fiap.territorypredictiveinsightsfev.persistence.AlertaRepository;
import br.com.fiap.territorypredictiveinsightsfev.persistence.MedicaoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ServicoMonitoramento {

    private final MedicaoRepository medicaoRepository;
    private final AlertaRepository alertaRepository;

    private final List<Sensor> sensors = List.of(
            new RainSensor(),
            new RiverLevelSensor(),
            new SoilMoistureSensor()
    );

    private final FabricaPrevisao forecastFactory = new FabricaPrevisao();

    private final DadosTerritorio territoryData =
            new DadosTerritorio("Zona Leste - Bacia do Córrego Verde", 18000, 3, "GS2026");

    private final List<RiskArea> riskAreas = List.of(
            new RiskArea(1L, "Baixada do Córrego Verde", "Zona Leste", "Transbordamento de córrego", true),
            new RiskArea(2L, "Passagem sob o viaduto central", "Zona Leste", "Alagamento de via", true),
            new RiskArea(3L, "Entorno da UBS Jardim das Flores", "Zona Leste", "Alagamento de acesso", false)
    );

    public ServicoMonitoramento(MedicaoRepository medicaoRepository, AlertaRepository alertaRepository) {
        this.medicaoRepository = medicaoRepository;
        this.alertaRepository = alertaRepository;
    }

    public Medicao readAndSave(Sensor sensor) throws FonteIndisponivelException, DadosInvalidosException {
        double value = sensor.readValue();
        Medicao medicao = new Medicao(sensor, value);
        medicaoRepository.save(medicao);
        return medicao;
    }

    public List<Medicao> findHistoryBySensor(SensorType sensorType) {
        return medicaoRepository.findBySensorTypeOrderByIdDesc(sensorType);
    }

    public List<Alerta> listIssuedAlerts() {
        return alertaRepository.findAllByOrderByIdDesc();
    }

    public ForecastResult runForecast(SensorType sensorType, EstrategiaPrevisao strategy) throws DadosInvalidosException {
        List<Medicao> history = findHistoryBySensor(sensorType);
        double predicted = strategy.prever(history);
        String unit = history.get(0).getUnit();

        Optional<Alerta> alert = evaluateAlert(sensorType, predicted, unit, strategy.getName());
        String meaning = alert.map(Alerta::getMessage)
                .orElseGet(() -> describeNormal(sensorType, predicted, unit, strategy.getName()));

        return new ForecastResult(sensorType, strategy.getName(), predicted, unit, meaning, alert.orElse(null));
    }

    public Optional<Alerta> evaluateAlert(SensorType sensorType, double predicted, String unit, String strategyName) {
        ConfiguracaoSistema config = ConfiguracaoSistema.getInstance();

        List<NivelAlerta> mostSevereFirst = new ArrayList<>(Arrays.asList(NivelAlerta.values()));
        Collections.reverse(mostSevereFirst);

        for (NivelAlerta level : mostSevereFirst) {
            double threshold = config.getThreshold(sensorType, level);
            if (predicted >= threshold) {
                String message = String.format(
                        "%s: previsão de %.1f %s (%s) atingiu o limite de %s (%.1f %s). Ação sugerida: %s.",
                        sensorType.getLabel(), predicted, unit, strategyName,
                        level.getDescription(), threshold, unit, level.getRecommendedAction());
                Alerta alerta = new Alerta(sensorType, level, predicted, threshold, strategyName, message);
                alertaRepository.save(alerta);
                riskAreas.forEach(area -> area.registerAlertLevel(level));
                return Optional.of(alerta);
            }
        }
        return Optional.empty();
    }

    private String describeNormal(SensorType sensorType, double predicted, String unit, String strategyName) {
        double attention = ConfiguracaoSistema.getInstance().getThreshold(sensorType, NivelAlerta.ATENCAO);
        return String.format("%s: previsão de %.1f %s (%s) abaixo do limite de Atenção (%.1f %s). Sem alerta.",
                sensorType.getLabel(), predicted, unit, strategyName, attention, unit);
    }

    public String buildFullStatus() {
        ConfiguracaoSistema config = ConfiguracaoSistema.getInstance();
        StringBuilder sb = new StringBuilder();

        sb.append("=== TERRITÓRIO ===\n").append(territoryData).append('\n');

        sb.append("\n=== SENSORES (histórico no banco) ===\n");
        for (SensorType type : SensorType.values()) {
            long count = medicaoRepository.countBySensorType(type);
            String last = medicaoRepository.findFirstBySensorTypeOrderByIdDesc(type)
                    .map(Medicao::toString)
                    .orElse("nenhuma leitura ainda");
            sb.append(String.format("- %s: %d leitura(s) | última: %s%n", type.getLabel(), count, last));
        }

        sb.append("\n=== LIMIARES (Atenção / Alerta / Crítico) ===\n");
        for (SensorType type : SensorType.values()) {
            Map<NivelAlerta, Double> levels = config.getThresholds(type);
            sb.append(String.format("- %s: %.1f / %.1f / %.1f%n", type.getLabel(),
                    levels.get(NivelAlerta.ATENCAO), levels.get(NivelAlerta.ALERTA), levels.get(NivelAlerta.CRITICO)));
        }

        sb.append("\n=== ÁREAS DE RISCO (nesta execução) ===\n");
        riskAreas.forEach(area -> sb.append("- ").append(area).append('\n'));

        sb.append("\nEstratégia de previsão configurada: ")
                .append(forecastFactory.createConfigured().getName()).append('\n');

        sb.append("\n=== ÚLTIMOS ALERTAS ===\n");
        List<Alerta> lastAlerts = alertaRepository.findTop5ByOrderByIdDesc();
        if (lastAlerts.isEmpty()) {
            sb.append("nenhum alerta emitido\n");
        }
        lastAlerts.forEach(a -> sb.append("- ").append(a).append('\n'));

        return sb.toString();
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    public FabricaPrevisao getForecastFactory() {
        return forecastFactory;
    }

    public DadosTerritorio getTerritoryData() {
        return territoryData;
    }

    public List<RiskArea> getRiskAreas() {
        return riskAreas;
    }
}
