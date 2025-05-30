package org.example.discerial.entities;

import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;


@Entity
@Table(name = "usuarios")
public class Usuarios {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int id;

    // Validación del nombre

    // La expresión regular garantiza que el nombre solo contenga letras (mayúsculas y minúsculas) y que tenga entre 2 y 20 caracteres.
    // Esto asegura que el nombre sea adecuado para un usuario (sin números ni caracteres especiales).

    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ]{2,20}$", message = "El nombre debe contener solo letras y tener entre 2 y 20 caracteres.")
    @Column(nullable = false, length = 20)
    private String nombre;

    // Validación del nickname

    // La expresión regular asegura que el nickname solo contenga letras, números y guiones bajos, con una longitud de entre 3 y 15 caracteres.
    // Esto es útil para definir un nombre de usuario único y legible.

    @Pattern(regexp = "^[A-Za-z0-9_]{3,15}$", message = "El nickname debe tener entre 3 y 15 caracteres y solo puede contener letras, números y guiones bajos.")
    @Column(unique = true, nullable = false, length = 15)
    private String nickname;

    // Validación del correo

    // Utiliza la anotación @Email para validar que el correo electrónico tenga un formato correcto (ejemplo: user@domain.com).
    // Esto es necesario para asegurarse de que los usuarios proporcionen un correo electrónico válido para la autenticación o comunicaciones.

    @Email(message = "El correo debe tener un formato válido.")
    @Column(unique = true, nullable = false, length = 100)
    private String correo;

    // Validación de la contraseña

    // La expresión regular valida que la contraseña cumpla con varios requisitos:
    // - Debe contener al menos una letra mayúscula ( (?=.*[A-Z]) ),
    // - Al menos un número ( (?=.*\\d) ),
    // - Al menos un carácter especial como #, @, $, etc. ( (?=.*[@#$%^&+=!]) ),
    // - La longitud mínima es de 8 caracteres.
    // Esto asegura que las contraseñas sean lo suficientemente seguras y difíciles de adivinar.

    @Pattern(regexp = "(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}",
            message = "La contraseña debe contener al menos una mayúscula, un número, un símbolo y tener al menos 8 caracteres.")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres.")
    private String contrasena;

    @Column  private int preguntasAcertadas;
    @Column  private int preguntasErroneas;
    @Lob @Column(columnDefinition = "TEXT")private String imagen;

    @Column(nullable = false)
    private long horasJugadas = 0;

    @Column(name = "session_active", nullable = false)
    private boolean sessionActive = false;

    public Usuarios() {}

    public Usuarios(String nombre, String nickname, String correo, String contrasena, int preguntasAcertadas, int preguntasErroneas, String imagen ,  boolean sessionActive , long horasJugadas) {
        this.nombre = nombre;
        this.nickname = nickname;
        this.correo = correo;
        this.contrasena = contrasena;
        this.preguntasAcertadas = preguntasAcertadas;
        this.preguntasErroneas = preguntasErroneas;
        this.imagen = imagen;
        this.sessionActive = sessionActive;
        this.horasJugadas = horasJugadas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getPreguntasAcertadas() {
        return preguntasAcertadas;
    }

    public void setPreguntasAcertadas(int preguntasAcertadas) {
        this.preguntasAcertadas = preguntasAcertadas;
    }

    public int getPreguntasErroneas() {
        return preguntasErroneas;
    }

    public void setPreguntasErroneas(int preguntasErroneas) {
        this.preguntasErroneas = preguntasErroneas;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getHorasJugadas() {
        long horas = horasJugadas / 3600000; // 1 hora = 3,600,000 ms
        long minutos = (horasJugadas % 3600000) / 60000; // 1 minuto = 60,000 ms
        return String.format("%02d:%02d", horas, minutos);
    }
    public void setHorasJugadas(long horasJugadas) {
        this.horasJugadas = horasJugadas;
    }

    //Sesion activa sera un buleano dentro de la propia clase, el programa preguntara cual es el estado de la sesion si es true o false, en caso de ser true se guardaran los datos en ese menu
    public boolean isSessionActive() { return sessionActive;}
    public void setSessionActive(boolean sessionActive) { this.sessionActive = sessionActive; }
    // Inicializar valores por defecto antes de persistir en la BD

    @PrePersist
    public void prePersist() {
        if (preguntasAcertadas == 0) preguntasAcertadas = 0;
        if (preguntasErroneas == 0) preguntasErroneas = 0;
        if (imagen == null) imagen = "hombre2.jpg";

    }

    // Convertir el tiempo en formato "hh:mm"
    public String getHorasJugadasFormato() {
        long horas = horasJugadas / 3600;
        long minutos = (horasJugadas % 3600) / 60;
        return String.format("%02d:%02d", horas, minutos);
    }

    @Override
    public String toString() {
        return """
       ┌───────────────────────────────────────────────────────┐
       │                     USUARIO                           │
       └───────────────────────────────────────────────────────┘
       - ID: %d
       - Nombre: %s
       - Nickname: %s
       - Correo: %s
       - Contraseña: %s
       - Preguntas Acertadas: %d
       - Preguntas Erróneas: %d
       - Imagen: %s 
       """.formatted(id, nombre, nickname, correo, "********",
                preguntasAcertadas, preguntasErroneas,
                (imagen != null && !imagen.isEmpty()) ? imagen : "No disponible");
    }

}
