package org.example.discerial.Controladores.Preguntas;

import org.example.discerial.DAO.IPregunta;
import org.example.discerial.DAO.IPreguntaImpl;
import org.example.discerial.entities.Categoria;
import org.example.discerial.entities.Pregunta;

import java.util.List;

public class PreguntasHistoria {

    public static void crearPreguntasHistoria() {
        IPregunta preguntaDao = new IPreguntaImpl();

        // Verificamos si ya existen preguntas para la categoría "Historia"
        List<Pregunta> preguntasHistoria = preguntaDao.findByCategoria(1); // Asegúrate de usar el ID correcto
        if (!preguntasHistoria.isEmpty()) {
            System.out.println("Ya existen preguntas para la categoría 'Historia'.");
            return;
        }
        String[][] preguntas = {
                {
                        "¿En qué año comenzó la Primera Guerra Mundial?",
                        "1912",
                        "1914",
                        "1918",
                        "1914",
                        "https://upload.wikimedia.org/wikipedia/commons/6/6b/WWI_Soldiers_1914-1918.jpg",
                        "multiple"
                },
                {
                        "¿Quién fue el primer presidente de los Estados Unidos?",
                        "Thomas Jefferson",
                        "George Washington",
                        "Abraham Lincoln",
                        "George Washington",
                        "https://upload.wikimedia.org/wikipedia/commons/6/6f/George_Washington_by_Gilbert_Stuart_1797.jpg",
                        "multiple"
                },
                {
                        "¿Qué imperio construyó la Gran Muralla China?",
                        "Imperio Mongol",
                        "Imperio Qin",
                        "Imperio Romano",
                        "Imperio Qin",
                        "https://upload.wikimedia.org/wikipedia/commons/1/1b/GreatWall_2004_Summer_4.jpg",
                        "multiple"
                },
                {
                        "¿Cuál fue la causa principal de la Revolución Francesa?",
                        "Descontento social y económico",
                        "Invasión extranjera",
                        "Cambio climático",
                        "Descontento social y económico",
                        "https://upload.wikimedia.org/wikipedia/commons/f/f9/Prise_de_la_Bastille.jpg",
                        "multiple"
                },
                {
                        "¿Quién fue el líder de la independencia de la India?",
                        "Jawaharlal Nehru",
                        "Mahatma Gandhi",
                        "Bhagat Singh",
                        "Mahatma Gandhi",
                        "https://upload.wikimedia.org/wikipedia/commons/d/d1/Mahatma_Gandhi_1942.jpg",
                        "multiple"
                },
                {
                        "¿Qué evento marcó el fin de la Edad Media?",
                        "La caída de Constantinopla",
                        "El descubrimiento de América",
                        "La Revolución Industrial",
                        "La caída de Constantinopla",
                        "https://upload.wikimedia.org/wikipedia/commons/6/6e/Fall_of_Constantinople.jpg",
                        "multiple"
                },
                {
                        "¿Quién fue el dictador alemán durante la Segunda Guerra Mundial?",
                        "Adolf Hitler",
                        "Benito Mussolini",
                        "Joseph Stalin",
                        "Adolf Hitler",
                        "https://upload.wikimedia.org/wikipedia/commons/8/88/Bundesarchiv_Bild_183-R57262%2C_Adolf_Hitler.jpg",
                        "multiple"
                },
                {
                        "¿En qué año se firmó la Declaración de Independencia de los Estados Unidos?",
                        "1776",
                        "1783",
                        "1791",
                        "1776",
                        "https://upload.wikimedia.org/wikipedia/commons/4/4a/Declaration_of_Independence_%281819%29_by_John_Trumbull.jpg",
                        "multiple"
                },
                {
                        "¿Qué civilización construyó las pirámides de Egipto?",
                        "Los mesopotámicos",
                        "Los egipcios",
                        "Los mayas",
                        "Los egipcios",
                        "https://upload.wikimedia.org/wikipedia/commons/e/e3/Kheops-Pyramid.jpg",
                        "multiple"
                },
                {
                        "¿Qué fue la Revolución Industrial?",
                        "Un movimiento artístico",
                        "Un cambio en la agricultura",
                        "La transición a la producción mecanizada",
                        "La transición a la producción mecanizada",
                        "https://upload.wikimedia.org/wikipedia/commons/3/3d/Spinning_jenny.jpg",
                        "multiple"
                },
                {
                        "¿Quién fue el primer emperador romano?",
                        "Julio César",
                        "Augusto",
                        "Nerón",
                        "Augusto",
                        "https://upload.wikimedia.org/wikipedia/commons/8/8b/Augustus_of_Prima_Porta.jpg",
                        "multiple"
                },
                {
                        "¿Qué evento inició la Segunda Guerra Mundial?",
                        "La invasión de Polonia por Alemania",
                        "El ataque a Pearl Harbor",
                        "La caída de Francia",
                        "La invasión de Polonia por Alemania",
                        "https://upload.wikimedia.org/wikipedia/commons/4/4b/WWII_invasion_of_Poland_1939.png",
                        "multiple"
                },
                {
                        "¿Cuál fue el principal resultado de la Guerra Fría?",
                        "La unificación de Alemania",
                        "La competencia entre EEUU y URSS",
                        "El fin de la esclavitud",
                        "La competencia entre EEUU y URSS",
                        "https://upload.wikimedia.org/wikipedia/commons/7/7a/Cold_War_map_1953-1989.png",
                        "multiple"
                },
                {
                        "¿Qué emperador ordenó la construcción del Coliseo romano?",
                        "Nerón",
                        "Vespasiano",
                        "Trajano",
                        "Vespasiano",
                        "https://upload.wikimedia.org/wikipedia/commons/d/d9/Colosseum_in_Rome%2C_Italy_-_April_2007.jpg",
                        "multiple"
                },
                {
                        "¿En qué año cayó el Imperio Romano de Occidente?",
                        "476 d.C.",
                        "1492",
                        "1066",
                        "476 d.C.",
                        "https://upload.wikimedia.org/wikipedia/commons/e/e3/Western_Roman_Empire_476_AD.png",
                        "multiple"
                },
                {
                        "¿Quién fue el autor de la teoría de la relatividad?",
                        "Isaac Newton",
                        "Albert Einstein",
                        "Galileo Galilei",
                        "Albert Einstein",
                        "https://upload.wikimedia.org/wikipedia/commons/d/d3/Albert_Einstein_Head.jpg",
                        "multiple"
                },
                {
                        "¿Qué movimiento social luchó contra el apartheid en Sudáfrica?",
                        "El Congreso Nacional Africano",
                        "Los Boers",
                        "El Frente Nacional",
                        "El Congreso Nacional Africano",
                        "https://upload.wikimedia.org/wikipedia/commons/e/e2/Nelson_Mandela-2008.jpg",
                        "multiple"
                },
                {
                        "¿Cuál fue la función principal de la Liga de Naciones?",
                        "Organizar guerras",
                        "Mantener la paz mundial",
                        "Promover la colonización",
                        "Mantener la paz mundial",
                        "https://upload.wikimedia.org/wikipedia/commons/2/20/League_of_Nations_emblem.png",
                        "multiple"
                },
                {
                        "¿Quién fue el faraón durante la construcción de la Gran Pirámide de Giza?",
                        "Tutankamón",
                        "Keops",
                        "Ramsés II",
                        "Keops",
                        "https://upload.wikimedia.org/wikipedia/commons/e/e3/Kheops-Pyramid.jpg",
                        "multiple"
                },
                {
                        "¿Qué imperio fue gobernado por Gengis Kan?",
                        "Imperio Mongol",
                        "Imperio Persa",
                        "Imperio Bizantino",
                        "Imperio Mongol",
                        "https://upload.wikimedia.org/wikipedia/commons/6/6e/Genghis_Khan_painting.jpg",
                        "multiple"
                }
        };

        // Insertamos las preguntas en la base de datos
        for (String[] datos : preguntas) {
            Pregunta pregunta = new Pregunta();
            pregunta.setCategoria(new Categoria(1 ,"Historia"));
            pregunta.setPregunta(datos[0]);
            pregunta.setRespuestaCorrecta(datos[5]);
            pregunta.setRespuesta2(datos[1]);
            pregunta.setRespuesta3(datos[2]);
            pregunta.setRespuesta4(datos[3]);
            pregunta.setTipo("multiple");
            preguntaDao.save(pregunta);
        }

        System.out.println("Preguntas de Historia añadidas con éxito.");
    }

}
