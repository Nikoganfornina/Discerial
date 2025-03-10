package org.example.discerial.Controladores.Preguntas;

import org.example.discerial.DAO.IPregunta;
import org.example.discerial.DAO.IPreguntaImpl;
import org.example.discerial.entities.Pregunta;
import org.example.discerial.entities.Categoria;

import java.util.List;

public class PreguntasFilosofia {

    public static void crearPreguntasFilosofia() {
        IPregunta preguntaDao = new IPreguntaImpl();

        // Verificamos si ya existen preguntas para la categoría "Filosofía"
        List<Pregunta> preguntasFilosofia = preguntaDao.findByCategoria(1); // Asegúrate de usar el ID correcto
        if (!preguntasFilosofia.isEmpty()) {
            System.out.println("Ya existen preguntas para la categoría 'Filosofía'.");
            return;
        }

        // Crear preguntas de filosofía (total de 50 preguntas)
        String[][] preguntas = {
                {"¿Quién es considerado el padre de la filosofía occidental?", "https://es.wikipedia.org/wiki/S%C3%B3crates", "Sócrates", "Platón", "Aristóteles", "Diógenes", "Sócrates", "multiple"},
                {"¿Qué filósofo es conocido por su teoría del 'Eternalismo'?", "https://es.wikipedia.org/wiki/Mart%C3%ADn_Heidegger", "Heidegger", "Nietzsche", "Hegel", "Descartes", "Heidegger", "multiple"},
                {"¿Qué filósofo decía 'Pienso, luego existo'?", "https://es.wikipedia.org/wiki/Ren%C3%A9_Descartes", "René Descartes", "Santo Tomás de Aquino", "Jean-Paul Sartre", "Emmanuel Kant", "René Descartes", "multiple"},
                {"¿Cuál es el principio fundamental del empirismo?", "https://es.wikipedia.org/wiki/Empirismo", "La experiencia es la fuente de todo conocimiento.", "La razón es la única fuente de conocimiento.", "El conocimiento proviene de la intuición.", "El conocimiento es innato.", "La experiencia es la fuente de todo conocimiento.", "multiple"},
                {"La ética kantiana afirma que la moralidad está basada en la razón y la autonomía.", "https://es.wikipedia.org/wiki/%C3%89tica_de_Kant", "Verdadero", "Falso", "Verdadero", "Falso", "Verdadero", "true"},
                {"¿Quién desarrolló la teoría del contrato social?", "https://es.wikipedia.org/wiki/Jean-Jacques_Rousseau", "Jean-Jacques Rousseau", "John Locke", "Karl Marx", "Thomas Hobbes", "Jean-Jacques Rousseau", "multiple"},
                {"¿Qué filósofo propuso la teoría de la voluntad de poder?", "https://es.wikipedia.org/wiki/Friedrich_Nietzsche", "Nietzsche", "Schopenhauer", "Fichte", "Kant", "Nietzsche", "multiple"},
                {"¿Qué pensador es conocido por su concepto de 'el superhombre'?", "https://es.wikipedia.org/wiki/Superhombre", "Nietzsche", "Sartre", "Hegel", "Foucault", "Nietzsche", "multiple"},
                {"¿Cuál es la obra más famosa de Platón?", "https://es.wikipedia.org/wiki/La_rep%C3%BAblica_(di%C3%A1logo)", "La República", "Fedón", "La Apología de Sócrates", "Timeo", "La República", "multiple"},
                {"¿Qué filósofo es conocido por su teoría del 'sujeto trascendental'?", "https://es.wikipedia.org/wiki/Immanuel_Kant", "Immanuel Kant", "René Descartes", "John Locke", "David Hume", "Immanuel Kant", "multiple"},
                {"¿Quién fue el maestro de Aristóteles?", "https://es.wikipedia.org/wiki/Plat%C3%B3n", "Platón", "Sócrates", "Heráclito", "Pitágoras", "Platón", "multiple"},
                {"Según Descartes, ¿qué es lo único que no se puede dudar?", "https://es.wikipedia.org/wiki/Ren%C3%A9_Descartes", "La existencia del yo", "La realidad material", "Las matemáticas", "El mundo externo", "La existencia del yo", "multiple"},
                {"¿Qué filósofo desarrolló el concepto de 'la voluntad de vivir'?", "https://es.wikipedia.org/wiki/Arthur_Schopenhauer", "Arthur Schopenhauer", "Friedrich Nietzsche", "Karl Marx", "Jean-Paul Sartre", "Arthur Schopenhauer", "multiple"},
                {"¿Qué pensador es conocido por sus contribuciones al concepto de 'existencialismo'?", "https://es.wikipedia.org/wiki/Existencialismo", "Jean-Paul Sartre", "Simone de Beauvoir", "Karl Marx", "Martin Heidegger", "Jean-Paul Sartre", "multiple"},
                {"¿Qué filósofo dijo: 'El hombre está condenado a ser libre'?", "https://es.wikipedia.org/wiki/Jean-Paul_Sartre", "Jean-Paul Sartre", "Simone de Beauvoir", "Friedrich Nietzsche", "Heidegger", "Jean-Paul Sartre", "multiple"},
                {"¿Quién es el autor de la obra 'Así habló Zaratustra'?", "https://es.wikipedia.org/wiki/Así_habló_Zaratustra", "Friedrich Nietzsche", "Heidegger", "Karl Marx", "Jean-Paul Sartre", "Friedrich Nietzsche", "multiple"},
                {"¿Cuál es el concepto central en la filosofía de la Escuela de Frankfurt?", "https://es.wikipedia.org/wiki/Escuela_de_Frankfurt", "Teoría crítica", "Empirismo lógico", "Fenomenología", "Cognición social", "Teoría crítica", "multiple"},
                {"¿Qué filósofo se centró en la teoría del 'conocimiento a priori'?", "https://es.wikipedia.org/wiki/Immanuel_Kant", "Immanuel Kant", "René Descartes", "David Hume", "Ludwig Wittgenstein", "Immanuel Kant", "multiple"},
                {"¿Quién fue el creador del principio de la 'razón suficiente'?", "https://es.wikipedia.org/wiki/Gottfried_Wilhelm_Leibniz", "Leibniz", "Hegel", "Fichte", "Kant", "Leibniz", "multiple"},
                {"Según Platón, ¿cuál es la forma más perfecta de gobierno?", "https://es.wikipedia.org/wiki/La_república_(diálogo)", "La aristocracia de los filósofos", "La democracia", "La oligarquía", "La tiranía", "La aristocracia de los filósofos", "multiple"},
                {"¿Qué filósofo dijo 'La vida sin reflexión no merece la pena ser vivida'?", "https://es.wikipedia.org/wiki/S%C3%B3crates", "Sócrates", "Platón", "Aristóteles", "Séneca", "Sócrates", "multiple"},
                {"¿Quién fue el principal filósofo del empirismo británico?", "https://es.wikipedia.org/wiki/John_Locke", "John Locke", "David Hume", "George Berkeley", "Thomas Hobbes", "John Locke", "multiple"},
                {"¿Qué filósofo se ocupó del estudio de la fenomenología?", "https://es.wikipedia.org/wiki/Edmund_Husserl", "Edmund Husserl", "Martin Heidegger", "Immanuel Kant", "Jean-Paul Sartre", "Edmund Husserl", "multiple"},
                {"¿Cuál es la teoría central de la fenomenología?", "https://es.wikipedia.org/wiki/Fenomenología", "El estudio de la experiencia tal como se presenta en la conciencia", "La interpretación del sentido de la vida", "El estudio de la moralidad humana", "La teoría de la evolución", "El estudio de la experiencia tal como se presenta en la conciencia", "multiple"},
                {"¿Qué filósofo es conocido por su concepto de 'la voluntad de poder'?", "https://es.wikipedia.org/wiki/Friedrich_Nietzsche", "Friedrich Nietzsche", "Hegel", "Aristóteles", "Platón", "Friedrich Nietzsche", "multiple"},
                {"¿Qué filósofo fue el creador de la idea de 'la razón instrumental'?", "https://es.wikipedia.org/wiki/Max_Weber", "Max Weber", "Friedrich Nietzsche", "Emmanuel Kant", "Martin Heidegger", "Max Weber", "multiple"},
                {"¿Qué filósofo fue crítico de la sociedad industrial y defendió la teoría de la alienación?", "https://es.wikipedia.org/wiki/Karl_Marx", "Karl Marx", "Friedrich Engels", "Jean-Paul Sartre", "Emmanuel Kant", "Karl Marx", "multiple"},
                {"¿Quién propuso la teoría del 'método científico'?", "https://es.wikipedia.org/wiki/Francis_Bacon", "Francis Bacon", "Descartes", "Galileo Galilei", "Isaac Newton", "Francis Bacon", "multiple"},
                {"¿Cuál es la obra más conocida de René Descartes?", "https://es.wikipedia.org/wiki/Discurso_del_metodo", "Discurso del método", "Meditaciones Metafísicas", "Principios de la Filosofía", "La ética demostrada según el orden geométrico", "Discurso del método", "multiple"},
                {"¿Qué filósofo considera que la realidad es un conjunto de esencias a priori?", "https://es.wikipedia.org/wiki/Immanuel_Kant", "Immanuel Kant", "David Hume", "René Descartes", "Martin Heidegger", "Immanuel Kant", "multiple"},
                {"¿Qué filósofo es conocido por su teoría de los 'mundos posibles'?", "https://es.wikipedia.org/wiki/Gottfried_Wilhelm_Leibniz", "Gottfried Wilhelm Leibniz", "Immanuel Kant", "Friedrich Nietzsche", "Arthur Schopenhauer", "Gottfried Wilhelm Leibniz", "multiple"},
                {"¿Qué pensador desarrolló la teoría de la justicia basada en la 'estructura básica' de la sociedad?", "https://es.wikipedia.org/wiki/John_Rawls", "John Rawls", "Karl Marx", "Jean-Jacques Rousseau", "Immanuel Kant", "John Rawls", "multiple"},
                // Añadir más preguntas hasta completar 50
        };

        // Insertamos las preguntas en la base de datos
        for (String[] datos : preguntas) {
            Pregunta pregunta = new Pregunta();
            pregunta.setCategoria(new Categoria(1 ,"Filosofía"));
            pregunta.setPregunta(datos[0]);
            pregunta.setImagen(datos[1]);  // Agregar URL de referencia
            pregunta.setRespuestaCorrecta(datos[5]);
            pregunta.setRespuesta2(datos[2]);
            pregunta.setRespuesta3(datos[3]);
            pregunta.setRespuesta4(datos[4]);
            pregunta.setTipo(datos[6]);  // Agregar tipo de pregunta
            preguntaDao.save(pregunta);
        }

        System.out.println("Preguntas de filosofía añadidas con éxito.");
    }
}
