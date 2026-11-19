package br.com.fiap.territorypredictiveinsightsfev.domain;

import br.com.fiap.territorypredictiveinsightsfev.exception.FonteIndisponivelException;

import java.util.Random;

public class RiverLevelSensor implements Sensor {

    private static final double MIN_VALUE = 0;
    private static final double MAX_VALUE = 800;
    private static final double SIMULATED_MIN = 100;
    private static final double SIMULATED_MAX = 700;
    private static final double FAILURE_RATE = 0.05;

    private final Random random;

    public RiverLevelSensor() {
        this(new Random());
    }

    RiverLevelSensor(Random random) {
        this.random = random;
    }

    @Override
    public double readValue() throws FonteIndisponivelException {
        if (!isWorking()) {
            throw new FonteIndisponivelException("sensor de nível do rio sem resposta (falha simulada)");
        }

        double value = SIMULATED_MIN + random.nextDouble() * (SIMULATED_MAX - SIMULATED_MIN);
        return Math.round(value * 10) / 10.0;
    }

    @Override
    public boolean isWorking() {
        return random.nextDouble() >= FAILURE_RATE;
    }

    @Override
    public SensorType getType() {
        return SensorType.RIVER_LEVEL;
    }

    @Override
    public String getUnit() {
        return "cm";
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
