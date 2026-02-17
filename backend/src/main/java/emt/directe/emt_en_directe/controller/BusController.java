package emt.directe.emt_en_directe.controller;

import emt.directe.emt_en_directe.dto.BusResponseDTO;
import emt.directe.emt_en_directe.service.BusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/buses")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class BusController {

    private final BusService busService;

    @GetMapping("/linea/{lineaId}")
    public ResponseEntity<BusResponseDTO> getBusByLinea(@PathVariable Long lineaId) {
        return ResponseEntity.ok(busService.obtenerPosicionBus(lineaId));
    }
}