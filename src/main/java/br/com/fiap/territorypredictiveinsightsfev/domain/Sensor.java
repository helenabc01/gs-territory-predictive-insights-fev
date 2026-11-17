package br.com.fiap.territorypredictiveinsightsfev.domain;

import br.com.fiap.territorypredictiveinsightsfev.exception.FonteIndisponivelException;

public interface Sensor {

    double readValue() throws FonteIndisponivelException;

    boolean isWorking();

    SensorType getType();

    String getUnit();

    double getMinValue();

    double getMaxValue();
}
