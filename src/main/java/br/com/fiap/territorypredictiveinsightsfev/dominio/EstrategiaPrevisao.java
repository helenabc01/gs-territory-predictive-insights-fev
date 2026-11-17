package br.com.fiap.territorypredictiveinsightsfev.dominio;

import java.util.List;

/**
 * Classe abstrata das estratégias de previsão (polimorfismo).
 * Filhas: PrevisaoMediaMovel e PrevisaoComparacao.
 *
 * Responsável: Integrante 3 (previsões, alertas e testes)
 */
public abstract class EstrategiaPrevisao {

    private final String nome;

    protected EstrategiaPrevisao(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    // TODO: método comum para as filhas reaproveitarem via super, ex.:
    //       protected void validarHistorico(List<Medicao> historico)
    //       -> histórico com poucas leituras (ex.: < 5): sistema deve "se calar" (ver reflexão crítica)

    /**
     * TODO: cada filha sobrescreve com a sua regra.
     * Recebe o histórico vindo do banco e devolve o valor previsto.
     */
    public abstract double prever(List<Medicao> historico);

    // TODO (opcional): String explicar(double valorPrevisto) -> "o que esse valor significa" para o usuário
}
