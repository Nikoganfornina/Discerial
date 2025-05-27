package org.example.discerial.Controladores.Preguntas;

import org.example.discerial.DAO.IPregunta;
import org.example.discerial.DAO.IPreguntaImpl;
import org.example.discerial.entities.Categoria;
import org.example.discerial.entities.Pregunta;

import java.util.List;

public class PreguntasHistoria {

    public static void crearPreguntasHistoria() {
        IPregunta preguntaDao = new IPreguntaImpl();
        int categoriaId = 1; // ID para Filosofia

        // Lista de preguntas con ID manual (añadido un campo id en la posición 0)
        Object[][] preguntas = {


                {201, "¿En qué año comenzó la Primera Guerra Mundial?", "1912", "1913", "1915", "1914", "multiple"},
                {202, "¿Quién fue el líder de la Revolución Cubana?", "Che Guevara", "Fulgencio Batista", "Raúl Castro", "Fidel Castro", "multiple"},
                {203, "¿Qué imperio construyó la Muralla China?", "Imperio Mongol", "Imperio Han", "Imperio Tang", "Imperio Qin", "multiple"},
                {204, "¿En qué año cayó el Muro de Berlín?", "1987", "1988", "1990", "1989", "multiple"},
                {205, "¿Quién fue el primer emperador romano?", "Julio César", "Nerón", "Calígula", "Augusto", "multiple"},
                {206, "¿Qué civilización construyó Machu Picchu?", "Mayas", "Aztecas", "Olmecas", "Incas", "multiple"},
                {207, "¿En qué año se firmó la Declaración de Independencia de Estados Unidos?", "1775", "1777", "1780", "1776", "multiple"},
                {208, "¿Quién fue el líder nazi durante la Segunda Guerra Mundial?", "Joseph Goebbels", "Hermann Göring", "Heinrich Himmler", "Adolf Hitler", "multiple"},
                {209, "¿Cuál fue la causa principal de la Guerra de Secesión en Estados Unidos?", "Impuestos", "Religión", "Territorio", "Esclavitud", "multiple"},
                {210, "¿Qué país fue el primero en abolir la esclavitud?", "Estados Unidos", "Brasil", "Reino Unido", "Haití", "multiple"},
                {211, "¿Quién fue el conquistador del Imperio Azteca?", "Francisco Pizarro", "Pedro de Alvarado", "Diego de Almagro", "Hernán Cortés", "multiple"},
                {212, "¿En qué año comenzó la Revolución Francesa?", "1788", "1790", "1791", "1789", "multiple"},
                {213, "¿Qué tratado puso fin a la Primera Guerra Mundial?", "Tratado de París", "Tratado de Tordesillas", "Tratado de Utrecht", "Tratado de Versalles", "multiple"},
                {214, "¿Quién fue el primer presidente de Sudáfrica tras el apartheid?", "Jacob Zuma", "Thabo Mbeki", "Frederik de Klerk", "Nelson Mandela", "multiple"},
                {215, "¿Qué civilización antigua es conocida por sus pirámides?", "Mesopotámica", "Griega", "Romana", "Egipcia", "multiple"},
                {216, "¿En qué año se descubrió América por Cristóbal Colón?", "1490", "1495", "1500", "1492", "multiple"},
                {217, "¿Quién fue el líder de la independencia de la India?", "Jawaharlal Nehru", "Subhas Chandra Bose", "Bhagat Singh", "Mahatma Gandhi", "multiple"},
                {218, "¿Qué país fue invadido por Alemania para iniciar la Segunda Guerra Mundial?", "Francia", "Checoslovaquia", "Austria", "Polonia", "multiple"},
                {219, "¿Qué invento revolucionó la producción industrial en el siglo XVIII?", "Telar mecánico", "Motor de combustión", "Telégrafo", "Máquina de vapor", "multiple"},
                {220, "¿Quién fue el emperador francés que conquistó gran parte de Europa?", "Luis XIV", "Carlos Magno", "Luis XVI", "Napoleón Bonaparte", "multiple"},
                {221, "¿En qué conflicto se utilizó por primera vez la bomba atómica?", "Primera Guerra Mundial", "Guerra de Corea", "Guerra de Vietnam", "Segunda Guerra Mundial", "multiple"},
                {222, "¿Qué imperio cayó en 1453 con la toma de Constantinopla?", "Imperio Romano", "Imperio Carolingio", "Imperio Austrohúngaro", "Imperio Bizantino", "multiple"},
                {223, "¿Quién fue el famoso faraón cuyo tesoro fue hallado intacto?", "Ramsés II", "Cleopatra", "Akenatón", "Tutankamón", "multiple"},
                {224, "¿Qué evento marcó el inicio de la Edad Moderna?", "Caída del Imperio Romano", "Guerra de los Cien Años", "Reforma Protestante", "Descubrimiento de América", "multiple"},
                {225, "¿Qué país inició la Revolución Industrial?", "Francia", "Alemania", "Estados Unidos", "Reino Unido", "multiple"},
                {226, "¿Quién lideró la unificación de Alemania en el siglo XIX?", "Guillermo I", "Federico II", "Adolf Hitler", "Otto von Bismarck", "multiple"},
                {227, "¿Cuál fue el principal conflicto de la Guerra Fría?", "Guerra de Corea", "Guerra de Vietnam", "Invasión de Afganistán", "Carrera armamentista", "multiple"},
                {228, "¿En qué batalla fue derrotado Napoleón definitivamente?", "Batalla de Trafalgar", "Batalla de Austerlitz", "Batalla de Leipzig", "Batalla de Waterloo", "multiple"},
                {229, "¿Qué cultura mesoamericana inventó un complejo calendario?", "Incas", "Olmecas", "Toltecas", "Mayas", "multiple"},
                {230, "¿Qué civilización construyó el Partenón?", "Egipcia", "Romana", "Fenicia", "Griega", "multiple"},
                {231, "¿Cuál fue la primera civilización de la humanidad?", "Romana", "Egipcia", "China", "Mesopotámica", "multiple"},
                {232, "¿Qué documento limitó por primera vez el poder del rey en Inglaterra?", "Bill of Rights", "Declaración de Derechos del Hombre", "Paz de Westfalia", "Carta Magna", "multiple"},
                {233, "¿Qué guerra inspiró la creación de la ONU?", "Guerra de Vietnam", "Primera Guerra Mundial", "Guerra de Crimea", "Segunda Guerra Mundial", "multiple"},
                {234, "¿Quién escribió 'El Príncipe', influyente obra política del Renacimiento?", "Tomás Moro", "Erasmo de Rotterdam", "Galileo Galilei", "Nicolás Maquiavelo", "multiple"},
                {235, "¿Qué conflicto dividió a Vietnam en dos estados?", "Guerra de Corea", "Guerra de Camboya", "Guerra Civil China", "Guerra de Vietnam", "multiple"},
                {236, "¿Qué revolución puso fin a la monarquía absoluta en Francia?", "Revolución Industrial", "Revolución Americana", "Revolución de 1848", "Revolución Francesa", "multiple"},
                {237, "¿Qué país fue colonizado por los británicos y luego dirigido por Gandhi?", "Pakistán", "Sudáfrica", "Bangladés", "India", "multiple"},
                {238, "¿Quién fue el dictador español durante gran parte del siglo XX?", "Miguel Primo de Rivera", "José María Aznar", "Juan Carlos I", "Francisco Franco", "multiple"},
                {239, "¿Qué tratado dividió el mundo entre España y Portugal?", "Tratado de Utrecht", "Tratado de Versalles", "Tratado de París", "Tratado de Tordesillas", "multiple"},
                {240, "¿Qué país fue invadido por Napoleón en su fallida campaña oriental?", "Alemania", "Austria", "Grecia", "Rusia", "multiple"},
                {241, "¿Qué muro dividió Berlín durante la Guerra Fría?", "Muro de Londres", "Muro de Viena", "Muro de París", "Muro de Berlín", "multiple"},
                {242, "¿Quién fue el primer presidente de Estados Unidos?", "Abraham Lincoln", "Thomas Jefferson", "John Adams", "George Washington", "multiple"},
                {243, "¿En qué siglo ocurrió la Revolución Francesa?", "XVI", "XVII", "XX", "XVIII", "multiple"},
                {244, "¿Qué emperador romano legalizó el cristianismo?", "Nerón", "Augusto", "Tito", "Constantino", "multiple"},
                {245, "¿Quién fue el líder de los bolcheviques en la Revolución Rusa?", "Stalin", "Trotsky", "Gorbachov", "Lenin", "multiple"},
                {246, "¿Qué ciudad fue destruida por una erupción del Vesubio en el año 79 d.C.?", "Roma", "Nápoles", "Florencia", "Pompeya", "multiple"},
                {247, "¿Cuál fue el motivo de las Cruzadas?", "Expandir el comercio", "Buscar nuevas rutas marítimas", "Conquistar el imperio romano", "Recuperar Tierra Santa", "multiple"},
                {248, "¿Qué imperio gobernó gran parte de América del Sur antes de la llegada de los españoles?", "Azteca", "Maya", "Tolteca", "Inca", "multiple"},
                {249, "¿Quién fue el explorador que llegó a América en 1492?", "Américo Vespucio", "Fernando de Magallanes", "Vasco da Gama", "Cristóbal Colón", "multiple"},
                {250, "¿Cuál fue el principal objetivo de la Revolución Industrial?", "Expandir la agricultura", "Mejorar la educación", "Reducir la población", "Incrementar la producción con máquinas", "multiple"},
                {251, "¿Qué país fue el primero en enviar un satélite artificial al espacio?", "Estados Unidos", "China", "Japón", "Unión Soviética", "multiple"},
                {252, "¿Quién fue el líder del movimiento de derechos civiles en Estados Unidos en los años 60?", "Malcolm X", "Rosa Parks", "Elijah Muhammad", "Martin Luther King Jr.", "multiple"},
                {253, "¿Qué evento desencadenó la Primera Guerra Mundial?", "Invasión de Polonia", "Revolución Rusa", "Caída del Muro de Berlín", "Asesinato del archiduque Francisco Fernando", "multiple"},
                {254, "¿Qué tratado puso fin a la Guerra de los Cien Años?", "Tratado de Versalles", "Tratado de París", "Tratado de Tordesillas", "Tratado de Bretigny", "multiple"},
                {255, "¿Quién fue el primer astronauta en pisar la Luna?", "Buzz Aldrin", "Yuri Gagarin", "Michael Collins", "Neil Armstrong", "multiple"},
                {256, "¿Cuál fue el resultado principal de la Revolución Americana?", "Independencia de Canadá", "Fin de la monarquía británica", "Expansión colonial británica", "Independencia de Estados Unidos", "multiple"},
                {257, "¿Qué evento inició la Gran Depresión?", "Fin de la Primera Guerra Mundial", "Crisis petrolera", "Revolución Rusa", "Caída de la Bolsa de Nueva York en 1929", "multiple"},
                {258, "¿Quién fue el líder de la Unión Soviética durante la Segunda Guerra Mundial?", "Lenin", "Khrushchev", "Brezhnev", "Stalin", "multiple"},
                {259, "¿En qué año se firmó el Tratado de Versalles?", "1918", "1920", "1917", "1919", "multiple"},
                {260, "¿Qué país estuvo dividido en dos después de la Segunda Guerra Mundial?", "Italia", "Francia", "Polonia", "Alemania", "multiple"},
                {261, "¿Quién fue el principal líder de la independencia de México?", "Benito Juárez", "Porfirio Díaz", "Emiliano Zapata", "Miguel Hidalgo", "multiple"},
                {262, "¿Qué evento dio inicio a la Revolución Rusa de 1917?", "Revolución de Octubre", "Guerra Civil Rusa", "Golpe de Estado", "Revolución de Febrero", "multiple"},
                {263, "¿Quién fue el presidente estadounidense durante la Gran Depresión?", "Harry Truman", "Dwight Eisenhower", "Franklin D. Roosevelt", "Herbert Hoover", "multiple"},
                {264, "¿Qué evento marcó el fin de la Edad Media?", "Caída del Imperio Romano", "La Peste Negra", "Revolución Francesa", "Descubrimiento de América", "multiple"},
                {265, "¿Quién fue el líder de la unificación italiana?", "Giuseppe Garibaldi", "Victor Emmanuel II", "Benito Mussolini", "Camillo Cavour", "multiple"},
                {266, "¿Qué ciudad fue sede de los juicios por crímenes de guerra después de la Segunda Guerra Mundial?", "Helsinki", "Ginebra", "Roma", "Núremberg", "multiple"},
                {267, "¿Qué acontecimiento provocó la Guerra Civil Española?", "Invasión de Francia", "Muerte de Franco", "Proclamación de la República", "Golpe de Estado de 1936", "multiple"},
                {268, "¿Qué imperio fue gobernado por Gengis Kan?", "Imperio Romano", "Imperio Otomano", "Imperio Bizantino", "Imperio Mongol", "multiple"},
                {269, "¿Qué filósofo griego fue maestro de Alejandro Magno?", "Sócrates", "Platón", "Pitagoras", "Aristóteles", "multiple"},
                {270, "¿Cuál fue la causa principal de la Revolución Industrial?", "Cambio climático", "Guerras napoleónicas", "Descubrimiento de América", "Innovaciones tecnológicas", "multiple"},
                {271, "¿Quién fue el líder de la independencia de Argentina?", "Simón Bolívar", "Bernardino Rivadavia", "Manuel Belgrano", "José de San Martín", "multiple"},
                {272, "¿En qué año comenzó la Segunda Guerra Mundial?", "1937", "1938", "1940", "1939", "multiple"},
                {273, "¿Qué país fue el principal enemigo de Inglaterra durante las guerras napoleónicas?", "Rusia", "España", "Austria", "Francia", "multiple"},
                {274, "¿Quién fue el líder de la Revolución Cubana?", "Che Guevara", "Raúl Castro", "Fulgencio Batista", "Fidel Castro", "multiple"},
                {275, "¿Qué invento permitió la Revolución Industrial?", "Telar manual", "Motor de combustión", "Electricidad", "Máquina de vapor", "multiple"},
                {276, "¿Qué imperio construyó la Gran Muralla China?", "Imperio Mongol", "Imperio Han", "Imperio Tang", "Imperio Qin", "multiple"},
                {277, "¿Quién fue el primer presidente de Estados Unidos?", "Thomas Jefferson", "John Adams", "Abraham Lincoln", "George Washington", "multiple"},
                {278, "¿En qué año cayó el Muro de Berlín?", "1990", "1988", "1987", "1989", "multiple"},
                {279, "¿Qué ciudad fue destruida por la erupción del Vesubio?", "Roma", "Nápoles", "Herculano", "Pompeya", "multiple"},
                {280, "¿Quién fue el dictador de España durante el siglo XX?", "Miguel Primo de Rivera", "Juan Carlos I", "José María Aznar", "Francisco Franco", "multiple"},

                {281, "¿Qué líder soviético implementó las reformas de la Perestroika?", "Stalin", "Lenin", "Jruschov", "Gorbachov", "multiple"},
                {282, "¿Qué país fue invadido por Alemania al iniciar la Segunda Guerra Mundial?", "Francia", "Bélgica", "Checoslovaquia", "Polonia", "multiple"},
                {283, "¿Qué evento marcó el inicio de la Revolución Francesa?", "Ejecución de Luis XVI", "Toma de Versalles", "Declaración de los Derechos del Hombre", "Toma de la Bastilla", "multiple"},
                {284, "¿Qué civilización antigua floreció en la isla de Creta?", "Micénica", "Espartana", "Romana", "Minoica", "multiple"},
                {285, "¿Cuál fue la principal ruta comercial entre Europa y Asia en la Edad Media?", "Ruta del Ámbar", "Ruta Marítima del Sur", "Ruta del Especiero", "Ruta de la Seda", "multiple"},
                {286, "¿Qué emperador romano legalizó el cristianismo?", "Nerón", "Julio César", "Trajano", "Constantino", "multiple"},
                {287, "¿Quién fue el dictador español durante gran parte del siglo XX?", "Primo de Rivera", "Carlos V", "Alfonso XIII", "Francisco Franco", "multiple"},
                {288, "¿Qué guerra enfrentó a Esparta contra Atenas?", "Guerras Médicas", "Guerra de Troya", "Guerra de Sicilia", "Guerra del Peloponeso", "multiple"},
                {289, "¿Quién fue el explorador que llegó a América en 1492?", "Vasco da Gama", "Fernando de Magallanes", "Américo Vespucio", "Cristóbal Colón", "multiple"},
                {290, "¿Qué civilización desarrolló el sistema de castas?", "China", "Egipto", "Mesopotamia", "India", "multiple"},
                {291, "¿Qué país fue conocido como la Prusia Oriental?", "Austria", "Lituania", "Polonia", "Alemania", "multiple"},
                {292, "¿Qué documento proclamó la independencia de Estados Unidos?", "Constitución", "Tratado de Versalles", "Acta de Unión", "Declaración de Independencia", "multiple"},
                {293, "¿Quién fue el líder comunista de la Revolución Rusa?", "Trotsky", "Stalin", "Kerensky", "Lenin", "multiple"},
                {294, "¿Qué imperio construyó la Gran Muralla China?", "Han", "Ming", "Tang", "Qin", "multiple"},
                {295, "¿Qué año cayó el Muro de Berlín?", "1985", "1988", "1991", "1989", "multiple"},
                {296, "¿Qué civilización precolombina habitó en la región andina?", "Tolteca", "Olmeca", "Zapoteca", "Inca", "multiple"},
                {297, "¿Quién fue el último zar de Rusia?", "Pedro el Grande", "Nicolás I", "Alejandro II", "Nicolás II", "multiple"},
                {298, "¿Qué ciudad fue destruida por la erupción del Vesubio?", "Roma", "Nápoles", "Capua", "Pompeya", "multiple"},
                {299, "¿Qué movimiento impulsó Lutero en el siglo XVI?", "Humanismo", "Renacimiento", "Contrarreforma", "Reforma Protestante", "multiple"},
                {300, "¿Qué conflicto colonial enfrentó a Inglaterra y Francia en América del Norte?", "Guerra del Pacífico", "Guerra de Independencia", "Guerra Civil Americana", "Guerra de los Siete Años", "multiple"}




        };

        for (Object[] d : preguntas) {
            int idPregunta = (int) d[0];

            // 1) Verificar si ya existe la pregunta con ese ID
            if (preguntaDao.findById(idPregunta).isEmpty()) {
                Pregunta q = new Pregunta();
                q.setId(idPregunta); // Asignamos el ID manual
                q.setCategoria(new Categoria(categoriaId, "Historia"));
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
