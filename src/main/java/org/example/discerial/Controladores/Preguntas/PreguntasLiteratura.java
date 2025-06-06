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
                {303, "¿Qué novela escribió Alejo Carpentier?", "El amor en los tiempos del cólera", "Crónica de una muerte anunciada", "El otoño del patriarca", "El reino de este mundo", "multiple"},
                {304, "¿Qué movimiento literario pertenece a Federico García Lorca?", "Modernismo", "Generación del 98", "Realismo", "Generación del 27", "multiple"},
                {305, "¿Quién es el autor de 'La casa de Bernarda Alba'?", "Antonio Machado", "Pablo Neruda", "Juan Ramón Jiménez", "Federico García Lorca", "multiple"},
                {306, "¿Qué novela existencial escribió J.D. Salinger?", "El gran Gatsby", "El guardián entre el centeno", "Matar a un ruiseñor", "1984", "multiple"},
                {307, "¿Cuál es la nacionalidad de Julio Cortázar?", "Española", "Mexicana", "Chilena", "Argentina", "multiple"},
                {308, "¿Quién escribió 'El Lazarillo de Tormes'?", "Miguel de Cervantes", "Lope de Vega", "Góngora", "Anónimo", "multiple"},
                {309, "¿Qué obra es de Lope de Vega?", "La vida es sueño", "La Regenta", "El Quijote", "Fuenteovejuna", "multiple"},
                {310, "¿Cuál es el tema principal de 'La Regenta' de Leopoldo Alas?", "Amor imposible", "La guerra civil", "La aventura", "La hipocresía social", "multiple"},
                {311, "¿Qué escritor español es conocido como 'el poeta del pueblo'?", "Antonio Machado", "Jorge Guillén", "Gabriel Celaya", "Miguel Hernández", "multiple"},
                {312, "¿Quién escribió 'Cien años de soledad'?", "Isabel Allende", "Mario Vargas Llosa", "Pablo Neruda", "Gabriel García Márquez", "multiple"},
                {313, "¿Qué género literario representa 'La Divina Comedia' de Dante Alighieri?", "Novela", "Ensayo", "Poesía lírica", "Poema épico", "multiple"},
                {314, "¿Quién es el autor de 'Fausto'?", "Goethe", "Shakespeare", "Victor Hugo", "Johann Wolfgang von Goethe", "multiple"},
                {315, "¿Cuál es la lengua original de la obra 'Hamlet'?", "Francés", "Italiano", "Español", "Inglés", "multiple"},
                {316, "¿Qué corriente literaria se caracteriza por el enfoque en la subjetividad y emociones?", "Realismo", "Naturalismo", "Clasicismo", "Romanticismo", "multiple"},
                {317, "¿Quién escribió 'El príncipe'?", "Antoine Saint Exupery", "Sócrates", "Platón", "Nicolás Maquiavelo", "multiple"},
                {318, "¿Qué escritor español es famoso por su obra de teatro 'El perro del hortelano'?", "Calderón de la Barca", "Federico García Lorca", "Tirso de Molina", "Lope de Vega", "multiple"},
                {319, "¿Quién es el autor de 'Pedro Páramo'?", "Carlos Fuentes", "Gabriel García Márquez", "Julio Cortázar", "Juan Rulfo", "multiple"},
                {320, "¿Qué obra escribió Shakespeare?", "El retrato de Dorian Gray", "El proceso", "Don Juan Tenorio", "Macbeth", "multiple"},
                {321, "¿Qué poeta español escribió 'Campos de Castilla'?", "Azorín", "Juan Ramón Jiménez", "Miguel Hernández", "Antonio Machado", "multiple"},
                {322, "¿Qué obra es considerada la primera novela moderna?", "El almuerzo desnudo", "El Lazarillo de Tormes", "La Celestina", "Don Quijote de la Mancha", "multiple"},
                {323, "¿Quién es el autor de 'Rayuela'?", "Jorge Luis Borges", "Mario Vargas Llosa", "Pablo Neruda", "Julio Cortázar", "multiple"},
                {324, "¿Qué escritor escribió 'El extranjero'?", "Jean-Paul Sartre", "Umberto Eco", "Victor Hugo", "Albert Camus", "multiple"},
                {325, "¿Cuál es el nombre del poema épico nacional de España?", "La Araucana", "OH capitán mi capitán", "La Eneida", "El Cantar del Mío Cid", "multiple"},
                {326, "¿Quién escribió 'El amor en los tiempos del cólera'?", "Isabel Allende", "Mario Vargas Llosa", "Juan Rulfo", "Gabriel García Márquez", "multiple"},
                {327, "¿Qué autor español es conocido por su obra 'Niebla'?", "Juan Ramón Jiménez", "Azorín", "Federico García Lorca", "Miguel de Unamuno", "multiple"},
                {328, "¿Qué novela comienza con la frase 'Call me Ishmael'?", "El Gran Gatsby", "Don Quijote", "Ulises", "Moby Dick", "multiple"},
                {329, "¿Quién es el autor de 'El retrato de Dorian Gray'?", "Samuel Beckett", "James Joyce", "Charles Dickens", "Oscar Wilde", "multiple"},
                {330, "¿Qué escritor argentino es conocido por sus cuentos cortos y obras de ficción fantástica?", "Julio Cortázar", "Mario Vargas Llosa", "Adolfo Bioy Casares", "Jorge Luis Borges", "multiple"},
                {331, "¿Quién escribió 'Cumbres borrascosas'?", "Charlotte Brontë", "Jane Austen", "Emily Dickinson", "Emily Brontë", "multiple"},
                {332, "¿En qué idioma escribió Dante Alighieri 'La Divina Comedia'?", "Latín", "Griego", "Francés", "Italiano", "multiple"},
                {333, "¿Qué autor escribió '1984' y 'Rebelión en la granja'?", "Ray Bradbury", "Aldous Huxley", "Philip K. Dick", "George Orwell", "multiple"},
                {334, "¿Quién escribió 'La sombra del viento'?", "Pérez Reverte", "Isabel Allende", "Javier Marías", "Carlos Ruiz Zafón", "multiple"},
                {335, "¿Cuál es la nacionalidad de Isabel Allende?", "Argentina", "España", "México", "Chile", "multiple"},
                {336, "¿Qué poeta español ganó el Premio Nobel en 1956?", "Vicente Aleixandre", "Antonio Machado", "Gabriel Celaya", "Juan Ramón Jiménez", "multiple"},
                {337, "¿Qué novela describe la decadencia de la burguesía en España y es obra de Benito Pérez Galdós?", "La Regenta", "Bodas de sangre", "Doña Perfecta", "Fortunata y Jacinta", "multiple"},
                {338, "¿Qué autor escribió la obra 'La familia de Pascual Duarte'?", "Antonio Gala", "Miguel Delibes", "Antonio Machado", "Camilo José Cela", "multiple"},
                {339, "¿Quién es el autor de 'El Aleph'?", "Juan Rulfo", "Julio Cortázar", "Gabriel García Márquez", "Jorge Luis Borges", "multiple"},
                {340, "¿Qué poeta chileno fue premio Nobel de Literatura en 1971?", "Julio Cortazar", "Gabriela Mistral", "Vicente Huidobro", "Pablo Neruda", "multiple"},
                {341, "¿Quién escribió 'La Odisea'?", "Sócrates", "Sófocles", "Virgilio", "Homero", "multiple"},
                {342, "¿Qué obra escribió Cervantes además de 'Don Quijote'?", "La Regenta", "La niebla", "La Celestina", "Novelas Ejemplares", "multiple"},
                {343, "¿Quién es el autor de 'Esperando a Godot'?", "James Joyce", "Eugene Ionesco", "Arthur Miller", "Samuel Beckett", "multiple"},
                {344, "¿Qué escritor argentino ganó el Nobel de Literatura en 1984?", "Jorge Luis Borges", "Adolfo Bioy Casares", "Julio Cortázar", "Carlos Fuentes", "multiple"},
                {345, "¿Qué género literario predomina en las obras de Shakespeare?", "Comedia", "Tragedia", "Poesía", "Drama", "multiple"},
                {346, "¿Qué escritor español fue representante del Modernismo?", "García Lorca", "Antonio Machado", "José Martí", "Rubén Darío", "multiple"},
                {347, "¿Quién escribió 'El camino'?", "Miguel de Unamuno", "Camilo José Cela", "Juan Goytisolo", "Miguel Delibes", "multiple"},
                {348, "¿Cuál es el tema central de 'La casa de Bernarda Alba'?", "La libertad", "El amor", "La muerte", "La opresión social", "multiple"},
                {349, "¿Quién es el autor de 'El señor de los anillos'?", "C.S. Lewis", "George R.R. Martin", "J.K. Rowling", "J.R.R. Tolkien", "multiple"},
                {350, "¿Qué novela escribió Truman Capote?", "El otoño del patriarca", "En el camino", "El guardián entre el centeno", "A sangre fría", "multiple"},
                {351, "¿Quién fue la primera mujer en ganar el Premio Nobel de Literatura?", "Gabriela Mistral", "Toni Morrison", "Pearl S. Buck", "Selma Lagerlöf", "multiple"},
                {352, "¿Quién escribió 'Los versos satánicos'?", "Orhan Pamuk", "V. S. Naipaul", "Percy B Shelley", "Salman Rushdie", "multiple"},
                {353, "¿Qué novela escribió Mary Shelley?", "Carmen", "Drácula", "El extraño caso del Dr. Jekyll y Mr. Hyde", "Frankenstein", "multiple"},
                {354, "¿Qué poeta es conocido como 'el Príncipe de los Poetas' en España?", "Antonio Machado", "Juan Ramón Jiménez", "Cernuda", "Luis de Góngora", "multiple"},
                {355, "¿Quién escribió el nombre de la rosa?", "Yourcenar", "Mark Twain", "Italo Calvino", "Umberto Eco", "multiple"},
                {356, "¿Cuál es el género literario de 'Cien años de soledad'?", "Neorrealismo", "Romanticismo", "Modernismo", "Realismo mágico", "multiple"},
                {357, "¿Quién escribió 'En busca del tiempo perdido'?", "Sartre", "Albert Camus", "Victor Hugo", "Marcel Proust", "multiple"},
                {358, "¿Qué obra de Shakespeare es una tragedia sobre un príncipe danés?", "Otelo", "Macbeth", "Rey Lear", "Hamlet", "multiple"},
                {359, "¿Quién es el autor de la novela de ciencia ficción un mundo feliz?", "Thomas Mann", "H.G Wells", "Isaac Asimov", "Aldous Huxley", "multiple"},
                {360, "¿Cuál es la nacionalidad de Isabel Allende?", "Argentina", "México", "España", "Chile", "multiple"},
                {361, "¿Quién escribió 'Don Juan Tenorio'?", "Calderón de la Barca", "Gustavo Adolfo Bécquer", "Antonio Machado", "José Zorrilla", "multiple"},
                {362, "¿Cuál es la obra más famosa de Federico García Lorca?", "Poeta en Nueva York", "Yerma", "Romancero gitano", "La casa de Bernarda Alba", "multiple"},
                {363, "¿Quién es el autor del 'Cantar de mio Cid'?", "Cervantes", "Gonzalo de Berceo", "Juan Ruiz", "Anónimo", "multiple"},
                {364, "¿Qué poeta chileno escribió 'Veinte poemas de amor y una canción desesperada'?", "Vargas Llosa", "Gabriela Mistral", "Nicanor Parra", "Pablo Neruda", "multiple"},
                {365, "¿Quién escribió Lolita?", "Paul Verlaine", "Jules Verne", "Victor Hugo", "Vladimir Nabokov", "multiple"},
                {366, "¿Qué novela de Franz Kafka describe la transformación de Gregor Samsa?", "El proceso", "El castillo", "La condena", "La metamorfosis", "multiple"},
                {367, "¿Qué poeta español es conocido por sus 'Rimas y Leyendas'?", "Cernuda", "Antonio Machado", "Juan Ramón Jiménez", "Gustavo Adolfo Bécquer", "multiple"},
                {368, "¿Quién escribió 'Ulises', considerada una obra maestra de la literatura modernista?", "Bernard Shaw", "William Faulkner", "T.S. Eliot", "James Joyce", "multiple"},
                {369, "¿Qué escritor ruso es autor de 'Crimen y castigo'?", "Lev Tolstói", "Antón Chéjov", "Vladimir Nabokov", "Fiódor Dostoyevski", "multiple"},
                {370, "¿Qué poeta británico escribió 'Las flores del mal'?", "William Blake", "William Wordsworth", "John Keats", "Charles Baudelaire", "multiple"},
                {371, "¿Qué poeta español ganó el Nobel de Literatura en 1977?", "Juan Ramón Jiménez", "Jorge Guillén", "Luis Cernuda", "Vicente Aleixandre", "multiple"},
                {372, "¿Quién es el autor de 'La Iliada'?", "Esquilo", "Sófocles", "Eurípides", "Homero", "multiple"},
                {373, "¿Qué poeta colombiano es famoso por su obra 'Cien sonetos de amor'?", "Gabriela Mistral", "Pablo Neruda", "Jorge Luis Borges", "Gabriel García Márquez", "multiple"},
                {374, "¿Quién escribió 'El Decamerón'?", "Geoffrey Chaucer", "Dante Alighieri", "Petrarca", "Giovanni Boccaccio", "multiple"},
                {375, "¿Cuál es el nombre del poema épico de Virgilio?", "La Odisea", "La Ilíada", "El Cantar de mio Cid", "La Eneida", "multiple"},
                {376, "¿Quién es el autor de 'Cantos de vida y esperanza'?", "Azorín", "José Martí", "Gabriela Mistral", "Rubén Darío", "multiple"},
                {377, "¿Quién es el autor de la novela el viejo y el mar?", "William Faulkner", "John Dos Passos", "Scott Fitzgerald", "Hemingway", "multiple"},
                {378, "¿Qué poeta simbolista francés escribió 'El pájaro azul'?", "Paul Verlaine", "Stéphane Mallarmé", "Arthur Rimbaud", "Maurice Maeterlinck", "multiple"},
                {379, "¿Quién escribió 'La divina comedia'?", "Homero", "Petrarca", "Giovanni Boccaccio", "Dante Alighieri", "multiple"},
                {380, "¿Qué poeta peruano es autor de 'Los heraldos negros'?", "Vargas Llosa", "Jorge Eduardo Eielson", "José María Arguedas", "César Vallejo", "multiple"},
                {381, "¿Quién escribió 'Pedro Páramo'?", "Gabriel García Márquez", "Julio Cortázar", "Byo Caceres", "Juan Rulfo", "multiple"},
                {382, "¿Qué poeta francés es conocido por 'Las flores del mal'?", "Arthur Rimbaud", "Paul Verlaine", "Marcel Proust", "Charles Baudelaire", "multiple"},
                {383, "¿Quién escribió 'El Principito'?", "Jules Verne", "Victor Hugo", "Marcel Proust", "Antoine de Saint-Exupéry", "multiple"},
                {384, "¿Qué autor escribió 'Crimen y castigo'?", "León Tolstói", "Nikolái Gógol", "Chéjov", "Fiódor Dostoyevski", "multiple"},
                {385, "¿Cuál es el autor de la novela 'Moby Dick'?", "Nathaniel Hawthorne", "Edgar Allan Poe", "Mark Twain", "Herman Melville", "multiple"},
                {386, "¿Quién escribió 'La metamorfosis'?", "Thomas Mann", "Rainer Maria Rilke", "Goethe", "Franz Kafka", "multiple"},
                {387, "¿Qué poeta chileno escribió 'Veinte poemas de amor y una canción desesperada'?", "Gabriela Mistral", "Nicanor Parra", "Juan Rulfo", "Pablo Neruda", "multiple"},
                {388, "¿Quién es el autor de la historia interminable?", "William Faulkner", "T. S. Eliot", "Bernard Shaw", "Michael Ende", "multiple"},
                {389, "¿Cuál es el nombre del poema épico griego atribuido a Homero?", "La Odisea", "Los Trabajos y los Días", "La Eneida", "La Ilíada", "multiple"},
                {390, "¿Quién escribió la tragedia 'Edipo Rey'?", "Eurípides", "Esquilo", "Homero", "Sófocles", "multiple"},
                {391, "¿Qué escritora británica es autor de 'Orgullo y prejuicio'?", "Charlotte Brontë", "Emily Brontë", "P. Merimee", "Jane Austen", "multiple"},
                {392, "¿Quién escribió 'En busca del tiempo perdido'?", "Albert Camus", "Jean-Paul Sartre", "Thomas Mann", "Marcel Proust", "multiple"},
                {393, "¿Cuál es el nombre del poeta inglés famoso por 'Paradise Lost'?", "William Blake", "John Keats", "Lord Byron", "John Milton", "multiple"},
                {394, "¿Quién escribió la novela las uvas de la ira?", "Hemingway", "Bukowski", "Truman Capote", "John Steinbeck", "multiple"},
                {395, "¿Quién es el autor de 'Las mil y una noches'?", "Omar Khayyam", "Rumi", "Kypling", "Autores anónimos", "multiple"},
                {396, "¿Quién escribió 'Madame Bovary'?", "Émile Zola", "Honoré de Balzac", "Marcel Proust", "Gustave Flaubert", "multiple"},
                {397, "¿Qué autor ruso escribió 'Anna Karénina'?", "Fiódor Dostoyevski", "Anton Chéjov", "Gógol", "León Tolstói", "multiple"},
                {398, "¿Quién escribió 'El retrato de Dorian Gray'?", "George Bernard Shaw", "H. G. Wells", "Samuel Beckett", "Oscar Wilde", "multiple"},
                {399, "¿Cuál es el autor de 'El conde de Montecristo'?", "Victor Hugo", "Honoré de Balzac", "Gustave Flaubert", "Alejandro Dumas", "multiple"},
                {400, "¿Quién escribió 'Hamlet'?", "Christopher Marlowe", "Ben Jonson", "John Keats", "William Shakespeare", "multiple"}






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


