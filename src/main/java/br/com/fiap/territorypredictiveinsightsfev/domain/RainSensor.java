package br.com.fiap.territorypredictiveinsightsfev.domain;

import br.com.fiap.territorypredictiveinsightsfev.exception.FonteIndisponivelException;

import java.util.Random;

public class RainSensor implements Sensor {

    private final Random random = new Random();

    // TODO (opcional): constantes privadas, ex.: MIN_VALUE, MAX_VALUE, FAILURE_RATE

    @Override
    public double readValue() throws FonteIndisponivelException {
        // TODO: se !isWorking() -> throw new FonteIndisponivelException("pluviômetro sem resposta")
        // TODO: gerar valor aleatório realista (ex.: 0 a 80 mm/h) com 1 casa decimal
        throw new UnsupportedOperationException("TODO: implementar readValue()");
    }

    @Override
    public boolean isWorking() {
        // TODO: simular falha de vez em quando (ex.: 5% das vezes retorna false)
        throw new UnsupportedOperationException("TODO: implementar isWorking()");
    }

    @Override
    public SensorType getType() {
        // TODO: retornar SensorType.RAIN
        throw new UnsupportedOperationException("TODO: implementar getType()");
    }

    @Override
    public String getUnit() {
        // TODO: retornar "mm/h"
        throw new UnsupportedOperationException("TODO: implementar getUnit()");
    }

    @Override
    public double getMinValue() {
        // TODO: retornar 0
        throw new UnsupportedOperationException("TODO: implementar getMinValue()");
    }

    @Override
    public double getMaxValue() {
        // TODO: retornar 150
        throw new UnsupportedOperationException("TODO: implementar getMaxValue()");
    }
}
