package org.example.discerial.Controladores;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
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
        int index = (int) (Math.random() * 15) + 1; // 1 a 15
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
                "Comparte con tus amigos tu perfil de Discerial para comparar los resultados y mejorar tu aprendizaje."

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

            GameController controller = loader.getController();
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
