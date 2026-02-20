package emt.directe.emt_en_directe.service;

import emt.directe.emt_en_directe.dto.LineaResponseDTO;
import emt.directe.emt_en_directe.model.Linea;
import emt.directe.emt_en_directe.repository.LineaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LineaService {

    private final LineaRepository lineaRepository;

    public List<LineaResponseDTO> getAllLineas() {
        return lineaRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public LineaResponseDTO getLineaById(Long id) {
        Linea linea = lineaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("línia no trobada amb id: " + id));
        return convertToResponseDTO(linea);
    }

    private LineaResponseDTO convertToResponseDTO(Linea linea) {
        List<LineaResponseDTO.ParadaResumen> paradas = linea.getLineasParadas().stream()
                .sorted((lp1, lp2) -> lp1.getOrden().compareTo(lp2.getOrden()))
                .map(lp -> new LineaResponseDTO.ParadaResumen(
                        lp.getParada().getId(),
                        lp.getParada().getCodigo(),
                        lp.getParada().getNombre(),
                        lp.getOrden(),
                        lp.getEsParada(),
                        lp.getParada().getLatitud(),
                        lp.getParada().getLongitud()
                ))
                .collect(Collectors.toList());

        return new LineaResponseDTO(
                linea.getId(),
                linea.getNumero(),
                linea.getNombre(),
                linea.getColor(),
                linea.getCreatedAt(),
                linea.getUpdatedAt(),
                paradas
        );
    }
}