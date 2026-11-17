package br.com.fiap.territorypredictiveinsightsfev.dominio;

/**
 * Classe abstrata base de tudo que existe no território monitorado
 * (ex.: uma área de risco de alagamento, um córrego, uma bacia).
 *
 * Responsável: Integrante 1 (domínio e exceções)
 */
public abstract class ComponenteTerritorial {

    // TODO: atributos privados: id, nome, regiao, status
    // TODO: construtor recebendo os atributos (as filhas chamam com super(...))
    // TODO: getters (e setters só onde fizer sentido, validando com DadosInvalidosException)

    /**
     * TODO: cada filha decide como calcular o próprio status
     * (ex.: "NORMAL", "EM RISCO"), a partir das leituras/limiares.
     */
    public abstract String verificarStatus();
}
