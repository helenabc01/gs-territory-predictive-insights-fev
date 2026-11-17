package br.com.fiap.territorypredictiveinsightsfev.domain;

public record ForecastResult(
        SensorType sensorType,
        String strategyName,
        double predictedValue,
        String unit,
        String meaning,
        Alerta alert
) {
    public boolean hasAlert() {
        return alert != null;
    }
}
