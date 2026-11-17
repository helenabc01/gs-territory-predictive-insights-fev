package br.com.fiap.territorypredictiveinsightsfev.dominio;

import br.com.fiap.territorypredictiveinsightsfev.excecao.FonteIndisponivelException;

/**
 * Sensor 3: umidade/saturação do solo em %. Solo saturado não absorve chuva -> mais alagamento.
 * Faixa válida: 0 a 100 %.
 *
 * Responsável: você (integrante principal)
 */
public class SensorUmidadeSolo implements Sensor {

    // TODO: atributo Random (ou usar Math.random())

    @Override
    public double lerValor() throws FonteIndisponivelException {
        // TODO: se !estaFuncionando() -> throw new FonteIndisponivelException("sensor UMIDADE_SOLO sem resposta")
        // TODO: gerar valor aleatório entre getValorMinimo() e getValorMaximo()
        throw new UnsupportedOperationException("TODO: implementar lerValor()");
    }

    @Override
    public boolean estaFuncionando() {
        // TODO: simular falha de vez em quando (ex.: 5% das vezes retorna false)
        throw new UnsupportedOperationException("TODO: implementar estaFuncionando()");
    }

    @Override
    public String getTipo() {
        // TODO: retornar "UMIDADE_SOLO"
        throw new UnsupportedOperationException("TODO: implementar getTipo()");
    }

    @Override
    public String getUnidade() {
        // TODO: retornar "%"
        throw new UnsupportedOperationException("TODO: implementar getUnidade()");
    }

    @Override
    public double getValorMinimo() {
        // TODO: retornar 0
        throw new UnsupportedOperationException("TODO: implementar getValorMinimo()");
    }

    @Override
    public double getValorMaximo() {
        // TODO: retornar 100
        throw new UnsupportedOperationException("TODO: implementar getValorMaximo()");
    }
}
