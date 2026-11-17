package br.com.fiap.territorypredictiveinsightsfev.service;

import br.com.fiap.territorypredictiveinsightsfev.domain.NivelAlerta;

import java.util.Map;

/**
 * Padrão criacional SINGLETON: uma única instância guardando os limiares do sistema.
 * Nome exigido pelo enunciado. Justificar no README por que Singleton aqui.
 */
public class ConfiguracaoSistema {

    /** A única instância. */
    private static ConfiguracaoSistema instance;

    /**
     * Limiares por tipo de sensor e por nível.
     * Ex.: thresholds.get("RAIN").get(NivelAlerta.CRITICO) -> 50.0
     */
    private Map<String, Map<NivelAlerta, Double>> thresholds;

    /** Estratégia usada por padrão pela FabricaPrevisao (ex.: "MOVING_AVERAGE"). */
    private String defaultStrategy;

    // TODO: construtor PRIVADO preenchendo thresholds (ajustar valores com o grupo), ex.:
    //       RAIN (mm/h):           ATENCAO 10, ALERTA 30, CRITICO 50
    //       RIVER_LEVEL (cm):      ATENCAO 300, ALERTA 450, CRITICO 600
    //       SOIL_MOISTURE (%):     ATENCAO 70, ALERTA 85, CRITICO 95
    // TODO: public static ConfiguracaoSistema getInstance()
    // TODO: double getThreshold(String sensorType, NivelAlerta level)
    // TODO: void setThreshold(String sensorType, NivelAlerta level, double value)
    //       -> limiar inválido: DadosInvalidosException
    // TODO: getDefaultStrategy() / setDefaultStrategy(...)
}
