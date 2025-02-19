package org.example.discerial.Sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConexionSQLite {

    private static final String DATABASE_NAME = "discerial.db";
    private static Connection conn;

    public static Connection conectar() throws SQLException {
        // Obtener la ruta del directorio src/main/java (directorio raíz del proyecto)
        Path path = Paths.get("src", "main", "java", "org", "example", "discerial", "Sqlite", DATABASE_NAME);
        String url = "jdbc:sqlite:" + path.toAbsolutePath().toString();  // Convertir la ruta a absoluta

        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(url);  // Conectar usando la URL modificada
        }
        return conn;
    }

    public void desconectar() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
