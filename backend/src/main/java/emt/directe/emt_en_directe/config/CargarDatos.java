package emt.directe.emt_en_directe.config;

import emt.directe.emt_en_directe.model.Linea;
import emt.directe.emt_en_directe.model.LineaParada;
import emt.directe.emt_en_directe.model.Parada;
import emt.directe.emt_en_directe.repository.LineaParadaRepository;
import emt.directe.emt_en_directe.repository.LineaRepository;
import emt.directe.emt_en_directe.repository.ParadaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CargarDatos implements CommandLineRunner {

    private final LineaRepository lineaRepository;
    private final ParadaRepository paradaRepository;
    private final LineaParadaRepository lineaParadaRepository;

    //creamos la línea
    private Linea crearLinea(String numero, String nombre, String color) {
        Linea linea = new Linea();
        linea.setNumero(numero);
        linea.setNombre(nombre);
        linea.setColor(color);

        return lineaRepository.save(linea);
    }

    //creamos los puntos y los asociamos a cada línea
    private int agregarPunto(Linea linea, String codigo, String nombre, double latitud, double longitud, boolean esParada, int orden) {
        Parada parada = new Parada();
        parada.setCodigo(codigo);
        parada.setNombre(nombre);
        parada.setLatitud(latitud);
        parada.setLongitud(longitud);

        paradaRepository.save(parada);

        LineaParada lineaParada = new LineaParada();
        lineaParada.setLinea(linea);
        lineaParada.setParada(parada);
        lineaParada.setOrden(orden);
        lineaParada.setEsParada(esParada);

        lineaParadaRepository.save(lineaParada);

        return orden + 1; //se crea el orden
    }

    //este método se ejecutará al iniciar springBoot
    public void run(String... args) throws Exception {

        if (lineaRepository.count() > 0) {
            System.out.println("datos cargados.");
            return;
        }

        //línea 35
        Linea linea35 = crearLinea("35", "Aquarium - Can Pastilla", "#FF0000");
        int orden = 1;

        orden = agregarPunto(linea35, "P001", "Aquarium",                   39.531322838820735, 2.729162786755216, true,  orden);
        orden = agregarPunto(linea35, "I001", "punto intermedio",           39.532123570927865, 2.727730889777552, false, orden);
        orden = agregarPunto(linea35, "I002", "punto intermedio",           39.533699785231450, 2.726223045908703, false, orden);
        orden = agregarPunto(linea35, "I003", "punto intermedio",           39.534927465953570, 2.724305411002386, false, orden);
        orden = agregarPunto(linea35, "I004", "punto intermedio",           39.535572898701550, 2.722674616054813, false, orden);

        orden = agregarPunto(linea35, "P002", "Can Pastilla - Racó de Can Ripoll", 39.536451220961474, 2.719956183937689, true, orden);
        orden = agregarPunto(linea35, "I005", "punto intermedio",           39.537031842081390, 2.719146759716450, false, orden);
        orden = agregarPunto(linea35, "I006", "punto intermedio",           39.537777941844350, 2.717906054825109, false, orden);
        orden = agregarPunto(linea35, "I007", "punto intermedio",           39.538029812443720, 2.716305810742012, false, orden);

        orden = agregarPunto(linea35, "P003", "Camí de Can Pastilla",       39.538408810628660, 2.714728130568537, true,  orden);
        orden = agregarPunto(linea35, "I008", "punto intermedio",           39.538842667660750, 2.714186970991560, false, orden);
        orden = agregarPunto(linea35, "I009", "punto intermedio",           39.539392906906950, 2.713473303431435, false, orden);
        orden = agregarPunto(linea35, "I010", "punto intermedio",           39.539957682570890, 2.712725140618029, false, orden);

        agregarPunto(linea35,         "P004", "es Carnatge",                   39.541039247284765, 2.711252773367413, true,  orden);

        System.out.println(" datos de la línea 35 cargados");
    }

}