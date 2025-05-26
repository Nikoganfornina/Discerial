package org.example.discerial.Util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.discerial.DAO.IusuariosImpl;
import org.example.discerial.entities.Usuarios;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SessionManager {

    private static Stage mainStage;

    public static void switchScene(String fxmlPath) throws IOException {
        if (mainStage == null) {
            throw new IllegalStateException("Error: mainStage no configurado.");
        }

        // Cargar y mostrar la nueva escena
        Parent root = FXMLLoader.load(SessionManager.class.getResource(fxmlPath));
        mainStage.setScene(new Scene(root));
    }

    public static void setMainStage(Stage primaryStage) {
        mainStage = primaryStage;
    }


    public static void ajustarAutoIncrementPreguntas() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // 1) Obtener el mayor ID existente
            Number maxId = (Number) session.createQuery(
                            "select max(p.id) from Pregunta p")
                    .uniqueResult();
            int siguiente = (maxId != null ? maxId.intValue() : 0) + 1;

            // 2) Lanzar ALTER TABLE para ajustar AUTO_INCREMENT
            Transaction tx = session.beginTransaction();
            session.createNativeQuery(
                            "ALTER TABLE preguntas AUTO_INCREMENT = :next")
                    .setParameter("next", siguiente)
                    .executeUpdate();
            tx.commit();
        }
    }


}