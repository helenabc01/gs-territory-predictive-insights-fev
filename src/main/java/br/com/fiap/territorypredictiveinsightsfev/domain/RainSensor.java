package br.com.fiap.territorypredictiveinsightsfev.domain;

import br.com.fiap.territorypredictiveinsightsfev.exception.FonteIndisponivelException;

import java.util.Random;

/**
 * Sensor 1: pluviômetro. Mede intensidade de chuva em mm/h.
 * Faixa realista sugerida: 0 a 150 mm/h (valor negativo é inválido).
 *
 * Responsável: Colega 1
 */
public class RainSensor implements Sensor {

    private final Random random = new Random();

    // TODO (opcional): constantes privadas, ex.: TYPE, UNIT, MIN_VALUE, MAX_VALUE, FAILURE_RATE

    @Override
    public double readValue() throws FonteIndisponivelException {
        // TODO: se !isWorking() -> throw new FonteIndisponivelException("sensor RAIN sem resposta")
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
        // TODO: retornar "RAIN"
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
