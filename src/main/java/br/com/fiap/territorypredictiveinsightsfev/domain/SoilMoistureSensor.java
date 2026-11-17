package br.com.fiap.territorypredictiveinsightsfev.domain;

import br.com.fiap.territorypredictiveinsightsfev.exception.FonteIndisponivelException;

import java.util.Random;

/**
 * Sensor 3: umidade/saturação do solo em %. Solo saturado não absorve chuva -> mais alagamento.
 * Faixa válida: 0 a 100 %.
 *
 * Responsável: integrante principal
 */
public class SoilMoistureSensor implements Sensor {

    private final Random random = new Random();

    // TODO (opcional): constantes privadas, ex.: TYPE, UNIT, MIN_VALUE, MAX_VALUE, FAILURE_RATE

    @Override
    public double readValue() throws FonteIndisponivelException {
        // TODO: se !isWorking() -> throw new FonteIndisponivelException("sensor SOIL_MOISTURE sem resposta")
        // TODO: gerar valor aleatório entre getMinValue() e getMaxValue()
        throw new UnsupportedOperationException("TODO: implementar readValue()");
    }

    @Override
    public boolean isWorking() {
        // TODO: simular falha de vez em quando (ex.: 5% das vezes retorna false)
        throw new UnsupportedOperationException("TODO: implementar isWorking()");
    }

    @Override
    public String getType() {
        // TODO: retornar "SOIL_MOISTURE"
        throw new UnsupportedOperationException("TODO: implementar getType()");
    }

    @Override
    public String getUnit() {
        // TODO: retornar "%"
        throw new UnsupportedOperationException("TODO: implementar getUnit()");
    }

    @Override
    public double getMinValue() {
        // TODO: retornar 0
        throw new UnsupportedOperationException("TODO: implementar getMinValue()");
    }

    @Override
    public double getMaxValue() {
        // TODO: retornar 100
        throw new UnsupportedOperationException("TODO: implementar getMaxValue()");
    }
}
