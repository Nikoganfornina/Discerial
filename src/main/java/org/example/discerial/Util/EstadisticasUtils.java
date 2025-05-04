package org.example.discerial.Util;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.Map;

public class EstadisticasUtils {

    /**
     * Crea un BarChart<String,Number> con dos series ("Aciertos" y "Fallos")
     * a partir de un mapa donde la clave es el nombre de la categoría y el valor
     * es un array int[]{aciertos, fallos}.
     */
    public static BarChart<String,Number> crearBarChart(Map<String,int[]> stats) {
        // Ejes
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Categoría");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Cantidad");

        // Gráfico
        BarChart<String,Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Avance por Categoría");

        // Serie de aciertos
        XYChart.Series<String,Number> serieAciertos = new XYChart.Series<>();
        serieAciertos.setName("Aciertos");

        // Serie de fallos
        XYChart.Series<String,Number> serieFallos = new XYChart.Series<>();
        serieFallos.setName("Fallos");

        // Rellenar series
        for (Map.Entry<String,int[]> entry : stats.entrySet()) {
            String categoria = entry.getKey();
            int[] valores = entry.getValue();
            int aciertos = valores[0];
            int fallos   = valores[1];

            serieAciertos.getData().add(new XYChart.Data<>(categoria, aciertos));
            serieFallos   .getData().add(new XYChart.Data<>(categoria, fallos));
        }

        // Añadir series al chart
        barChart.getData().addAll(serieAciertos, serieFallos);

        return barChart;
    }
}
