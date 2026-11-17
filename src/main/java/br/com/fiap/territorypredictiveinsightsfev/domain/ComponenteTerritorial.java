package br.com.fiap.territorypredictiveinsightsfev.domain;

/**
 * Classe abstrata base de tudo que existe no território monitorado
 * (ex.: uma área de risco de alagamento, um córrego, uma bacia).
 * Nome exigido pelo enunciado (mantido em português).
 */
public abstract class ComponenteTerritorial {

    private Long id;
    private String name;
    private String region;
    private String status;

    // TODO: construtor recebendo os atributos (as filhas chamam com super(...))
    // TODO: getters (e setters só onde fizer sentido, validando com DadosInvalidosException)

    /**
     * TODO: cada filha decide como calcular o próprio status
     * (ex.: "NORMAL", "AT RISK"), a partir das leituras/limiares.
     * Nome exigido pelo enunciado.
     */
    public abstract String verificarStatus();
}
