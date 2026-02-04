package emt.directe.emt_en_directe.service;

import emt.directe.emt_en_directe.dto.LineaRequestDTO;
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

    public LineaResponseDTO createLinea(LineaRequestDTO requestDTO) {
        if (lineaRepository.existsByNumero(requestDTO.getNumero())) {
            throw new RuntimeException("ja existeix una línia " + requestDTO.getNumero());
        }

        Linea linea = new Linea();
        linea.setNumero(requestDTO.getNumero());
        linea.setNombre(requestDTO.getNombre());
        linea.setColor(requestDTO.getColor());

        Linea savedLinea = lineaRepository.save(linea);
        return convertToResponseDTO(savedLinea);
    }

    public void deleteLinea(Long id) {
        if (!lineaRepository.existsById(id)) {
            throw new RuntimeException("línia no trobada amb aquest id: " + id);
        }
        lineaRepository.deleteById(id);
    }

    public LineaResponseDTO updateLinea(Long id, LineaRequestDTO requestDTO) {
        Linea linea = lineaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("línia no trobada amb id: " + id));

        // Validar que el nuevo número no exista (si se cambia)
        if (!linea.getNumero().equals(requestDTO.getNumero()) &&
                lineaRepository.existsByNumero(requestDTO.getNumero())) {
            throw new RuntimeException("ja existeix una línia " + requestDTO.getNumero());
        }

        linea.setNumero(requestDTO.getNumero());
        linea.setNombre(requestDTO.getNombre());
        linea.setColor(requestDTO.getColor());

        Linea updatedLinea = lineaRepository.save(linea);
        return convertToResponseDTO(updatedLinea);
    }

    private LineaResponseDTO convertToResponseDTO(Linea linea) {
        List<LineaResponseDTO.ParadaResumen> paradas = linea.getLineasParadas().stream()
                .sorted((lp1, lp2) -> lp1.getOrden().compareTo(lp2.getOrden()))
                .map(lp -> new LineaResponseDTO.ParadaResumen(
                        lp.getParada().getId(),
                        lp.getParada().getCodigo(),
                        lp.getParada().getNombre(),
                        lp.getOrden()
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