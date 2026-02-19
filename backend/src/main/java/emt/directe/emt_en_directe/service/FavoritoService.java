package emt.directe.emt_en_directe.service;

import emt.directe.emt_en_directe.dto.FavoritoRequestDTO;
import emt.directe.emt_en_directe.dto.FavoritoResponseDTO;
import emt.directe.emt_en_directe.model.Favorito;
import emt.directe.emt_en_directe.model.Parada;
import emt.directe.emt_en_directe.model.Usuario;
import emt.directe.emt_en_directe.repository.FavoritoRepository;
import emt.directe.emt_en_directe.repository.ParadaRepository;
import emt.directe.emt_en_directe.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoritoService {
    private final FavoritoRepository favoritoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ParadaRepository paradaRepository;

    public List<FavoritoResponseDTO> getFavoritosByUsuarioId(Long usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new RuntimeException("usuari no trobat amb l'id: " + usuarioId);
        }
        return favoritoRepository.findByUsuarioId(usuarioId).stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public FavoritoResponseDTO addFavorito(Long usuarioId, FavoritoRequestDTO requestDTO) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("usuari no trobat amb l'id: " + usuarioId));

        Parada parada = paradaRepository.findById(requestDTO.getParadaId())
                .orElseThrow(() -> new RuntimeException("parada no trobada amb l'id: " + requestDTO.getParadaId()));

        boolean yaExiste = favoritoRepository.findByUsuarioId(usuarioId).stream()
                .anyMatch(f -> f.getParada().getId().equals(requestDTO.getParadaId()));

        if (yaExiste) {
            throw new RuntimeException("aquesta parada ja és als teus favorits");
        }

        Favorito favorito = new Favorito();
        favorito.setUsuario(usuario);
        favorito.setParada(parada);
        Favorito saved = favoritoRepository.save(favorito);
        return convertToResponseDTO(saved);
    }

    public void deleteFavorito(Long favoritoId, Long usuarioId) {
        Favorito favorito = favoritoRepository.findById(favoritoId)
                .orElseThrow(() -> new RuntimeException("favorit no trobat amb l'id: " + favoritoId));

        if (!favorito.getUsuario().getId().equals(usuarioId)) {
            throw new RuntimeException("no tens permisos per eliminar aquest favorit");
        }
        favoritoRepository.deleteById(favoritoId);
    }

    private FavoritoResponseDTO convertToResponseDTO(Favorito favorito) {

        FavoritoResponseDTO dto = new FavoritoResponseDTO();

        dto.setId(favorito.getId());
        dto.setUsuarioId(favorito.getUsuario().getId());
        dto.setUsuarioUsername(favorito.getUsuario().getUsername());
        dto.setParadaId(favorito.getParada().getId());
        dto.setParadaNombre(favorito.getParada().getNombre());
        dto.setParadaCodigo(favorito.getParada().getCodigo());
        dto.setParadaLatitud(favorito.getParada().getLatitud());
        dto.setParadaLongitud(favorito.getParada().getLongitud());
        dto.setCreatedAt(favorito.getCreatedAt());

        List<String> lineas = favorito.getParada().getLineasParadas().stream()
                .map(lp -> lp.getLinea().getNumero() + " - " + lp.getLinea().getNombre())
                .collect(Collectors.toList());
        dto.setLineas(lineas);

        return dto; }
}