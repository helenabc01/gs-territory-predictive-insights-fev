package br.com.fiap.territorypredictiveinsightsfev.dominio;

import br.com.fiap.territorypredictiveinsightsfev.excecao.FonteIndisponivelException;

/**
 * Sensor 2: régua/sensor de nível do córrego ou rio. Mede a altura da água em cm.
 * Faixa realista sugerida: 0 a 800 cm.
 *
 * Responsável: Colega 2
 */
public class SensorNivelRio implements Sensor {

    // TODO: atributo Random (ou usar Math.random())

    @Override
    public double lerValor() throws FonteIndisponivelException {
        // TODO: se !estaFuncionando() -> throw new FonteIndisponivelException("sensor NIVEL_RIO sem resposta")
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
        // TODO: retornar "NIVEL_RIO"
        throw new UnsupportedOperationException("TODO: implementar getTipo()");
    }

    @Override
    public String getUnidade() {
        // TODO: retornar "cm"
        throw new UnsupportedOperationException("TODO: implementar getUnidade()");
    }

    @Override
    public double getValorMinimo() {
        // TODO: retornar 0
        throw new UnsupportedOperationException("TODO: implementar getValorMinimo()");
    }

    @Override
    public double getValorMaximo() {
        // TODO: retornar 800
        throw new UnsupportedOperationException("TODO: implementar getValorMaximo()");
    }
}
