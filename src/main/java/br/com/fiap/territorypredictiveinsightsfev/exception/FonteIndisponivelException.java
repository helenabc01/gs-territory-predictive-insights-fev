package br.com.fiap.territorypredictiveinsightsfev.exception;

/**
 * Lançada quando um sensor (ou gerador de dados) falha e não consegue fornecer leitura.
 * Nome exigido pelo enunciado.
 *
 * É CHECKED (extends Exception): o compilador obriga quem chama a tratar ou repassar,
 * então o erro não passa despercebido e acaba capturado no menu com mensagem clara.
 */
public class FonteIndisponivelException extends Exception {

    /**
     * @param message explica o problema, ex.: "sensor RIVER_LEVEL sem resposta"
     */
    public FonteIndisponivelException(String message) {
        super(message);
    }
}
