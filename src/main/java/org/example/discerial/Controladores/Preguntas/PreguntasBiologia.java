package org.example.discerial.Controladores.Preguntas;

import org.example.discerial.DAO.ICategoria;
import org.example.discerial.DAO.ICategoriaImpl;
import org.example.discerial.DAO.IPregunta;
import org.example.discerial.DAO.IPreguntaImpl;
import org.example.discerial.entities.Categoria;
import org.example.discerial.entities.Pregunta;

import java.util.List;

public class PreguntasBiologia {

    public void crearPreguntasBiologia() {

        ICategoria categoriaDao = new ICategoriaImpl();
        IPregunta preguntaDao = new IPreguntaImpl();

        // 1) Obtener (o crear) la categoría "Filosofía"
        List<Categoria> cats = categoriaDao.findByNombre("Biología");
        Categoria Biología;
        if (cats.isEmpty()) {
            Biología = new Categoria("Biología");
            categoriaDao.save(Biología);
        } else {
            Biología = cats.get(0);
        }


        // 2) Verificar si ya existen preguntas para esa categoría
        List<Pregunta> existentes = preguntaDao.findByCategoria(Biología.getId());
        if (!existentes.isEmpty()) {
            System.out.println("Ya existen preguntas para la categoría 'Biología'.");
            return;
        }

        // 3) Datos de prueba: {Pregunta, imagenURL, resp2, resp3, resp4, respCorrecta, tipo}
        String[][] datos = {
                //1
                {"¿Cuál es la unidad básica de la vida?",
                        "https://img.freepik.com/premium-photo/microscopic-image-human-alien-cells-inside-human-body-creation-life-mesmerize-movement-cells-representation-virus-medical-general-image-use-movie-games-books_751108-2359.jpg?w=1380",
                        " El átomo","El tejido","El órgano","La célula","multiple"},
                //2
                {"¿Qué orgánulo celular es responsable de producir energía en forma de ATP?",
                        "https://m1.paperblog.com/i/203/2033212/importancia-mitocondria-sintesis-atp-fosforil-T-p8UdHU.png",
                        "Lisosoma","Núcleo","Ribosoma","Mitocondria","multiple"},
                //3
                {"¿Qué tipo de molécula es el ADN?",
                        "https://pngimg.com/uploads/dna/dna_PNG52.png",
                        "Carbohidrato","Proteína","Lípido","Ácido  Nucleico","multiple"},
                //4
                {"¿Cuál de los siguientes reinos incluye a los hongos?",
                        "https://filesedc.com/uploads/333/img/2022/02/1200/varios-hongos-crecidos-encima-de-una-zona-con-musgo-en-un-bosque-6218968445b70.jpg",
                        "Plantae","Animalia","Protista","Fungi","multiple"},
                //5
                {"¿Qué proceso permite a las plantas transformar la luz solar en energía química?",
                        "https://th.bing.com/th/id/OIP.c_hhNTKhk1aPMvj8XXQnTgHaD_?w=500&h=270&rs=1&pid=ImgDetMain",
                        "Respiración celular","Transpiración","Fermentación","Fotosíntesis","multiple"},
                //6
                {"¿Cómo se llama la estructura que conecta músculos y huesos?",
                        "https://th.bing.com/th/id/OIP.Gp11dFtaQu9eLVQxJenhtAAAAA?rs=1&pid=ImgDetMain",
                        "Cartílago","Ligamento","Fascia","Tendón","multiple"},
                //7
                {"¿Cuál es el cromosoma sexual que determina el sexo masculino en los humanos?",
                        "https://th.bing.com/th/id/OIP.y5YxE-SoAyjc9Qnwlxse6QHaFG?rs=1&pid=ImgDetMain",
                        "X","Z","W","Y","multiple"},
                //8
                {"¿Qué parte del sistema nervioso controla las funciones involuntarias como la respiración o el ritmo cardíaco?",
                        "https://th.bing.com/th/id/R.bdc46e4cd893b6d7a25b9737416295a6?rik=0qlY7jMzmaLpXA&riu=http%3a%2f%2fc.files.bbci.co.uk%2f13D58%2fproduction%2f_98804218_cerebro.jpg&ehk=A7p1XWHHT7cf6sBnEc1czdPSOFcK7%2boPBLFm28kZJjQ%3d&risl=&pid=ImgRaw&r=0",
                        "Cerebelo","Tálamo","Corteza cerebral","Bulbo raquídeo","multiple"},
                //9
                {"¿Qué tipo de célula produce anticuerpos?",
                        "https://th.bing.com/th/id/R.bdc46e4cd893b6d7a25b9737416295a6?rik=0qlY7jMzmaLpXA&riu=http%3a%2f%2fc.files.bbci.co.uk%2f13D58%2fproduction%2f_98804218_cerebro.jpg&ehk=A7p1XWHHT7cf6sBnEc1czdPSOFcK7%2boPBLFm28kZJjQ%3d&risl=&pid=ImgRaw&r=0",
                        "Eritrocito","Neurona","Plaqueta","Célula B","multiple"},
                //10
                {"¿Qué nombre recibe la división celular que produce células sexuales?",
                        "https://th.bing.com/th/id/R.350eefed33c1d3c666edfa085f1fe465?rik=Ob7wlKndn%2bZ0PQ&pid=ImgRaw&r=0",
                        "Mitosis","Fisión","Gemación","Meiosis","multiple"},



        };

        // 4) Insertar cada pregunta
        for (String[] d : datos) {
            String texto       = d[0];
            String imagenURL   = d[1];
            String opcion2     = d[2];
            String opcion3     = d[3];
            String opcion4     = d[4];
            String correcta    = d[5];
            String tipo        = d[6];

            Pregunta p = new Pregunta(
                    Biología,
                    texto,
                    correcta,
                    opcion2,
                    opcion3,
                    opcion4,
                    imagenURL,
                    tipo
            );
            preguntaDao.save(p);
        }

        System.out.println("Preguntas de Biologia añadidas con éxito.");


    }
}

