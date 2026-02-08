package emt.directe.emt_en_directe.repository;

import emt.directe.emt_en_directe.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    List<Bus> findByLineaId(Long lineaId);
}