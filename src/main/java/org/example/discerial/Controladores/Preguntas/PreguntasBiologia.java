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
        List<Pregunta> preguntasBiologia = preguntaDao.findByCategoria(4); // ID 4 para Biología
        if (!preguntasBiologia.isEmpty()) {
            System.out.println("Ya existen preguntas para la categoría 'Biología'.");
            return;
        }

        String[][] preguntas = {
                {
                        "¿Cuál es la molécula que almacena la información genética?",
                        "ARN",
                        "Proteínas",
                        "Carbohidratos",
                        "ADN",
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
                },
                {
                        "¿Qué estructura celular controla la entrada y salida de sustancias?",
                        "Mitocondria",
                        "Núcleo",
                        "Pared celular",
                        "Membrana plasmática",
                        "https://upload.wikimedia.org/wikipedia/commons/7/76/Cell_membrane_detailed_diagram_en.svg",
                        "multiple"
                },
                {
                        "¿Cuál es el orgánulo encargado de la fotosíntesis?",
                        "Núcleo",
                        "Mitocondria",
                        "Cloroplasto",
                        "Lisosoma",
                        "https://upload.wikimedia.org/wikipedia/commons/4/4c/Chloroplast_diagram.svg",
                        "multiple"
                },
                {
                        "¿Qué tipo de célula no tiene núcleo definido?",
                        "Eucariota animal",
                        "Eucariota vegetal",
                        "Fúngica",
                        "Procariota",
                        "https://upload.wikimedia.org/wikipedia/commons/6/6f/Prokaryote_cell_diagram-en.svg",
                        "multiple"
                },
                {
                        "¿Qué estructura almacena el ADN en las células eucariotas?",
                        "Citoplasma",
                        "Nucleolo",
                        "Mitocondria",
                        "Núcleo",
                        "https://upload.wikimedia.org/wikipedia/commons/f/f2/Nucleus_diagram.svg",
                        "multiple"
                },
                {
                        "¿Qué macromolécula es la principal fuente de energía inmediata para las células?",
                        "Proteínas",
                        "Lípidos",
                        "Vitaminas",
                        "Carbohidratos",
                        "https://upload.wikimedia.org/wikipedia/commons/7/7f/Carbohydrate_general_structure.png",
                        "multiple"
                },
                {
                        "¿Qué componente de la sangre transporta oxígeno?",
                        "Plaquetas",
                        "Glóbulos blancos",
                        "Plasma",
                        "Hemoglobina",
                        "https://upload.wikimedia.org/wikipedia/commons/e/e5/Hemoglobin_structure.png",
                        "multiple"
                },
                {
                        "¿Qué vitamina es producida en la piel con ayuda de la luz solar?",
                        "Vitamina C",
                        "Vitamina A",
                        "Vitamina K",
                        "Vitamina D",
                        "https://upload.wikimedia.org/wikipedia/commons/7/7a/Vitamin_D3_structure.png",
                        "multiple"
                },
                {
                        "¿Qué glándula regula el metabolismo del cuerpo?",
                        "Páncreas",
                        "Glándulas suprarrenales",
                        "Hipófisis",
                        "Tiroides",
                        "https://upload.wikimedia.org/wikipedia/commons/2/2f/Thyroid_gland.svg",
                        "multiple"
                },
                {
                        "¿Qué sistema del cuerpo humano transporta sangre?",
                        "Digestivo",
                        "Excretor",
                        "Respiratorio",
                        "Circulatorio",
                        "https://upload.wikimedia.org/wikipedia/commons/5/59/Circulatory_System_en.svg",
                        "multiple"
                },
                {
                        "¿Qué tipo de tejido conecta músculos con huesos?",
                        "Ligamentos",
                        "Cartílago",
                        "Epitelial",
                        "Tendón",
                        "https://upload.wikimedia.org/wikipedia/commons/f/f9/Tendon_diagram.svg",
                        "multiple"
                },
                {
                        "¿Qué parte del cerebro controla el equilibrio y la coordinación?",
                        "Bulbo raquídeo",
                        "Tálamo",
                        "Hipotálamo",
                        "Cerebelo",
                        "https://upload.wikimedia.org/wikipedia/commons/e/e0/Cerebellum_SVG.svg",
                        "multiple"
                },
                {
                        "¿Cómo se llama la unidad funcional del riñón?",
                        "Glomérulo",
                        "Alvéolo",
                        "Nefrona",
                        "Vesícula",
                        "https://upload.wikimedia.org/wikipedia/commons/5/5c/Nephron_structure.png",
                        "multiple"
                },
                {
                        "¿Cuál es el principal gas que exhalamos al respirar?",
                        "Oxígeno",
                        "Nitrógeno",
                        "Hidrógeno",
                        "Dióxido de carbono",
                        "https://upload.wikimedia.org/wikipedia/commons/4/4c/Carbon_dioxide_3D_ball.png",
                        "multiple"
                },
                {
                        "¿Qué tipo de célula del sistema inmune produce anticuerpos?",
                        "Macrófago",
                        "Neurona",
                        "Plaqueta",
                        "Linfocito B",
                        "https://upload.wikimedia.org/wikipedia/commons/3/3b/B_cell_and_antibodies.png",
                        "multiple"
                }
        };


        for (String[] datos : preguntas) {
            Pregunta pregunta = new Pregunta();
            pregunta.setCategoria(new Categoria(4, "Biología"));
            pregunta.setPregunta(datos[0]);
            pregunta.setRespuesta2(datos[1]);
            pregunta.setRespuesta3(datos[2]);
            pregunta.setRespuesta4(datos[3]);
            pregunta.setRespuestaCorrecta(datos[4]); // ← posición 4
            pregunta.setImagen(datos[5]);             // ← posición 5
            pregunta.setTipo(datos[6]);               // ← posición 6
            preguntaDao.save(pregunta);
        }


        System.out.println("Preguntas de biología añadidas con éxito.");
    }
}