package br.com.fiap.territorypredictiveinsightsfev.domain;

import br.com.fiap.territorypredictiveinsightsfev.exception.FonteIndisponivelException;

import java.util.Random;

public class RainSensor implements Sensor {

    private static final double MIN_VALUE = 0;
    private static final double MAX_VALUE = 150;
    private static final double SIMULATED_MAX = 80;
    private static final double FAILURE_RATE = 0.05;

    private final Random random;

    public RainSensor() {
        this(new Random());
    }

    RainSensor(Random random) {
        this.random = random;
    }

    @Override
    public double readValue() throws FonteIndisponivelException {
        if (!isWorking()) {
            throw new FonteIndisponivelException(
                    "pluviômetro sem resposta (falha simulada)"
            );
        }

        double value = random.nextDouble() * SIMULATED_MAX;
        return Math.round(value * 10) / 10.0;
    }

    @Override
    public boolean isWorking() {
        return random.nextDouble() >= FAILURE_RATE;
    }

    @Override
    public SensorType getType() {
        return SensorType.RAIN;
    }

    @Override
    public String getUnit() {
        return "mm/h";
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