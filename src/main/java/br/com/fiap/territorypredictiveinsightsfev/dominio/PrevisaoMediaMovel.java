package br.com.fiap.territorypredictiveinsightsfev.dominio;

import java.util.List;

/**
 * Previsão 1: média das últimas N medições do histórico.
 *
 * Responsável: Integrante 3 (previsões, alertas e testes)
 */
public class PrevisaoMediaMovel extends EstrategiaPrevisao {

    // TODO: atributo com o tamanho da janela (N), recebido no construtor

    public PrevisaoMediaMovel() {
        super("Média móvel");
        // TODO: receber N como parâmetro, se quiserem configurável
    }

    @Override
    public double prever(List<Medicao> historico) {
        // TODO: chamar a validação comum da mãe (super.validarHistorico(historico))
        // TODO: somar os valores das últimas N medições e dividir por N
        throw new UnsupportedOperationException("TODO: implementar prever() da média móvel");
    }
}
