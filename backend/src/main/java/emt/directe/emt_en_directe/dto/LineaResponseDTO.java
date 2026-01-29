package emt.directe.emt_en_directe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineaResponseDTO {

    private Long id;
    private String numero;
    private String nombre;
    private String color;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
