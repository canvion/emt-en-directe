package emt.directe.emt_en_directe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParadaResponseDTO {

    private Long id;
    private String nombre;
    private String codigo;
    private Double latitud;
    private Double longitud;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    //añadimos la información de las líneas:
    private List<LineaResumen> lineas = new ArrayList<>();

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LineaResumen {
        private Long id;
        private String numero;
        private String nombre;
        private String color;
        private Integer orden;
    }
}