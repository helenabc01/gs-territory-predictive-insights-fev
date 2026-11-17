package br.com.fiap.territorypredictiveinsightsfev.domain;

import br.com.fiap.territorypredictiveinsightsfev.exception.FonteIndisponivelException;

/**
 * Contrato de todo sensor do recorte de clima.
 * Implementações: RainSensor, RiverLevelSensor, SoilMoistureSensor.
 *
 * Cada sensor informa a própria faixa válida (getMinValue/getMaxValue).
 * Assim a validação da medição funciona para qualquer sensor, sem if por tipo.
 */
public interface Sensor {

    /**
     * Simula uma leitura com Random/Math.random() dentro da faixa realista do sensor.
     *
     * @throws FonteIndisponivelException se o sensor não estiver funcionando
     */
    double readValue() throws FonteIndisponivelException;

    /** Indica se o sensor está funcionando (pode simular falha aleatória, ex.: 5% das vezes). */
    boolean isWorking();

    /** Tipo do sensor: "RAIN", "RIVER_LEVEL" ou "SOIL_MOISTURE". É o que vai para o banco. */
    String getType();

    /** Unidade da leitura, ex.: "mm/h", "cm", "%". */
    String getUnit();

    /** Menor valor fisicamente válido (abaixo disso: DadosInvalidosException). */
    double getMinValue();

    /** Maior valor fisicamente válido (acima disso: DadosInvalidosException). */
    double getMaxValue();
}
