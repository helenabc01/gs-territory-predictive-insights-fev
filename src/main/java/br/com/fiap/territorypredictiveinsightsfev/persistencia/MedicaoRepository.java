package br.com.fiap.territorypredictiveinsightsfev.persistencia;

import br.com.fiap.territorypredictiveinsightsfev.dominio.Medicao;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Acesso ao banco para as medições (Spring Data JPA). save() e findAll() já vêm prontos.
 *
 * Responsável: Integrante 2 (persistência)
 */
public interface MedicaoRepository extends JpaRepository<Medicao, Long> {

    // TODO: histórico por sensor (os nomes dos campos precisam bater com os atributos de Medicao):
    //       List<Medicao> findByTipoSensorOrderByDataHoraDesc(String tipoSensor);

    // TODO: últimas N leituras de um sensor (para a média móvel), ex.:
    //       List<Medicao> findTop10ByTipoSensorOrderByDataHoraDesc(String tipoSensor);
}
