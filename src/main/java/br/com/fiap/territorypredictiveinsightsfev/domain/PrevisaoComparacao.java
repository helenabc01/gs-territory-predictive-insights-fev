package br.com.fiap.territorypredictiveinsightsfev.domain;

import java.util.List;

/**
 * Previsão 2: compara o período recente com o período anterior
 * e projeta a tendência (subindo/descendo).
 * Nome exigido pelo enunciado.
 */
public class PrevisaoComparacao extends EstrategiaPrevisao {

    private static final int DEFAULT_PERIOD_SIZE = 3;

    /** Quantas leituras formam cada período (atual e anterior). */
    private final int periodSize;

    public PrevisaoComparacao() {
        super("Previous period comparison");
        this.periodSize = DEFAULT_PERIOD_SIZE;
    }

    @Override
    public double prever(List<Medicao> history) {
        // TODO: chamar a validação comum da mãe (super.validateHistory(history))
        // TODO: média das periodSize leituras mais recentes (atual) x as periodSize anteriores
        // TODO: projetar a tendência: atual + (atual - anterior)
        throw new UnsupportedOperationException("TODO: implementar prever() da comparação");
    }
}
