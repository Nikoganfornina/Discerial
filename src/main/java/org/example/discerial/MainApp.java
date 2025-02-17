package org.example.discerial;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainApp extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage Stage) throws Exception {
        primaryStage = Stage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/discerial/MainApp_View.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Biblioteca Niko");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static void switchScene(String fxml) throws Exception {
        FXMLLoader fXMLLoader = new FXMLLoader(MainApp.class.getResource(fxml));
        Scene scene = new Scene(fXMLLoader.load());
        primaryStage.setScene(scene);
    }

    public static void main(String[] args) {
        launch();
        Font.getFamilies().forEach(System.out::println);
    }
}
