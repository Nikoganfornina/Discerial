package org.example.discerial.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

public class Sesion {

    @Id
    @GeneratedValue
     private int id;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    Usuarios usuario;
    @Column(nullable = false)
    private boolean conectado;

    @Column(name = "ultimaConexion")
    private LocalDateTime ultimaConexion;

    public Sesion() {
    }

    public Sesion(Usuarios usuario, boolean conectado, LocalDateTime ultimaConexion) {
        this.usuario = usuario;
        this.conectado = conectado;
        this.ultimaConexion = ultimaConexion;
    }

    public int getId() {
        return id;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public boolean isConectado() {
        return conectado;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
        this.ultimaConexion = LocalDateTime.now(); 
    }

    public LocalDateTime getUltimaConexion() {
        return ultimaConexion;
    }

}

