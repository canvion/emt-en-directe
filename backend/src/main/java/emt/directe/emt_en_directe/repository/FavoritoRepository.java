package emt.directe.emt_en_directe.repository;

import emt.directe.emt_en_directe.model.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, Long> {
    List<Favorito> findByUsuarioId(Long usuarioId);
    Optional<Favorito> findByUsuarioIdAndParadaId(Long usuarioId, Long paradaId);

    boolean existsByUsuarioIdAndParadaId(Long usuarioId, Long paradaId);
}