package emt.directe.emt_en_directe.repository;

import emt.directe.emt_en_directe.model.Parada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParadaRepository extends JpaRepository<Parada, Long> {

}