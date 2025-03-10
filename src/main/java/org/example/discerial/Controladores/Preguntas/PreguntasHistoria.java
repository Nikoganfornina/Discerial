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
        List<Pregunta> preguntasHistoria = preguntaDao.findByCategoria(2); // Asegúrate de usar el ID correcto
        if (!preguntasHistoria.isEmpty()) {
            System.out.println("Ya existen preguntas para la categoría 'Historia'.");
            return;
        }

        String[][] preguntas = {
                {"¿Quién fue el primer presidente de Estados Unidos?", "George Washington", "Abraham Lincoln", "Thomas Jefferson", "John Adams", "George Washington"},
                {"¿En qué año terminó la Segunda Guerra Mundial?", "1945", "1944", "1946", "1939", "1945"},
                {"¿Qué imperio construyó la Gran Muralla China?", "Imperio chino", "Imperio mongol", "Imperio romano", "Imperio persa", "Imperio chino"},
                {"¿Cuál fue el evento que dio inicio a la Primera Guerra Mundial?", "Asesinato del archiduque Francisco Fernando", "Invasión de Polonia", "La firma del Tratado de Versalles", "El hundimiento del Lusitania", "Asesinato del archiduque Francisco Fernando"},
                {"¿Qué imperio cayó en 476 d.C.?", "Imperio Romano de Occidente", "Imperio Romano de Oriente", "Imperio Bizantino", "Imperio Otomano", "Imperio Romano de Occidente"},
                {"¿Qué famoso líder revolucionario cubano estuvo al mando durante la Revolución Cubana?", "Fidel Castro", "Ernesto 'Che' Guevara", "Carlos Manuel de Céspedes", "José Martí", "Fidel Castro"},
                {"¿Quién fue el último zar de Rusia?", "Nicolás II", "Pedro el Grande", "Alejandro III", "Iván el Terrible", "Nicolás II"},
                {"¿En qué guerra se utilizó por primera vez la aviación de combate?", "Primera Guerra Mundial", "Segunda Guerra Mundial", "Guerra de Corea", "Guerra de Vietnam", "Primera Guerra Mundial"},
                {"¿Quién fue el líder de la independencia de India?", "Mahatma Gandhi", "Jawaharlal Nehru", "Sardar Patel", "Subhas Chandra Bose", "Mahatma Gandhi"},
                {"¿En qué año cayó el Muro de Berlín?", "1989", "1987", "1990", "1985", "1989"},
                {"¿Cuál fue la causa principal de la Revolución Francesa?", "Desigualdad social y económica", "La invasión napoleónica", "El fracaso del sistema feudal", "La influencia de las ideas ilustradas", "Desigualdad social y económica"},
                {"¿Qué evento histórico desencadenó la Guerra Civil Americana?", "La batalla de Fort Sumter", "La elección de Abraham Lincoln", "El ataque a Pearl Harbor", "La abdicación de Luis XVI", "La batalla de Fort Sumter"},
                {"¿Quién fue el líder de los nazis durante la Segunda Guerra Mundial?", "Adolf Hitler", "Hermann Göring", "Joseph Goebbels", "Heinrich Himmler", "Adolf Hitler"},
                {"¿En qué año se firmó la Declaración de Independencia de los Estados Unidos?", "1776", "1775", "1781", "1787", "1776"},
                {"¿Qué batalla marcó el final de las Guerras Napoleónicas?", "Batalla de Waterloo", "Batalla de Leipzig", "Batalla de Austerlitz", "Batalla de Trafalgar", "Batalla de Waterloo"},
                {"¿Qué tratado puso fin a la Primera Guerra Mundial?", "Tratado de Versalles", "Tratado de París", "Tratado de Tordesillas", "Tratado de Utrecht", "Tratado de Versalles"},
                {"¿En qué año se produjo el ataque a Pearl Harbor?", "1941", "1939", "1942", "1940", "1941"},
                {"¿Quién fue el líder militar que derrotó a Napoleón en la batalla de Leipzig?", "Príncipe de Schwarzenberg", "Duque de Wellington", "Mariscal Ney", "Guillaume de Nassau", "Príncipe de Schwarzenberg"},
                {"¿Qué país fue el primero en declarar la guerra a Alemania en la Segunda Guerra Mundial?", "Polonia", "Reino Unido", "Francia", "Canadá", "Polonia"},
                {"¿Quién fue el líder de la Revolución Mexicana?", "Emiliano Zapata", "Pancho Villa", "Venustiano Carranza", "Francisco I. Madero", "Emiliano Zapata"},
                {"¿En qué año se disolvió la Unión Soviética?", "1991", "1989", "1990", "1985", "1991"},
                {"¿Qué país fue invadido por Alemania en 1939, lo que dio inicio a la Segunda Guerra Mundial?", "Polonia", "Francia", "Bélgica", "Checoslovaquia", "Polonia"},
                {"¿Qué monarca inglés se separó de la Iglesia Católica en el siglo XVI?", "Enrique VIII", "Carlos I", "Eduardo VI", "Isabel I", "Enrique VIII"},
                {"¿En qué año fue asesinado John F. Kennedy?", "1963", "1965", "1962", "1964", "1963"},
                {"¿Quién fue el líder de los aliados durante la Segunda Guerra Mundial?", "Winston Churchill", "Franklin D. Roosevelt", "Charles de Gaulle", "Dwight D. Eisenhower", "Winston Churchill"},
                {"¿Qué país estaba bajo el control de la Alemania nazi durante la invasión de 1940?", "Francia", "Polonia", "Bélgica", "Holanda", "Francia"},
                {"¿Qué tratado puso fin a la Guerra Fría?", "Tratado de Helsinki", "Tratado INF", "Tratado de no proliferación nuclear", "Tratado de París", "Tratado INF"},
                {"¿En qué país comenzó la Revolución Industrial?", "Inglaterra", "Francia", "Alemania", "Estados Unidos", "Inglaterra"},
                {"¿Cuál fue el primer imperio que dominó casi todo el mundo conocido en la Edad Antigua?", "Imperio Romano", "Imperio Persa", "Imperio Macedonio", "Imperio Egipcio", "Imperio Romano"},
                {"¿Quién fue el creador del Imperio Mongol?", "Gengis Kan", "Kublai Kan", "Batu Kan", "Tamerlán", "Gengis Kan"},
                {"¿Qué emperador romano adoptó el cristianismo como religión oficial del imperio?", "Constantino I", "Nerva", "Trajano", "Teodosio", "Constantino I"},
                {"¿Qué evento histórico tuvo lugar el 14 de julio de 1789?", "La toma de la Bastilla", "La firma de la Declaración de los Derechos del Hombre", "La ejecución de Luis XVI", "La proclamación de la República", "La toma de la Bastilla"},
                {"¿Qué evento histórico marcó el fin de la Edad Media?", "La caída de Constantinopla", "La firma de la Carta Magna", "La invasión mongola", "La peste negra", "La caída de Constantinopla"},
                {"¿Quién fue el principal líder del Movimiento de los Derechos Civiles en Estados Unidos?", "Martin Luther King Jr.", "Malcolm X", "Rosa Parks", "Frederick Douglass", "Martin Luther King Jr."},
                {"¿Qué imperio existió en la península ibérica durante la Edad Media?", "El Imperio Visigodo", "El Imperio Romano", "El Imperio Islámico", "El Imperio Franco", "El Imperio Islámico"},
                {"¿Qué país fue el primero en ser colonizado por los españoles en América?", "República Dominicana", "México", "Cuba", "Puerto Rico", "República Dominicana"}
        };

        // Insertamos las preguntas en la base de datos
        for (String[] datos : preguntas) {
            Pregunta pregunta = new Pregunta();
            pregunta.setCategoria(new Categoria(2 ,"Historia"));
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
