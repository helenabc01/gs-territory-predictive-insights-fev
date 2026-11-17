package br.com.fiap.territorypredictiveinsightsfev.domain;

import br.com.fiap.territorypredictiveinsightsfev.exception.DadosInvalidosException;

import java.util.List;

public class PrevisaoMediaMovel extends EstrategiaPrevisao {

    private static final int DEFAULT_WINDOW_SIZE = 5;

    private final int windowSize;

    public PrevisaoMediaMovel() {
        super("Média móvel");
        this.windowSize = DEFAULT_WINDOW_SIZE;
    }

    @Override
    public String getDescription() {
        return "média das " + windowSize + " leituras mais recentes";
    }

    @Override
    public double prever(List<Medicao> history) throws DadosInvalidosException {
        super.validateHistory(history, windowSize);
        return average(history.subList(0, windowSize));
    }
}
