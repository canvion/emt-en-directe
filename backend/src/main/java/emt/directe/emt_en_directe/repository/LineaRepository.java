package emt.directe.emt_en_directe.repository;

import emt.directe.emt_en_directe.model.Linea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LineaRepository extends JpaRepository<Linea, Long> {

    Optional<Linea> findByNumero(String numero);
    boolean existsByNumero(String numero);
}
