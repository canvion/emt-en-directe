package emt.directe.emt_en_directe.repository;

import emt.directe.emt_en_directe.model.LineaParada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineaParadaRepository extends JpaRepository<LineaParada, Long> {

}