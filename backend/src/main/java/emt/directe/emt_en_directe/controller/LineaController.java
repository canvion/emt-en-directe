package emt.directe.emt_en_directe.controller;

import emt.directe.emt_en_directe.dto.LineaResponseDTO;
import emt.directe.emt_en_directe.service.LineaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lineas")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class LineaController {

    private final LineaService lineaService;

    @GetMapping
    public ResponseEntity<List<LineaResponseDTO>> getAllLineas() {
        try {
            List<LineaResponseDTO> lineas = lineaService.getAllLineas();
            return ResponseEntity.ok(lineas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<LineaResponseDTO> getLineaById(@PathVariable Long id) {
        try {
            LineaResponseDTO linea = lineaService.getLineaById(id);
            return ResponseEntity.ok(linea);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
