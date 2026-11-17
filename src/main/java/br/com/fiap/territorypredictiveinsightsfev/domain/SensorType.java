package br.com.fiap.territorypredictiveinsightsfev.domain;

public enum SensorType {
    RAIN("Chuva"),
    RIVER_LEVEL("Nível do rio"),
    SOIL_MOISTURE("Umidade do solo");

    private final String label;

    SensorType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
