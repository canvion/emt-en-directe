package emt.directe.emt_en_directe.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParadaRequestDTO {

    @NotBlank(message = "el nombre de la parada es obligatorio")
    private String nombre;

    @NotNull(message = "la latitud es obligatoria")
    @DecimalMin(value = "-90.0", message = "la latitud debe ser mayor o igual a -90")
    @DecimalMax(value = "90.0", message = "la latitud debe ser menor o igual a 90")
    private Double latitud;

    @NotNull(message = "la longitud es obligatoria")
    @DecimalMin(value = "-180.0", message = "la longitud debe ser mayor o igual a -180")
    @DecimalMax(value = "180.0", message = "la longitud debe ser menor o igual a 180")
    private Double longitud;

    @NotNull(message = "el ID de la línea es obligatorio")
    private Long lineaId;
}
