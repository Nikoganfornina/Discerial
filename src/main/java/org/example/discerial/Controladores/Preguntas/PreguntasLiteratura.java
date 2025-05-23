package org.example.discerial.Controladores.Preguntas;

import org.example.discerial.DAO.IPregunta;
import org.example.discerial.DAO.IPreguntaImpl;
import org.example.discerial.entities.Categoria;
import org.example.discerial.entities.Pregunta;

import java.util.List;

public class PreguntasLiteratura {

    public static void crearPreguntasLiteratura() {
        IPregunta preguntaDao = new IPreguntaImpl();
        int categoriaId = 2; // ID para Literatura

        // Lista de preguntas con ID manual (añadido un campo id en la posición 0)
        Object[][] preguntas = {


                {301, "¿Quién escribió 'Don Quijote de la Mancha'?", "Lope de Vega", "García Lorca", "Miguel Hernández", "Miguel de Cervantes", "multiple"},
                {302, "¿En qué siglo se escribió 'La Celestina'?", "Siglo XVI", "Siglo XVIII", "Siglo XIX", "Siglo XV", "multiple"},
                {303, "¿Cuál es la obra más famosa de Gabriel García Márquez?", "El amor en los tiempos del cólera", "Crónica de una muerte anunciada", "El otoño del patriarca", "Cien años de soledad", "multiple"},
                {304, "¿Qué movimiento literario pertenece a Federico García Lorca?", "Modernismo", "Generación del 98", "Realismo", "Generación del 27", "multiple"},
                {305, "¿Quién es el autor de 'La casa de Bernarda Alba'?", "Antonio Machado", "Pablo Neruda", "Juan Ramón Jiménez", "Federico García Lorca", "multiple"},
                {306, "¿Qué novela escribió George Orwell?", "El gran Gatsby", "Crimen y castigo", "Matar a un ruiseñor", "1984", "multiple"},
                {307, "¿Cuál es la nacionalidad de Julio Cortázar?", "Española", "Mexicana", "Chilena", "Argentina", "multiple"},
                {308, "¿Quién escribió 'El Lazarillo de Tormes'?", "Miguel de Cervantes", "Lope de Vega", "Góngora", "Anónimo", "multiple"},
                {309, "¿Qué obra es de Lope de Vega?", "Fuenteovejuna", "La Regenta", "El Quijote", "La vida es sueño", "multiple"},
                {310, "¿Cuál es el tema principal de 'La Regenta' de Leopoldo Alas?", "Amor imposible", "La guerra civil", "La aventura", "La hipocresía social", "multiple"},
                {311, "¿Qué escritor español es conocido como 'el poeta del pueblo'?", "Antonio Machado", "Jorge Guillén", "Gabriel Celaya", "Miguel Hernández", "multiple"},
                {312, "¿Quién escribió 'Cien años de soledad'?", "Isabel Allende", "Mario Vargas Llosa", "Pablo Neruda", "Gabriel García Márquez", "multiple"},
                {313, "¿Qué género literario representa 'La Divina Comedia' de Dante Alighieri?", "Novela", "Ensayo", "Poesía lírica", "Poema épico", "multiple"},
                {314, "¿Quién es el autor de 'Fausto'?", "Goethe", "Shakespeare", "Victor Hugo", "Johann Wolfgang von Goethe", "multiple"},
                {315, "¿Cuál es la lengua original de la obra 'Hamlet'?", "Francés", "Italiano", "Español", "Inglés", "multiple"},
                {316, "¿Qué corriente literaria se caracteriza por el enfoque en la subjetividad y emociones?", "Realismo", "Naturalismo", "Clasicismo", "Romanticismo", "multiple"},
                {317, "¿Quién escribió 'El príncipe'?", "Maquiavelo", "Sócrates", "Platón", "Nicolás Maquiavelo", "multiple"},
                {318, "¿Qué escritor español es famoso por su obra de teatro 'El perro del hortelano'?", "Lope de Vega", "Federico García Lorca", "Tirso de Molina", "Lope de Vega", "multiple"},
                {319, "¿Quién es el autor de 'Pedro Páramo'?", "Carlos Fuentes", "Gabriel García Márquez", "Julio Cortázar", "Juan Rulfo", "multiple"},
                {320, "¿Qué obra escribió Shakespeare?", "El retrato de Dorian Gray", "Macbeth", "Don Juan Tenorio", "Macbeth", "multiple"},
                {321, "¿Qué poeta español escribió 'Campos de Castilla'?", "Antonio Machado", "Juan Ramón Jiménez", "Miguel Hernández", "Antonio Machado", "multiple"},
                {322, "¿Qué obra es considerada la primera novela moderna?", "El Quijote", "El Lazarillo de Tormes", "La Celestina", "Don Quijote de la Mancha", "multiple"},
                {323, "¿Quién es el autor de 'Rayuela'?", "Jorge Luis Borges", "Mario Vargas Llosa", "Pablo Neruda", "Julio Cortázar", "multiple"},
                {324, "¿Qué escritor escribió 'El extranjero'?", "Jean-Paul Sartre", "Albert Camus", "Victor Hugo", "Albert Camus", "multiple"},
                {325, "¿Cuál es el nombre del poema épico nacional de España?", "La Araucana", "El Cantar del Mío Cid", "La Eneida", "El Cantar del Mío Cid", "multiple"},
                {326, "¿Quién escribió 'El amor en los tiempos del cólera'?", "Isabel Allende", "Mario Vargas Llosa", "Gabriel García Márquez", "Gabriel García Márquez", "multiple"},
                {327, "¿Qué autor español es conocido por su obra 'Niebla'?", "Juan Ramón Jiménez", "Miguel de Unamuno", "Federico García Lorca", "Miguel de Unamuno", "multiple"},
                {328, "¿Qué novela comienza con la frase 'Call me Ishmael'?", "Moby Dick", "Don Quijote", "Ulises", "Moby Dick", "multiple"},
                {329, "¿Quién es el autor de 'El retrato de Dorian Gray'?", "Oscar Wilde", "James Joyce", "Charles Dickens", "Oscar Wilde", "multiple"},
                {330, "¿Qué escritor argentino es conocido por sus cuentos cortos y obras de ficción fantástica?", "Julio Cortázar", "Jorge Luis Borges", "Adolfo Bioy Casares", "Jorge Luis Borges", "multiple"},
                {331, "¿Quién escribió 'Cumbres borrascosas'?", "Charlotte Brontë", "Jane Austen", "Emily Dickinson", "Emily Brontë", "multiple"},
                {332, "¿En qué idioma escribió Dante Alighieri 'La Divina Comedia'?", "Latín", "Italiano", "Francés", "Italiano", "multiple"},
                {333, "¿Qué autor escribió '1984' y 'Rebelión en la granja'?", "Ray Bradbury", "Aldous Huxley", "Philip K. Dick", "George Orwell", "multiple"},
                {334, "¿Quién escribió 'La sombra del viento'?", "Carlos Ruiz Zafón", "Isabel Allende", "Javier Marías", "Carlos Ruiz Zafón", "multiple"},
                {335, "¿Cuál es la nacionalidad de Isabel Allende?", "Argentina", "España", "México", "Chile", "multiple"},
                {336, "¿Qué poeta español ganó el Premio Nobel en 1956?", "Juan Ramón Jiménez", "Antonio Machado", "Gabriel Celaya", "Juan Ramón Jiménez", "multiple"},
                {337, "¿Qué novela describe la decadencia de la burguesía en España y es obra de Benito Pérez Galdós?", "La Regenta", "Fortunata y Jacinta", "Doña Perfecta", "Fortunata y Jacinta", "multiple"},
                {338, "¿Qué autor escribió la obra 'La familia de Pascual Duarte'?", "Camilo José Cela", "Miguel Delibes", "Antonio Machado", "Camilo José Cela", "multiple"},
                {339, "¿Quién es el autor de 'El Aleph'?", "Jorge Luis Borges", "Julio Cortázar", "Gabriel García Márquez", "Jorge Luis Borges", "multiple"},
                {340, "¿Qué poeta chileno fue premio Nobel de Literatura en 1971?", "Pablo Neruda", "Gabriela Mistral", "Vicente Huidobro", "Pablo Neruda", "multiple"},
                {341, "¿Quién escribió 'La Odisea'?", "Sócrates", "Homero", "Virgilio", "Homero", "multiple"},
                {342, "¿Qué obra escribió Cervantes además de 'Don Quijote'?", "La Regenta", "Novelas Ejemplares", "La Celestina", "Novelas Ejemplares", "multiple"},
                {343, "¿Quién es el autor de 'Esperando a Godot'?", "Samuel Beckett", "Eugene Ionesco", "Arthur Miller", "Samuel Beckett", "multiple"},
                {344, "¿Qué escritor argentino ganó el Nobel de Literatura en 1984?", "Jorge Luis Borges", "Adolfo Bioy Casares", "Julio Cortázar", "Carlos Fuentes", "multiple"},
                {345, "¿Qué género literario predomina en las obras de Shakespeare?", "Comedia", "Tragedia", "Poesía", "Drama", "multiple"},
                {346, "¿Qué escritor español fue representante del Modernismo?", "Rubén Darío", "Antonio Machado", "José Martí", "Rubén Darío", "multiple"},
                {347, "¿Quién escribió 'El camino'?", "Miguel Delibes", "Camilo José Cela", "Juan Goytisolo", "Miguel Delibes", "multiple"},
                {348, "¿Cuál es el tema central de 'La casa de Bernarda Alba'?", "La libertad", "El amor", "La muerte", "La opresión social", "multiple"},
                {349, "¿Quién es el autor de 'El señor de los anillos'?", "C.S. Lewis", "George R.R. Martin", "J.K. Rowling", "J.R.R. Tolkien", "multiple"},
                {350, "¿Qué obra escribió Gabriel García Márquez sobre el tiempo y la soledad?", "El otoño del patriarca", "El amor en los tiempos del cólera", "Crónica de una muerte anunciada", "Cien años de soledad", "multiple"},
                {351, "¿Quién fue la primera mujer en ganar el Premio Nobel de Literatura?", "Gabriela Mistral", "Toni Morrison", "Pearl S. Buck", "Selma Lagerlöf", "multiple"},
                {352, "¿Quién escribió 'Los versos satánicos'?", "Orhan Pamuk", "V. S. Naipaul", "Salman Rushdie", "Salman Rushdie", "multiple"},
                {353, "¿Qué novela escribió Mary Shelley?", "Frankenstein", "Drácula", "El extraño caso del Dr. Jekyll y Mr. Hyde", "Frankenstein", "multiple"},
                {354, "¿Qué poeta es conocido como 'el Príncipe de los Poetas' en España?", "Antonio Machado", "Juan Ramón Jiménez", "Luis de Góngora", "Luis de Góngora", "multiple"},
                {355, "¿Quién escribió 'Moby Dick'?", "Herman Melville", "Mark Twain", "Nathaniel Hawthorne", "Herman Melville", "multiple"},
                {356, "¿Cuál es el género literario de 'Cien años de soledad'?", "Realismo mágico", "Romanticismo", "Modernismo", "Realismo mágico", "multiple"},
                {357, "¿Quién escribió 'En busca del tiempo perdido'?", "Marcel Proust", "Albert Camus", "Victor Hugo", "Marcel Proust", "multiple"},
                {358, "¿Qué obra de Shakespeare es una tragedia sobre un príncipe danés?", "Otelo", "Macbeth", "Rey Lear", "Hamlet", "multiple"},
                {359, "¿Quién es el autor de 'La metamorfosis'?", "Franz Kafka", "Friedrich Nietzsche", "Albert Camus", "Franz Kafka", "multiple"},
                {360, "¿Cuál es la nacionalidad de Isabel Allende?", "Argentina", "México", "España", "Chile", "multiple"},
                {361, "¿Quién escribió 'Don Juan Tenorio'?", "José Zorrilla", "Gustavo Adolfo Bécquer", "Antonio Machado", "José Zorrilla", "multiple"},
                {362, "¿Cuál es la obra más famosa de Federico García Lorca?", "Poeta en Nueva York", "La casa de Bernarda Alba", "Romancero gitano", "La casa de Bernarda Alba", "multiple"},
                {363, "¿Quién es el autor del 'Cantar de mio Cid'?", "Anónimo", "Gonzalo de Berceo", "Juan Ruiz", "Anónimo", "multiple"},
                {364, "¿Qué poeta chileno escribió 'Veinte poemas de amor y una canción desesperada'?", "Pablo Neruda", "Gabriela Mistral", "Nicanor Parra", "Pablo Neruda", "multiple"},
                {365, "¿Quién escribió 'El Principito'?", "Antoine de Saint-Exupéry", "Jules Verne", "Victor Hugo", "Antoine de Saint-Exupéry", "multiple"},
                {366, "¿Qué novela de Franz Kafka describe la transformación de Gregor Samsa?", "El proceso", "El castillo", "La condena", "La metamorfosis", "multiple"},
                {367, "¿Qué poeta español es conocido por sus 'Rimas'?", "Gustavo Adolfo Bécquer", "Antonio Machado", "Juan Ramón Jiménez", "Gustavo Adolfo Bécquer", "multiple"},
                {368, "¿Quién escribió 'Ulises', considerada una obra maestra de la literatura modernista?", "James Joyce", "William Faulkner", "T.S. Eliot", "James Joyce", "multiple"},
                {369, "¿Qué escritor ruso es autor de 'Crimen y castigo'?", "Lev Tolstói", "Antón Chéjov", "Vladimir Nabokov", "Fiódor Dostoyevski", "multiple"},
                {370, "¿Qué poeta británico escribió 'Las flores del mal'?", "William Blake", "William Wordsworth", "John Keats", "Charles Baudelaire", "multiple"},
                {371, "¿Qué poeta español ganó el Nobel de Literatura en 1977?", "Vicente Aleixandre", "Jorge Guillén", "Luis Cernuda", "Vicente Aleixandre", "multiple"},
                {372, "¿Quién es el autor de 'La Iliada'?", "Homero", "Sófocles", "Eurípides", "Homero", "multiple"},
                {373, "¿Qué poeta colombiano es famoso por su obra 'Cien sonetos de amor'?", "Gabriel García Márquez", "Pablo Neruda", "Jorge Luis Borges", "Pablo Neruda", "multiple"},
                {374, "¿Quién escribió 'El Decamerón'?", "Geoffrey Chaucer", "Dante Alighieri", "Giovanni Boccaccio", "Giovanni Boccaccio", "multiple"},
                {375, "¿Cuál es el nombre del poema épico de Virgilio?", "La Odisea", "La Eneida", "El Cantar de mio Cid", "La Eneida", "multiple"},
                {376, "¿Quién es el autor de 'Cantos de vida y esperanza'?", "Rubén Darío", "José Martí", "Gabriela Mistral", "Rubén Darío", "multiple"},
                {377, "¿Qué escritora inglesa escribió 'Orgullo y prejuicio'?", "Emily Brontë", "Mary Shelley", "Charlotte Brontë", "Jane Austen", "multiple"},
                {378, "¿Qué poeta simbolista francés escribió 'El pájaro azul'?", "Paul Verlaine", "Stéphane Mallarmé", "Arthur Rimbaud", "Maurice Maeterlinck", "multiple"},
                {379, "¿Quién escribió 'La divina comedia'?", "Dante Alighieri", "Petrarca", "Giovanni Boccaccio", "Dante Alighieri", "multiple"},
                {380, "¿Qué poeta peruano es autor de 'Los heraldos negros'?", "César Vallejo", "Jorge Eduardo Eielson", "José María Arguedas", "César Vallejo", "multiple"},
                {381, "¿Quién escribió 'Pedro Páramo'?", "Gabriel García Márquez", "Julio Cortázar", "Juan Rulfo", "Juan Rulfo", "multiple"},
                {382, "¿Qué poeta francés es conocido por 'Las flores del mal'?", "Arthur Rimbaud", "Paul Verlaine", "Charles Baudelaire", "Charles Baudelaire", "multiple"},
                {383, "¿Quién escribió 'El Principito'?", "Jules Verne", "Victor Hugo", "Antoine de Saint-Exupéry", "Antoine de Saint-Exupéry", "multiple"},
                {384, "¿Qué autor escribió 'Crimen y castigo'?", "León Tolstói", "Nikolái Gógol", "Fiódor Dostoyevski", "Fiódor Dostoyevski", "multiple"},
                {385, "¿Cuál es el autor de la novela 'Moby Dick'?", "Nathaniel Hawthorne", "Edgar Allan Poe", "Herman Melville", "Herman Melville", "multiple"},
                {386, "¿Quién escribió 'La metamorfosis'?", "Thomas Mann", "Rainer Maria Rilke", "Franz Kafka", "Franz Kafka", "multiple"},
                {387, "¿Qué poeta chileno escribió 'Veinte poemas de amor y una canción desesperada'?", "Gabriela Mistral", "Nicanor Parra", "Pablo Neruda", "Pablo Neruda", "multiple"},
                {388, "¿Quién es el autor de 'Ulises'?", "William Faulkner", "T. S. Eliot", "James Joyce", "James Joyce", "multiple"},
                {389, "¿Cuál es el nombre del poema épico griego atribuido a Homero?", "La Odisea", "Los Trabajos y los Días", "La Ilíada", "La Ilíada", "multiple"},
                {390, "¿Quién escribió la tragedia 'Edipo Rey'?", "Eurípides", "Esquilo", "Sófocles", "Sófocles", "multiple"},
                {391, "¿Qué escritor británico es autor de 'Orgullo y prejuicio'?", "Charlotte Brontë", "Emily Brontë", "Jane Austen", "Jane Austen", "multiple"},
                {392, "¿Quién escribió 'En busca del tiempo perdido'?", "Albert Camus", "Jean-Paul Sartre", "Marcel Proust", "Marcel Proust", "multiple"},
                {393, "¿Cuál es el nombre del poeta inglés famoso por 'Paradise Lost'?", "William Blake", "John Keats", "John Milton", "John Milton", "multiple"},
                {394, "¿Qué poeta italiano escribió 'La Divina Comedia'?", "Giovanni Boccaccio", "Petrarca", "Dante Alighieri", "Dante Alighieri", "multiple"},
                {395, "¿Quién es el autor de 'Las mil y una noches'?", "Omar Khayyam", "Rumi", "Autores anónimos", "Autores anónimos", "multiple"},
                {396, "¿Quién escribió 'Madame Bovary'?", "Émile Zola", "Honoré de Balzac", "Gustave Flaubert", "Gustave Flaubert", "multiple"},
                {397, "¿Qué autor ruso escribió 'Anna Karénina'?", "Fiódor Dostoyevski", "Anton Chéjov", "León Tolstói", "León Tolstói", "multiple"},
                {398, "¿Quién escribió 'El retrato de Dorian Gray'?", "George Bernard Shaw", "H. G. Wells", "Oscar Wilde", "Oscar Wilde", "multiple"},
                {399, "¿Cuál es el autor de 'El conde de Montecristo'?", "Victor Hugo", "Honoré de Balzac", "Alejandro Dumas", "Alejandro Dumas", "multiple"},
                {400, "¿Quién escribió 'Hamlet'?", "Christopher Marlowe", "Ben Jonson", "William Shakespeare", "William Shakespeare", "multiple"}






        };

        for (Object[] d : preguntas) {
            int idPregunta = (int) d[0];

            // 1) Verificar si ya existe la pregunta con ese ID
            if (preguntaDao.findById(idPregunta).isEmpty()) {
                Pregunta q = new Pregunta();
                q.setId(idPregunta); // Asignamos el ID manual
                q.setCategoria(new Categoria(categoriaId, "Literatura"));
                q.setPregunta((String) d[1]);
                q.setRespuesta2((String) d[2]);
                q.setRespuesta3((String) d[3]);
                q.setRespuesta4((String) d[4]);
                q.setRespuestaCorrecta((String) d[5]);
                q.setTipo((String) d[6]);

                preguntaDao.save(q);
            } else {
                System.out.println("La pregunta con ID " + idPregunta + " ya existe. No se crea.");
            }
        }

        System.out.println("Proceso de creación de preguntas terminado.");
    }

}


