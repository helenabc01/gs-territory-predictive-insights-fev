package br.com.fiap.territorypredictiveinsightsfev.excecao;

/**
 * Lançada quando um dado do território é inválido:
 * medição fora da faixa, população negativa, limiar inválido.
 *
 * É CHECKED (extends Exception): o compilador obriga quem chama a tratar ou repassar,
 * então o erro não passa despercebido e acaba capturado no menu com mensagem clara.
 *
 * Responsável: Integrante 1 (domínio e exceções)
 */
public class DadosInvalidosException extends Exception {

    /**
     * @param mensagem explica o problema, ex.: "medição de chuva negativa: -3.0 mm/h"
     */
    public DadosInvalidosException(String mensagem) {
        super(mensagem);
    }
}
