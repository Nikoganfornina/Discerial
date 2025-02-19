package org.example.discerial.Sqlite;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InicializarBD {

    // Constante para la sentencia SQL de creación de tabla
    private static final String SQL_USUARIOS = "CREATE TABLE IF NOT EXISTS usuarios ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "nombre TEXT NOT NULL, "
            + "nickname TEXT NOT NULL UNIQUE, "
            + "correo TEXT NOT NULL UNIQUE, "
            + "contraseña TEXT NOT NULL, "
            + "categoriafav TEXT, "
            + "preguntas_acertadas INTEGER DEFAULT 0, "
            + "preguntas_erroneas INTEGER DEFAULT 0, "
            + "imagen TEXT"
            + ");";


    public static void crearTablaUsuarios() {
        try (Connection conn = ConexionSQLite.conectar();
             Statement stmt = conn.createStatement()) {

            stmt.execute(SQL_USUARIOS);
            System.out.println("Tabla usuarios creada correctamente en discerial.db.");
        } catch (SQLException e) {
            System.out.println("Error al crear tabla usuarios: " + e.getMessage());
        }
    }

    public static void inicializarBD() {
        crearTablaUsuarios();
    }
}
