package br.com.fiap.territorypredictiveinsightsfev.service;

import br.com.fiap.territorypredictiveinsightsfev.domain.NivelAlerta;
import br.com.fiap.territorypredictiveinsightsfev.domain.SensorType;
import br.com.fiap.territorypredictiveinsightsfev.exception.DadosInvalidosException;

import java.util.HashMap;
import java.util.Map;

public class ConfiguracaoSistema {

    private static ConfiguracaoSistema instancia = new ConfiguracaoSistema();

    private Map<SensorType, Map<NivelAlerta, Double>> thresholds = new HashMap<>();

    private String defaultStrategy;

    private ConfiguracaoSistema() {
        restaurarPadroes();
    }

    public static ConfiguracaoSistema getInstance() {
        return instancia;
    }

    public void restaurarPadroes() {
        thresholds.clear();

        definirLimites(SensorType.RAIN, 10, 30, 50);
        definirLimites(SensorType.RIVER_LEVEL, 300, 450, 600);
        definirLimites(SensorType.SOIL_MOISTURE, 70, 85, 95);

        defaultStrategy = FabricaPrevisao.MOVING_AVERAGE;
    }

    private void definirLimites(SensorType sensor, double atencao,
            double alerta, double critico) {

        Map<NivelAlerta, Double> limites = new HashMap<>();

        limites.put(NivelAlerta.ATENCAO, atencao);
        limites.put(NivelAlerta.ALERTA, alerta);
        limites.put(NivelAlerta.CRITICO, critico);

        thresholds.put(sensor, limites);
    }

    public double getThreshold(SensorType sensor, NivelAlerta nivel) {
        return thresholds.get(sensor).get(nivel);
    }

    public Map<NivelAlerta, Double> getThresholds(SensorType sensor) {
        return thresholds.get(sensor);
    }

    public void setThreshold(SensorType sensor, NivelAlerta nivel,
            double valor) throws DadosInvalidosException {

        if (Double.isNaN(valor) || valor < 0) {
            throw new DadosInvalidosException("Valor do limite inválido.");
        }

        Map<NivelAlerta, Double> limites = thresholds.get(sensor);

        for (NivelAlerta outroNivel : NivelAlerta.values()) {

            double outroValor = limites.get(outroNivel);

            if (outroNivel.compareTo(nivel) < 0 && valor <= outroValor) {
                throw new DadosInvalidosException(
                        "O valor precisa ser maior que o nível anterior.");
            }

            if (outroNivel.compareTo(nivel) > 0 && valor >= outroValor) {
                throw new DadosInvalidosException(
                        "O valor precisa ser menor que o próximo nível.");
            }
        }

        limites.put(nivel, valor);
    }

    public String getDefaultStrategy() {
        return defaultStrategy;
    }

    public void setDefaultStrategy(String estrategia) {
        defaultStrategy = estrategia;
    }
}
