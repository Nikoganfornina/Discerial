package org.example.discerial.Controladores.Preguntas;

import org.example.discerial.DAO.IPregunta;
import org.example.discerial.DAO.IPreguntaImpl;
import org.example.discerial.entities.Pregunta;
import org.example.discerial.entities.Categoria;

import java.util.List;

public class PreguntasFilosofia {

    private static final int FILOSOFIA_ID = 1;

    public static void crearPreguntasFilosofia() {
        IPregunta preguntaDao = new IPreguntaImpl();

        // 1) Buscar todas las preguntas de Filosofía
        List<Pregunta> existentes = preguntaDao.findByCategoria(FILOSOFIA_ID);

        // 2) Si hay alguna, borrarlas todas
        if (!existentes.isEmpty()) {
            existentes.forEach(p -> preguntaDao.deleteById(p.getId()));
            System.out.println("Preguntas de Filosofía existentes eliminadas.");
        }

        // 3) Definir el array de datos (añade hasta 50)
        String[][] datos = {
                {"¿Quién es considerado el padre de la filosofía occidental?",
                        "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Socrate_du_Louvre.jpg/800px-Socrate_du_Louvre.jpg",
                        "Platón", "Aristóteles", "Diógenes", "Sócrates", "multiple"},
                {"¿Qué filósofo decía 'Pienso, luego existo'?",
                        "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6d/Descartes.jpg/800px-Descartes.jpg",
                        "Santo Tomás de Aquino", "Jean-Paul Sartre", "Emmanuel Kant", "René Descartes", "multiple"},
                {"La ética kantiana afirma que la moralidad está basada en la razón y la autonomía.",
                        "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4f/Immanuel_Kant_%28painted_portrait%29.jpg/800px-Immanuel_Kant_%28painted_portrait%29.jpg",
                        "Falso", null, null, "Verdadero", "vf"},
                {"¿Qué filósofo desarrolló la teoría del imperativo categórico?",
                        "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9c/Portrait_of_Immanuel_Kant_%28painted_portrait%29.jpg/800px-Portrait_of_Immanuel_Kant_%28painted_portrait%29.jpg",
                        "John Stuart Mill", "Friedrich Nietzsche", "Immanuel Kant", "Arthur Schopenhauer", "multiple"},
                {"¿Quién escribió 'Más allá del bien y del mal'?",
                        "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a9/Nietzsche187a.jpg/800px-Nietzsche187a.jpg",
                        "Arthur Schopenhauer", "Friedrich Nietzsche", "Georg Hegel", "Karl Marx", "multiple"},
                // … sigue completando hasta las 50 entradas …
        };

        // 4) Reinsertar todas las preguntas
        Categoria catFilo = new Categoria(FILOSOFIA_ID, "Filosofía");
        for (String[] d : datos) {
            Pregunta p = new Pregunta();
            p.setCategoria(catFilo);
            p.setPregunta(d[0]);
            p.setImagen(d[1]);                      // URL o null
            p.setRespuesta2(d[2]);
            p.setRespuesta3(d[3]);
            p.setRespuesta4(d[4]);
            p.setRespuestaCorrecta(d[5]);           // penúltimo elemento
            p.setTipo(d[6].equalsIgnoreCase("vf") ? "vf" : "multiple");
            preguntaDao.save(p);
        }

        System.out.println("Preguntas de Filosofía reinsertadas con éxito.");
    }
}
