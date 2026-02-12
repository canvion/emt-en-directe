package emt.directe.emt_en_directe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoritoResponseDTO {
    private Long id;
    private Long usuarioId;
    private String usuarioUsername;
    private Long paradaId;
    private String paradaNombre;
    private String paradaCodigo;
    private Double paradaLatitud;
    private Double paradaLongitud;
    private LocalDateTime createdAt;
}