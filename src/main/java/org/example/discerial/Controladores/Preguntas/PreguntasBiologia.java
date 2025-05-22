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

        // Lista de preguntas con ID manual (añadido un campo id en la posición 0)
        Object[][] preguntas = {
                {1, "¿Cuál es la molécula que almacena la información genética?", "ARN", "Proteínas", "Carbohidratos", "ADN", "multiple"},
                {2, "¿Qué partícula subatómica tiene carga negativa?", "Protón", "Neutrón", "Quark", "Electrón", "multiple"},
                {3, "¿Cuál es el principal pigmento fotosintético en las plantas?", "Caroteno", "Antocianina", "Xantofila", "Clorofila", "multiple"},
                {4, "¿Cuál es el órgano principal del sistema circulatorio?", "Pulmón", "Hígado", "Riñón", "Corazón", "multiple"},
                {5, "¿Qué tipo de célula carece de núcleo definido?", "Eucariota", "Muscular", "Nerviosa", "Procariota", "multiple"},
                {6, "¿Cuál es la función de la mitocondria en la célula?", "Síntesis de proteínas", "Reproducción celular", "Almacenamiento de información genética", "Producción de energía", "multiple"},
                {7, "¿Qué grupo de organismos realiza la fotosíntesis?", "Hongos", "Animales", "Protozoos", "Plantas", "multiple"},
                {8, "¿Cuál es la molécula responsable de transportar oxígeno en la sangre?", "Glucosa", "Colágeno", "Insulina", "Hemoglobina", "multiple"},
                {9, "¿En qué parte de la célula ocurre la síntesis de proteínas?", "Núcleo", "Mitocondria", "Lisosoma", "Ribosoma", "multiple"},
                {10, "¿Qué tipo de tejido conecta los músculos a los huesos?", "Cartílago", "Ligamento", "Tejido epitelial", "Tendón", "multiple"},
                {11, "¿Qué proceso convierte la glucosa en energía en ausencia de oxígeno?", "Fotosíntesis", "Fermentación", "Respiración aeróbica", "Fermentación láctica", "multiple"},
                {12, "¿Qué parte del sistema nervioso controla las funciones involuntarias?", "Cerebro", "Cerebelo", "Corteza cerebral", "Sistema nervioso autónomo", "multiple"},
                {13, "¿Cuál es el componente principal de la pared celular en las plantas?", "Quitina", "Celulosa", "Colágeno", "Celulosa", "multiple"},
                {14, "¿Qué hormona regula los niveles de glucosa en sangre?", "Adrenalina", "Tiroxina", "Insulina", "Insulina", "multiple"},
                {15, "¿Cuál es el nombre del proceso de división celular que produce gametos?", "Mitosis", "Fusión", "Meiosis", "Meiosis", "multiple"},
                {16, "¿Qué parte del cerebro está involucrada en la memoria y el aprendizaje?", "Tálamo", "Cerebelo", "Hipocampo", "Hipocampo", "multiple"},
                {17, "¿Qué elemento es esencial para la formación de hemoglobina?", "Calcio", "Potasio", "Hierro", "Hierro", "multiple"},
                {18, "¿Cuál es el tipo de ARN que lleva la información genética desde el ADN hasta el ribosoma?", "ARN de transferencia", "ARN ribosómico", "ARN mensajero", "ARN mensajero", "multiple"},
                {19, "¿Qué estructura celular contiene enzimas digestivas?", "Núcleo", "Mitocondria", "Lisosoma", "Lisosoma", "multiple"},
                {20, "¿Cuál es la unidad básica de la vida?", "Molécula", "Átomo", "Orgánulo", "Célula", "multiple"},
                {21, "¿Qué tipo de macromolécula forman las enzimas?", "Carbohidratos", "Lípidos", "Proteínas", "Proteínas", "multiple"},
                {22, "¿Qué tipo de tejido cubre la superficie del cuerpo y órganos?", "Conectivo", "Muscular", "Nervioso", "Epitelial", "multiple"},
                {23, "¿Cuál es la función principal de los glóbulos rojos?", "Defensa contra infecciones", "Transporte de oxígeno", "Coagulación sanguínea", "Transporte de oxígeno", "multiple"},
                {24, "¿Qué gas liberan las plantas durante la fotosíntesis?", "Dióxido de carbono", "Nitrógeno", "Oxígeno", "Oxígeno", "multiple"},
                {25, "¿Qué parte del sistema digestivo absorbe la mayoría de los nutrientes?", "Estómago", "Intestino grueso", "Esófago", "Intestino delgado", "multiple"},
                {26, "¿Cuál es el órgano encargado de filtrar la sangre?", "Hígado", "Páncreas", "Corazón", "Riñón", "multiple"},
                {27, "¿Qué tipo de biomolécula son los ácidos nucleicos?", "Carbohidratos", "Lípidos", "Proteínas", "Ácidos nucleicos", "multiple"},
                {28, "¿Cuál es la función de los ribosomas?", "Almacenamiento de energía", "Producción de lípidos", "Transcripción de ADN", "Síntesis de proteínas", "multiple"},
                {29, "¿Qué es un ecosistema?", "Un conjunto de organismos de la misma especie", "Una comunidad de organismos y su ambiente", "Un tipo de célula", "Una comunidad de organismos y su ambiente", "multiple"},
                {30, "¿Cuál es el principal componente del plasma sanguíneo?", "Glóbulos rojos", "Glóbulos blancos", "Plaquetas", "Agua", "multiple"},
                {31, "¿Qué es la ósmosis?", "Difusión de solutos", "Movimiento activo de agua", "Movimiento pasivo de agua a través de una membrana semipermeable", "Movimiento pasivo de agua a través de una membrana semipermeable", "multiple"},
                {32, "¿Qué función tienen las mitocondrias?", "Almacenamiento de ADN", "Síntesis de proteínas", "Producción de energía", "Producción de energía", "multiple"},
                {33, "¿Qué tipo de reproducción realizan las bacterias?", "Sexuada", "Asexual", "Sexuada y asexual", "Asexual", "multiple"},
                {34, "¿Qué molécula es la fuente principal de energía para las células?", "ADN", "ARN", "ATP", "ATP", "multiple"},
                {35, "¿Qué es un gen?", "Un tipo de célula", "Una proteína", "Una unidad de herencia", "Una unidad de herencia", "multiple"},
                {36, "¿Qué es la fotosíntesis?", "Proceso de respiración celular", "Proceso para descomponer glucosa", "Proceso para convertir luz en energía química", "Proceso para convertir luz en energía química", "multiple"},
                {37, "¿Qué estructura permite la comunicación entre neuronas?", "Axón", "Dendrita", "Sinapsis", "Sinapsis", "multiple"},
                {38, "¿Qué son los antibióticos?", "Virus", "Bacterias", "Medicamentos que matan o inhiben bacterias", "Medicamentos que matan o inhiben bacterias", "multiple"},
                {39, "¿Qué es la evolución?", "Cambio de estación", "Cambio genético en una población a través del tiempo", "Cambio de comportamiento", "Cambio genético en una población a través del tiempo", "multiple"},
                {40, "¿Qué es la homeostasis?", "Mantenimiento del equilibrio interno del cuerpo", "Cambio climático", "Movimiento de sangre", "Mantenimiento del equilibrio interno del cuerpo", "multiple"},
                {41, "¿Cuál es el nombre del pigmento que da color rojo a la sangre?", "Clorofila", "Melanina", "Hemoglobina", "Hemoglobina", "multiple"},
                {42, "¿Qué función tienen las enzimas?", "Almacenar energía", "Construir células", "Acelerar reacciones químicas", "Acelerar reacciones químicas", "multiple"},
                {43, "¿Qué es el ADN?", "Ácido desoxirribonucleico", "Ácido ribonucleico", "Proteína", "Ácido desoxirribonucleico", "multiple"},
                {44, "¿Qué tipo de tejido conecta huesos entre sí?", "Muscular", "Cartílago", "Tejido epitelial", "Ligamento", "multiple"},
                {45, "¿Cuál es la principal función del sistema respiratorio?", "Digestión", "Circulación", "Intercambio de gases", "Intercambio de gases", "multiple"},
                {46, "¿Qué molécula usan las células para almacenar energía a corto plazo?", "ARN", "ADN", "Glucógeno", "ATP", "multiple"},
                {47, "¿Qué tipo de célula tiene pared celular y cloroplastos?", "Animal", "Fúngica", "Bacteriana", "Vegetal", "multiple"},
                {48, "¿Cuál es el proceso por el cual las plantas liberan vapor de agua?", "Fotosíntesis", "Respiración", "Transpiración", "Transpiración", "multiple"},
                {49, "¿Qué es un virus?", "Un organismo vivo", "Una célula", "Un agente infeccioso no celular", "Un agente infeccioso no celular", "multiple"},
                {50, "¿Qué órgano produce insulina?", "Hígado", "Riñón", "Páncreas", "Páncreas", "multiple"} ,
                {51, "¿Cuál es la función principal del sistema linfático?", "Transporte de oxígeno", "Producción de hormonas", "Digestión de alimentos", "Defensa inmunitaria", "multiple"},
                {52, "¿Qué estructura celular participa en la división celular?", "Ribosoma", "Mitocondria", "Retículo endoplásmico", "Centriolo", "multiple"},
                {53, "¿Cuál es el proceso por el que las plantas absorben agua?", "Difusión", "Ósmosis inversa", "Transpiración", "Ósmosis", "multiple"},
                {54, "¿Qué tipo de músculo es voluntario?", "Liso", "Cardíaco", "Involuntario", "Esquelético", "multiple"},
                {55, "¿Cuál es la principal función de los glóbulos blancos?", "Transportar oxígeno", "Coagular la sangre", "Producir hormonas", "Defender al organismo", "multiple"},
                {56, "¿Qué es la fagocitosis?", "Reproducción celular", "Producción de energía", "Captura y destrucción de partículas por células", "Transporte de nutrientes", "multiple"},
                {57, "¿Qué tipo de biomolécula forman las membranas celulares?", "Carbohidratos", "Proteínas", "Ácidos nucleicos", "Lípidos", "multiple"},
                {58, "¿Qué nombre recibe el proceso de formación de nuevas especies?", "Mutación", "Reproducción", "Selección natural", "Especiación", "multiple"},
                {59, "¿Cuál es el principal gas que utilizan las plantas durante la fotosíntesis?", "Oxígeno", "Nitrógeno", "Metano", "Dióxido de carbono", "multiple"},
                {60, "¿Qué es un tejido?", "Una célula", "Un órgano", "Un conjunto de órganos", "Un conjunto de células con función común", "multiple"},
                {61, "¿Cuál es la función de los cloroplastos?", "Almacenamiento de agua", "Producción de proteínas", "Producción de energía", "Fotosíntesis", "multiple"},
                {62, "¿Qué es la migración genética?", "Movimiento de genes entre poblaciones", "Mutación de genes", "Formación de gametos", "Reproducción sexual", "multiple"},
                {63, "¿Qué órgano produce la bilis?", "Páncreas", "Riñón", "Estómago", "Hígado", "multiple"},
                {64, "¿Qué nombre recibe la capa externa de la piel?", "Dermis", "Hipodermis", "Folículo piloso", "Epidermis", "multiple"},
                {65, "¿Cuál es la función del sistema endocrino?", "Digestión", "Respiración", "Control de movimientos", "Regulación hormonal", "multiple"},
                {66, "¿Qué son los cromosomas?", "Proteínas", "Células", "Ácidos nucleicos libres", "Estructuras que contienen ADN", "multiple"},
                {67, "¿Qué nombre recibe la reacción que libera energía en las células?", "Fotosíntesis", "Fermentación", "Anabolismo", "Catabolismo", "multiple"},
                {68, "¿Qué tipo de células forman los huesos?", "Condrocitos", "Fibroblastos", "Osteoclastos", "Osteoblastos", "multiple"},
                {69, "¿Qué nombre recibe el estudio de los fósiles?", "Anatomía", "Ecología", "Genética", "Paleontología", "multiple"},
                {70, "¿Cuál es el principal componente del citoplasma?", "Ácido nucleico", "Proteínas", "Lípidos", "Agua", "multiple"},
                {71, "¿Qué tipo de célula produce anticuerpos?", "Neurona", "Eritrocito", "Célula muscular", "Linfocito B", "multiple"},
                {72, "¿Cuál es el nombre del proceso por el que las plantas liberan oxígeno?", "Respiración", "Fermentación", "Fotosíntesis", "Fotólisis del agua", "multiple"},
                {73, "¿Qué es la biodiversidad?", "Número de individuos", "Cantidad de biomasa", "Diversidad genética en un solo organismo", "Diversidad de especies en un área", "multiple"},
                {74, "¿Qué función tiene el sistema excretor?", "Digestión de alimentos", "Transporte de oxígeno", "Producción de hormonas", "Eliminación de desechos", "multiple"},
                {75, "¿Qué tipo de biomoléculas son los lípidos?", "Moléculas formadas por aminoácidos", "Moléculas formadas por nucleótidos", "Moléculas formadas por azúcares", "Moléculas grasas", "multiple"},
                {76, "¿Qué tipo de respiración utilizan las células animales?", "Fotosíntesis", "Fermentación", "Respiración aeróbica", "Respiración aeróbica", "multiple"},
                {77, "¿Cuál es el órgano responsable de la audición?", "Ojo", "Nariz", "Piel", "Oído", "multiple"},
                {78, "¿Qué nombre recibe la capa que protege el cerebro?", "Músculo", "Cartílago", "Piel", "Meninges", "multiple"},
                {79, "¿Cuál es el nombre de las células que transmiten impulsos nerviosos?", "Osteocitos", "Eritrocitos", "Fibroblastos", "Neuronas", "multiple"},
                {80, "¿Qué es un virus?", "Una célula", "Un organismo unicelular", "Un parásito multicelular", "Un agente infeccioso sin célula", "multiple"},
                {81, "¿Qué nombre recibe la unión entre dos neuronas?", "Axón", "Dendrita", "Núcleo", "Sinapsis", "multiple"},
                {82, "¿Qué molécula es el principal componente de las membranas celulares?", "Glucosa", "Colesterol", "Ácidos nucleicos", "Fosfolípidos", "multiple"},
                {83, "¿Qué nombre recibe el proceso de creación de nuevas proteínas?", "Transcripción", "Traducción", "Replicación", "Traducción", "multiple"},
                {84, "¿Cuál es el órgano principal de la digestión?", "Corazón", "Pulmón", "Esófago", "Estómago", "multiple"},
                {85, "¿Qué tipo de célula produce insulina?", "Células alfa", "Células beta", "Células delta", "Células beta", "multiple"},
                {86, "¿Qué es un enzima?", "Un carbohidrato", "Un lípido", "Una proteína que cataliza reacciones", "Una proteína que cataliza reacciones", "multiple"},
                {87, "¿Cuál es la principal función de las plaquetas?", "Defensa inmunitaria", "Transporte de oxígeno", "Digestión", "Coagulación sanguínea", "multiple"},
                {88, "¿Qué nombre recibe la capa de células que recubre el interior del corazón?", "Endotelio", "Miocardio", "Epicardio", "Endocardio", "multiple"},
                {89, "¿Qué es la inmunidad?", "Proceso de digestión", "Producción de energía", "Respuesta del cuerpo a patógenos", "Respuesta del cuerpo a patógenos", "multiple"},
                {90, "¿Qué nombre recibe el proceso por el cual las células se especializan?", "Diferenciación celular", "Multiplicación", "Meiosis", "Diferenciación celular", "multiple"},
                {91, "¿Cuál es la función del ácido clorhídrico en el estómago?", "Almacenar nutrientes", "Absorber agua", "Neutralizar ácidos", "Descomponer alimentos", "multiple"},
                {92, "¿Qué estructura une músculos a huesos?", "Ligamento", "Tendón", "Cartílago", "Tendón", "multiple"},
                {93, "¿Qué nombre recibe el proceso de producción de espermatozoides?", "Ovulación", "Fertilización", "Menstruación", "Espermatogénesis", "multiple"},
                {94, "¿Qué tipo de célula produce óvulos?", "Espermatocito", "Neurona", "Célula somática", "Ovocito", "multiple"},
                {95, "¿Qué nombre recibe la respiración celular que utiliza oxígeno?", "Fermentación", "Anaeróbica", "Fotosíntesis", "Aeróbica", "multiple"},
                {96, "¿Cuál es la función de los pulmones?", "Bombear sangre", "Producir hormonas", "Filtrar toxinas", "Intercambio de gases", "multiple"},
                {97, "¿Qué nombre recibe la unión de un espermatozoide y un óvulo?", "Ovulación", "Menstruación", "Fertilización", "Fertilización", "multiple"},
                {98, "¿Qué nombre recibe el líquido que transporta oxígeno en la sangre?", "Plasma", "Linfa", "Suero", "Hemoglobina", "multiple"},
                {99, "¿Qué es un tejido epitelial?", "Tejido conectivo", "Tejido muscular", "Tejido nervioso", "Tejido que cubre superficies", "multiple"},
                {100, "¿Qué órgano produce jugos digestivos para descomponer grasas?", "Estómago", "Hígado", "Riñón", "Páncreas", "multiple"}


        };

        for (Object[] d : preguntas) {
            int idPregunta = (int) d[0];

            // 1) Verificar si ya existe la pregunta con ese ID
            if (preguntaDao.findById(idPregunta).isEmpty()) {
                Pregunta q = new Pregunta();
                q.setId(idPregunta); // Asignamos el ID manual
                q.setCategoria(new Categoria(categoriaId, "Biología"));
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