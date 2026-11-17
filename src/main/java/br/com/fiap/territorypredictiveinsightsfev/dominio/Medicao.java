package br.com.fiap.territorypredictiveinsightsfev.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Uma leitura de sensor gravada no banco (entidade JPA).
 *
 * Responsável: Integrante 2 (persistência), com validação do Integrante 1
 */
@Entity
public class Medicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO: atributos: tipoSensor, valor, unidade, regiao, dataHora (LocalDateTime)
    // TODO: construtor com os campos, validando:
    //       valor fora da faixa do sensor -> lançar DadosInvalidosException
    //       (ex.: "medição de chuva negativa: -3.0 mm/h")
    // TODO: getters
    // TODO (opcional): toString() para exibir no histórico do menu

    /** Construtor vazio exigido pelo JPA. Não usar no código do sistema. */
    protected Medicao() {
    }

    public Long getId() {
        return id;
    }
}
