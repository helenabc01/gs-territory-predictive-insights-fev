package br.com.fiap.territorypredictiveinsightsfev.domain;

/**
 * Dados do território monitorado. Atributos PRIVADOS; alterar limiar exige código de acesso.
 * Nome exigido pelo enunciado (mantido em português).
 *
 * Os limiares em si ficam no ConfiguracaoSistema (Singleton); esta classe confere o
 * código de acesso e repassa a alteração para lá.
 */
public class DadosTerritorio {

    private String region;
    private int exposedPopulation;
    private int vulnerablePoints;
    private final String accessCode;

    // TODO: construtor (region, exposedPopulation, vulnerablePoints, accessCode)
    //       -> por enquanto este construtor existe só porque accessCode é final
    public DadosTerritorio(String accessCode) {
        this.accessCode = accessCode;
    }

    // TODO: getters

    // TODO: setExposedPopulation(int) -> população negativa lança DadosInvalidosException

    // TODO: changeThreshold(String sensorType, NivelAlerta level, double newValue, String informedCode)
    //       - código errado -> recusar com mensagem clara
    //       - limiar negativo/absurdo -> DadosInvalidosException ("limiar inválido")
    //       - se ok -> ConfiguracaoSistema.getInstance().setThreshold(...)
}
