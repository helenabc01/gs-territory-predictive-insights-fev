package br.com.fiap.territorypredictiveinsightsfev.exception;

/**
 * Lançada quando um dado do território é inválido:
 * medição fora da faixa, população negativa, limiar inválido.
 * Nome exigido pelo enunciado.
 *
 * É CHECKED (extends Exception): o compilador obriga quem chama a tratar ou repassar,
 * então o erro não passa despercebido e acaba capturado no menu com mensagem clara.
 */
public class DadosInvalidosException extends Exception {

    /**
     * @param message explica o problema, ex.: "medição de chuva negativa: -3.0 mm/h"
     */
    public DadosInvalidosException(String message) {
        super(message);
    }
}
