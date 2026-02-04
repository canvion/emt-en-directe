package emt.directe.emt_en_directe.service;

import emt.directe.emt_en_directe.dto.CrearLineaParadaDTO;
import emt.directe.emt_en_directe.dto.LineaParadaDTO;
import emt.directe.emt_en_directe.model.Linea;
import emt.directe.emt_en_directe.model.LineaParada;
import emt.directe.emt_en_directe.model.Parada;
import emt.directe.emt_en_directe.repository.LineaParadaRepository;
import emt.directe.emt_en_directe.repository.LineaRepository;
import emt.directe.emt_en_directe.repository.ParadaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LineaParadaService {

    private final LineaParadaRepository lineaParadaRepository;
    private final LineaRepository lineaRepository;
    private final ParadaRepository paradaRepository;

    public List<LineaParadaDTO> getParadasByLinea(Long lineaId) {
        if (!lineaRepository.existsById(lineaId)) {
            throw new RuntimeException("línia no trobada amb id: " + lineaId);
        }
        return lineaParadaRepository.findByLineaIdOrderByOrden(lineaId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<LineaParadaDTO> getLineasByParada(Long paradaId) {
        if (!paradaRepository.existsById(paradaId)) {
            throw new RuntimeException("parada no trobada amb id: " + paradaId);
        }
        return lineaParadaRepository.findByParadaId(paradaId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public LineaParadaDTO addParadaToLinea(CrearLineaParadaDTO dto) {
        Linea linea = lineaRepository.findById(dto.getLineaId())
                .orElseThrow(() -> new RuntimeException("línia no trobada amb id: " + dto.getLineaId()));

        Parada parada = paradaRepository.findById(dto.getParadaId())
                .orElseThrow(() -> new RuntimeException("parada no trobada amb id: " + dto.getParadaId()));

        LineaParada lineaParada = new LineaParada();
        lineaParada.setLinea(linea);
        lineaParada.setParada(parada);
        lineaParada.setOrden(dto.getOrden());

        LineaParada saved = lineaParadaRepository.save(lineaParada);
        return convertToDTO(saved);
    }

    @Transactional
    public void removeParadaFromLinea(Long lineaId, Long paradaId) {
        if (!lineaRepository.existsById(lineaId)) {
            throw new RuntimeException("línia no trobada amb id: " + lineaId);
        }
        if (!paradaRepository.existsById(paradaId)) {
            throw new RuntimeException("parada no trobada amb id: " + paradaId);
        }
        lineaParadaRepository.deleteByLineaIdAndParadaId(lineaId, paradaId);
    }

    private LineaParadaDTO convertToDTO(LineaParada lp) {
        return new LineaParadaDTO(
                lp.getId(),
                lp.getLinea().getId(),
                lp.getParada().getId(),
                lp.getLinea().getNumero(),
                lp.getLinea().getNombre(),
                lp.getParada().getNombre(),
                lp.getParada().getCodigo(),
                lp.getOrden()
        );
    }
}