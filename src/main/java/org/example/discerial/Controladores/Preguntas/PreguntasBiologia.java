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
                        "https://upload.wikimedia.org/wikipedia/commons/8/80/DNA_methylation.jpg",
                        "multiple"
                },
                {
                        "¿Qué partícula subatómica tiene carga negativa?",
                        "Protón",
                        "Neutrón",
                        "Quark",
                        "Electrón",
                        "https://img.europapress.es/fotoweb/fotonoticia_20181017185536_690.jpg",
                        "multiple"
                },
                {
                        "¿Cuál es el principal pigmento fotosintético en las plantas?",
                        "Caroteno",
                        "Antocianina",
                        "Xantofila",
                        "Clorofila",
                        "https://mejorconsalud.as.com/wp-content/uploads/2015/06/Composision-molecular-500x334.jpg",
                        "multiple"
                },
                {
                        "¿Qué orgánulo se encarga de detoxificar sustancias y sintetizar lípidos?",
                        "Mitocondria",
                        "Ribosoma",
                        "Retículo endoplasmático rugoso",
                        "Retículo endoplasmático liso",
                        "https://pymstatic.com/47451/conversions/reticulo-endoplasmatico-liso-wide.jpg",
                        "multiple"
                },
                {
                        "¿Qué molécula transporta aminoácidos hasta el ribosoma durante la síntesis de proteínas?",
                        "ARN mensajero",
                        "ARN ribosómico",
                        "ADN",
                        "ARN de transferencia",
                        "https://www.genome.gov/sites/default/files/media/images/tg/Transfer_RNA_hero.png",
                        "multiple"
                },
                {
                        "¿Cuál de estos procesos ocurre en la mitocondria?",
                        "Síntesis de proteínas",
                        "Glucólisis",
                        "Fermentación",
                        "Ciclo de Krebs",
                        "https://pymstatic.com/63253/conversions/mitocondrias-wide.jpg",
                        "multiple"
                },
                {
                        "¿Cuál es la función principal de las mitocondrias?",
                        "Síntesis de proteínas",
                        "Almacenar material genético",
                        "Transportar sustancias",
                        "Producir energía (ATP)",
                        "https://www.supersmart.com/data/images/blog/mitochondrie.jpg",
                        "multiple"
                },
                {
                        "¿Qué orgánulo contiene clorofila en las células vegetales?",
                        "Mitocondria",
                        "Núcleo",
                        "Vacuola",
                        "Cloroplasto",
                        "https://s1.significados.com/foto/chloroplasts-g98a80c219-1920.png",
                        "multiple"
                },

                // Genética
                {
                        "¿Qué científico es conocido como el padre de la genética?",
                        "Charles Darwin",
                        "Louis Pasteur",
                        "James Watson",
                        "Gregor Mendel",
                        "https://c.files.bbci.co.uk/11D4D/production/_118073037_hi066780220-1.jpg",
                        "multiple"
                },
                {
                        "¿Cuál es la probabilidad de que un hijo herede un alelo recesivo de ambos padres heterocigotos?",
                        "0%",
                        "75%",
                        "50%",
                        "25%",
                        "https://cdn.computerhoy.com/sites/navi.axelspringer.es/public/media/image/2023/05/pelirrojo-ojos-azules-3039576.jpg?tf=3840x",
                        "multiple"
                },

                // Anatomía Humana
                {
                        "¿Qué glándula produce la insulina?",
                        "Tiroides",
                        "Suprarrenal",
                        "Hipófisis",
                        "Páncreas",
                        "https://salud.nih.gov/sites/salud/files/styles/teaser_900x527/public/2023-08/pancreas-con-tumores.jpg?itok=gBCi_czh",
                        "multiple"
                },
                {
                        "¿Cuál es el hueso más largo del cuerpo humano?",
                        "Húmero",
                        "Esternón",
                        "Tibia",
                        "Fémur",
                        "https://media.istockphoto.com/id/526829550/es/foto/el-f%C3%A9mur-humanos.jpg?s=612x612&w=0&k=20&c=sdRTrWrUqQ9mTwD-0YT47LPKurKgjC2rRUV5V6WY4kQ=",
                        "multiple"
                },

                // Ecología
                {
                        "¿Qué organismo ocuparía el primer nivel trófico en una pirámide alimenticia?",
                        "Carnívoros",
                        "Herbívoros",
                        "Descomponedores",
                        "Plantas",
                        "https://lh6.googleusercontent.com/proxy/cOtjZFJpEzt3zOz1AfJPM0MU7Y8b87VCTDF0AapeogUntFFUQjXKKPNOcD-dEqtnyfcOCDPAzeLwo25WZbZDv0czsKpJSIDkLsQX_sJriTF1v7yRtTxZ6wo0Ap6Q3XVNWtN2npo6Nw",
                        "multiple"
                },
                {
                        "¿Qué proceso realizan las plantas durante la noche?",
                        "Fotosíntesis",
                        "Germinación",
                        "Transpiración",
                        "Respiración celular",
                        "https://i.pinimg.com/474x/aa/2f/62/aa2f6263de44746345deeae9d31fb739.jpg",
                        "multiple"
                },

                // Microbiología
                {
                        "¿Qué tipo de microorganismo es responsable de la producción de yogur?",
                        "Virus",
                        "Protozoos",
                        "Levaduras",
                        "Bacterias lácticas",
                        "https://mountainsideorganicos.com/cdn/shop/articles/Lactobacillus_para_Plantas-1800x900-opt_2000x.jpg?v=1673206062",
                        "multiple"
                },
                {
                        "¿Qué estructura permite a las bacterias moverse?",
                        "Pared celular",
                        "Plásmido",
                        "Cápsula",
                        "Flagelo",
                        "https://media.istockphoto.com/id/859433642/es/foto/bacterias-azul-aisladas-3d.jpg?s=612x612&w=0&k=20&c=-1Q0rDzUZCzsriz-5BP1S2MNRg0bBsvM_b3qKFxjIwU=",
                        "multiple"
                },

                // Fisiología Vegetal
                {
                        "¿Qué proceso permite el movimiento del agua a través de una planta?",
                        "Ósmosis",
                        "Capilaridad",
                        "Difusión",
                        "Transpiración",
                        "https://agraria.pe/imgs/a/lx/facultad-de-agronomia-de-la-unalm-realizara-curso-de-actuali-22377.jpg",
                        "multiple"
                },
                {
                        "¿Qué hormonas vegetales promueven el crecimiento celular?",
                        "Citoquininas",
                        "Etileno",
                        "Ácido abscísico",
                        "Auxinas",
                        "https://upload.wikimedia.org/wikipedia/commons/thumb/f/fb/Auxina.png/250px-Auxina.png",
                        "multiple"
                },

                // Evolución
                {
                        "¿Qué científico propuso la teoría de la selección natural?",
                        "Gregor Mendel",
                        "Louis Pasteur",
                        "Alfred Wallace",
                        "Charles Darwin",
                        "https://external-preview.redd.it/QJjlV0xpPx68nlHXJxntwgkg61qn6ClgThbKov2ZTPY.jpg?auto=webp&s=9f38309fd574e0e71882f218a076b239316a62d0",
                        "multiple"
                },
                {
                        "¿Qué órgano humano se considera vestigial?",
                        "Corazón",
                        "Páncreas",
                        "Hígado",
                        "Apéndice",
                        "https://uvn-brightspot.s3.amazonaws.com/assets/vixes/btg/curiosidades.batanga.com/files/Para-que-sirve-el-apendice-2.jpg",
                        "multiple"
                },

                // Sistema Nervioso
                {
                        "¿Qué parte del cerebro controla el equilibrio y la coordinación?",
                        "Hipotálamo",
                        "Cerebro",
                        "Tálamo",
                        "Cerebelo",
                        "https://s3-us-west-2.amazonaws.com/courses-images/wp-content/uploads/sites/1940/2017/05/29213441/olpulgaeqnkqnqaoeubv.png",
                        "multiple"
                },
                {
                        "¿Qué células son responsables de transmitir impulsos nerviosos?",
                        "Eritrocitos",
                        "Hepatocitos",
                        "Osteocitos",
                        "Neuronas",
                        "https://www.muyinteresante.com/wp-content/uploads/sites/5/2023/10/27/653bcdae153ce.jpeg",
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
            q.setImagen(d[5]);
            q.setTipo(d[6]);
            preguntaDao.save(q);
        }

        System.out.println("Preguntas de biología añadidas con éxito.");
    }
}