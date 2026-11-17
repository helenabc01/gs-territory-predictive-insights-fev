package br.com.fiap.territorypredictiveinsightsfev.persistence;

import br.com.fiap.territorypredictiveinsightsfev.domain.Medicao;
import br.com.fiap.territorypredictiveinsightsfev.domain.SensorType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MedicaoRepository extends JpaRepository<Medicao, Long> {

    List<Medicao> findBySensorTypeOrderByIdDesc(SensorType sensorType);

    Optional<Medicao> findFirstBySensorTypeOrderByIdDesc(SensorType sensorType);

    long countBySensorType(SensorType sensorType);
}
