package emt.directe.emt_en_directe.controller;

import emt.directe.emt_en_directe.dto.CrearLineaParadaDTO;
import emt.directe.emt_en_directe.dto.LineaParadaDTO;
import emt.directe.emt_en_directe.service.LineaParadaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lineas-paradas")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class LineaParadaController {

    private final LineaParadaService lineaParadaService;

    @GetMapping("/linea/{lineaId}")
    public ResponseEntity<List<LineaParadaDTO>> getParadasByLinea(@PathVariable Long lineaId) {
        try {
            List<LineaParadaDTO> paradas = lineaParadaService.getParadasByLinea(lineaId);
            return ResponseEntity.ok(paradas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/parada/{paradaId}")
    public ResponseEntity<List<LineaParadaDTO>> getLineasByParada(@PathVariable Long paradaId) {
        try {
            List<LineaParadaDTO> lineas = lineaParadaService.getLineasByParada(paradaId);
            return ResponseEntity.ok(lineas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> addParadaToLinea(@Valid @RequestBody CrearLineaParadaDTO dto) {
        try {
            LineaParadaDTO nuevaRelacion = lineaParadaService.addParadaToLinea(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaRelacion);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("error al añadir parada a la línea");
        }
    }

    @DeleteMapping("/linea/{lineaId}/parada/{paradaId}")
    public ResponseEntity<?> removeParadaFromLinea(
            @PathVariable Long lineaId,
            @PathVariable Long paradaId) {
        try {
            lineaParadaService.removeParadaFromLinea(lineaId, paradaId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}