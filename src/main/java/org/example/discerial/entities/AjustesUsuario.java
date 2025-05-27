package org.example.discerial.entities;

import jakarta.persistence.*;
import org.example.discerial.Util.TiempoPreguntas;

@Entity
@Table(name = "ajustes_usuario")
public class AjustesUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean efectosActivados = true;

    private boolean musicaActivada = true;

    @Column(name = "nivel_musica")
    private int nivelMusica = 100; // valor por defecto de volumen (1 a 100)

    @Enumerated(EnumType.STRING)
    @Column(name = "tiempo_preguntas")
    private TiempoPreguntas tiempoPreguntas = TiempoPreguntas.SEGUNDOS_20;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false, unique = true)
    private Usuarios usuario;

    public AjustesUsuario() {}

    public AjustesUsuario(boolean efectosActivados, boolean musicaActivada, int nivelMusica, TiempoPreguntas tiempoPreguntas, Usuarios usuario) {
        this.efectosActivados = efectosActivados;
        this.musicaActivada = musicaActivada;
        this.nivelMusica = nivelMusica;
        this.tiempoPreguntas = tiempoPreguntas;
        this.usuario = usuario;
    }

    // Getters y setters

    public int getId() { return id; }

    public boolean isEfectosActivados() { return efectosActivados; }
    public void setEfectosActivados(boolean efectosActivados) { this.efectosActivados = efectosActivados; }

    public boolean isMusicaActivada() { return musicaActivada; }
    public void setMusicaActivada(boolean musicaActivada) { this.musicaActivada = musicaActivada; }

    public int getNivelMusica() { return nivelMusica; }
    public void setNivelMusica(int nivelMusica) {
        if (nivelMusica < 1) this.nivelMusica = 1;
        else if (nivelMusica > 100) this.nivelMusica = 100;
        else this.nivelMusica = nivelMusica;
    }

    public TiempoPreguntas getTiempoPreguntas() { return tiempoPreguntas; }
    public void setTiempoPreguntas(TiempoPreguntas tiempoPreguntas) { this.tiempoPreguntas = tiempoPreguntas; }

    public Usuarios getUsuario() { return usuario; }
    public void setUsuario(Usuarios usuario) { this.usuario = usuario; }
}
