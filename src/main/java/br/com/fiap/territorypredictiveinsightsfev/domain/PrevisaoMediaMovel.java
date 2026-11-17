package br.com.fiap.territorypredictiveinsightsfev.domain;

import java.util.List;

/**
 * Previsão 1: média das últimas N medições do histórico.
 * Nome exigido pelo enunciado.
 */
public class PrevisaoMediaMovel extends EstrategiaPrevisao {

    private static final int DEFAULT_WINDOW_SIZE = 5;

    /** Quantas leituras recentes entram na média (o "N"). */
    private final int windowSize;

    public PrevisaoMediaMovel() {
        super("Moving average");
        this.windowSize = DEFAULT_WINDOW_SIZE;
    }

    @Override
    public double prever(List<Medicao> history) {
        // TODO: chamar a validação comum da mãe (super.validateHistory(history))
        // TODO: somar os valores das últimas windowSize medições e dividir por windowSize
        throw new UnsupportedOperationException("TODO: implementar prever() da média móvel");
    }
}
