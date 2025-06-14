package org.example.discerial.Controladores.Preguntas;

import org.example.discerial.DAO.IPregunta;
import org.example.discerial.DAO.IPreguntaImpl;
import org.example.discerial.entities.Pregunta;
import org.example.discerial.entities.Categoria;

import java.util.List;

public class PreguntasFilosofia {

    public static void crearPreguntasFilosofia() {
        IPregunta preguntaDao = new IPreguntaImpl();
        int categoriaId = 3; // ID para Filosofia

        // Lista de preguntas con ID manual (añadido un campo id en la posición 0)positivismo lógico

        Object[][] preguntas = {

                {101, "¿Quién es considerado el padre de la filosofía occidental?", "Heráclito", "Sócrates", "Epicuro", "Tales de Mileto", "multiple"},
                {102, "¿Qué filósofo escribió 'La República'?", "Aristóteles", "Sócrates", "Heráclito", "Platón", "multiple"},
                {103, "¿Qué filósofo constituye el raciovitalismo como doctrina?", "Hegel", "María Zambrano", "Nietzsche", "Ortega y Gasset", "multiple"},
                {104, "¿Cuál es una característica de la duda metódica'?", "Innata", "Permanente", "Empírica", "Teorética", "multiple"},
                {105, "¿Cuál es una característica del mundo sensible?", "Eterna", "Extramental", "Inmutable", "Mutable", "multiple"},
                {106, "¿Qué escuela filosófica enseñaba a vivir conforme a la virtud y la razón?", "Escepticismo", "Hedonismo", "Cinismo", "Estoicismo", "multiple"},
                {107, "¿Qué filósofo es conocido por su teoría de las ideas o formas?", "Aristóteles", "Santo Tomás", "Descartes", "Platón", "multiple"},
                {108, "¿Cuál es el concepto central del existencialismo?", "Razón universal", "Armonía natural", "Sensación", "Libertad individual", "multiple"},
                {109, "¿Quién escribió 'Así habló Zaratustra'?", "Kant", "Heidegger", "Schopenhauer", "Nietzsche", "multiple"},
                {110, "¿Qué filósofo medieval intentó conciliar fe y razón?", "San Agustín", "Avicena", "Anselmo", "Santo Tomás de Aquino", "multiple"},
                {111, "¿Qué filósofo defendió la abolición de las pasiones para alcanzar la felicidad?", "Aristóteles", "Epicuro", "Nietzsche", "Spinoza", "multiple"},
                {112, "¿Qué corriente filosófica moderna pone el énfasis en la estructura del lenguaje?", "Utilitarismo", "Existencialismo", "Fenomenología", "Estructuralismo", "multiple"},
                {113, "¿Qué filósofo escribió 'El contrato social'?", "Locke", "Hobbes", "Montesquieu", "Rousseau", "multiple"},
                {114, "¿Quién es el filosófo que se opone al dualismo epistemológico de Platón?", "Kant", "Spinoza", "Heidegger", "Nietzsche", "multiple"},
                {115, "¿Qué filósofo fue maestro de Alejandro Magno?", "Sócrates", "Platón", "Demócrito", "Aristóteles", "multiple"},
                {116, "¿Cuál es el principio fundamental del utilitarismo?", "Intuición moral", "Deber ético", "Derechos naturales", "La mayor felicidad para el mayor número", "multiple"},
                {117, "¿Qué filósofo introdujo el concepto de voluntad de poder?", "Hegel", "Kant", "Sartre", "Nietzsche", "multiple"},
                {118, "¿Qué filósofo es considerado fundador de la lógica formal?", "Descartes", "Kant", "Sócrates", "Aristóteles", "multiple"},
                {119, "¿Qué filósofo desarrolló el método de la duda metódica?", "Hume", "Kant", "Spinoza", "Descartes", "multiple"},
                {120, "¿Qué movimiento filosófico afirma que el mundo existe independientemente de nuestras percepciones?", "Idealismo", "Positivismo", "Fenomenología", "Realismo", "multiple"},
                {121, "¿Qué filósofo escribió 'Crítica de la razón pura'?", "Hume", "Descartes", "Nietzsche", "Kant", "multiple"},
                {122, "¿Qué pensador afirmó que 'el hombre es un lobo para el hombre'?", "Rousseau", "Kant", "Locke", "Hobbes", "multiple"},
                {123, "¿Quién propuso el concepto de alienación en el trabajo?", "Nietzsche", "Hegel", "Durkheim", "Marx", "multiple"},
                {124, "¿Qué filósofo utilizó el diálogo como forma principal de enseñanza?", "Aristóteles", "Kant", "Epicuro", "Sócrates", "multiple"},
                {125, "¿Qué corriente filosófica niega la posibilidad de conocer la verdad absoluta?", "Racionalismo", "Empirismo", "Idealismo", "Escepticismo", "multiple"},
                {126, "¿Qué filósofo escribió 'El ser y la nada'?", "Heidegger", "Nietzsche", "Camus", "Sartre", "multiple"},
                {127, "¿Quién desarrolló la dialéctica del amo y el esclavo?", "Nietzsche", "Kant", "Marx", "Hegel", "multiple"},
                {128, "¿Qué escuela filosófica fundó Zenón de Citio?", "Cinismo", "Epicureísmo", "Escepticismo", "Estoicismo", "multiple"},
                {129, "¿Qué filósofo propuso el 'velo de la ignorancia'?", "Nietzsche", "Kant", "Hobbes", "Rawls", "multiple"},
                {130, "¿Qué obra escribió Maquiavelo sobre la política?", "El Leviatán", "El contrato social", "Utopía", "El Príncipe", "multiple"},
                {131, "¿Qué filósofo defendía que el lenguaje estructura el pensamiento?", "Descartes", "Hume", "Nietzsche", "Wittgenstein", "multiple"},
                {132, "¿Quién dijo que 'el hombre está condenado a ser libre'?", "Heidegger", "Nietzsche", "Camus", "Sartre", "multiple"},
                {133, "¿Qué filósofo es conocido por su principio de 'la navaja'?", "Kant", "Hume", "Aristóteles", "Ockham", "multiple"},
                {134, "¿Qué filósofo francés exploró la noción de biopoder?", "Sartre", "Camus", "Bergson", "Foucault", "multiple"},
                {135, "¿Qué filósofo desarrolló la fenomenología?", "Nietzsche", "Heidegger", "Sartre", "Husserl", "multiple"},
                {136, "¿Qué filósofo moderno desarrolló el concepto de 'Lebenswelt' o 'mundo de la vida'?", "Foucault", "Camus", "Sartre", "Husserl", "multiple"},
                {137, "¿Quién propuso la 'teoría de la justicia como equidad'?", "Kant", "Hobbes", "Mill", "Rawls", "multiple"},
                {138, "¿Qué filósofo influyó profundamente en la teología cristiana con su obra 'Confesiones'?", "Tomás de Aquino", "Boecio", "Anselmo", "San Agustín", "multiple"},
                {139, "¿Qué pensador existencialista escribió 'El mito de Sísifo'?", "Heidegger", "Kierkegaard", "Sartre", "Camus", "multiple"},
                {140, "¿Qué filósofo definió al hombre como 'animal racional'?", "Descartes", "Platón", "Sócrates", "Aristóteles", "multiple"},
                {141, "¿Qué filósofo propone el ascetismo como actitud ante la vida?", "Kant", "Sartre", "Heidegger", "Schopenhauer", "multiple"},
                {142, "¿Quién es considerado el padre del racionalismo moderno?", "Aristóteles", "Hume", "Kant", "Descartes", "multiple"},
                {143, "¿Qué corriente sostiene que el conocimiento se origina en la experiencia?", "Racionalismo", "Idealismo", "Escepticismo", "Empirismo", "multiple"},
                {144, "¿Quién escribió 'Meditaciones metafísicas'?", "Kant", "Leibniz", "Hume", "Descartes", "multiple"},
                {145, "¿Cuál es la tercera verdad que encuentra Descartes con respecto al conocimiento indudable'?", "Cogito, ergo sum", "Res infinita", "Res cogitans", "Res extensa", "multiple"},
                {146, "¿Qué filósofo propuso el concepto de 'superhombre'?", "Sartre", "Heidegger", "Kant", "Nietzsche", "multiple"},
                {147, "¿Qué corriente filosófica defiende que 'el fin justifica los medios'?", "Estoicismo", "Epicureísmo", "Escepticismo", "Maquiavelismo", "multiple"},
                {148, "¿Qué pensador defendió la separación entre Iglesia y Estado?", "Hobbes", "Kant", "Nietzsche", "Voltaire", "multiple"},
                {149, "¿Qué filósofo dijo que el conocimiento es una 'tabla rasa' al nacer?", "Descartes", "Kant", "Sartre", "Locke", "multiple"},
                {150, "¿Qué escuela griega buscaba la felicidad mediante la ausencia de dolor?", "Cinismo", "Estoicismo", "Escépticos", "Epicureísmo", "multiple"},
                {151, "¿Qué filósofo medieval integró la filosofía de Aristóteles con el cristianismo?", "San Agustín", "Boecio", "Anselmo", "Tomás de Aquino", "multiple"},
                {152, "¿Qué autor considera que el cristianismo es un platonismo para el pueblo que crea valores propios del rebaño y resentimiento por la vida?", "Sartre", "Heidegger", "Kant", "Nietzsche", "multiple"},
                {153, "¿Quién propuso el 'imperativo categórico' como base de la moral?", "Hobbes", "Mill", "Socrates", "Kant", "multiple"},
                {154, "¿Qué filósofo afirmaba que el ser precede a la esencia?", "Heidegger", "Nietzsche", "Camus", "Sartre", "multiple"},
                {155, "¿Qué pensador fue condenado a muerte por corromper a la juventud?", "Platón", "Aristóteles", "Epicuro", "Sócrates", "multiple"},
                {156, "¿Qué filósofo planteó el dilema del 'burro de Buridán'?", "Kant", "Hume", "Descartes", "Buridán", "multiple"},
                {157, "¿Qué corriente filosófica plantea que solo existe una sustancia?", "Dualismo", "Empirismo", "Escepticismo", "Monismo", "multiple"},
                {158, "¿Qué filósofo desarrolló el concepto de 'nihilismo'?", "Sartre", "Hume", "Heidegger", "Nietzsche", "multiple"},
                {159, "¿Qué corriente sostiene que el placer es el bien supremo?", "Estoicismo", "Cinismo", "Escepticismo", "Hedonismo", "multiple"},
                {160, "¿Qué filósofo es considerado el fundador del utilitarismo?", "Kant", "Sartre", "Hobbes", "Bentham", "multiple"},
                {161, "¿Qué filósofo es conocido por su teoría del contrato social?", "Nietzsche", "Aristóteles", "Kant", "Rousseau", "multiple"},
                {162, "¿Qué filósofo es famoso por su alegoría de la caverna?", "Epicuro", "Sócrates", "Aristóteles", "Platón", "multiple"},
                {163, "¿Qué corriente filosófica afirma que nada puede ser conocido con certeza?", "Hedonismo", "Racionalismo", "Empirismo", "Escepticismo", "multiple"},
                {164, "¿Qué pensador medieval afirmó que 'comprender es creer'?", "Tomás de Aquino", "Boecio", "Ockham", "San Agustín", "multiple"},
                {165, "¿Qué filósofo moderno escribió 'El Leviatán'?", "Kant", "Descartes", "Rousseau", "Hobbes", "multiple"},
                {166, "¿Qué pensador estableció las bases del positivismo lógico?", "Kant", "Hume", "Wittgenstein", "Comte", "multiple"},
                {167, "¿Qué escuela filosófica sostiene que todo está determinado por causas previas?", "Existencialismo", "Escepticismo", "Hedonismo", "Determinismo", "multiple"},
                {168, "¿Qué corriente sostiene que la realidad última es inmaterial?", "Empirismo", "Realismo", "Materialismo", "Idealismo", "multiple"},
                {169, "¿Qué filósofo desarrolló la dialéctica hegeliana?", "Nietzsche", "Kant", "Marx", "Hegel", "multiple"},
                {170, "¿Qué pensador afirmó que 'la existencia precede a la esencia'?", "Camus", "Heidegger", "Nietzsche", "Sartre", "multiple"},
                {171, "¿Qué filósofo escribió 'La náusea'?", "Nietzsche", "Camus", "Kierkegaard", "Sartre", "multiple"},
                {172, "¿Qué filósofo promovió el escepticismo como medio para alcanzar la ataraxia?", "Aristóteles", "Epicteto", "Epicuro", "Pirrón", "multiple"},
                {173, "¿Qué corriente filosófica considera el lenguaje como una herramienta para delimitar el pensamiento?", "Hedonismo", "Racionalismo", "Existencialismo", "Positivismo lógico", "multiple"},
                {174, "¿Qué filósofo escribió 'El mito de Sísifo'?", "Nietzsche", "Heidegger", "Sartre", "Camus", "multiple"},
                {175, "¿Qué corriente sostiene que los valores no existen objetivamente?", "Racionalismo", "Empirismo", "Idealismo", "Nihilismo", "multiple"},
                {176, "¿Qué filósofo es considerado el primer pensador existencialista?", "Camus", "Sartre", "Nietzsche", "Kierkegaard", "multiple"},
                {177, "¿Qué filósofo propuso el 'duda metódica' como camino al conocimiento?", "Hume", "Kant", "Aristóteles", "Descartes", "multiple"},
                {178, "¿Qué filósofo escribió 'Crítica de la razón pura'?", "Nietzsche", "Hume", "Sartre", "Kant", "multiple"},
                {179, "¿Qué escuela griega valoraba el autocontrol y la virtud ante todo?", "Epicureísmo", "Cinismo", "Escepticismo", "Estoicismo", "multiple"},
                {180, "¿Qué filósofo es conocido por la frase 'Dios ha muerto'?", "Sartre", "Camus", "Kant", "Nietzsche", "multiple"},
                {181, "¿Qué filósofo griego fue maestro de Alejandro Magno?", "Sócrates", "Platón", "Heráclito", "Aristóteles", "multiple"},
                {182, "¿Qué filósofo sostuvo que el arjé era el fuego?", "Anaximandro", "Tales", "Pitágoras", "Heráclito", "multiple"},
                {183, "¿Qué filósofo presocrático decía que todo estaba compuesto por átomos?", "Parménides", "Empédocles", "Anaxímenes", "Demócrito", "multiple"},
                {184, "¿Quién fue el fundador del cinismo?", "Epicteto", "Sócrates", "Zenón", "Diógenes", "multiple"},
                {185, "¿Qué filósofo defendía que la virtud era suficiente para la felicidad?", "Epicuro", "Protagoras", "Gorgias", "Sócrates", "multiple"},
                {186, "¿Qué filósofo es considerado el padre del empirismo moderno?", "Descartes", "Kant", "Leibniz", "Locke", "multiple"},
                {187, "¿Qué filósofo escribió 'La política'?", "Platón", "Sócrates", "Heródoto", "Aristóteles", "multiple"},
                {188, "¿Qué filósofo medieval desarrolló el argumento ontológico de la existencia de Dios?", "Boecio", "Tomás de Aquino", "Ockham", "Anselmo de Canterbury", "multiple"},
                {189, "¿Qué filósofo dijo 'Pienso, luego existo'?", "Kant", "Locke", "Spinoza", "Descartes", "multiple"},
                {190, "¿Qué corriente filosófica sostiene que la experiencia es la fuente del conocimiento?", "Racionalismo", "Idealismo", "Escepticismo", "Empirismo", "multiple"},
                {191, "¿Qué filósofo decía que el hombre es 'un lobo para el hombre'?", "Platón", "Kant", "Rousseau", "Hobbes", "multiple"},
                {192, "¿Qué escuela filosófica afirma que el placer es el fin supremo de la vida?", "Estoicismo", "Cinisimo", "Escepticismo", "Hedonismo", "multiple"},
                {193, "¿Qué filósofo fundó la escuela del Liceo?", "Platón", "Sócrates", "Pitágoras", "Aristóteles", "multiple"},
                {194, "¿Qué corriente filosófica es característica del pensamiento oriental antiguo?", "Racionalismo", "Materialismo", "Positivismo", "Taoísmo", "multiple"},
                {195, "¿Qué filósofo desarrolló la noción del superhombre?", "Camus", "Sartre", "Hegel", "Nietzsche", "multiple"},
                {196, "¿Qué filósofo sostuvo que el tiempo y el espacio son formas de la sensibilidad?", "Locke", "Hume", "Descartes", "Kant", "multiple"},
                {197, "¿Qué filósofo hablaba del 'eterno retorno'?", "Sartre", "Heidegger", "Kierkegaard", "Nietzsche", "multiple"},
                {198, "¿Qué filósofo escribió 'Ser y tiempo'?", "Camus", "Sartre", "Nietzsche", "Heidegger", "multiple"},
                {199, "¿Qué filósofo sostenía que el lenguaje configura la realidad?", "Hume", "Descartes", "Kant", "Wittgenstein", "multiple"},
                {200, "¿Qué corriente filosófica pone al individuo como centro de la existencia?", "Racionalismo", "Escepticismo", "Nihilismo", "Existencialismo", "multiple"}




        };

        for (Object[] d : preguntas) {
            int idPregunta = (int) d[0];

            // 1) Verificar si ya existe la pregunta con ese ID
            if (preguntaDao.findById(idPregunta).isEmpty()) {
                Pregunta q = new Pregunta();
                q.setId(idPregunta); // Asignamos el ID manual
                q.setCategoria(new Categoria(categoriaId, "Filosofía"));
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
