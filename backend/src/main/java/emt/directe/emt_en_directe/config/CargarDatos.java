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

            Linea linea1 = crearLinea("1", "Aeroport", "#0000FF");
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
        if (lineaRepository.findByNumero("5").isEmpty()) {
            System.out.println("cargando la línea 5...");

            Linea linea5 = crearLinea("5", "Es Rafal Nou - Pl. Progrés", "#FFFF00");
            int orden = 1;


            orden = agregarPunto(linea5, "P501", "es Rafal Nou", 39.58318127318386, 2.681728280558855, true, orden);
            orden = agregarPunto(linea5, "I501", "punto intermedio", 39.583274475221685, 2.682330808678146, false, orden);
            orden = agregarPunto(linea5, "I502", "punto intermedio", 39.5824604470886, 2.682561087864933, false, orden);
            orden = agregarPunto(linea5, "I503", "punto intermedio", 39.58258113209315, 2.683222756815302, false, orden);

            orden = agregarPunto(linea5, "P502", "Son Gibert", 39.58282553066309, 2.683965205030266, true, orden);
            orden = agregarPunto(linea5, "I504", "punto intermedio", 39.58312284644207, 2.6849119399861716, false, orden);
            orden = agregarPunto(linea5, "I505", "punto intermedio", 39.58362337058409, 2.68530656033863, false, orden);
            orden = agregarPunto(linea5, "I506", "punto intermedio", 39.584438748808644, 2.6849110270063443, false, orden);
            orden = agregarPunto(linea5, "I507", "punto intermedio", 39.58591211670801, 2.684342044507003, false, orden);

            orden = agregarPunto(linea5, "P503", "Baltasar Valentí", 39.58681417894544, 2.6841357308529066, true, orden);
            orden = agregarPunto(linea5, "I508", "punto intermedio", 39.58737155527385, 2.68391800555901, false, orden);
            orden = agregarPunto(linea5, "I509", "punto intermedio", 39.5881508733328, 2.683652461133645, false, orden);
            orden = agregarPunto(linea5, "I510", "punto intermedio", 39.58893013967543, 2.683397651278907, false, orden);
            orden = agregarPunto(linea5, "I511", "punto intermedio", 39.58950476546415, 2.6832125788500694, false, orden);

            orden = agregarPunto(linea5, "P504", "es Viver", 39.58950904405498, 2.682743740175598, true, orden);
            orden = agregarPunto(linea5, "I512", "punto intermedio", 39.58931365892445, 2.6821872432294844, false, orden);
            orden = agregarPunto(linea5, "I513", "punto intermedio", 39.58906197556966, 2.6815147198682205, false, orden);
            orden = agregarPunto(linea5, "I514", "punto intermedio", 39.588892976707534, 2.681012858032842, false, orden);
            orden = agregarPunto(linea5, "I515", "punto intermedio", 39.588671451398184, 2.6803797248183634, false, orden);


            orden = agregarPunto(linea5, "P505", "Mestre Chapi - camí Salard", 39.588606956554734, 2.6799794681885203, true, orden);
            orden = agregarPunto(linea5, "I516", "punto intermedio", 39.58825363589328, 2.6794700507054205, false, orden);
            orden = agregarPunto(linea5, "I517", "punto intermedio", 39.587843659016755, 2.678961592881993, false, orden);
            orden = agregarPunto(linea5, "I518", "punto intermedio", 39.587467456674695, 2.6785270750055914, false, orden);

            orden = agregarPunto(linea5, "P506", "Heura - Taronger", 39.58710778877722, 2.6780228197169103, true, orden);
            orden = agregarPunto(linea5, "I519", "punto intermedio", 39.586691036556026, 2.677604626624782, false, orden);
            orden = agregarPunto(linea5, "I520", "punto intermedio", 39.586176452195645, 2.6769807022477803, false, orden);
            orden = agregarPunto(linea5, "I521", "punto intermedio", 39.58563396032985, 2.6763164903115086, false, orden);
            orden = agregarPunto(linea5, "I522", "punto intermedio", 39.585266713706794, 2.6758569831871593, false, orden);


            orden = agregarPunto(linea5, "P507", "Heura - Àlber", 39.58489946513741, 2.6753804572804265, true, orden);
            orden = agregarPunto(linea5, "I523", "punto intermedio", 39.5844172322218, 2.6748350069160063, false, orden);
            orden = agregarPunto(linea5, "I524", "punto intermedio", 39.58387080462883, 2.674170518128063, false, orden);
            orden = agregarPunto(linea5, "I525", "punto intermedio", 39.58334440449983, 2.673537679654213, false, orden);


            agregarPunto(linea5, "P508", "es Rafal Vell", 39.58292641095817, 2.6734196159479064, true, orden);


            System.out.println("la línea 5 ya está cargada");
        } else {
            System.out.println("la línea 5 ya existe");
        }

        // línea 23
        if (lineaRepository.findByNumero("23").isEmpty()) {
            System.out.println("cargando la línea 23...");

            Linea linea23 = crearLinea("23", "Pl. Espanya", "#FF00FF");
            int orden = 1;


            orden = agregarPunto(linea23, "P2301", "pl. d'Espanya - Estació Intermodal", 39.57685953147904, 2.6541914967428886, true, orden);
            orden = agregarPunto(linea23, "I2301", "punto intermedio", 39.57640635654766, 2.6536832057311432, false, orden);
            orden = agregarPunto(linea23, "I2302", "punto intermedio", 39.57605258565685, 2.6535238906474, false, orden);

            orden = agregarPunto(linea23, "P2302", "pl. d'Espanya - Estació Intermodal", 39.575590634190824, 2.653876659782417, true, orden);
            orden = agregarPunto(linea23, "I2303", "punto intermedio", 39.57527486810927, 2.6545252997787663, false, orden);
            orden = agregarPunto(linea23, "I2304", "punto intermedio", 39.574897701202545, 2.655310495595417, false, orden);
            orden = agregarPunto(linea23, "I2305", "punto intermedio", 39.574318209105705, 2.6554891045389137, false, orden);
            orden = agregarPunto(linea23, "I2306", "punto intermedio", 39.573631798202705, 2.655623209651158, false, orden);
            orden = agregarPunto(linea23, "I2307", "punto intermedio", 39.572915256928546, 2.6564045219944137, false, orden);
            orden = agregarPunto(linea23, "I2308", "punto intermedio", 39.572433552754916, 2.6567360242842293, false, orden);


            orden = agregarPunto(linea23, "P2303", "Alexandre Rosselló - Àrea d'intercanvi Sindicat", 39.571824079982164, 2.6565837124141085, true, orden);
            orden = agregarPunto(linea23, "I2309", "punto intermedio", 39.57132337594441, 2.6566083510984857, false, orden);
            orden = agregarPunto(linea23, "I2310", "punto intermedio", 39.57052572694569, 2.6570618559758623, false, orden);
            orden = agregarPunto(linea23, "I2311", "punto intermedio", 39.56959676206364, 2.657028246952937, false, orden);
            orden = agregarPunto(linea23, "I2312", "punto intermedio", 39.56855304963134, 2.6568650031264434, false, orden);
            orden = agregarPunto(linea23, "I2313", "punto intermedio", 39.567663696855995, 2.6572308203518236, false, orden);
            orden = agregarPunto(linea23, "I2314", "punto intermedio", 39.566849037121266, 2.6567426371375333, false, orden);

            orden = agregarPunto(linea23, "P2304", "Porta des Camp", 39.566245275879524, 2.656485145068362, true, orden);
            orden = agregarPunto(linea23, "I2315", "punto intermedio", 39.56557534288503, 2.656485145068362, false, orden);
            orden = agregarPunto(linea23, "I2316", "punto intermedio", 39.5647234434463, 2.6564529585589995, false, orden);
            orden = agregarPunto(linea23, "I2317", "punto intermedio", 39.56434298010408, 2.657257621280642, false, orden);
            orden = agregarPunto(linea23, "I2318", "punto intermedio", 39.56398320500681, 2.658877653470779, false, orden);
            orden = agregarPunto(linea23, "I2319", "punto intermedio", 39.56325120486369, 2.660948364560205, false, orden);
            orden = agregarPunto(linea23, "I2320", "punto intermedio", 39.56277146774079, 2.662863496445631, false, orden);

            agregarPunto(linea23, "P2305", "Àrea d'intercanvi Palau de Congressos", 39.56265980709958, 2.664537194904087, true, orden);

            System.out.println("línea 23 cargada");
        } else {
            System.out.println("la línea 23 ya existe");
        }

        System.out.println("la carga de datos y la comprobación completadas");
    }

}