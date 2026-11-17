package br.com.fiap.territorypredictiveinsightsfev.domain;

/**
 * Filha concreta de ComponenteTerritorial: um ponto vulnerável a alagamento
 * (ex.: "Baixada do córrego X - Zona Sul"). Descrever por região, nunca por pessoas.
 */
public class RiskArea extends ComponenteTerritorial {

    /** Tipo de risco, ex.: "FLOOD", "LANDSLIDE". */
    private String riskType;

    /** Se a área já alagou antes (histórico conhecido). */
    private boolean floodHistory;

    // TODO: construtor chamando super(id, name, region, status) + atributos próprios
    // TODO: getters

    @Override
    public String verificarStatus() {
        // TODO: retornar o status da área (ex.: com base no último alerta ou nas leituras)
        throw new UnsupportedOperationException("TODO: implementar verificarStatus()");
    }
}
