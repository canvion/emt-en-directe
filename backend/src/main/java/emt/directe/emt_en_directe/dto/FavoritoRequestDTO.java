package emt.directe.emt_en_directe.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoritoRequestDTO {
    @NotNull(message = "L'id de la parada és obligatori")
    private Long paradaId;
}