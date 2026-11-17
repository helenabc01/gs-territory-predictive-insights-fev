package br.com.fiap.territorypredictiveinsightsfev.domain;

import br.com.fiap.territorypredictiveinsightsfev.exception.FonteIndisponivelException;

import java.util.Random;

public class SoilMoistureSensor implements Sensor {

    private static final double MIN_VALUE = 0;
    private static final double MAX_VALUE = 100;

    private static final double TYPICAL_MIN = 40;

    private static final double FAILURE_RATE = 0.05;

    private static final double NOISE_RATE = 0.03;

    private final Random random = new Random();

    @Override
    public double readValue() throws FonteIndisponivelException {
        if (!isWorking()) {
            throw new FonteIndisponivelException("sensor de umidade do solo sem resposta (falha simulada)");
        }
        if (random.nextDouble() < NOISE_RATE) {
            return MAX_VALUE + 5;
        }
        double value = TYPICAL_MIN + random.nextDouble() * (MAX_VALUE - TYPICAL_MIN);
        return Math.round(value * 10) / 10.0;
    }

    @Override
    public boolean isWorking() {
        return random.nextDouble() >= FAILURE_RATE;
    }

    @Override
    public SensorType getType() {
        return SensorType.SOIL_MOISTURE;
    }

    @Override
    public String getUnit() {
        return "%";
    }

    @Override
    public double getMinValue() {
        return MIN_VALUE;
    }

    @Override
    public double getMaxValue() {
        return MAX_VALUE;
    }
}
