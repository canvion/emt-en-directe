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

        //línea 35
        if (lineaRepository.findByNumero("35").isEmpty()) {
            System.out.println("cargando línea 35...");

            Linea linea35 = crearLinea("35", "Aquarium - Can Pastilla", "#FF0000");
            int orden = 1;

            orden = agregarPunto(linea35, "P001", "Aquarium", 39.531322838820735, 2.729162786755216, true, orden);
            orden = agregarPunto(linea35, "I001", "punto intermedio", 39.532123570927865, 2.727730889777552, false, orden);
            orden = agregarPunto(linea35, "I002", "punto intermedio", 39.533699785231450, 2.726223045908703, false, orden);
            orden = agregarPunto(linea35, "I003", "punto intermedio", 39.534927465953570, 2.724305411002386, false, orden);
            orden = agregarPunto(linea35, "I004", "punto intermedio", 39.535572898701550, 2.722674616054813, false, orden);

            orden = agregarPunto(linea35, "P002", "Can Pastilla - Racó de Can Ripoll", 39.536451220961474, 2.719956183937689, true, orden);
            orden = agregarPunto(linea35, "I005", "punto intermedio", 39.537031842081390, 2.719146759716450, false, orden);
            orden = agregarPunto(linea35, "I006", "punto intermedio", 39.537777941844350, 2.717906054825109, false, orden);
            orden = agregarPunto(linea35, "I007", "punto intermedio", 39.538029812443720, 2.716305810742012, false, orden);

            orden = agregarPunto(linea35, "P003", "Camí de Can Pastilla", 39.538408810628660, 2.714728130568537, true, orden);
            orden = agregarPunto(linea35, "I008", "punto intermedio", 39.538842667660750, 2.714186970991560, false, orden);
            orden = agregarPunto(linea35, "I009", "punto intermedio", 39.539392906906950, 2.713473303431435, false, orden);
            orden = agregarPunto(linea35, "I010", "punto intermedio", 39.539957682570890, 2.712725140618029, false, orden);

            agregarPunto(linea35, "P004", "es Carnatge", 39.541039247284765, 2.711252773367413, true, orden);

            System.out.println("línea 35 cargada");
        } else {
            System.out.println("línea 35 ya existe");
        }

        // línea 1
        if (lineaRepository.findByNumero("1").isEmpty()) {
            System.out.println("cargando línea 1");

            Linea linea1 = crearLinea("1", "Aeropot", "#0000FF");
            int orden = 1;

            orden = agregarPunto(linea1, "P101", "Aeroport", 39.54690559350617, 2.7293653266128692, true, orden);
            orden = agregarPunto(linea1, "I101", "punto intermedio", 39.548385472554635, 2.7279733036721505, false, orden);
            orden = agregarPunto(linea1, "I102", "punto intermedio", 39.54612227694089, 2.7236286735311976, false, orden);
            orden = agregarPunto(linea1, "I103", "punto intermedio", 39.543974270200636, 2.7184783964387695, false, orden);
            orden = agregarPunto(linea1, "I104", "punto intermedio", 39.54253346079138, 2.712452003154251, false, orden);
            orden = agregarPunto(linea1, "I105", "punto intermedio", 39.543188824753216, 2.7092478825136164, false, orden);
            orden = agregarPunto(linea1, "I106", "punto intermedio", 39.5444886072877, 2.706625456437472, false, orden);
            orden = agregarPunto(linea1, "I107", "punto intermedio", 39.546514376417825, 2.7046307313768336, false, orden);
            orden = agregarPunto(linea1, "I108", "punto intermedio", 39.5490397710924, 2.7034562348833266, false, orden);
            orden = agregarPunto(linea1, "I109", "punto intermedio", 39.55212473799681, 2.7037994628401094, false, orden);
            orden = agregarPunto(linea1, "I110", "punto intermedio", 39.55470425036688, 2.7033123430786197, false, orden);
            orden = agregarPunto(linea1, "I111", "punto intermedio", 39.55433335702659, 2.7040717743905383, false, orden);
            orden = agregarPunto(linea1, "I112", "punto intermedio", 39.55302055573841, 2.7021899513174517, false, orden);


            orden = agregarPunto(linea1, "P102", "Darwin - Cardenal Rossell", 39.552291273407235, 2.7024173949165835, true, orden);
            orden = agregarPunto(linea1, "I113", "punto intermedio", 39.55140740128715, 2.7024277221662225, false, orden);
            orden = agregarPunto(linea1, "I114", "punto intermedio", 39.55007394106356, 2.70214576766215, false, orden);
            orden = agregarPunto(linea1, "I115", "punto intermedio", 39.54889902846658, 2.7018823848594575, false, orden);

            orden = agregarPunto(linea1, "P103", "Sant Joan de Déu", 39.54922583283524, 2.7009476050913768, true, orden);
            orden = agregarPunto(linea1, "I116", "punto intermedio", 39.54981362132342, 2.7000759955064506, false, orden);
            orden = agregarPunto(linea1, "I117", "punto intermedio", 39.55085837283258, 2.698318557202304, false, orden);
            orden = agregarPunto(linea1, "I118", "punto intermedio", 39.55132290586955, 2.6972379379636178, false, orden);

            orden = agregarPunto(linea1, "P104", "es Coll d'en Rabassa", 39.551499494270466, 2.69657512133315, true, orden);
            orden = agregarPunto(linea1, "I119", "punto intermedio", 39.552135251970846, 2.695247796751527, false, orden);

            orden = agregarPunto(linea1, "P105", "Cardenal Rossell - Torre d'en Pau", 39.55316290778004, 2.6942161683127543, true, orden);
            orden = agregarPunto(linea1, "I120", "punto intermedio", 39.55372948458747, 2.6935860937455, false, orden);
            orden = agregarPunto(linea1, "I121", "punto intermedio", 39.55449236451794, 2.6929137823714853, false, orden);

            orden = agregarPunto(linea1, "P106", "Ciutat Jardí", 39.55548936629159, 2.692025051179431, true, orden);
            orden = agregarPunto(linea1, "I122", "punto intermedio", 39.555859538222414, 2.691495314899236, false, orden);
            orden = agregarPunto(linea1, "I123", "punto intermedio", 39.556331253343444, 2.690793074723885, false, orden);
            orden = agregarPunto(linea1, "I124", "punto intermedio", 39.55662179705512, 2.689787403438453, false, orden);
            orden = agregarPunto(linea1, "I125", "punto intermedio", 39.55684166059112, 2.688779055353653, false, orden);
            orden = agregarPunto(linea1, "I126", "punto intermedio", 39.55716429998429, 2.687904699661632, false, orden);
            orden = agregarPunto(linea1, "I127", "punto intermedio", 39.55705794278646, 2.6869454068875753, false, orden);

            agregarPunto(linea1, "P007", "la Gruta", 39.5571716804016, 2.6863633675099265, true, orden);

            System.out.println("línea 1 cargada");
        } else {
            System.out.println("⏭línea 1 ya existe");
        }

        //línea 33
        if (lineaRepository.findByNumero("33").isEmpty()) {
            System.out.println("cargando la línea 33...");

            Linea linea33 = crearLinea("33", "Son Espases", "#00FF00");
            int orden = 1;

            orden = agregarPunto(linea33, "P301", "Son Espases", 39.60696782281064, 2.643931529710883, true, orden);
            orden = agregarPunto(linea33, "I301", "punto intermedio", 39.606130939807336, 2.644576881398417, false, orden);
            orden = agregarPunto(linea33, "I302", "punto intermedio", 39.60538238789838, 2.6451723909494054, false, orden);
            orden = agregarPunto(linea33, "I303", "punto intermedio", 39.60474618599738, 2.6455142601225488, false, orden);
            orden = agregarPunto(linea33, "I304", "punto intermedio", 39.60526049364281, 2.6465580562373505, false, orden);
            orden = agregarPunto(linea33, "I305", "punto intermedio", 39.605114773633154, 2.647134800885628, false, orden);
            orden = agregarPunto(linea33, "I306", "punto intermedio", 39.60459242074733, 2.646620844316936, false, orden);
            orden = agregarPunto(linea33, "I307", "punto intermedio", 39.603943637156014, 2.6459065506085393, false, orden);
            orden = agregarPunto(linea33, "I308", "punto intermedio", 39.60325168633949, 2.6448532359035983, false, orden);
            orden = agregarPunto(linea33, "I309", "punto intermedio", 39.602128161908624, 2.6440844029552246, false, orden);
            orden = agregarPunto(linea33, "I310", "punto intermedio", 39.60087723041247, 2.643164473619568, false, orden);
            orden = agregarPunto(linea33, "I311", "punto intermedio", 39.60036596578006, 2.6427220986666904, false, orden);
            orden = agregarPunto(linea33, "I312", "punto intermedio", 39.5997375768865, 2.6421442995546007, false, orden);


            orden = agregarPunto(linea33, "P302", "Hospital Palmaplanas", 39.59888889238742, 2.6411726424771738, true, orden);
            orden = agregarPunto(linea33, "I313", "punto intermedio", 39.59859065181924, 2.640678942626729, false, orden);
            orden = agregarPunto(linea33, "I314", "punto intermedio", 39.59796563100309, 2.6395287093254085, false, orden);
            orden = agregarPunto(linea33, "I315", "punto intermedio", 39.597742422907274, 2.640100019858806, false, orden);

            orden = agregarPunto(linea33, "P303", "Hospital Palmaplanas", 39.59737454133695, 2.6405211266854933, true, orden);
            orden = agregarPunto(linea33, "I316", "punto intermedio", 39.596929061668135, 2.6409484785096695, false, orden);
            orden = agregarPunto(linea33, "I317", "punto intermedio", 39.596230258189344, 2.641620914102684, false, orden);
            orden = agregarPunto(linea33, "I318", "punto intermedio", 39.595476396127914, 2.6422807419829564, false, orden);
            orden = agregarPunto(linea33, "I320", "punto intermedio", 39.59475770216876, 2.6427899210860653, false, orden);



            orden = agregarPunto(linea33, "P304", "General Riera - Centre Comercial", 39.5937555707261, 2.6433245965373056, true, orden);
            orden = agregarPunto(linea33, "I321", "punto intermedio", 39.59318504520609, 2.6437032432364607, false, orden);
            orden = agregarPunto(linea33, "I322", "punto intermedio", 39.59252070491648, 2.6441066365058083, false, orden);


            orden = agregarPunto(linea33, "P305", "General Riera - Consell de Mallorca", 39.59203932022725, 2.6443066643054824, true, orden);
            orden = agregarPunto(linea33, "I323", "punto intermedio", 39.59139981447096, 2.64470005281502, false, orden);
            orden = agregarPunto(linea33, "I324", "punto intermedio", 39.59052655137269, 2.645321773023018, false, orden);

            orden = agregarPunto(linea33, "P306", "General Riera - es Camp Redó", 39.59011826303975, 2.645514341087324, true, orden);
            orden = agregarPunto(linea33, "I325", "punto intermedio", 39.5892700713994, 2.645682010827466, false, orden);
            orden = agregarPunto(linea33, "I326", "punto intermedio", 39.58862436459531, 2.645687204064083, false, orden);

            agregarPunto(linea33, "P307", "General Riera - Velodrom", 39.58803644020065, 2.6456898604027805, true, orden);



            System.out.println("línea 33 cargada");
        } else {
            System.out.println("la línea 33 ya existe");
        }

        //línea 30
        if (lineaRepository.findByNumero("30").isEmpty()) {
            System.out.println("cargando la línea 30...");

            Linea linea30 = crearLinea("30", "Marivent-Palau de Congressos", "#FFFF00");
            int orden = 1;





            System.out.println("la línea 30 ya está cargada");
        } else {
            System.out.println("la línea 30 ya existe");
        }

        // línea 23
        if (lineaRepository.findByNumero("23").isEmpty()) {
            System.out.println("cargando la línea 23...");

            Linea linea23 = crearLinea("23", "S'Arenal - Pl. Espanya", "#FF00FF");
            int orden = 1;




            System.out.println("línea 23 cargada");
        } else {
            System.out.println("la línea 23 ya existe");
        }

        System.out.println("la carga de datos y la comprobación completadas");
    }

}