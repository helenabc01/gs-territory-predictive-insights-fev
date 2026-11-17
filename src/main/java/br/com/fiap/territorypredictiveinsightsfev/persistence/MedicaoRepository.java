package br.com.fiap.territorypredictiveinsightsfev.persistence;

import br.com.fiap.territorypredictiveinsightsfev.domain.Medicao;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Acesso ao banco para as medições (Spring Data JPA). save() e findAll() já vêm prontos.
 * Nome exigido pelo enunciado.
 */
public interface MedicaoRepository extends JpaRepository<Medicao, Long> {

    // TODO: histórico por sensor (nomes batem com os atributos de Medicao):
    //       List<Medicao> findBySensorTypeOrderByMeasuredAtDesc(String sensorType);
}
