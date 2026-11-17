package br.com.fiap.territorypredictiveinsightsfev.domain;

import java.util.List;

/**
 * Classe abstrata das estratégias de previsão (polimorfismo).
 * Filhas: PrevisaoMediaMovel e PrevisaoComparacao.
 * Nomes da classe e de prever() exigidos pelo enunciado.
 */
public abstract class EstrategiaPrevisao {

    /** Abaixo disso o sistema "se cala": previsão não é confiável (ver reflexão crítica). */
    protected static final int MIN_READINGS = 5;

    private final String name;

    protected EstrategiaPrevisao(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // TODO: método comum para as filhas reaproveitarem via super, ex.:
    //       protected void validateHistory(List<Medicao> history) throws DadosInvalidosException
    //       -> history.size() < MIN_READINGS: lançar exceção com mensagem clara

    /**
     * TODO: cada filha sobrescreve com a sua regra.
     * Recebe o histórico vindo do banco e devolve o valor previsto.
     */
    public abstract double prever(List<Medicao> history);

    // TODO (opcional): String explain(double predictedValue) -> "o que esse valor significa"
}
