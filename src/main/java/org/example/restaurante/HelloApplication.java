package org.example.restaurante;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal de la aplicación que extiende de Application.
 *
 * @autor oscarruiz-code
 */
public class HelloApplication extends Application {

    public static Stage primaryStage;

    /**
     * Método que se ejecuta al iniciar la aplicación.
     *
     * @param stage El escenario principal de la aplicación.
     * @throws IOException Si ocurre un error al cargar la vista.
     */
    @Override
    public void start(Stage stage) throws IOException {
        if (primaryStage == null) {
            primaryStage = stage;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/restaurante/vistas/Principal.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Restaurante Oscarruiz-code");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Método principal de la aplicación.
     *
     * @param args Los argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        launch();
    }
}
