package br.com.fiap.territorypredictiveinsightsfev.dominio;

import java.util.List;

/**
 * Previsão 2: compara o período recente com o período anterior
 * e projeta a tendência (subindo/descendo).
 *
 * Responsável: Integrante 3 (previsões, alertas e testes)
 */
public class PrevisaoComparacao extends EstrategiaPrevisao {

    public PrevisaoComparacao() {
        super("Comparação com período anterior");
    }

    @Override
    public double prever(List<Medicao> historico) {
        // TODO: chamar a validação comum da mãe (super.validarHistorico(historico))
        // TODO: dividir o histórico em período atual x período anterior
        // TODO: calcular a variação e projetar: atual + (atual - anterior)
        throw new UnsupportedOperationException("TODO: implementar prever() da comparação");
    }
}
