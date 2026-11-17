package br.com.fiap.territorypredictiveinsightsfev.domain;

import br.com.fiap.territorypredictiveinsightsfev.exception.DadosInvalidosException;

import java.util.List;

public abstract class EstrategiaPrevisao {

    protected static final int MIN_READINGS = 5;

    private final String name;

    protected EstrategiaPrevisao(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract String getDescription();

    public abstract double prever(List<Medicao> history) throws DadosInvalidosException;

    protected void validateHistory(List<Medicao> history, int requiredReadings) throws DadosInvalidosException {
        int required = Math.max(MIN_READINGS, requiredReadings);
        int available = history == null ? 0 : history.size();
        if (available < required) {
            throw new DadosInvalidosException(String.format(
                    "histórico insuficiente para %s: %d leitura(s), mínimo %d. Previsão suspensa para não gerar alerta sem base.",
                    name, available, required));
        }
    }

    protected double average(List<Medicao> readings) {
        return readings.stream().mapToDouble(Medicao::getValue).average().orElse(0);
    }
}
