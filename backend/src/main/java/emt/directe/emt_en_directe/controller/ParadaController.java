package emt.directe.emt_en_directe.controller;

import emt.directe.emt_en_directe.dto.ParadaRequestDTO;
import emt.directe.emt_en_directe.dto.ParadaResponseDTO;
import emt.directe.emt_en_directe.service.ParadaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/paradas")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ParadaController {

    private final ParadaService paradaService;

    @GetMapping
    public ResponseEntity<List<ParadaResponseDTO>> getAllParadas() {
        try {
            List<ParadaResponseDTO> paradas = paradaService.getAllParadas();
            return ResponseEntity.ok(paradas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParadaResponseDTO> getParadaById(@PathVariable Long id) {
        try {
            ParadaResponseDTO parada = paradaService.getParadaById(id);
            return ResponseEntity.ok(parada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createParada(@Valid @RequestBody ParadaRequestDTO requestDTO) {
        try {
            ParadaResponseDTO nuevaParada = paradaService.createParada(requestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaParada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("error al crear la parada");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateParada(
            @PathVariable Long id,
            @Valid @RequestBody ParadaRequestDTO requestDTO) {
        try {
            ParadaResponseDTO paradaActualizada = paradaService.updateParada(id, requestDTO);
            return ResponseEntity.ok(paradaActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("error al actualizar la parada");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteParada(@PathVariable Long id) {
        try {
            paradaService.deleteParada(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
