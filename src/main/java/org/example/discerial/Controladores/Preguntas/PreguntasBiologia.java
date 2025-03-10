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
        List<Pregunta> preguntasBiologia = preguntaDao.findByCategoria(4); // Asegúrate de usar el ID correcto
        if (!preguntasBiologia.isEmpty()) {
            System.out.println("Ya existen preguntas para la categoría 'Biología'.");
            return;
        }

        String[][] preguntas = {
                {"¿Cuál es la molécula que almacena la información genética?", "ADN", "ARN", "Proteínas", "Carbohidratos", "ADN", "https://es.wikipedia.org/wiki/ADN", "multiple"},
                {"¿Qué partícula subatómica tiene carga negativa?", "Electrón", "Protón", "Neutrón", "Quark", "Electrón", "https://es.wikipedia.org/wiki/Part%C3%ADcula_subat%C3%B3mica", "multiple"},
                {"¿Quién es conocido como el padre de la teoría de la evolución?", "Charles Darwin", "Gregor Mendel", "Louis Pasteur", "Jean-Baptiste Lamarck", "Charles Darwin", "https://es.wikipedia.org/wiki/Charles_Darwin", "multiple"},
                {"¿Qué molécula es la principal fuente de energía para las células?", "ATP", "ADN", "ARN", "Glucosa", "ATP", "https://es.wikipedia.org/wiki/Adenos%C3%ADn_trifosfato", "multiple"},
                {"¿Cuál de las siguientes es una célula eucariota?", "Célula animal", "Célula bacteriana", "Célula procariota", "Virus", "Célula animal", "https://es.wikipedia.org/wiki/C%C3%A9lula_eucariota", "multiple"},
                {"¿Cómo se llama el proceso mediante el cual las plantas producen su propio alimento?", "Fotosíntesis", "Respiración celular", "Fermentación", "Transpiración", "Fotosíntesis", "https://es.wikipedia.org/wiki/Fotos%C3%ADntesis", "multiple"},
                {"¿Qué orgánulo celular es responsable de la producción de energía?", "Mitocondria", "Núcleo", "Retículo endoplasmático", "Cloroplasto", "Mitocondria", "https://es.wikipedia.org/wiki/Mitocondria", "multiple"},
                {"¿Qué tipo de moléculas son los enzimas?", "Proteínas", "Ácidos nucleicos", "Carbohidratos", "Lípidos", "Proteínas", "https://es.wikipedia.org/wiki/Enzima", "multiple"},
                {"¿Qué elemento químico es el principal componente de los ácidos nucleicos?", "Carbono", "Oxígeno", "Nitrógeno", "Fósforo", "Carbono", "https://es.wikipedia.org/wiki/Ácido_nucleico", "multiple"},
                {"¿En qué parte de la célula se encuentra el núcleo?", "Citoplasma", "Membrana plasmática", "Núcleo", "Mitocondria", "Citoplasma", "https://es.wikipedia.org/wiki/N%C3%BAcleo_(biolog%C3%ADa)", "multiple"},
                {"¿Qué tipo de célula carece de núcleo?", "Célula procariota", "Célula eucariota", "Célula animal", "Célula vegetal", "Célula procariota", "https://es.wikipedia.org/wiki/C%C3%A9lula_procariota", "multiple"},
                {"¿Cuál es la función principal de los ribosomas?", "Sintetizar proteínas", "Producir energía", "Almacenar información genética", "Controlar el metabolismo celular", "Sintetizar proteínas", "https://es.wikipedia.org/wiki/Ribosoma", "multiple"},
                {"¿Qué órgano en el cuerpo humano es responsable de bombear la sangre?", "Corazón", "Hígado", "Estómago", "Riñones", "Corazón", "https://es.wikipedia.org/wiki/Coraz%C3%B3n", "multiple"},
                {"¿Cómo se llama el proceso en el que las células se dividen para formar células hijas?", "Mitosis", "Meiosis", "Fertilización", "Ciclosis", "Mitosis", "https://es.wikipedia.org/wiki/Mitosis", "multiple"},
                {"¿Cuál es el componente principal de las paredes celulares de las plantas?", "Celulosa", "Quitina", "Colágeno", "Lignina", "Celulosa", "https://es.wikipedia.org/wiki/Celulosa", "multiple"},
                {"¿Qué tipo de enlace se forma cuando los átomos comparten electrones?", "Enlace covalente", "Enlace iónico", "Enlace metálico", "Enlace de hidrógeno", "Enlace covalente", "https://es.wikipedia.org/wiki/Enlace_covalente", "multiple"},
                {"¿Qué vitamina es conocida por su papel en la coagulación sanguínea?", "Vitamina K", "Vitamina C", "Vitamina D", "Vitamina A", "Vitamina K", "https://es.wikipedia.org/wiki/Vitamina_K", "multiple"},
                {"¿Qué tipo de ácidos nucleicos están involucrados en la síntesis de proteínas?", "ARN mensajero", "ADN", "ARN ribosómico", "ARN de transferencia", "ARN mensajero", "https://es.wikipedia.org/wiki/ARN_mensajero", "multiple"},
                {"¿En qué fase de la mitosis se alinean los cromosomas en el centro de la célula?", "Metafase", "Profase", "Anafase", "Telofase", "Metafase", "https://es.wikipedia.org/wiki/Mitosis", "multiple"},
                {"¿Qué parte de la célula es responsable del control y coordinación de las actividades celulares?", "Núcleo", "Mitocondria", "Retículo endoplasmático", "Citoesqueleto", "Núcleo", "https://es.wikipedia.org/wiki/N%C3%BAcleo_(biolog%C3%ADa)", "multiple"},
                {"¿Qué compuesto es esencial para la fotosíntesis y está presente en los cloroplastos?", "Clorofila", "Caroteno", "Ficoeritrina", "Ficocianina", "Clorofila", "https://es.wikipedia.org/wiki/Clorofila", "multiple"},
                {"¿Cuál de los siguientes es un tipo de tejido animal?", "Tejido epitelial", "Tejido muscular", "Tejido conectivo", "Todos los anteriores", "Todos los anteriores", "https://es.wikipedia.org/wiki/Tejido_animal", "multiple"},
                {"¿Qué tipo de célula tiene una pared celular hecha de quitina?", "Células fúngicas", "Células animales", "Células bacterianas", "Células vegetales", "Células fúngicas", "https://es.wikipedia.org/wiki/Fungi", "multiple"},
                {"¿Qué función tiene la clorofila en las plantas?", "Absorber luz para la fotosíntesis", "Almacenar energía", "Proteger contra enfermedades", "Producir proteínas", "Absorber luz para la fotosíntesis", "https://es.wikipedia.org/wiki/Clorofila", "multiple"},
                {"¿Qué tipo de sangre se puede donar a cualquier persona?", "O-", "AB+", "A+", "B-", "O-", "https://es.wikipedia.org/wiki/Grupo_sangu%C3%ADneo", "multiple"},
                {"¿Cuál es la fórmula química del agua?", "H2O", "CO2", "O2", "H2O2", "H2O", "https://es.wikipedia.org/wiki/Agua", "multiple"},
                {"¿Qué elemento químico es el principal componente de las proteínas?", "Carbono", "Nitrógeno", "Hidrógeno", "Azufre", "Nitrógeno", "https://es.wikipedia.org/wiki/Prote%C3%ADna", "multiple"},
                {"¿Qué gas es liberado por las plantas durante la fotosíntesis?", "Oxígeno", "Dióxido de carbono", "Nitrógeno", "Metano", "Oxígeno", "https://es.wikipedia.org/wiki/Fotos%C3%ADntesis", "multiple"},
                {"¿Qué proceso celular produce células sexuales en los organismos?", "Meiosis", "Mitosis", "Fertilización", "Ciclosis", "Meiosis", "https://es.wikipedia.org/wiki/Meiosis", "multiple"},
                {"¿Qué es el ADN?", "Ácido desoxirribonucleico", "Ácido ribonucleico", "Ácido aminoacídico", "Ácido nucleico", "Ácido desoxirribonucleico", "https://es.wikipedia.org/wiki/ADN", "multiple"},
                {"¿Qué sustancia en el cuerpo humano es responsable de transportar oxígeno?", "Hemoglobina", "Glucosa", "Colágeno", "Queratina", "Hemoglobina", "https://es.wikipedia.org/wiki/Hemoglobina", "multiple"},
                {"¿Cuál es el proceso por el cual las células se dividen para producir dos células hijas genéticamente idénticas?", "Mitosis", "Meiosis", "Fertilización", "Interfase", "Mitosis", "https://es.wikipedia.org/wiki/Mitosis", "multiple"},
                {"¿Qué estructura celular es responsable de la fotosíntesis en las plantas?", "Cloroplasto", "Mitocondria", "Núcleo", "Ribosoma", "Cloroplasto", "https://es.wikipedia.org/wiki/Cloroplasto", "multiple"},
                {"¿Cuál es la principal fuente de energía para las plantas?", "Luz solar", "Glucosa", "Agua", "Nitrógeno", "Luz solar", "https://es.wikipedia.org/wiki/Fotos%C3%ADntesis", "multiple"},
                {"¿Qué tipo de ácido se encuentra en el jugo gástrico?", "Ácido clorhídrico", "Ácido sulfúrico", "Ácido acético", "Ácido cítrico", "Ácido clorhídrico", "https://es.wikipedia.org/wiki/Ácido_clorhídrico", "multiple"},
                {"¿Cuál es el nombre de la teoría que describe el origen de la vida?", "Teoría celular", "Teoría de la evolución", "Teoría de la biogénesis", "Teoría de la abiogénesis", "Teoría de la biogénesis", "https://es.wikipedia.org/wiki/Teoría_de_la_biogénesis", "multiple"},
                {"¿Qué tipo de vínculo químico mantiene unidos a los átomos en una molécula de agua?", "Enlace covalente", "Enlace iónico", "Enlace metálico", "Enlace de hidrógeno", "Enlace covalente", "https://es.wikipedia.org/wiki/Enlace_covalente", "multiple"},
                {"¿Cuál de los siguientes nutrientes es esencial para el crecimiento celular?", "Proteínas", "Carbohidratos", "Vitaminas", "Minerales", "Proteínas", "https://es.wikipedia.org/wiki/Proteína", "multiple"},
                {"¿Qué tipo de glóbulos se encargan de la defensa contra infecciones?", "Glóbulos blancos", "Glóbulos rojos", "Plaquetas", "Eritrocitos", "Glóbulos blancos", "https://es.wikipedia.org/wiki/Glóbulo_blanco", "multiple"},
                {"¿Qué molécula es la fuente primaria de energía en los músculos?", "ATP", "Glucógeno", "Ácido láctico", "Creatina", "ATP", "https://es.wikipedia.org/wiki/Adenos%C3%ADn_trifosfato", "multiple"}
        };

        // Insertamos las preguntas en la base de datos
        for (String[] datos : preguntas) {
            Pregunta pregunta = new Pregunta();
            pregunta.setCategoria(new Categoria(4, "Biología"));
            pregunta.setPregunta(datos[0]);
            pregunta.setRespuestaCorrecta(datos[5]);
            pregunta.setRespuesta2(datos[1]);
            pregunta.setRespuesta3(datos[2]);
            pregunta.setRespuesta4(datos[3]);
            pregunta.setTipo(datos[7]);
            pregunta.setImagen(datos[6]); // URL de la pregunta
            preguntaDao.save(pregunta);
        }

        System.out.println("Preguntas de biología añadidas con éxito.");
    }
}
