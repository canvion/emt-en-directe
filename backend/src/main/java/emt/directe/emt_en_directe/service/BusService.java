package emt.directe.emt_en_directe.service;

import emt.directe.emt_en_directe.dto.BusResponseDTO;
import emt.directe.emt_en_directe.model.Bus;
import emt.directe.emt_en_directe.model.Linea;
import emt.directe.emt_en_directe.model.LineaParada;
import emt.directe.emt_en_directe.repository.BusRepository;
import emt.directe.emt_en_directe.repository.LineaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class BusService {

    private final BusRepository busRepository;
    private final LineaRepository lineaRepository;

    private List<LineaParada> obtenerTodosLosPuntos(Linea linea) {
        return linea.getLineasParadas().stream()
                .sorted((a, b) -> a.getOrden().compareTo(b.getOrden()))
                .collect(Collectors.toList());
    }

    //creamos el bus en el primer punto de cada línea
    private BusResponseDTO crearBusEnPrimerPunto(Linea linea, List<LineaParada> puntos) {
        Bus bus = new Bus();
        bus.setLinea(linea);
        bus.setIndicePunto(0);
        bus.setLatitud(puntos.get(0).getParada().getLatitud());
        bus.setLongitud(puntos.get(0).getParada().getLongitud());

        busRepository.save(bus);

        return convertirADTO(bus);
    }

    // Mover el bus al siguiente punto del recorrido
    private BusResponseDTO moverBusAlSiguientePunto(Bus bus, List<LineaParada> puntos) {

        //simulamos un posible "atasco del bus" con una probabilidad del 15% de que no se mueva el marcadorr.
        double probabilidad = Math.random();
        if (probabilidad < 0.15) {
            return convertirADTO(bus);
        }

        //cuando el resto sea 0 vuelve a empezar
        int siguientePunto = (bus.getIndicePunto() + 1) % puntos.size();

        bus.setIndicePunto(siguientePunto);
        bus.setLatitud(puntos.get(siguientePunto).getParada().getLatitud());
        bus.setLongitud(puntos.get(siguientePunto).getParada().getLongitud());

        busRepository.save(bus);

        return convertirADTO(bus);
    }

    private BusResponseDTO convertirADTO(Bus bus) {
        return new BusResponseDTO(
                bus.getId(),
                bus.getLinea().getId(),
                bus.getLinea().getNumero(),
                bus.getLinea().getNombre(),
                bus.getLinea().getColor(),
                bus.getLatitud(),
                bus.getLongitud(),
                bus.getUpdatedAt()
        );
    }

    //creamos este método para que el frontend sepa donde esta el bus en cada pooling
    public BusResponseDTO obtenerPosicionBus(Long lineaId) {
        Linea linea = lineaRepository.findById(lineaId).orElseThrow();
        List<LineaParada> puntos = obtenerTodosLosPuntos(linea);
        List<Bus> buses = busRepository.findByLineaId(lineaId);

        if (buses.isEmpty()) {
            return crearBusEnPrimerPunto(linea, puntos);
        }

        return moverBusAlSiguientePunto(buses.get(0), puntos);
    }
}