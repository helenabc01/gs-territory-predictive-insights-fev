package br.com.fiap.territorypredictiveinsightsfev.excecao;

/**
 * Lançada quando um sensor (ou gerador de dados) falha e não consegue fornecer leitura.
 *
 * É CHECKED (extends Exception): o compilador obriga quem chama a tratar ou repassar,
 * então o erro não passa despercebido e acaba capturado no menu com mensagem clara.
 *
 * Responsável: Integrante 1 (domínio e exceções)
 */
public class FonteIndisponivelException extends Exception {

    /**
     * @param mensagem explica o problema, ex.: "sensor NIVEL_RIO sem resposta"
     */
    public FonteIndisponivelException(String mensagem) {
        super(mensagem);
    }
}
