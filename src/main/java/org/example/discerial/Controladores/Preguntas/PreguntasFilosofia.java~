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
        List<Pregunta> preguntasFilosofia = preguntaDao.findByCategoria(3); // Asegúrate de usar el ID correcto
        if (!preguntasFilosofia.isEmpty()) {
            System.out.println("Ya existen preguntas para la categoría 'Filosofía'.");
            return;
        }

        String[][] preguntas = {
                {
                        "¿Quién es considerado el padre de la filosofía occidental?",
                        "Platón",
                        "Sócrates",
                        "Aristóteles",
                        "Sócrates",
                        "https://upload.wikimedia.org/wikipedia/commons/6/6f/Socrates_Louvre.jpg",
                        "multiple"
                },
                {
                        "¿Cuál es la obra más famosa de René Descartes?",
                        "Crítica de la razón pura",
                        "Meditaciones metafísicas",
                        "Discurso del método",
                        "Discurso del método",
                        "https://upload.wikimedia.org/wikipedia/commons/7/7a/René_Descartes_1649.jpg",
                        "multiple"
                },
                {
                        "¿Qué filósofo formuló la teoría de las Ideas o Formas?",
                        "Epicuro",
                        "Platón",
                        "Aristóteles",
                        "Platón",
                        "https://upload.wikimedia.org/wikipedia/commons/5/55/Plato_Silanion_Musei_Capitolini_MC1377.jpg",
                        "multiple"
                },
                {
                        "¿Cuál es el concepto central en la ética de Aristóteles?",
                        "El deber",
                        "La virtud",
                        "La felicidad (eudaimonía)",
                        "La felicidad (eudaimonía)",
                        "https://upload.wikimedia.org/wikipedia/commons/1/1c/Aristotle_Altemps_Inv8575.jpg",
                        "multiple"
                },
                {
                        "¿Qué corriente filosófica sostiene que la realidad está compuesta únicamente por materia?",
                        "Idealismo",
                        "Materialismo",
                        "Dualismo",
                        "Materialismo",
                        "https://upload.wikimedia.org/wikipedia/commons/7/7a/Democritus_Louvre.jpg",
                        "multiple"
                },
                {
                        "¿Quién escribió 'Así habló Zaratustra'?",
                        "Friedrich Nietzsche",
                        "Immanuel Kant",
                        "Jean-Paul Sartre",
                        "Friedrich Nietzsche",
                        "https://upload.wikimedia.org/wikipedia/commons/2/29/Nietzsche187a-crop.jpg",
                        "multiple"
                },
                {
                        "¿Qué filósofo es conocido por la frase 'Pienso, luego existo'?",
                        "René Descartes",
                        "David Hume",
                        "John Locke",
                        "René Descartes",
                        "https://upload.wikimedia.org/wikipedia/commons/7/7a/René_Descartes_1649.jpg",
                        "multiple"
                },
                {
                        "¿Cuál es la obra principal de Immanuel Kant?",
                        "Meditaciones",
                        "Crítica de la razón pura",
                        "La República",
                        "Crítica de la razón pura",
                        "https://upload.wikimedia.org/wikipedia/commons/0/09/Kant_gemaelde_3.jpg",
                        "multiple"
                },
                {
                        "¿Qué filósofo desarrolló el concepto de 'voluntad de poder'?",
                        "Arthur Schopenhauer",
                        "Friedrich Nietzsche",
                        "Karl Marx",
                        "Friedrich Nietzsche",
                        "https://upload.wikimedia.org/wikipedia/commons/2/29/Nietzsche187a-crop.jpg",
                        "multiple"
                },
                {
                        "¿Quién es el autor del 'Contrato Social'?",
                        "Jean-Jacques Rousseau",
                        "John Locke",
                        "Thomas Hobbes",
                        "Jean-Jacques Rousseau",
                        "https://upload.wikimedia.org/wikipedia/commons/3/32/Jean-Jacques_Rousseau_1753.jpg",
                        "multiple"
                },
                {
                        "¿Qué filósofo es conocido por su teoría del existencialismo?",
                        "Martin Heidegger",
                        "Jean-Paul Sartre",
                        "Michel Foucault",
                        "Jean-Paul Sartre",
                        "https://upload.wikimedia.org/wikipedia/commons/0/0d/Jean-Paul_Sartre_1967.jpg",
                        "multiple"
                },
                {
                        "¿Cuál es el objetivo principal de la ética según Kant?",
                        "La búsqueda de la felicidad",
                        "El cumplimiento del deber",
                        "La autorrealización",
                        "El cumplimiento del deber",
                        "https://upload.wikimedia.org/wikipedia/commons/0/09/Kant_gemaelde_3.jpg",
                        "multiple"
                },
                {
                        "¿Qué filósofo propuso la teoría del 'estado de naturaleza' como un estado de guerra de todos contra todos?",
                        "Thomas Hobbes",
                        "John Locke",
                        "Jean-Jacques Rousseau",
                        "Thomas Hobbes",
                        "https://upload.wikimedia.org/wikipedia/commons/6/68/Thomas_Hobbes_by_Francis_Barnard_1650.jpg",
                        "multiple"
                },
                {
                        "¿Quién escribió 'La República'?",
                        "Platón",
                        "Aristóteles",
                        "Sócrates",
                        "Platón",
                        "https://upload.wikimedia.org/wikipedia/commons/5/55/Plato_Silanion_Musei_Capitolini_MC1377.jpg",
                        "multiple"
                },
                {
                        "¿Cuál es la idea central del empirismo?",
                        "El conocimiento proviene de la experiencia",
                        "El conocimiento es innato",
                        "El conocimiento es una ilusión",
                        "El conocimiento proviene de la experiencia",
                        "https://upload.wikimedia.org/wikipedia/commons/a/a0/John_Locke_%281633-1704%29_by_Sir_Godfrey_Kneller%2C_Bt.jpg",
                        "multiple"
                },
                {
                        "¿Qué filósofo es conocido por la dialéctica y el idealismo absoluto?",
                        "Georg Wilhelm Friedrich Hegel",
                        "Karl Marx",
                        "Friedrich Nietzsche",
                        "Georg Wilhelm Friedrich Hegel",
                        "https://upload.wikimedia.org/wikipedia/commons/3/3e/Georg_Wilhelm_Friedrich_Hegel.jpg",
                        "multiple"
                },
                {
                        "¿Qué corriente filosófica sostiene que la existencia precede a la esencia?",
                        "Existencialismo",
                        "Racionalismo",
                        "Esencialismo",
                        "Existencialismo",
                        "https://upload.wikimedia.org/wikipedia/commons/0/0d/Jean-Paul_Sartre_1967.jpg",
                        "multiple"
                },
                {
                        "¿Cuál es el concepto de 'superhombre' desarrollado por Nietzsche?",
                        "Un ser divino",
                        "Un ser moralmente superior",
                        "Un individuo que trasciende las normas sociales y crea sus propios valores",
                        "Un individuo que trasciende las normas sociales y crea sus propios valores",
                        "https://upload.wikimedia.org/wikipedia/commons/2/29/Nietzsche187a-crop.jpg",
                        "multiple"
                },
                {
                        "¿Quién es considerado el fundador del positivismo?",
                        "Auguste Comte",
                        "Émile Durkheim",
                        "Max Weber",
                        "Auguste Comte",
                        "https://upload.wikimedia.org/wikipedia/commons/f/fb/Auguste_Comte_Alte_Galerie_001.jpg",
                        "multiple"
                },
                {
                        "¿Qué filósofo francés es conocido por su trabajo en la fenomenología y el estructuralismo?",
                        "Michel Foucault",
                        "Jean-Paul Sartre",
                        "Edmund Husserl",
                        "Michel Foucault",
                        "https://upload.wikimedia.org/wikipedia/commons/f/f7/Michel_Foucault_1975.jpg",
                        "multiple"
                }
        };

        // Insertamos las preguntas en la base de datos
        for (String[] datos : preguntas) {
            Pregunta pregunta = new Pregunta();
            pregunta.setCategoria(new Categoria(3 ,"Filosofía"));
            pregunta.setPregunta(datos[0]);
            pregunta.setRespuesta2(datos[1]);
            pregunta.setRespuesta3(datos[2]);
            pregunta.setRespuesta4(datos[3]);
            pregunta.setRespuestaCorrecta(datos[4]); // ← posición 4
            pregunta.setImagen(datos[5]);             // ← posición 5
            pregunta.setTipo(datos[6]);               // ← posición 6
            preguntaDao.save(pregunta);
        }

        System.out.println("Preguntas de filosofía añadidas con éxito.");
    }
}
