package emt.directe.emt_en_directe.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lineas_paradas")
@Entity

public class LineaParada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "linea_id", nullable = false)
    private Linea linea;

    @ManyToOne
    @JoinColumn(name = "parada_id", nullable = false)
    private Parada parada;

    @Column(nullable = false)
    private Integer orden;

    //diferenciamos entre paradas y puntos para hacer el pooling y simular el movimiento del bus
    @Column(nullable = false)
    private Boolean esParada = false;
}