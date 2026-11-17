package br.com.fiap.territorypredictiveinsightsfev.servico;

/**
 * Padrão criacional SINGLETON: uma única instância guardando os limiares do sistema.
 * Justificar no README por que Singleton aqui.
 *
 * Responsável: Integrante 3 (previsões, alertas e testes)
 */
public class ConfiguracaoSistema {

    // TODO: atributo estático privado com a única instância
    // TODO: construtor PRIVADO (ninguém de fora dá new)
    // TODO: método estático getInstancia()

    // TODO: limiares por sensor e por nível (ATENCAO / ALERTA / CRITICO), ex. para chuva em mm/h:
    //       ATENCAO >= 10, ALERTA >= 30, CRITICO >= 50  (ajustar com o grupo)
    //       nível do rio (cm) e umidade do solo (%) também

    // TODO: getLimiar(String tipoSensor, NivelAlerta nivel)
    // TODO: setLimiar(...) validando -> DadosInvalidosException para limiar inválido

    // TODO: estratégia de previsão configurada (ex.: "MEDIA_MOVEL"), usada pela FabricaPrevisao
}
