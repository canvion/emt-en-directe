package emt.directe.emt_en_directe.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineaRequestDTO {

    @NotBlank(message = "número de línia obligatori")
    @Size(max = 3, message = "es número no pot superar 3 caràcters")
    private String numero;

    @NotBlank(message = "nom de la línia  obligatori")
    private String nombre;

    @Pattern(regexp = "^#[0-9A-Fa-f]{6}$", message = "El color debe estar en formato HEX (#RRGGBB)")
    private String color;
}
