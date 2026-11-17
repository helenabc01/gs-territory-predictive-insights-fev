package br.com.fiap.territorypredictiveinsightsfev.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

/**
 * Uma leitura de sensor gravada no banco (entidade JPA).
 * Nome exigido pelo enunciado (mantido em português).
 */
@Entity
public class Medicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** "RAIN", "RIVER_LEVEL" ou "SOIL_MOISTURE" (vem de sensor.getType()). */
    @Column(nullable = false)
    private String sensorType;

    /** Coluna renomeada porque VALUE é palavra reservada no H2. */
    @Column(name = "measured_value", nullable = false)
    private double value;

    @Column(nullable = false)
    private String unit;

    @Column(nullable = false)
    private LocalDateTime measuredAt;

    // TODO: construtor Medicao(Sensor sensor, double value) throws DadosInvalidosException
    //       - value < sensor.getMinValue() ou > sensor.getMaxValue() -> lançar DadosInvalidosException
    //         (ex.: "medição de RAIN fora da faixa: -3.0 mm/h (válido: 0 a 150)")
    //       - copiar sensorType e unit do sensor; measuredAt = LocalDateTime.now()
    // TODO: getters
    // TODO: toString() para exibir no histórico do menu

    /** Construtor vazio exigido pelo JPA. Não usar no código do sistema. */
    protected Medicao() {
    }

    public Long getId() {
        return id;
    }
}
