package org.example.discerial.Util;

public enum TiempoPreguntas {
    SEGUNDOS_15("15 segundos"),
    SEGUNDOS_20("20 segundos"),
    SEGUNDOS_30("30 segundos");

    private final String texto;

    TiempoPreguntas(String texto) {
        this.texto = texto;
    }

    public static TiempoPreguntas fromString(String texto) {
        for (TiempoPreguntas t : values()) {
            if (t.texto.equalsIgnoreCase(texto)) {
                return t;
            }
        }
        return SEGUNDOS_20; // Valor por defecto
    }

    public int getSegundos() {
        return Integer.parseInt(texto.split(" ")[0]);
    }

    @Override
    public String toString() {
        return texto;
    }
}