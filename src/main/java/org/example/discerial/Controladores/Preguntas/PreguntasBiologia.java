package org.example.discerial.Controladores.Preguntas;

import org.example.discerial.DAO.IPregunta;
import org.example.discerial.DAO.IPreguntaImpl;
import org.example.discerial.entities.Categoria;
import org.example.discerial.entities.Pregunta;

import java.util.List;

public class PreguntasBiologia {

    public static void crearPreguntasBiologia() {
        IPregunta preguntaDao = new IPreguntaImpl();

        // Verificamos si ya existen preguntas para la categoría "Biología"
        List<Pregunta> preguntasBiologia = preguntaDao.findByCategoria(4); // ID 4 para Biología
        if (!preguntasBiologia.isEmpty()) {
            System.out.println("Ya existen preguntas para la categoría 'Biología'.");
            return;
        }

        String[][] preguntas = {
                {
                        "¿Cuál es la molécula que almacena la información genética?", // [0]
                        "ARN",        // [1]
                        "Proteínas",  // [2]
                        "Carbohidratos", // [3]
                        "ADN",             // [4] → respuesta correcta
                        "https://upload.wikimedia.org/wikipedia/commons/1/1d/DNA_double_helix.svg", // [5] imagen
                        "multiple"    // [6] tipo
                },
                {
                        "¿Qué partícula subatómica tiene carga negativa?",
                        "Protón",
                        "Neutrón",
                        "Quark",
                        "Electrón",        // respuesta correcta
                        "https://upload.wikimedia.org/wikipedia/commons/8/8f/Electron.svg",
                        "multiple"
                },
                {
                        "¿Cuál es el principal pigmento fotosintético en las plantas?",
                        "Caroteno",
                        "Antocianina",
                        "Xantofila",
                        "Clorofila",       // respuesta correcta
                        "https://upload.wikimedia.org/wikipedia/commons/8/8b/Chlorophyll.png",
                        "multiple"
                },
                {
                        "¿Cómo se llama el proceso por el cual las células convierten glucosa en energía en ausencia de oxígeno?",
                        "Respiración aeróbica",
                        "Fotosíntesis",
                        "Fermentación láctica", // respuesta correcta
                        "Transpiración",
                        "https://upload.wikimedia.org/wikipedia/commons/1/1d/Lactic_fermentation.svg",
                        "multiple"
                },
                {
                        "¿Qué orgánulo se encarga de detoxificar sustancias y sintetizar lípidos?",
                        "Mitocondria",
                        "Ribosoma",
                        "Retículo endoplasmático rugoso",
                        "Retículo endoplasmático liso", // respuesta correcta
                        "https://upload.wikimedia.org/wikipedia/commons/7/7e/Smooth_ER.png",
                        "multiple"
                },
                {
                        "¿Qué molécula transporta aminoácidos hasta el ribosoma durante la síntesis de proteínas?",
                        "ARN mensajero",
                        "ARN ribosómico",
                        "ARN de transferencia", // respuesta correcta
                        "ADN",
                        "https://upload.wikimedia.org/wikipedia/commons/5/5c/tRNA.svg",
                        "multiple"
                },
                {
                        "¿Cuál de estos procesos ocurre en la mitocondria?",
                        "Síntesis de proteínas",
                        "Glucólisis",
                        "Ciclo de Krebs",      // respuesta correcta
                        "Fermentación",
                        "https://upload.wikimedia.org/wikipedia/commons/6/6f/Krebs_cycle_diagram.svg",
                        "multiple"
                }
        };


        for (String[] datos : preguntas) {
            Pregunta pregunta = new Pregunta();
            pregunta.setCategoria(new Categoria(4, "Biología"));
            pregunta.setPregunta(datos[0]);
            pregunta.setRespuesta2(datos[1]);
            pregunta.setRespuesta3(datos[2]);
            pregunta.setRespuesta4(datos[3]);
            pregunta.setRespuestaCorrecta(datos[4]); // ← posición 4
            pregunta.setImagen(datos[5]);             // ← posición 5
            pregunta.setTipo(datos[6]);               // ← posición 6
            preguntaDao.save(pregunta);
        }


        System.out.println("Preguntas de biología añadidas con éxito.");
    }
}
