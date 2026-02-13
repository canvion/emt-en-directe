package emt.directe.emt_en_directe.controller;

import emt.directe.emt_en_directe.dto.FavoritoRequestDTO;
import emt.directe.emt_en_directe.dto.FavoritoResponseDTO;
import emt.directe.emt_en_directe.model.Usuario;
import emt.directe.emt_en_directe.repository.UsuarioRepository;
import emt.directe.emt_en_directe.service.FavoritoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favoritos")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class FavoritoController {
    private final FavoritoService favoritoService;
    private final UsuarioRepository usuarioRepository;

    private Long obtenerUsuarioIdAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("este usuario no se ha encontrado"));
        return usuario.getId();
    }

    @GetMapping
    public ResponseEntity<List<FavoritoResponseDTO>> getMisFavoritos() {
        try {
            Long usuarioId = obtenerUsuarioIdAutenticado();
            List<FavoritoResponseDTO> favoritos = favoritoService.getFavoritosByUsuarioId(usuarioId);
            return ResponseEntity.ok(favoritos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> addFavorito(@Valid @RequestBody FavoritoRequestDTO requestDTO) {
        try {
            Long usuarioId = obtenerUsuarioIdAutenticado();
            FavoritoResponseDTO favorito = favoritoService.addFavorito(usuarioId, requestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(favorito);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("errro al añadir el favorito ");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFavorito(@PathVariable Long id) {
        try {
            Long usuarioId = obtenerUsuarioIdAutenticado();
            favoritoService.deleteFavorito(id, usuarioId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
