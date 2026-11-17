package br.com.fiap.territorypredictiveinsightsfev.dominio;

import br.com.fiap.territorypredictiveinsightsfev.excecao.FonteIndisponivelException;

/**
 * Sensor 1: pluviômetro. Mede intensidade de chuva em mm/h.
 * Faixa realista sugerida: 0 a 150 mm/h (valor negativo é inválido).
 *
 * Responsável: Colega 1
 */
public class SensorChuva implements Sensor {

    // TODO: atributo Random (ou usar Math.random())

    @Override
    public double lerValor() throws FonteIndisponivelException {
        // TODO: se !estaFuncionando() -> throw new FonteIndisponivelException("sensor CHUVA sem resposta")
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
        // TODO: retornar "CHUVA"
        throw new UnsupportedOperationException("TODO: implementar getTipo()");
    }

    @Override
    public String getUnidade() {
        // TODO: retornar "mm/h"
        throw new UnsupportedOperationException("TODO: implementar getUnidade()");
    }

    @Override
    public double getValorMinimo() {
        // TODO: retornar 0
        throw new UnsupportedOperationException("TODO: implementar getValorMinimo()");
    }

    @Override
    public double getValorMaximo() {
        // TODO: retornar 150
        throw new UnsupportedOperationException("TODO: implementar getValorMaximo()");
    }
}
