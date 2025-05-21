package org.example.discerial.Controladores.Preguntas;

import org.example.discerial.DAO.IPregunta;
import org.example.discerial.DAO.IPreguntaImpl;
import org.example.discerial.entities.Categoria;
import org.example.discerial.entities.Pregunta;

import java.util.List;

public class PreguntasLiteratura {

    public static void crearPreguntasLiteratura() {
        IPregunta preguntaDao = new IPreguntaImpl();

        // Verificamos si ya existen preguntas para la categoría "Literatura"
        List<Pregunta> preguntasLiteratura = preguntaDao.findByCategoria(2); // Asegúrate de usar el ID correcto
        if (!preguntasLiteratura.isEmpty()) {
            System.out.println("Ya existen preguntas para la categoría 'Literatura'.");
            return;
        }
        String[][] preguntas = {
                {
                        "¿Quién escribió 'Don Quijote de la Mancha'?",
                        "Miguel de Cervantes",
                        "Lope de Vega",
                        "García Lorca",
                        "Miguel de Cervantes",
                        "https://upload.wikimedia.org/wikipedia/commons/4/4b/Miguel_de_Cervantes_1.jpg",
                        "multiple"
                },
                {
                        "¿Cuál es la obra más famosa de William Shakespeare?",
                        "Hamlet",
                        "Macbeth",
                        "Romeo y Julieta",
                        "Hamlet",
                        "https://upload.wikimedia.org/wikipedia/commons/a/a2/Shakespeare.jpg",
                        "multiple"
                },
                {
                        "¿A qué movimiento literario pertenece Federico García Lorca?",
                        "Modernismo",
                        "Generación del 98",
                        "Generación del 27",
                        "Generación del 27",
                        "https://upload.wikimedia.org/wikipedia/commons/1/15/Federico_García_Lorca_1934.jpg",
                        "multiple"
                },
                {
                        "¿Quién es el autor de 'Cien años de soledad'?",
                        "Mario Vargas Llosa",
                        "Gabriel García Márquez",
                        "Julio Cortázar",
                        "Gabriel García Márquez",
                        "https://upload.wikimedia.org/wikipedia/commons/6/6f/Gabriel_Garcia_Marquez_2002.jpg",
                        "multiple"
                },
                {
                        "¿Qué novela escribió Franz Kafka?",
                        "El proceso",
                        "Los hermanos Karamazov",
                        "El extranjero",
                        "El proceso",
                        "https://upload.wikimedia.org/wikipedia/commons/0/00/Franz_Kafka_2.jpg",
                        "multiple"
                },
                {
                        "¿Cuál es la nacionalidad de Jorge Luis Borges?",
                        "Chileno",
                        "Argentino",
                        "Mexicano",
                        "Argentino",
                        "https://upload.wikimedia.org/wikipedia/commons/d/dd/Jorge_Luis_Borges_1951.jpg",
                        "multiple"
                },
                {
                        "¿Cuál es la obra emblemática de la literatura rusa escrita por Fiódor Dostoievski?",
                        "Crimen y castigo",
                        "Guerra y paz",
                        "Ana Karenina",
                        "Crimen y castigo",
                        "https://upload.wikimedia.org/wikipedia/commons/1/1d/Fyodor_Dostoyevsky_1876.jpg",
                        "multiple"
                },
                {
                        "¿Quién escribió 'La Odisea'?",
                        "Homero",
                        "Virgilio",
                        "Sófocles",
                        "Homero",
                        "https://upload.wikimedia.org/wikipedia/commons/5/53/Homer_British_Museum.jpg",
                        "multiple"
                },
                {
                        "¿Qué escritor estadounidense es autor de 'Matar a un ruiseñor'?",
                        "Ernest Hemingway",
                        "Harper Lee",
                        "F. Scott Fitzgerald",
                        "Harper Lee",
                        "https://upload.wikimedia.org/wikipedia/commons/3/35/Harper_Lee_1961.jpg",
                        "multiple"
                },
                {
                        "¿Qué novela es considerada la obra más importante del realismo mágico?",
                        "El amor en los tiempos del cólera",
                        "Cien años de soledad",
                        "Pedro Páramo",
                        "Cien años de soledad",
                        "https://upload.wikimedia.org/wikipedia/commons/6/6f/Gabriel_Garcia_Marquez_2002.jpg",
                        "multiple"
                },
                {
                        "¿Qué poeta chileno ganó el Premio Nobel de Literatura en 1971?",
                        "Pablo Neruda",
                        "Gabriela Mistral",
                        "Nicanor Parra",
                        "Pablo Neruda",
                        "https://upload.wikimedia.org/wikipedia/commons/3/30/Pablo_Neruda%2C_1953.jpg",
                        "multiple"
                },
                {
                        "¿Quién escribió 'El retrato de Dorian Gray'?",
                        "Oscar Wilde",
                        "James Joyce",
                        "Virginia Woolf",
                        "Oscar Wilde",
                        "https://upload.wikimedia.org/wikipedia/commons/a/a3/Oscar_Wilde_portrait.jpg",
                        "multiple"
                },
                {
                        "¿Cuál es el género literario de 'El Quijote'?",
                        "Novela de caballerías",
                        "Novela picaresca",
                        "Novela moderna",
                        "Novela moderna",
                        "https://upload.wikimedia.org/wikipedia/commons/4/4b/Miguel_de_Cervantes_1.jpg",
                        "multiple"
                },
                {
                        "¿Qué poeta español es conocido como 'El Príncipe de los Poetas'?",
                        "Luis de Góngora",
                        "Garcilaso de la Vega",
                        "Gustavo Adolfo Bécquer",
                        "Gustavo Adolfo Bécquer",
                        "https://upload.wikimedia.org/wikipedia/commons/7/7b/Gustavo_Adolfo_Becquer.jpg",
                        "multiple"
                },
                {
                        "¿Quién escribió 'Ulises'?",
                        "James Joyce",
                        "William Faulkner",
                        "T.S. Eliot",
                        "James Joyce",
                        "https://upload.wikimedia.org/wikipedia/commons/1/1a/James_Joyce_by_Alexander_Bassano.jpg",
                        "multiple"
                },
                {
                        "¿Cuál es el tema principal de 'La Divina Comedia' de Dante Alighieri?",
                        "El amor cortés",
                        "El viaje del alma hacia Dios",
                        "La guerra y la paz",
                        "El viaje del alma hacia Dios",
                        "https://upload.wikimedia.org/wikipedia/commons/7/73/Dante_Alighieri.jpg",
                        "multiple"
                },
                {
                        "¿Quién escribió 'Crónica de una muerte anunciada'?",
                        "Gabriel García Márquez",
                        "Mario Vargas Llosa",
                        "Julio Cortázar",
                        "Gabriel García Márquez",
                        "https://upload.wikimedia.org/wikipedia/commons/6/6f/Gabriel_Garcia_Marquez_2002.jpg",
                        "multiple"
                },
                {
                        "¿Qué novela es considerada un clásico del existencialismo literario?",
                        "El extranjero",
                        "La náusea",
                        "El ser y la nada",
                        "El extranjero",
                        "https://upload.wikimedia.org/wikipedia/commons/9/9a/Albert_Camus_1957.jpg",
                        "multiple"
                },
                {
                        "¿Quién es la autora de 'Jane Eyre'?",
                        "Emily Brontë",
                        "Charlotte Brontë",
                        "Mary Shelley",
                        "Charlotte Brontë",
                        "https://upload.wikimedia.org/wikipedia/commons/e/e0/Charlotte_Brontë_by_G._Richmond.jpg",
                        "multiple"
                },
                {
                        "¿Cuál es el movimiento literario asociado con la obra 'En busca del tiempo perdido' de Marcel Proust?",
                        "Modernismo",
                        "Simbolismo",
                        "Modernismo francés / novela moderna",
                        "Modernismo francés / novela moderna",
                        "https://upload.wikimedia.org/wikipedia/commons/4/46/Marcel_Proust_1900s.jpg",
                        "multiple"
                }
        };

        // Insertamos las preguntas en la base de datos
        for (String[] datos : preguntas) {
            Pregunta pregunta = new Pregunta();
            pregunta.setCategoria(new Categoria(2, "Literatura"));
            pregunta.setPregunta(datos[0]);
            pregunta.setRespuestaCorrecta(datos[5]);
            pregunta.setRespuesta2(datos[1]);
            pregunta.setRespuesta3(datos[2]);
            pregunta.setRespuesta4(datos[3]);
            pregunta.setTipo("multiple");
            preguntaDao.save(pregunta);
        }

        System.out.println("Preguntas de literatura añadidas con éxito.");
    }
}


