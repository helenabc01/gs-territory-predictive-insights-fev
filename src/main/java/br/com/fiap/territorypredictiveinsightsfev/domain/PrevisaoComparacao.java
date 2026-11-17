package br.com.fiap.territorypredictiveinsightsfev.domain;

import br.com.fiap.territorypredictiveinsightsfev.exception.DadosInvalidosException;

import java.util.List;

public class PrevisaoComparacao extends EstrategiaPrevisao {

    private static final int DEFAULT_PERIOD_SIZE = 3;

    private final int periodSize;

    public PrevisaoComparacao() {
        super("Comparação com período anterior");
        this.periodSize = DEFAULT_PERIOD_SIZE;
    }

    @Override
    public String getDescription() {
        return "tendência entre as " + periodSize + " leituras mais recentes e as " + periodSize + " anteriores";
    }

    @Override
    public double prever(List<Medicao> history) throws DadosInvalidosException {
        super.validateHistory(history, periodSize * 2);
        double current = average(history.subList(0, periodSize));
        double previous = average(history.subList(periodSize, periodSize * 2));
        double projection = current + (current - previous);
        return Math.max(0, projection);
    }
}
