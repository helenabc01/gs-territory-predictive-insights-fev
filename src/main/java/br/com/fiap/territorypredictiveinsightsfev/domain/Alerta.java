package br.com.fiap.territorypredictiveinsightsfev.domain;

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
public class Alerta {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SensorType sensorType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NivelAlerta level;

    @Column(nullable = false)
    private double predictedValue;

    @Column(nullable = false)
    private double exceededThreshold;

    @Column(nullable = false)
    private String strategyName;

    @Column(nullable = false, length = 500)
    private String message;

    @Column(nullable = false)
    private LocalDateTime issuedAt;

    public Alerta(SensorType sensorType, NivelAlerta level, double predictedValue,
                  double exceededThreshold, String strategyName, String message) {
        this.sensorType = sensorType;
        this.level = level;
        this.predictedValue = predictedValue;
        this.exceededThreshold = exceededThreshold;
        this.strategyName = strategyName;
        this.message = message;
        this.issuedAt = LocalDateTime.now();
    }

    protected Alerta() {
    }

    public Long getId() {
        return id;
    }

    public SensorType getSensorType() {
        return sensorType;
    }

    public NivelAlerta getLevel() {
        return level;
    }

    public double getPredictedValue() {
        return predictedValue;
    }

    public double getExceededThreshold() {
        return exceededThreshold;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s", issuedAt.format(FORMAT), level.getDescription().toUpperCase(), message);
    }
}
