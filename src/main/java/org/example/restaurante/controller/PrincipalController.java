package org.example.restaurante.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import org.example.restaurante.HelloApplication;

import java.io.IOException;

/**
 * Controlador principal para la navegación en la interfaz de usuario.
 *
 * @autor oscarruiz-code
 */
public class PrincipalController {

    @FXML
    private ImageView backgroundImage;

    /**
     * Navega a la vista de gestión de clientes.
     *
     * @throws IOException Si ocurre un error al cargar la vista.
     */
    @FXML
    private void irAGestionClientes() throws IOException {
        openSecondaryStage("/org/example/restaurante/vistas/Cliente.fxml", "Gestión de Clientes");
    }

    /**
     * Navega a la vista de gestión de productos.
     *
     * @throws IOException Si ocurre un error al cargar la vista.
     */
    @FXML
    private void irAGestionProductos() throws IOException {
        openSecondaryStage("/org/example/restaurante/vistas/Producto.fxml", "Gestión de Productos");
    }

    /**
     * Navega a la vista de gestión de pedidos.
     *
     * @throws IOException Si ocurre un error al cargar la vista.
     */
    @FXML
    private void irAGestionPedidos() throws IOException {
        openSecondaryStage("/org/example/restaurante/vistas/Pedido.fxml", "Gestión de Productos");
    }

    /**
     * Abre una nueva escena en la ventana principal.
     *
     * @param fxmlPath La ruta del archivo FXML de la nueva vista.
     * @param title El título de la nueva vista.
     * @throws IOException Si ocurre un error al cargar la vista.
     */
    private void openSecondaryStage(String fxmlPath, String title) throws IOException {
        if (HelloApplication.primaryStage != null) {
            HelloApplication.primaryStage.close();
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Scene scene = new Scene(loader.load(), 800, 600);
        HelloApplication.primaryStage.setScene(scene);
        HelloApplication.primaryStage.show();
    }

}
