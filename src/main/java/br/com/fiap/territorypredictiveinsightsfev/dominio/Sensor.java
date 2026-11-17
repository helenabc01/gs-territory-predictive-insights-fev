package br.com.fiap.territorypredictiveinsightsfev.dominio;

import br.com.fiap.territorypredictiveinsightsfev.excecao.FonteIndisponivelException;

/**
 * Contrato de todo sensor do recorte de clima.
 * Implementações: SensorChuva, SensorNivelRio, SensorUmidadeSolo.
 *
 * Cada sensor informa a própria faixa válida (getValorMinimo/getValorMaximo).
 * Assim a validação da medição funciona para qualquer sensor, sem if por tipo.
 *
 * Responsável: Integrante 1 (domínio e exceções)
 */
public interface Sensor {

    /**
     * Simula uma leitura com Random/Math.random() dentro da faixa realista do sensor.
     *
     * @throws FonteIndisponivelException se o sensor não estiver funcionando
     */
    double lerValor() throws FonteIndisponivelException;

    /** Indica se o sensor está funcionando (pode simular falha aleatória, ex.: 5% das vezes). */
    boolean estaFuncionando();

    /** Tipo do sensor, ex.: "CHUVA", "NIVEL_RIO", "UMIDADE_SOLO". É o que vai para o banco. */
    String getTipo();

    /** Unidade da leitura, ex.: "mm/h", "cm", "%". */
    String getUnidade();

    /** Menor valor fisicamente válido (abaixo disso: DadosInvalidosException). */
    double getValorMinimo();

    /** Maior valor fisicamente válido (acima disso: DadosInvalidosException). */
    double getValorMaximo();
}
