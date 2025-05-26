package org.example.discerial.Controladores;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoadingPanelController {

    @FXML private ProgressBar progressBar;
    @FXML private ImageView imagenCuriosa;
    @FXML private Label sabiasQueLabel;
    @FXML private Label curiosidadLabel;

    private int categoria_id;

    public void initData(int categoria_id) {
        this.categoria_id = categoria_id;
        mostrarImagenAleatoria();
        mostrarCuriosidadAleatoria();
        startLoadingProgress();
    }

    private void mostrarImagenAleatoria() {
        int index = (int) (Math.random() * 35) + 1; // 1 a 22
        String ruta = "/Images/LoadingGame/loadingimage" + index + ".jpg";

        try {
            Image img = new Image(getClass().getResource(ruta).toExternalForm());
            imagenCuriosa.setImage(img);

            // Tamaño fijo 1000 x 500
            imagenCuriosa.setFitWidth(1000);
            imagenCuriosa.setFitHeight(500);
            imagenCuriosa.setPreserveRatio(false);  // Que se ajuste exactamente a 1000x500
            imagenCuriosa.setSmooth(true);

            // Aplica el clip redondeado con las dimensiones correctas
            aplicarClipRedondeado(imagenCuriosa, 1000, 500, 40);  // Cambia el 40 por el redondeado que quieras

        } catch (Exception e) {
            System.err.println("Error cargando imagen: " + ruta);
            e.printStackTrace();
        }
    }


    private void aplicarClipRedondeado(ImageView imageView, double width, double height, double arc) {
        Rectangle clip = new Rectangle(width, height);
        clip.setArcWidth(arc);
        clip.setArcHeight(arc);
        imageView.setClip(clip);
    }


    private void mostrarCuriosidadAleatoria() {
        String[] curiosidades = {
                "Napoleón Bonaparte medía aproximadamente 1,69 metros, una estatura promedio para su época, y no 1,50 metros como a menudo se cree debido a confusiones con las unidades de medida francesas.",
                "Cleopatra, la famosa reina de Egipto, vivió en el siglo I a.C., mucho más cerca en el tiempo de la invención del iPhone que de la construcción de las pirámides de Giza, que datan de hace más de 4.500 años.",
                "La Universidad de Oxford, en Inglaterra, comenzó a impartir enseñanza formal en el siglo XII, lo que la convierte en más antigua que la civilización azteca, que floreció en el siglo XIV.",
                "Julio César fue capturado y retenido por piratas cuando era joven, y se dice que al ser liberado organizó una expedición para capturarlos y ajusticiarlos personalmente.",
                "La Torre Eiffel, debido a la expansión térmica del metal con el calor del verano, puede crecer hasta 15 centímetros de altura durante los meses cálidos.",
                "Los vikingos usaban la miel no solo como alimento, sino también como remedio antibiótico natural para tratar heridas y prevenir infecciones, aprovechando sus propiedades antimicrobianas.",
                "William Shakespeare es acreditado con la invención o popularización de más de 1.700 palabras en el idioma inglés, muchas de las cuales se usan comúnmente hoy en día.",
                "En el antiguo Egipto, los gatos no solo eran mascotas sino que eran considerados animales sagrados, asociados con la diosa Bastet, protectora del hogar y la fertilidad.",
                "Leonardo da Vinci tenía la habilidad de escribir con una mano mientras dibujaba con la otra, y a menudo escribía sus notas al revés para que solo fueran legibles con un espejo.",
                "Contrario a la creencia popular, Albert Einstein no reprobó su examen de ingreso a la universidad; de hecho, sus notas fueron sobresalientes, aunque sí tuvo dificultades en otras áreas durante su infancia.",
                "Los romanos usaban la orina humana como un agente de limpieza para blanquear la ropa y hasta como un ingrediente para ciertos productos dentales, aprovechando su contenido de amoníaco.",
                "Alejandro Magno, durante su breve pero intenso reinado, fundó más de 70 ciudades a lo largo de su vasto imperio que se extendía desde Grecia hasta la India.",
                "Nikola Tesla afirmaba dormir tan solo dos horas al día, dedicando el resto de su tiempo a la investigación y desarrollo de sus inventos revolucionarios.",
                "El calendario juliano, introducido por Julio César, tuvo que corregirse porque en un año el calendario se desfasaba aproximadamente 11 minutos con respecto al año solar, lo que llevó a un año extraordinario con 445 días en un momento para reajustarlo.",
                "Isaac Newton desarrolló el cálculo diferencial e integral prácticamente al mismo tiempo que Gottfried Wilhelm Leibniz, aunque ambos trabajaron de forma independiente.",
                "El ADN humano, si se desenrollara y se colocara en línea recta, mediría aproximadamente 2 metros de longitud dentro de cada célula.",
                "Las medusas han existido por más de 500 millones de años, lo que las convierte en uno de los organismos más antiguos que aún sobreviven en los océanos.",
                "La luz del Sol tarda alrededor de 8 minutos y 20 segundos en llegar a la Tierra, lo que significa que siempre estamos viendo al Sol con un ligero retraso.",
                "La Gran Muralla China no es una estructura única y continua, sino una serie de murallas y fortificaciones construidas por distintas dinastías a lo largo de más de 2.000 años.",
                "Marie Curie fue la primera persona en ganar dos premios Nobel en distintas disciplinas: Física y Química, por sus estudios sobre la radiactividad.",
                "Las células nerviosas en el cuerpo humano pueden transmitir señales eléctricas a velocidades de hasta 120 metros por segundo.",
                "El universo observable tiene un diámetro estimado de 93 mil millones de años luz, pero se cree que el universo completo es mucho mayor, o incluso infinito.",
                "El tardígrado, un organismo microscópico, puede sobrevivir en condiciones extremas que matarían a casi cualquier otra forma de vida, incluyendo el vacío del espacio exterior.",
                "La Revolución Industrial, que comenzó en el siglo XVIII, transformó no solo la economía sino también la sociedad, permitiendo el surgimiento de las ciudades modernas y el avance tecnológico acelerado.",
                "La teoría de la relatividad general de Einstein cambió completamente nuestra comprensión del espacio y el tiempo, mostrando que ambos son relativos y pueden curvarse debido a la gravedad.",
                "El chocolate fue utilizado por las civilizaciones precolombinas como moneda y bebida sagrada, y no como el dulce que conocemos hoy en día.",
                "Los pulmones humanos contienen más de 300 millones de alveolos, que permiten el intercambio de oxígeno y dióxido de carbono en la sangre.",
                "El Cinturón de Kuiper, una región más allá de Neptuno, está compuesto por miles de cuerpos helados y es la fuente de muchos cometas de periodo corto.",
                "La filosofía griega antigua no solo sentó las bases del pensamiento occidental, sino que también influyó en la ciencia, la política y la ética modernas.",
                "El microbioma humano, compuesto por billones de bacterias y microorganismos, es esencial para la digestión, la inmunidad y la salud general.",
                "Las auroras boreales y australes se producen cuando partículas cargadas del viento solar interactúan con el campo magnético de la Tierra y su atmósfera.",
                "Los dinosaurios dominaron la Tierra durante aproximadamente 165 millones de años, mucho más tiempo que la humanidad ha existido.",
                "La velocidad de rotación de la Tierra está disminuyendo lentamente, alargando los días a razón de aproximadamente 1.7 milisegundos por siglo.",
                "Los cristales de nieve tienen una estructura hexagonal única y no existen dos cristales exactamente iguales debido a las condiciones variables de formación.",
                "Los primeros telescopios fueron construidos en el siglo XVII y revolucionaron nuestra capacidad para observar el cosmos, sentando las bases de la astronomía moderna.",
                "Discerial te permite crear tus propias preguntas , lo que te permite personalizar la experiencia de aprendizaje de tus estudiantes.",
                "La astronomía moderna ha descubierto numerosos planetas extrasolar, incluyendo planetas rocosos y planetas helados, lo que expande nuestra comprensión de la Tierra y el universo.",
                "Comparte con tus amigos tu perfil de Discerial para comparar los resultados y mejorar tu aprendizaje.",
                "Las Spice Girls se llamaban originalmente “Touch”, pero cambiaron el nombre porque sentían que eran “sosas”." ,
                "El primer avión voló el 17 de diciembre de 1903, cuando los hermanos Wright realizaron breves vuelos en Kitty Hawk." ,
                "La tripofobia es la aversión o miedo a patrones irregulares de pequeños agujeros o protuberancias." ,
                "La nuez moscada puede ser alucinógena si se consume en grandes cantidades debido a la miristicina.",
                "El corazón de las gambas está en la cabeza y tienen un sistema circulatorio abierto.",
                "Una nube pesa alrededor de un millón de toneladas debido a la cantidad de agua que contiene.",
                "En Suiza es ilegal tener una sola cobaya, ya que son animales sociales que necesitan compañía.",
                "Entre 1912 y 1948, los Juegos Olímpicos otorgaban medallas en disciplinas como música, pintura, escultura y arquitectura.",
                "Los aguacates son una fruta, no una verdura, y técnicamente se consideran una baya de una sola semilla.",
                "Las duchas calientes aumentan el flujo de dopamina, lo que fomenta la creatividad.",
                "El unicornio es el animal nacional de Escocia, asociado con pureza e inocencia en la mitología celta.",
                "Venus es el único planeta que gira en el sentido de las agujas del reloj, completando una rotación en 243 días terrestres.",
                "Los antiguos romanos echaban pan tostado en el vino para desear buena salud, práctica que dio origen al brindis.",
                "Amy Poehler, de 32 años, interpretó a la madre de Regina George, interpretada por Rachel McAdams a sus 25, en *Chicas malas*.",
                "En 2018, una botella de vino Romanee-Conti de 1945 se vendió en Sotheby por 558.000 dólares, siendo la más cara jamás subastada.",
                "Australia es más ancha que la Luna. Su diámetro de este a oeste es de casi 4000 km, mientras que el de la Luna es de 3400 km.",
                "Los dientes humanos no pueden regenerarse porque están cubiertos de esmalte, que no es tejido vivo.",
                "“Melifluo” describe un sonido agradablemente suave y musical.",
                "Los conejos bebés se llaman gazapos.",
                "La Torre Eiffel puede ser 15 cm más alta en verano debido a la expansión térmica del hierro al calentarse.",
                "La probabilidad de encontrar una langosta azul es de una entre dos millones debido a una anomalía genética.",
                "Las cabezas de la Isla de Pascua tienen cuerpos. En la década de 2010, arqueólogos descubrieron torsos de hasta 10 metros.",
                "Las suelas rojas de los zapatos Louboutin se inspiraron en una obra de Andy Warhol llamada “Flowers”.",
                "El himno nacional español, “Marcha Real”, es uno de los pocos himnos en el mundo que no tiene letra.",
                "Walt Disney tiene el récord de premios Oscar, con 26 galardones y 59 nominaciones en total.",
                "Los actores de Mickey y Minnie Mouse, Russi Taylor y Wayne Allwine, estuvieron casados en la vida real.",
                "El gorro de cocinero tiene 100 pliegues, simbolizando las 100 maneras de cocinar un huevo.",
                "Google Images se creó tras el impacto del vestido de Jennifer López en los Grammy del año 2000.",
                "La Reina Isabel II sabía reparar motores y neumáticos, ya que tenía formación en mecánica desde los 16 años.",
                "El libro más largo del mundo es *A la recherche du temps perdu* de Marcel Proust, con 9.609.000 caracteres.",
                "“Kuchi zamishi” es una palabra japonesa que describe el acto de comer por aburrimiento o soledad.",
                "El polvo que vemos a contraluz está compuesto en un 90% por nuestras células muertas.",
                "Los átomos de nuestro cuerpo tienen más de 13.700 millones de años, ya que se formaron en el Big Bang.",
                "Se puede escuchar el latido de una ballena azul a más de 3 kilómetros de distancia debido a su enorme tamaño.",
                "Sólo hay una letra que no aparece en el nombre de ningún estado de EE.UU.: la “Q”.",
                "El reloj del Big Ben se detuvo el 27 de mayo de 2005 debido a una ola de calor de 31,8 grados centígrados.",
                "Los M&M llevan el nombre de sus creadores, Forrest Mars y Bruce Murrie, quienes no tenían buena relación.",
                "En 2014, hubo un ‘match’ de Tinder en la Antártida entre dos investigadores en zonas remotas.",
                "Las palomas pueden distinguir entre las obras de Picasso y Monet, según un estudio de 1995.",
                "Hay una fruta llamada zapote negro que sabe a ‘pudding’ de chocolate y natillas dulces.",
                "La distancia a pie más larga del mundo es de 22.531 kilómetros, desde Magadán (Rusia) hasta Ciudad del Cabo (Sudáfrica).",
                "Japón tiene más de 200 sabores de Kit-Kat, algunos regionales y otros únicos, como el de wasabi o el de melón y queso.",
                "Todos los relojes en la película ‘Pulp Fiction’ están configurados a las 4:20.",
                "Los flamencos no nacen rosas; su color rosado proviene de su dieta rica en carotenoides.",
                "El ketchup se vendía como medicina en 1834 para tratar la indigestión.",
                "Existe una palabra para quien opina sin saber: “ultracrepidario”.",
                "La Ciudad del Vaticano es el país más pequeño del mundo, 120 veces más pequeño que Manhattan.",
                "Un ‘jiffy’ es una unidad de tiempo real equivalente a una centésima de segundo.",
                "Kim Kardashian tiene una cláusula de “glamour” en su testamento para mantener su apariencia impecable.",
                "La última letra añadida al alfabeto inglés fue la “J”, en 1524.",
                "La Luna tiene terremotos lunares debido a las tensiones de las mareas entre la Tierra y la Luna.",
                "El logotipo de Chupa Chups fue diseñado por Salvador Dalí en 1969.",
                "Los flamencos doblan las piernas por el tobillo y no por la rodilla.",
                "Existen dos países donde no se puede comprar Coca-Cola: Cuba y Corea del Norte.",
                "Nadie puede suicidarse conteniendo el aliento.",
                "El ojo de un avestruz es más grande que su cerebro.",
                "La lengua humana se compone de 16 músculos individuales.",
                "El primer correo electrónico enviado fue “QWERTYUIOP”, la primera fila del teclado.",
                "Chanel realizó el anuncio más caro de la historia, protagonizado por Nicole Kidman, con un costo de 33 millones de dólares.",
                "Los humanos son los únicos animales que se ruborizan debido a la vergüenza.",
                "La isla Faisanes cambia de nacionalidad cada 6 meses entre España y Francia.",
                "El nombre de Google viene del término ‘googol’, que significa “uno en un millón”.",
                "Has heredado tu inteligencia, en gran medida, de tu madre. Se estima que el 80% de las capacidades cognitivas provienen de la herencia materna.",
                "La palabra cementerio proviene del griego ‘koimeterion’, que significa “dormitorio”.",
                "El sudor de los hipopótamos es rosa.",
                "Las mujeres parpadean el doble que los hombres.",
                "Un colectivo de consumidores demandó a Red Bull en 2013 porque “no daba alas”.",
                "En los hospitales de Japón no hay habitaciones con el número 4 ni con el número 9 porque se consideran números de la muerte.",
                "En Urano, las estaciones duran 21 años debido a su inclinación.",
                "McDonald’s no vendía hamburguesas en sus inicios. Comenzó vendiendo perritos calientes en 1937.",
                "El primer logo de Facebook era la cara de Al Pacino, cambiado en 2007.",
                "‘WeChat’ en China es más grande que Instagram, con unos 900 millones de usuarios.",
                "Facebook es azul porque el creador Mark Zuckerberg sufre de daltonismo.",
                "El nombre de Yahoo es un acrónimo que significa ‘Yet Another Hierarchical Officious Oracle’.",
                "Gucci tiene un récord Guinness por vender los vaqueros más caros, con un precio de 3.314 dólares.",
                "De los 206 huesos de un humano adulto, 52 se encuentran en los pies.",
                "El libro más largo del mundo, ‘A la recherche du temps perdu’, tiene 9.609.000 caracteres.",
                "El cuerpo humano contiene alrededor de 16.000 toneladas de potasio al año.",
                "WhatsApp solo tiene 55 trabajadores en su equipo.",
                "El café puede matar, pero sólo si tomas unas 100 tazas en un día.",
                "El ataque de hipo más largo duró 68 años. Lo sufrió Charles Osborne tras un accidente en el campo.",
                "Besar ayuda a reducir el estrés. Lo suponías, pero ahora la ciencia lo confirma.",
                "El primer móvil del mundo, el Motorola DynaTAC 8000X, pesaba 800 gramos.",
                "Darle clic 10 millones de veces al ratón del ordenador te hace perder 1 caloría.",
                "China es el país que utiliza más Internet, con 721 millones de usuarios.",
                "Thomas Alva Edison le tenía miedo a la oscuridad. ¡Por eso creó la bombilla!",
                "El cocodrilo no puede sacar la lengua, ya que está unida a su paladar con una membrana.",
                "En su vida, una abeja obrera puede recorrer un total de 40 km.",
                "La palabra cementerio proviene del griego y significa “dormitorio”.",
                "El nombre completo de Barbie es Barbara Millicent Roberts, en homenaje a la hija de su creadora.",
                "La etapa del enamoramiento solo dura 3 meses; después, el sentimiento se transforma.",
                "Una gota de petróleo puede contaminar 25 litros de agua.",
                "En Ohio, Estados Unidos, está prohibido cazar ratones.",
                "Inspiramos y espiramos entre 5 y 6 litros de aire por minuto.",
                "Para hacer un kilo de miel, las abejas deben recorrer 1.440.000 flores.",
                "La dirección de tu casa es única en el mundo. ¿Te habías parado a pensarlo?",
                "La guerra de los 100 años duró 116 años, desde 1337 hasta 1453.",
                "Anthony Hopkins nunca parpadea en toda la película de ‘El silencio de los corderos’.",
                "HTC fue el primer creador de aplicaciones para móviles.",
                "Las hormigas nunca duermen, ya que viven bajo tierra y no tienen ciclos de día y noche.",





        };

        String curiosidad = curiosidades[(int)(Math.random() * curiosidades.length)];
        curiosidadLabel.setText(curiosidad);
        sabiasQueLabel.setText("¿Sabías que...?");
    }


    private void startLoadingProgress() {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        final double[] progress = {0};

        KeyFrame frame = new KeyFrame(Duration.millis(200), event -> {
            double incremento = Math.random() * 0.05 + 0.01;
            progress[0] += incremento;

            if (progress[0] >= 1.0) {
                progressBar.setProgress(1.0);
                timeline.stop();
                openGame();
            } else {
                progressBar.setProgress(progress[0]);
            }
        });

        timeline.getKeyFrames().add(frame);
        timeline.play();
    }

    private void openGame() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/discerial/VistaGameController.fxml"));
            Parent root = loader.load();

            VistaPreguntaController controller = loader.getController();
            controller.initData(categoria_id);

            Stage stage = (Stage) progressBar.getScene().getWindow();
            stage.getScene().setRoot(root);
            stage.setTitle("Categoría " + categoria_id);
        } catch (Exception e) {
            System.err.println("Error al cargar VistaGameController:");
            e.printStackTrace();
        }
    }

}
