// src/main/java/org/example/discerial/entities/EstadoUsuario.java
package org.example.discerial.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "estado_usuario")
public class EstadoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuarios usuario;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "pregunta_id", nullable = false)
    private Pregunta pregunta;

    @Column(nullable = false)
    private boolean acertada;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha = LocalDateTime.now();

    public EstadoUsuario() {}

    public EstadoUsuario(Usuarios usuario, Pregunta pregunta, boolean acertada) {
        this.usuario = usuario;
        this.pregunta = pregunta;
        this.acertada = acertada;
    }

    // getters & setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuarios getUsuario() { return usuario; }
    public void setUsuario(Usuarios usuario) { this.usuario = usuario; }

    public Pregunta getPregunta() { return pregunta; }
    public void setPregunta(Pregunta pregunta) { this.pregunta = pregunta; }

    public boolean isAcertada() { return acertada; }
    public void setAcertada(boolean acertada) { this.acertada = acertada; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
}
