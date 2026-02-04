package emt.directe.emt_en_directe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class LineaParadaDTO {

    private Long id;
    private Long lineaId;
    private Long paradaId;
    private String lineaNumero;
    private String lineaNombre;
    private String paradaNombre;
    private String paradaCodigo;
    private Integer orden;
}