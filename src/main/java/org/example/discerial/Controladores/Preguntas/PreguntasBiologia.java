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


        // 2) Definir el bloque de preguntas
        String[][] preguntas = {
                {
                        "¿Cuál es la molécula que almacena la información genética?",
                        "ARN",
                        "Proteínas",
                        "Carbohidratos",
                        "ADN",  // Respuesta correcta
                        "multiple"
                },
                {
                        "¿Qué partícula subatómica tiene carga negativa?",
                        "Protón",
                        "Neutrón",
                        "Quark",
                        "Electrón",
                        "multiple"
                },
                {
                        "¿Cuál es el principal pigmento fotosintético en las plantas?",
                        "Caroteno",
                        "Antocianina",
                        "Xantofila",
                        "Clorofila",
                        "multiple"
                },
                {
                        "¿Qué orgánulo se encarga de detoxificar sustancias y sintetizar lípidos?",
                        "Mitocondria",
                        "Ribosoma",
                        "Retículo endoplasmático rugoso",
                        "Retículo endoplasmático liso",
                        "multiple"
                },
                {
                        "¿Qué molécula transporta aminoácidos hasta el ribosoma durante la síntesis de proteínas?",
                        "ARN mensajero",
                        "ARN ribosómico",
                        "ADN",
                        "ARN de transferencia",
                        "multiple"
                },
                {
                        "¿Cuál de estos procesos ocurre en la mitocondria?",
                        "Síntesis de proteínas",
                        "Glucólisis",
                        "Fermentación",
                        "Ciclo de Krebs",
                        "multiple"
                },
                {
                        "¿Cuál es la función principal de las mitocondrias?",
                        "Síntesis de proteínas",
                        "Almacenar material genético",
                        "Transportar sustancias",
                        "Producir energía (ATP)",
                        "multiple"
                },
                {
                        "¿Qué orgánulo contiene clorofila en las células vegetales?",
                        "Mitocondria",
                        "Núcleo",
                        "Vacuola",
                        "Cloroplasto",
                        "multiple"
                },

                // Genética
                {
                        "¿Qué científico es conocido como el padre de la genética?",
                        "Charles Darwin",
                        "Louis Pasteur",
                        "James Watson",
                        "Gregor Mendel",
                        "multiple"
                },
                {
                        "¿Cuál es la probabilidad de que un hijo herede un alelo recesivo de ambos padres heterocigotos?",
                        "0%",
                        "75%",
                        "50%",
                        "25%",
                        "multiple"
                },

                // Anatomía Humana
                {
                        "¿Qué glándula produce la insulina?",
                        "Tiroides",
                        "Suprarrenal",
                        "Hipófisis",
                        "Páncreas",
                        "multiple"
                },
                {
                        "¿Cuál es el hueso más largo del cuerpo humano?",
                        "Húmero",
                        "Esternón",
                        "Tibia",
                        "Fémur",
                        "multiple"
                },

                // Ecología
                {
                        "¿Qué organismo ocuparía el primer nivel trófico en una pirámide alimenticia?",
                        "Carnívoros",
                        "Herbívoros",
                        "Descomponedores",
                        "Plantas",
                        "multiple"
                },
                {
                        "¿Qué proceso realizan las plantas durante la noche?",
                        "Fotosíntesis",
                        "Germinación",
                        "Transpiración",
                        "Respiración celular",
                        "multiple"
                },

                // Microbiología
                {
                        "¿Qué tipo de microorganismo es responsable de la producción de yogur?",
                        "Virus",
                        "Protozoos",
                        "Levaduras",
                        "Bacterias lácticas",
                        "multiple"
                },
                {
                        "¿Qué estructura permite a las bacterias moverse?",
                        "Pared celular",
                        "Plásmido",
                        "Cápsula",
                        "Flagelo",
                        "multiple"
                },

                // Fisiología Vegetal
                {
                        "¿Qué proceso permite el movimiento del agua a través de una planta?",
                        "Ósmosis",
                        "Capilaridad",
                        "Difusión",
                        "Transpiración",
                        "multiple"
                },
                {
                        "¿Qué hormonas vegetales promueven el crecimiento celular?",
                        "Citoquininas",
                        "Etileno",
                        "Ácido abscísico",
                        "Auxinas",
                        "multiple"
                },

                // Evolución
                {
                        "¿Qué científico propuso la teoría de la selección natural?",
                        "Gregor Mendel",
                        "Louis Pasteur",
                        "Alfred Wallace",
                        "Charles Darwin",
                        "multiple"
                },
                {
                        "¿Qué órgano humano se considera vestigial?",
                        "Corazón",
                        "Páncreas",
                        "Hígado",
                        "Apéndice",
                        "multiple"
                },

                // Sistema Nervioso
                {
                        "¿Qué parte del cerebro controla el equilibrio y la coordinación?",
                        "Hipotálamo",
                        "Cerebro",
                        "Tálamo",
                        "Cerebelo",
                        "multiple"
                },
                {
                        "¿Qué células son responsables de transmitir impulsos nerviosos?",
                        "Eritrocitos",
                        "Hepatocitos",
                        "Osteocitos",
                        "Neuronas",
                        "multiple"
                },
        };

        // 3) Insertar todas las preguntas
        for (String[] d : preguntas) {
            Pregunta q = new Pregunta();
            q.setCategoria(new Categoria(categoriaId, "Biología"));
            q.setPregunta(d[0]);
            q.setRespuesta2(d[1]);
            q.setRespuesta3(d[2]);
            q.setRespuesta4(d[3]);
            q.setRespuestaCorrecta(d[4]);
            q.setTipo(d[5]);
            preguntaDao.save(q);
        }

        System.out.println("Preguntas de biología añadidas con éxito.");
    }
}