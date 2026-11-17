package br.com.fiap.territorypredictiveinsightsfev.domain;

import br.com.fiap.territorypredictiveinsightsfev.exception.FonteIndisponivelException;

import java.util.ArrayList;
import java.util.List;

public class FakeSensor implements Sensor {

    private final SensorType type;
    private final String unit;
    private final double min;
    private final double max;
    private double nextValue;
    private boolean working = true;

    public FakeSensor(SensorType type, String unit, double min, double max) {
        this.type = type;
        this.unit = unit;
        this.min = min;
        this.max = max;
    }

    public static FakeSensor rain() {
        return new FakeSensor(SensorType.RAIN, "mm/h", 0, 150);
    }

    public FakeSensor willRead(double value) {
        this.nextValue = value;
        return this;
    }

    public FakeSensor broken() {
        this.working = false;
        return this;
    }

    public List<Medicao> measurements(double... values) throws Exception {
        List<Medicao> list = new ArrayList<>();
        for (double v : values) {
            list.add(new Medicao(this, v));
        }
        return list;
    }

    @Override
    public double readValue() throws FonteIndisponivelException {
        if (!working) {
            throw new FonteIndisponivelException("sensor falso quebrado");
        }
        return nextValue;
    }

    @Override
    public boolean isWorking() {
        return working;
    }

    @Override
    public SensorType getType() {
        return type;
    }

    @Override
    public String getUnit() {
        return unit;
    }

    @Override
    public double getMinValue() {
        return min;
    }

    @Override
    public double getMaxValue() {
        return max;
    }
}
