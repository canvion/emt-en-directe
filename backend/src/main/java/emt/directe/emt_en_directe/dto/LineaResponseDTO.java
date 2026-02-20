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
public class LineaResponseDTO {

    private Long id;
    private String numero;
    private String nombre;
    private String color;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    //añadimos la información de las paradas
    private List<ParadaResumen> paradas = new ArrayList<>();

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ParadaResumen {
        private Long id;
        private String codigo;
        private String nombre;
        private Integer orden;
        private Boolean esParada;
        private Double latitud;
        private Double longitud;
    }
}
