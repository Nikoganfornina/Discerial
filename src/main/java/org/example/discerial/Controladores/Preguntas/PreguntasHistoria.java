package org.example.discerial.Controladores.Preguntas;

import org.example.discerial.DAO.IPregunta;
import org.example.discerial.DAO.IPreguntaImpl;
import org.example.discerial.entities.Categoria;
import org.example.discerial.entities.Pregunta;

import java.util.List;

public class PreguntasHistoria {

    public static void crearPreguntasHistoria() {
        IPregunta preguntaDao = new IPreguntaImpl();
        int categoriaId = 1; // ID para Historia



        // 2) Definir el bloque de preguntas con nueva estructura
        String[][] preguntas = {
                {
                        "¿Quién fue el primer presidente de Estados Unidos?",
                        "Abraham Lincoln",
                        "Thomas Jefferson",
                        "John Adams",
                        "George Washington", // Respuesta correcta
                        "https://ejemplo.com/ruta_imagen1.jpg",
                        "multiple"
                },
                {
                        "¿En qué año terminó la Segunda Guerra Mundial?",
                        "1944",
                        "1946",
                        "1939",
                        "1945",
                        "https://ejemplo.com/ruta_imagen2.jpg",
                        "multiple"
                },
                {
                        "¿Qué imperio construyó la Gran Muralla China?",
                        "Imperio mongol",
                        "Imperio romano",
                        "Imperio persa",
                        "Imperio chino",
                        "https://ejemplo.com/ruta_imagen3.jpg",
                        "multiple"
                },
                // ... Resto de preguntas con la misma estructura
                {
                        "¿Qué país fue el primero en ser colonizado por los españoles en América?",
                        "México",
                        "Cuba",
                        "Puerto Rico",
                        "República Dominicana",
                        "https://ejemplo.com/ruta_imagenN.jpg",
                        "multiple"
                }
        };

        // 3) Insertar todas las preguntas
        for (String[] d : preguntas) {
            Pregunta q = new Pregunta();
            q.setCategoria(new Categoria(categoriaId, "Historia"));
            q.setPregunta(d[0]);
            q.setRespuesta2(d[1]);
            q.setRespuesta3(d[2]);
            q.setRespuesta4(d[3]);
            q.setRespuestaCorrecta(d[4]);
            q.setImagen(d[5]);
            q.setTipo(d[6]);
            preguntaDao.save(q);
        }

        System.out.println("Preguntas de historia añadidas con éxito.");
    }
}