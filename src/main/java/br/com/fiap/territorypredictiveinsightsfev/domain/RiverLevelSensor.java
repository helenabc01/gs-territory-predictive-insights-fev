package br.com.fiap.territorypredictiveinsightsfev.domain;

import br.com.fiap.territorypredictiveinsightsfev.exception.FonteIndisponivelException;

import java.util.Random;

/**
 * Sensor 2: régua/sensor de nível do córrego ou rio. Mede a altura da água em cm.
 * Faixa realista sugerida: 0 a 800 cm.
 *
 * Responsável: Colega 2
 */
public class RiverLevelSensor implements Sensor {

    private final Random random = new Random();

    // TODO (opcional): constantes privadas, ex.: TYPE, UNIT, MIN_VALUE, MAX_VALUE, FAILURE_RATE

    @Override
    public double readValue() throws FonteIndisponivelException {
        // TODO: se !isWorking() -> throw new FonteIndisponivelException("sensor RIVER_LEVEL sem resposta")
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
        // TODO: retornar "RIVER_LEVEL"
        throw new UnsupportedOperationException("TODO: implementar getType()");
    }

    @Override
    public String getUnit() {
        // TODO: retornar "cm"
        throw new UnsupportedOperationException("TODO: implementar getUnit()");
    }

    @Override
    public double getMinValue() {
        // TODO: retornar 0
        throw new UnsupportedOperationException("TODO: implementar getMinValue()");
    }

    @Override
    public double getMaxValue() {
        // TODO: retornar 800
        throw new UnsupportedOperationException("TODO: implementar getMaxValue()");
    }
}
