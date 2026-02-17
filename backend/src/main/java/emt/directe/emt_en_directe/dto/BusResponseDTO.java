package emt.directe.emt_en_directe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusResponseDTO {
    private Long id;
    private Long lineaId;
    private String lineaNumero;
    private String lineaNombre;
    private String lineaColor;
    private Double latitud;
    private Double longitud;
    private LocalDateTime updatedAt;
}