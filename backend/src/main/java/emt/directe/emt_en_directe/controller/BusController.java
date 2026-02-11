package emt.directe.emt_en_directe.controller;

import emt.directe.emt_en_directe.dto.BusResponseDTO;
import emt.directe.emt_en_directe.service.BusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buses")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class BusController {

    private final BusService busService;

    @GetMapping("/linea/{lineaId}")
    public ResponseEntity<List<BusResponseDTO>> getBusesByLineaId(@PathVariable Long lineaId) {
        try {
            List<BusResponseDTO> buses = busService.getBusesByLineaId(lineaId);
            return ResponseEntity.ok(buses);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}