package emt.directe.emt_en_directe.service;

import emt.directe.emt_en_directe.dto.LineaResponseDTO;
import emt.directe.emt_en_directe.dto.ParadaRequestDTO;
import emt.directe.emt_en_directe.dto.ParadaResponseDTO;
import emt.directe.emt_en_directe.model.Linea;
import emt.directe.emt_en_directe.model.Parada;
import emt.directe.emt_en_directe.repository.LineaRepository;
import emt.directe.emt_en_directe.repository.ParadaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParadaService {

    private final ParadaRepository paradaRepository;
    private final LineaRepository lineaRepository;

    public List<ParadaResponseDTO> getAllParadas() {
        return paradaRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public ParadaResponseDTO getParadaById(Long id) {
        Parada parada = paradaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("parada no encontrada con el id: " + id));
        return convertToResponseDTO(parada);
    }

    public List<ParadaResponseDTO> getParadasByLineaId(Long lineaId) {
        if (!lineaRepository.existsById(lineaId)) {
            throw new RuntimeException("línea no encontrada con el id: " + lineaId);
        }
        return paradaRepository.findByLineaId(lineaId).stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public ParadaResponseDTO createParada(ParadaRequestDTO requestDTO) {
        Linea linea = lineaRepository.findById(requestDTO.getLineaId())
                .orElseThrow(() -> new RuntimeException("línea no encontrada con el id: " + requestDTO.getLineaId()));
        Parada parada = new Parada();
        parada.setNombre(requestDTO.getNombre());
        parada.setLatitud(requestDTO.getLatitud());
        parada.setLongitud(requestDTO.getLongitud());
        parada.setLinea(linea);

        Parada savedParada = paradaRepository.save(parada);
        return convertToResponseDTO(savedParada);
    }

    public ParadaResponseDTO updateParada(Long id, ParadaRequestDTO requestDTO) {
        Parada parada = paradaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("parada no encontrada con el id: " + id));
        Linea linea = lineaRepository.findById(requestDTO.getLineaId())
                .orElseThrow(() -> new RuntimeException("línea no encontrada con el id: " + requestDTO.getLineaId()));

        parada.setNombre(requestDTO.getNombre());
        parada.setLatitud(requestDTO.getLatitud());
        parada.setLongitud(requestDTO.getLongitud());
        parada.setLinea(linea);

        Parada updatedParada = paradaRepository.save(parada);
        return convertToResponseDTO(updatedParada);
    }

    public void deleteParada(Long id) {
        if (!paradaRepository.existsById(id)) {
            throw new RuntimeException("parada no encontrada con el id: " + id);
        }
        paradaRepository.deleteById(id);
    }

    private ParadaResponseDTO convertToResponseDTO(Parada parada) {
        ParadaResponseDTO dto = new ParadaResponseDTO();
        dto.setId(parada.getId());
        dto.setNombre(parada.getNombre());
        dto.setLatitud(parada.getLatitud());
        dto.setLongitud(parada.getLongitud());
        dto.setCreatedAt(parada.getCreatedAt());
        dto.setUpdatedAt(parada.getUpdatedAt());

        LineaResponseDTO lineaDTO = new LineaResponseDTO();
        lineaDTO.setId(parada.getLinea().getId());
        lineaDTO.setNumero(parada.getLinea().getNumero());
        lineaDTO.setNombre(parada.getLinea().getNombre());
        lineaDTO.setColor(parada.getLinea().getColor());
        lineaDTO.setCreatedAt(parada.getLinea().getCreatedAt());
        lineaDTO.setUpdatedAt(parada.getLinea().getUpdatedAt());
        dto.setLinea(lineaDTO);
        return dto;
    }
}
