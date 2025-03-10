package org.example.discerial.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "preguntas")
public class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Relación con la categoría con carga EAGER para evitar problemas de LazyInitializationException
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @Column(nullable = false, length = 500)
    private String pregunta;

    @Column(name = "respuesta_correcta", nullable = false)
    private String respuestaCorrecta;

    @Column(name = "respuesta_2")
    private String respuesta2;

    @Column(name = "respuesta_3")
    private String respuesta3;

    @Column(name = "respuesta_4")
    private String respuesta4;

    // URL de la imagen (puede ser null)
    @Column
    private String imagen;

    // "multiple" para opción múltiple, "vf" para Verdadero/Falso
    @Column(name = "tipo", nullable = false)
    private String tipo;

    public Pregunta() { }

    public Pregunta(Categoria categoria, String pregunta, String respuestaCorrecta,
                    String respuesta2, String respuesta3, String respuesta4,
                    String imagen, String tipo) {
        this.categoria = categoria;
        this.pregunta = pregunta;
        this.respuestaCorrecta = respuestaCorrecta;
        this.respuesta2 = respuesta2;
        this.respuesta3 = respuesta3;
        this.respuesta4 = respuesta4;
        this.imagen = imagen;
        this.tipo = tipo;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public String getPregunta() {
        return pregunta;
    }
    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }
    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }
    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }
    public String getRespuesta2() {
        return respuesta2;
    }
    public void setRespuesta2(String respuesta2) {
        this.respuesta2 = respuesta2;
    }
    public String getRespuesta3() {
        return respuesta3;
    }
    public void setRespuesta3(String respuesta3) {
        this.respuesta3 = respuesta3;
    }
    public String getRespuesta4() {
        return respuesta4;
    }
    public void setRespuesta4(String respuesta4) {
        this.respuesta4 = respuesta4;
    }
    public String getImagen() {
        return imagen;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
