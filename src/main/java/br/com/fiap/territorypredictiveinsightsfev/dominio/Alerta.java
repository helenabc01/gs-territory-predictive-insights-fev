package br.com.fiap.territorypredictiveinsightsfev.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Alerta emitido quando a previsão estoura um limiar (entidade JPA).
 * Precisa explicar o PORQUÊ: qual sensor, valor previsto e limite estourado.
 *
 * Responsável: Integrante 2 (persistência), com regras do Integrante 3
 */
@Entity
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO: atributos: tipoSensor, valorPrevisto, limiteEstourado, estrategiaUsada,
    //       mensagem (o porquê), dataHora (LocalDateTime)
    // TODO: nivel do tipo NivelAlerta com @Enumerated(EnumType.STRING)
    // TODO: construtor com os campos + getters
    // TODO (opcional): toString() para exibir no menu

    /** Construtor vazio exigido pelo JPA. Não usar no código do sistema. */
    protected Alerta() {
    }

    public Long getId() {
        return id;
    }
}
