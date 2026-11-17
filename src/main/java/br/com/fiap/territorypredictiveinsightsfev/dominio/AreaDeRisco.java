package br.com.fiap.territorypredictiveinsightsfev.dominio;

/**
 * Filha concreta de ComponenteTerritorial: um ponto vulnerável a alagamento
 * (ex.: "Baixada do córrego X - Zona Sul"). Descrever por região, nunca por pessoas.
 *
 * Responsável: Integrante 1 (domínio e exceções)
 */
public class AreaDeRisco extends ComponenteTerritorial {

    // TODO: atributos próprios, se quiserem (ex.: tipo de risco, se tem histórico de alagamento)
    // TODO: construtor chamando super(id, nome, regiao, status)

    @Override
    public String verificarStatus() {
        // TODO: retornar o status da área (ex.: com base no último alerta ou nas leituras)
        throw new UnsupportedOperationException("TODO: implementar verificarStatus()");
    }
}
