package org.example.discerial.Controladores.Preguntas;

import org.example.discerial.DAO.IPregunta;
import org.example.discerial.DAO.IPreguntaImpl;
import org.example.discerial.entities.Categoria;
import org.example.discerial.entities.Pregunta;

import java.util.List;

public class PreguntasLiteratura {

    public static void crearPreguntasLiteratura() {
        IPregunta preguntaDao = new IPreguntaImpl();
        int categoriaId = 3; // ID para Literatura

        // 1) Eliminar cualquier pregunta existente en Literatura
        List<Pregunta> existentes = preguntaDao.findByCategoria(categoriaId);
        for (Pregunta p : existentes) {
            preguntaDao.deleteById(p.getId());
        }

        // 2) Definir el bloque de preguntas con nueva estructura
        String[][] preguntas = {
                {
                        "¿Quién escribió 'Don Quijote de la Mancha'?",
                        "Lope de Vega",
                        "Francisco de Quevedo",
                        "Garcilaso de la Vega",
                        "Miguel de Cervantes", // Respuesta correcta
                        "https://ejemplo.com/donquijote.jpg",
                        "multiple"
                },
                {
                        "¿Qué autor escribió 'Cien años de soledad'?",
                        "Mario Vargas Llosa",
                        "Julio Cortázar",
                        "Carlos Fuentes",
                        "Gabriel García Márquez",
                        "https://ejemplo.com/cien-anos-soledad.jpg",
                        "multiple"
                },
                {
                        "¿Quién es el protagonista de la obra 'Matar a un ruiseñor'?",
                        "Atticus Finch",
                        "Tom Robinson",
                        "Boo Radley",
                        "Scout Finch",
                        "https://ejemplo.com/ruisenor.jpg",
                        "multiple"
                },
                // ... Resto de preguntas con misma estructura
                {
                        "¿Quién escribió 'La metamorfosis'?",
                        "Herman Melville",
                        "Jorge Luis Borges",
                        "Gabriel García Márquez",
                        "Franz Kafka",
                        "https://ejemplo.com/metamorfosis.jpg",
                        "multiple"
                }
        };

        // 3) Insertar todas las preguntas
        for (String[] d : preguntas) {
            Pregunta q = new Pregunta();
            q.setCategoria(new Categoria(categoriaId, "Literatura"));
            q.setPregunta(d[0]);
            q.setRespuesta2(d[1]);
            q.setRespuesta3(d[2]);
            q.setRespuesta4(d[3]);
            q.setRespuestaCorrecta(d[4]);
            q.setImagen(d[5]);
            q.setTipo(d[6]);
            preguntaDao.save(q);
        }

        System.out.println("Preguntas de literatura añadidas con éxito.");
    }
}