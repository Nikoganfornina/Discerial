package org.example.discerial.entities;

import jakarta.persistence.*;



@Entity
@Table(name = "categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String nombre;

    public Categoria() { }

    // Si realmente lo necesitas, asigna tanto id como nombre:
    public Categoria(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Constructor para seed, que **sí** fije el nombre:
    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    // Getters & Setters…
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    @Override
    public String toString() {
        return nombre;
    }
}
