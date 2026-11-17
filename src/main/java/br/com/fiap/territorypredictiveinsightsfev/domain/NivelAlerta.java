package br.com.fiap.territorypredictiveinsightsfev.domain;

/**
 * Níveis de alerta exigidos pelo enunciado (nomes mantidos em português).
 */
public enum NivelAlerta {
    ATENCAO("Atenção"),
    ALERTA("Alerta"),
    CRITICO("Crítico");

    /** Texto amigável para exibir no menu. */
    private final String description;

    NivelAlerta(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
