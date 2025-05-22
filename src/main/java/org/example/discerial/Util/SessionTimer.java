package org.example.discerial.Util;

import org.example.discerial.DAO.IusuariosImpl;
import org.example.discerial.entities.Usuarios;

public class SessionTimer {

    private long startTime = 0;
    private long accumulatedTime = 0;
    private boolean running = false;

    private final IusuariosImpl dao; // DAO para acceder a usuarios

    public SessionTimer() {
        dao = new IusuariosImpl();
    }

    /**
     * Inicia el timer si hay usuario activo en BD (sessionActive = true).
     * Suma el tiempo acumulado previo desde la BD y comienza el conteo.
     * Si no hay usuario activo, no hace nada.
     */
    public void start() throws Exception {
        if (running) return;

        Usuarios usuarioActivo = dao.currentUser();
        if (usuarioActivo != null && usuarioActivo.isSessionActive()) {
            // Recuperamos tiempo acumulado previo (suponiendo que tienes un campo long en Usuarios)
            accumulatedTime = usuarioActivo.getHorasJugadas(); // Ejemplo: getter para tiempo acumulado
            startTime = System.currentTimeMillis();
            running = true;
            System.out.println("SessionTimer: iniciando timer para usuario " + usuarioActivo.getId());
        } else {
            System.out.println("SessionTimer: no hay usuario activo, no inicia timer.");
        }
    }

    /**
     * Para el timer y suma el tiempo transcurrido a la base de datos,
     * actualizando el campo tiempo acumulado del usuario.
     * También desactiva la sesión (sessionActive = false).
     */
    public void stop() throws Exception {
        if (!running) {
            System.out.println("SessionTimer: no estaba corriendo, nada que hacer en stop.");
            return;
        }

        Usuarios usuarioActivo = dao.currentUser();
        if (usuarioActivo != null && usuarioActivo.isSessionActive()) {
            long endTime = System.currentTimeMillis();
            long tiempoSesionActual = endTime - startTime;
            accumulatedTime += tiempoSesionActual;

            // Actualizar el tiempo acumulado en BD
            usuarioActivo.setHorasJugadas(accumulatedTime);

            // Guardar en BD y desactivar sesión
            dao.cerrarSesion(usuarioActivo.getId());

            running = false;

            System.out.println("SessionTimer: sesión parada para usuario " + usuarioActivo.getId() +
                    ". Tiempo total acumulado: " + accumulatedTime + " ms.");
        } else {
            System.out.println("SessionTimer: no hay usuario activo o sesión ya inactiva.");
        }
    }

    /**
     * Devuelve el tiempo acumulado total en milisegundos.
     * Si está corriendo, suma tiempo desde start hasta ahora.
     */
    public long getElapsedTime() {
        if (running) {
            return accumulatedTime + (System.currentTimeMillis() - startTime);
        } else {
            return accumulatedTime;
        }
    }
}
