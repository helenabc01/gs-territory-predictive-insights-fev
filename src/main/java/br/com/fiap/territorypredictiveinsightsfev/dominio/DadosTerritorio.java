package br.com.fiap.territorypredictiveinsightsfev.dominio;

/**
 * Dados do território monitorado (região, população exposta, limiares).
 * Atributos PRIVADOS; alterar limiar exige um código de acesso.
 *
 * Responsável: Integrante 1 (domínio e exceções)
 */
public class DadosTerritorio {

    // TODO: atributos privados: regiao, populacaoExposta, limiares (chuva, nível do rio, umidade do solo)
    // TODO: constante privada com o código de acesso (ex.: CODIGO_ACESSO)

    // TODO: getters

    // TODO: setPopulacaoExposta(int) -> população negativa lança DadosInvalidosException

    // TODO: alterarLimiar(String tipoSensor, double novoValor, String codigoAcesso)
    //       - código errado -> recusar com mensagem clara (exceção ou retorno)
    //       - limiar negativo/absurdo -> DadosInvalidosException ("limiar inválido")
    //       - decidir com o grupo se os limiares ficam aqui ou só no ConfiguracaoSistema
}
