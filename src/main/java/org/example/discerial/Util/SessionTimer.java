package org.example.discerial.Util;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import org.example.discerial.DAO.IusuariosImpl;
import org.example.discerial.entities.Usuarios;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class SessionTimer {
    private volatile long startTime = 0;
    private volatile long accumulatedTime = 0;
    private volatile boolean running = false;
    private final IusuariosImpl dao;
    private ScheduledExecutorService scheduler;
    private ScheduledFuture<?> updateTask;
    private volatile long sessionAccumulated = 0; // Tiempo de ESTA sesión
    private volatile long totalAccumulated = 0;    // Tiempo total (BD + sesión actual)
    private LongProperty elapsedTime = new SimpleLongProperty();
    private Usuarios usuarioActivo; // Declaración correcta

    // Singleton para una única instancia
    private static SessionTimer instance;

    private SessionTimer() {
        dao = new IusuariosImpl();
    }

    public static SessionTimer getInstance() {
        if (instance == null) {
            instance = new SessionTimer();
        }
        return instance;
    }



    public void start() throws Exception {
        this.usuarioActivo = dao.currentUser();

        if (running) return;

        Usuarios usuario = dao.currentUser();
        if (usuario != null && usuario.isSessionActive()) {
            // Obtener el valor ACTUAL de la BD
            totalAccumulated = dao.getHorasJugadasFromBD(usuario.getId());

            // Inicializar contadores
            sessionAccumulated = 0;
            startTime = System.currentTimeMillis();
            running = true;

            scheduler = Executors.newSingleThreadScheduledExecutor();
            updateTask = scheduler.scheduleAtFixedRate(() -> {

                try {
                    // Calcular tiempo transcurrido y sumar al total
                    long elapsed = System.currentTimeMillis() - startTime;
                    dao.addHorasJugadasNative(usuario.getId(), elapsed); // Suma a la BD
                    totalAccumulated += elapsed;
                    sessionAccumulated += elapsed;
                    startTime = System.currentTimeMillis(); // Resetear contador parcial
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, 5, 5, TimeUnit.SECONDS);

            System.out.printf("Timer iniciado. Tiempo acumulado: %d ms%n", totalAccumulated);
        }
    }

    public void stop() {
        if (!running) return;
        running = false;

        try {
            // Detener todas las tareas programadas
            if (updateTask != null && !updateTask.isDone()) {
                updateTask.cancel(true);
            }

            // Cerrar el scheduler
            if (scheduler != null && !scheduler.isShutdown()) {
                scheduler.shutdownNow();
                if (!scheduler.awaitTermination(1, TimeUnit.SECONDS)) {
                    System.err.println("El scheduler no terminó correctamente");
                }
            }

            // Guardar tiempo final en BD
            if (usuarioActivo != null) {
                long finalTime = getElapsedTime();
                dao.addHorasJugadasNative(usuarioActivo.getId(), finalTime);
                dao.cerrarSesionSoloSessionActive(usuarioActivo.getId());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Resetear variables
            accumulatedTime = 0;
            startTime = 0;
            usuarioActivo = null;
        }
    }

    public boolean isRunning() {
        return running;
    }


    private void updateDatabase() {
        if (!running) return;

        long currentElapsed = System.currentTimeMillis() - startTime;
        accumulatedTime += currentElapsed;
        startTime = System.currentTimeMillis();

        // Actualizar BD sumando tiempo transcurrido
        dao.addHorasJugadasNative(usuarioActivo.getId(), currentElapsed);
    }

    public LongProperty elapsedTimeProperty() {
        return elapsedTime;
    }
    public long getElapsedTime() {
        if (running) {
            return accumulatedTime + (System.currentTimeMillis() - startTime);
        } else {
            return accumulatedTime; // Devuelve el valor acumulado, no 0
        }
    }


}