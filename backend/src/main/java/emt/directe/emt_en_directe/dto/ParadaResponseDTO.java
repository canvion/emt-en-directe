package emt.directe.emt_en_directe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParadaResponseDTO {

    private Long id;
    private String nombre;
    private Double latitud;
    private Double longitud;
    private LineaResponseDTO linea;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
