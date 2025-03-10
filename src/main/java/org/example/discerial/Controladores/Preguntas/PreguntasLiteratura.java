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
        List<Pregunta> preguntasLiteratura = preguntaDao.findByCategoria(3); // Asegúrate de usar el ID correcto
        if (!preguntasLiteratura.isEmpty()) {
            System.out.println("Ya existen preguntas para la categoría 'Literatura'.");
            return;
        }
        String[][] preguntas = {
                {"¿Quién escribió 'Don Quijote de la Mancha'?", "Miguel de Cervantes", "Lope de Vega", "Francisco de Quevedo", "Garcilaso de la Vega", "Miguel de Cervantes"},
                {"¿Qué autor escribió 'Cien años de soledad'?", "Gabriel García Márquez", "Mario Vargas Llosa", "Julio Cortázar", "Carlos Fuentes", "Gabriel García Márquez"},
                {"¿Quién es el protagonista de la obra 'Matar a un ruiseñor'?", "Scout Finch", "Atticus Finch", "Tom Robinson", "Boo Radley", "Scout Finch"},
                {"¿Quién escribió 'Orgullo y prejuicio'?", "Jane Austen", "Charlotte Brontë", "Emily Dickinson", "Virginia Woolf", "Jane Austen"},
                {"¿En qué obra se encuentra el personaje de Hamlet?", "Hamlet", "Macbeth", "Romeo y Julieta", "El Rey Lear", "Hamlet"},
                {"¿Quién es el autor de 'La Odisea'?", "Homero", "Virgilio", "Platón", "Sófocles", "Homero"},
                {"¿Cuál de estos autores es conocido por su obra de teatro 'La Casa de Bernarda Alba'?", "Federico García Lorca", "Luis Buñuel", "Antonio Buero Vallejo", "Jorge Luis Borges", "Federico García Lorca"},
                {"¿Qué escritor británico es famoso por sus obras como '1984' y 'Rebelión en la granja'?", "George Orwell", "Aldous Huxley", "Ray Bradbury", "J.K. Rowling", "George Orwell"},
                {"¿Cuál es el título de la obra más famosa de William Shakespeare?", "Romeo y Julieta", "Hamlet", "Macbeth", "El Rey Lear", "Romeo y Julieta"},
                {"¿Quién escribió 'El Gran Gatsby'?", "F. Scott Fitzgerald", "Ernest Hemingway", "John Steinbeck", "Mark Twain", "F. Scott Fitzgerald"},
                {"¿En qué obra se basa la famosa frase 'Ser o no ser, esa es la cuestión'?", "Hamlet", "Macbeth", "El Cid", "Don Quijote", "Hamlet"},
                {"¿Quién escribió 'Matar a un ruiseñor'?", "Harper Lee", "John Steinbeck", "F. Scott Fitzgerald", "Ernest Hemingway", "Harper Lee"},
                {"¿Cuál de estos libros fue escrito por Charles Dickens?", "Oliver Twist", "La Cabaña del Tío Tom", "Matar a un ruiseñor", "Crimen y Castigo", "Oliver Twist"},
                {"¿Qué obra escribió Julio Cortázar que es considerada una de sus más importantes?", "Rayuela", "El Aleph", "Ficciones", "La Casa Tomada", "Rayuela"},
                {"¿Quién es el autor de 'Cumbres Borrascosas'?", "Emily Brontë", "Charlotte Brontë", "Jane Austen", "Virginia Woolf", "Emily Brontë"},
                {"¿Quién es el autor de 'Fahrenheit 451'?", "Ray Bradbury", "Aldous Huxley", "Isaac Asimov", "George Orwell", "Ray Bradbury"},
                {"¿Cuál de estos personajes pertenece a la obra 'Moby Dick'?", "Capitán Ahab", "Sherlock Holmes", "Dorian Gray", "Hannibal Lecter", "Capitán Ahab"},
                {"¿Qué autor ruso escribió 'Crimen y Castigo'?", "Fiódor Dostoyevski", "León Tolstói", "Antón Chéjov", "Ivan Turguénev", "Fiódor Dostoyevski"},
                {"¿Cuál de estos escritores no es estadounidense?", "Gabriel García Márquez", "Ernest Hemingway", "William Faulkner", "Mark Twain", "Gabriel García Márquez"},
                {"¿Qué libro es considerado el mayor exponente de la novela existencialista?", "La Náusea", "El extranjero", "Crimen y Castigo", "1984", "La Náusea"},
                {"¿Quién escribió 'La divina comedia'?", "Dante Alighieri", "Virgilio", "Homero", "Héctor Álvarez", "Dante Alighieri"},
                {"¿Qué escritor argentino es famoso por sus cuentos fantásticos y de terror?", "Jorge Luis Borges", "Julio Cortázar", "Adolfo Bioy Casares", "Ricardo Piglia", "Jorge Luis Borges"},
                {"¿Quién escribió 'La sombra del viento'?", "Carlos Ruiz Zafón", "Almudena Grandes", "Arturo Pérez-Reverte", "Julia Navarro", "Carlos Ruiz Zafón"},
                {"¿Qué obra de Gabriel García Márquez cuenta la historia de una familia a lo largo de varias generaciones?", "Cien años de soledad", "El amor en los tiempos del cólera", "Crónica de una muerte anunciada", "El otoño del patriarca", "Cien años de soledad"},
                {"¿Cuál de los siguientes escritores fue un gran representante del realismo literario?", "Honoré de Balzac", "Victor Hugo", "Charles Dickens", "Gustave Flaubert", "Honoré de Balzac"},
                {"¿Qué escritor publicó 'El retrato de Dorian Gray'?", "Oscar Wilde", "Virginia Woolf", "Henry James", "Jane Austen", "Oscar Wilde"},
                {"¿Qué escritor creó a Sherlock Holmes?", "Arthur Conan Doyle", "Agatha Christie", "Edgar Allan Poe", "G.K. Chesterton", "Arthur Conan Doyle"},
                {"¿En qué obra aparece el personaje de Raskólnikov?", "Crimen y Castigo", "El jugador", "Los hermanos Karamazov", "El idiota", "Crimen y Castigo"},
                {"¿Qué autora escribió 'El jardín secreto'?", "Frances Hodgson Burnett", "Louisa May Alcott", "Beatrix Potter", "L. Frank Baum", "Frances Hodgson Burnett"},
                {"¿Quién escribió 'Los miserables'?", "Victor Hugo", "Emile Zola", "Gustave Flaubert", "Éric-Emmanuel Schmitt", "Victor Hugo"},
                {"¿Quién es el autor de 'El Quijote'?", "Miguel de Cervantes", "Lope de Vega", "Tirso de Molina", "Quevedo", "Miguel de Cervantes"},
                {"¿En qué obra se encuentran los personajes de Gatsby y Daisy?", "El gran Gatsby", "Cumbres borrascosas", "Matar a un ruiseñor", "El retrato de Dorian Gray", "El gran Gatsby"},
                {"¿Quién escribió 'La metamorfosis'?", "Franz Kafka", "Herman Melville", "Jorge Luis Borges", "Gabriel García Márquez", "Franz Kafka"},
                {"¿Qué escritor escribió 'El amor en los tiempos del cólera'?", "Gabriel García Márquez", "Mario Vargas Llosa", "Carlos Fuentes", "Julio Cortázar", "Gabriel García Márquez"},
                {"¿Cuál de estas obras fue escrita por Emily Dickinson?", "Poesía completa", "El gran Gatsby", "Orgullo y prejuicio", "Cumbres borrascosas", "Poesía completa"},
                {"¿Quién fue el autor de 'El proceso'?", "Franz Kafka", "Hermann Hesse", "Gabriel García Márquez", "Fiódor Dostoyevski", "Franz Kafka"},
                {"¿En qué obra aparece el personaje de Ulises?", "La Odisea", "La Eneida", "La Ilíada", "La Odisea", "La Odisea"},
                {"¿Quién escribió 'Pedro Páramo'?", "Juan Rulfo", "Carlos Fuentes", "Gabriel García Márquez", "Mario Vargas Llosa", "Juan Rulfo"},
                {"¿Qué escritor escribió 'El viejo y el mar'?", "Ernest Hemingway", "John Steinbeck", "F. Scott Fitzgerald", "William Faulkner", "Ernest Hemingway"},
                {"¿Quién es el autor de 'Los hermanos Karamazov'?", "Fiódor Dostoyevski", "León Tolstói", "Antón Chéjov", "Iván Turgénev", "Fiódor Dostoyevski"},
                {"¿Qué novela de Franz Kafka describe la transformación de un hombre en insecto?", "La metamorfosis", "El proceso", "Carta al padre", "El castillo", "La metamorfosis"},
                {"¿Qué escritor estadounidense escribió 'Las aventuras de Huckleberry Finn'?", "Mark Twain", "Ernest Hemingway", "John Steinbeck", "F. Scott Fitzgerald", "Mark Twain"},
                {"¿Quién escribió 'Ficciones'?", "Jorge Luis Borges", "Julio Cortázar", "Gabriel García Márquez", "Carlos Fuentes", "Jorge Luis Borges"},
                {"¿En qué obra se encuentra el personaje de Gregor Samsa?", "La metamorfosis", "El proceso", "Los hermanos Karamazov", "Crimen y Castigo", "La metamorfosis"},
                {"¿Quién escribió 'Moby Dick'?", "Herman Melville", "Mark Twain", "Ernest Hemingway", "John Steinbeck", "Herman Melville"},
                {"¿Qué escritor escribió '1984'?", "George Orwell", "Aldous Huxley", "Ray Bradbury", "J.K. Rowling", "George Orwell"},
                {"¿Qué autor escribió 'Don Juan Tenorio'?", "Tirso de Molina", "Pedro Calderón de la Barca", "Lope de Vega", "Juan Ruiz de Alarcón", "Tirso de Molina"},
                {"¿En qué obra aparece el personaje de Drácula?", "Drácula", "Frankenstein", "El retrato de Dorian Gray", "El médico y el monstruo", "Drácula"},
                {"¿Quién escribió 'La caverna'?", "José Saramago", "Gabriel García Márquez", "Mario Vargas Llosa", "Carlos Fuentes", "José Saramago"},
                {"¿En qué obra aparece el personaje de Raskólnikov?", "Crimen y Castigo", "Los hermanos Karamazov", "El idiota", "El jugador", "Crimen y Castigo"}
        };
        // Insertamos las preguntas en la base de datos
        for (String[] datos : preguntas) {
            Pregunta pregunta = new Pregunta();
            pregunta.setCategoria(new Categoria(3, "Literatura"));
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


