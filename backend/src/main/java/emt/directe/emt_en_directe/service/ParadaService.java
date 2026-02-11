package emt.directe.emt_en_directe.service;

import emt.directe.emt_en_directe.dto.ParadaResponseDTO;
import emt.directe.emt_en_directe.model.Parada;
import emt.directe.emt_en_directe.repository.ParadaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParadaService {

    private final ParadaRepository paradaRepository;

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

    public ParadaResponseDTO createParada(ParadaRequestDTO requestDTO) {
        Parada parada = new Parada();
        parada.setNombre(requestDTO.getNombre());
        parada.setCodigo(requestDTO.getCodigo());
        parada.setLatitud(requestDTO.getLatitud());
        parada.setLongitud(requestDTO.getLongitud());
        Parada savedParada = paradaRepository.save(parada);

        return convertToResponseDTO(savedParada);
    }

    public ParadaResponseDTO updateParada(Long id, ParadaRequestDTO requestDTO) {
        Parada parada = paradaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("parada no encontrada con el id: " + id));

        parada.setNombre(requestDTO.getNombre());
        parada.setCodigo(requestDTO.getCodigo());
        parada.setLatitud(requestDTO.getLatitud());
        parada.setLongitud(requestDTO.getLongitud());
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
        List<ParadaResponseDTO.LineaResumen> lineas = parada.getLineasParadas().stream()
                .map(lp -> new ParadaResponseDTO.LineaResumen(
                        lp.getLinea().getId(),
                        lp.getLinea().getNumero(),
                        lp.getLinea().getNombre(),
                        lp.getLinea().getColor(),
                        lp.getOrden()
                ))
                .collect(Collectors.toList());

        return new ParadaResponseDTO(
                parada.getId(),
                parada.getNombre(),
                parada.getCodigo(),
                parada.getLatitud(),
                parada.getLongitud(),
                parada.getCreatedAt(),
                parada.getUpdatedAt(),
                lineas
        );
    }
}