package br.com.fiap.territorypredictiveinsightsfev.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

/**
 * Alerta emitido quando a previsão estoura um limiar (entidade JPA).
 * Precisa explicar o PORQUÊ: qual sensor, valor previsto e limite estourado.
 * Nome exigido pelo enunciado (mantido em português).
 */
@Entity
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String sensorType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NivelAlerta level;

    @Column(nullable = false)
    private double predictedValue;

    /** O limiar que foi ultrapassado (ex.: 50.0 mm/h para CRITICO). */
    @Column(nullable = false)
    private double exceededThreshold;

    /** Nome da estratégia que gerou a previsão (ex.: "Moving average"). */
    @Column(nullable = false)
    private String strategyName;

    /** Explicação do porquê do alerta, montada pelo serviço. */
    @Column(nullable = false, length = 500)
    private String message;

    @Column(nullable = false)
    private LocalDateTime issuedAt;

    // TODO: construtor com os campos (issuedAt = LocalDateTime.now())
    // TODO: getters
    // TODO: toString() para exibir no menu

    /** Construtor vazio exigido pelo JPA. Não usar no código do sistema. */
    protected Alerta() {
    }

    public Long getId() {
        return id;
    }
}
