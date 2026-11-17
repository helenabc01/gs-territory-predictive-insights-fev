package br.com.fiap.territorypredictiveinsightsfev.domain;

import br.com.fiap.territorypredictiveinsightsfev.exception.DadosInvalidosException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Medicao {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SensorType sensorType;

    @Column(name = "measured_value", nullable = false)
    private double value;

    @Column(nullable = false)
    private String unit;

    @Column(nullable = false)
    private LocalDateTime measuredAt;

    public Medicao(Sensor sensor, double value) throws DadosInvalidosException {
        if (Double.isNaN(value) || value < sensor.getMinValue() || value > sensor.getMaxValue()) {
            throw new DadosInvalidosException(String.format(
                    "medição de %s fora da faixa: %.1f %s (válido: %.0f a %.0f %s)",
                    sensor.getType().getLabel(), value, sensor.getUnit(),
                    sensor.getMinValue(), sensor.getMaxValue(), sensor.getUnit()));
        }
        this.sensorType = sensor.getType();
        this.value = value;
        this.unit = sensor.getUnit();
        this.measuredAt = LocalDateTime.now();
    }

    protected Medicao() {
    }

    public Long getId() {
        return id;
    }

    public SensorType getSensorType() {
        return sensorType;
    }

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    public LocalDateTime getMeasuredAt() {
        return measuredAt;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s: %.1f %s", measuredAt.format(FORMAT), sensorType.getLabel(), value, unit);
    }
}
