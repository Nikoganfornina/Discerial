package org.example.discerial.Controladores.Preguntas;

import org.example.discerial.DAO.IPregunta;
import org.example.discerial.DAO.IPreguntaImpl;
import org.example.discerial.entities.Categoria;
import org.example.discerial.entities.Pregunta;

import java.util.List;

public class PreguntasBiologia {

    public static void crearPreguntasBiologia() {
        IPregunta preguntaDao = new IPreguntaImpl();
        int categoriaId = 4; // ID para Biología

        // 1) Eliminar cualquier pregunta existente en Biología
        List<Pregunta> existentes = preguntaDao.findByCategoria(categoriaId);
        for (Pregunta p : existentes) {
            preguntaDao.deleteById(p.getId());
        }

        // 2) Definir el bloque de preguntas
        String[][] preguntas = {
                {
                        "¿Cuál es la molécula que almacena la información genética?",
                        "ARN",
                        "Proteínas",
                        "Carbohidratos",
                        "ADN",  // Respuesta correcta
                        "https://upload.wikimedia.org/wikipedia/commons/1/1d/DNA_double_helix.svg",
                        "multiple"
                },
                {
                        "¿Qué partícula subatómica tiene carga negativa?",
                        "Protón",
                        "Neutrón",
                        "Quark",
                        "Electrón",
                        "https://upload.wikimedia.org/wikipedia/commons/8/8f/Electron.svg",
                        "multiple"
                },
                {
                        "¿Cuál es el principal pigmento fotosintético en las plantas?",
                        "Caroteno",
                        "Antocianina",
                        "Xantofila",
                        "Clorofila",
                        "https://upload.wikimedia.org/wikipedia/commons/8/8b/Chlorophyll.png",
                        "multiple"
                },
                {
                        "¿Cómo se llama el proceso por el cual las células convierten glucosa en energía en ausencia de oxígeno?",
                        "Respiración aeróbica",
                        "Fotosíntesis",
                        "Fermentación láctica",
                        "Transpiración",
                        "https://upload.wikimedia.org/wikipedia/commons/1/1d/Lactic_fermentation.svg",
                        "multiple"
                },
                {
                        "¿Qué orgánulo se encarga de detoxificar sustancias y sintetizar lípidos?",
                        "Mitocondria",
                        "Ribosoma",
                        "Retículo endoplasmático rugoso",
                        "Retículo endoplasmático liso",
                        "https://upload.wikimedia.org/wikipedia/commons/7/7e/Smooth_ER.png",
                        "multiple"
                },
                {
                        "¿Qué molécula transporta aminoácidos hasta el ribosoma durante la síntesis de proteínas?",
                        "ARN mensajero",
                        "ARN ribosómico",
                        "ARN de transferencia",
                        "ADN",
                        "https://upload.wikimedia.org/wikipedia/commons/5/5c/tRNA.svg",
                        "multiple"
                },
                {
                        "¿Cuál de estos procesos ocurre en la mitocondria?",
                        "Síntesis de proteínas",
                        "Glucólisis",
                        "Ciclo de Krebs",
                        "Fermentación",
                        "https://upload.wikimedia.org/wikipedia/commons/6/6f/Krebs_cycle_diagram.svg",
                        "multiple"
                }
                // Añadir más preguntas hasta completar las 50
        };

        // 3) Insertar todas las preguntas
        for (String[] d : preguntas) {
            Pregunta q = new Pregunta();
            q.setCategoria(new Categoria(categoriaId, "Biología"));
            q.setPregunta(d[0]);
            q.setRespuesta2(d[1]);
            q.setRespuesta3(d[2]);
            q.setRespuesta4(d[3]);
            q.setRespuestaCorrecta(d[4]);
            q.setImagen(d[5]);
            q.setTipo(d[6]);
            preguntaDao.save(q);
        }

        System.out.println("Preguntas de biología añadidas con éxito.");
    }
}