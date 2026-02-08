package emt.directe.emt_en_directe.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BusResponseDTO {
    private Long id;
    private String matricula;
    private Double latitud;
    private Double longitud;
    private Long lineaId;
    private String lineaNumero;
    private String lineaNombre;
    private LocalDateTime updatedAt;
}
