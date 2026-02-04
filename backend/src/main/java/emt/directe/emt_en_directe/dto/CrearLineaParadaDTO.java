package emt.directe.emt_en_directe.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CrearLineaParadaDTO {
    @NotNull(message = "L'ID de la línia és obligatori")
    private Long lineaId;

    @NotNull(message = "L'ID de la parada és obligatori")
    private Long paradaId;

    @NotNull(message = "El orden es obligatorio")
    private Integer orden;
}
