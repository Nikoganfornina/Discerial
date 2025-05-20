package org.example.discerial.Controladores.Preguntas;

import org.example.discerial.DAO.IPregunta;
import org.example.discerial.DAO.IPreguntaImpl;
import org.example.discerial.entities.Categoria;
import org.example.discerial.entities.Pregunta;

import java.util.List;

public class PreguntasFilosofia {

    public static void crearPreguntasFilosofia() {
        IPregunta preguntaDao = new IPreguntaImpl();
        int categoriaId = 2; // ID para Filosofía



        // 2) Definir el bloque de preguntas
        String[][] preguntas = {
                {
                        "¿Quién es considerado el padre de la filosofía occidental?",
                        "Platón",
                        "Aristóteles",
                        "Diógenes",
                        "Sócrates", // [4] respuesta correcta
                        "https://upload.wikimedia.org/wikipedia/commons/6/6f/Socrates_Louvre.jpg",
                        "multiple"
                },
                {
                        "¿Qué filósofo decía 'Pienso, luego existo'?",
                        "Santo Tomás de Aquino",
                        "Jean-Paul Sartre",
                        "Emmanuel Kant",
                        "René Descartes", // correcta
                        "https://upload.wikimedia.org/wikipedia/commons/8/8b/Descartes_%28portrait%29.jpg",
                        "multiple"
                },
                {
                        "La ética kantiana afirma que la moralidad está basada en la razón y la autonomía.",
                        "Falso",
                        null,
                        null,
                        "Verdadero", // correcta
                        "https://upload.wikimedia.org/wikipedia/commons/4/4d/Immanuel_Kant_%28painted_portrait%29.jpg",
                        "vf"
                },
                {
                        "¿Quién desarrolló la teoría del contrato social?",
                        "John Locke",
                        "Karl Marx",
                        "Thomas Hobbes",
                        "Jean-Jacques Rousseau", // correcta
                        "https://upload.wikimedia.org/wikipedia/commons/8/8c/Rousseau.jpg",
                        "multiple"
                },
                {
                        "¿Qué pensador es conocido por su concepto de 'el superhombre'?",
                        "Sartre",
                        "Hegel",
                        "Foucault",
                        "Nietzsche", // correcta
                        "https://upload.wikimedia.org/wikipedia/commons/4/4f/Nietzsche187a.jpg",
                        "multiple"
                },
                {
                        "¿Cuál es la obra más famosa de Platón?",
                        "Fedón",
                        "La Apología de Sócrates",
                        "Timeo",
                        "La República", // correcta
                        "https://upload.wikimedia.org/wikipedia/commons/7/7e/Plato_Silanion_Musei_Capitolini_MC1377.jpg",
                        "multiple"
                },
                {
                        "¿Qué filósofo se ocupó del estudio de la fenomenología?",
                        "Martin Heidegger",
                        "Immanuel Kant",
                        "Jean-Paul Sartre",
                        "Edmund Husserl", // correcta
                        "https://upload.wikimedia.org/wikipedia/commons/3/3b/Edmund_Husserl_1910s.jpg",
                        "multiple"
                }
                // … añade más hasta completar los 50
        };

        // 3) Insertar todas las preguntas
        for (String[] d : preguntas) {
            Pregunta q = new Pregunta();
            q.setCategoria(new Categoria(categoriaId, "Filosofía"));
            q.setPregunta(d[0]);
            q.setRespuesta2(d[1]);
            q.setRespuesta3(d[2]);
            q.setRespuesta4(d[3]);
            q.setRespuestaCorrecta(d[4]);
            q.setImagen(d[5]);
            q.setTipo(d[6]);
            preguntaDao.save(q);
        }

        System.out.println("Preguntas de filosofía añadidas con éxito.");
    }
}
