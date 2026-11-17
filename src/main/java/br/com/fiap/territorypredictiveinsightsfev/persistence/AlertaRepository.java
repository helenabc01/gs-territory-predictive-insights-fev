package br.com.fiap.territorypredictiveinsightsfev.persistence;

import br.com.fiap.territorypredictiveinsightsfev.domain.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {

    List<Alerta> findAllByOrderByIdDesc();

    List<Alerta> findTop5ByOrderByIdDesc();
}
