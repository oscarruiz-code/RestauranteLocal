package org.example.restaurante.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class PrincipalController {

    @FXML
    private ImageView backgroundImage;

    @FXML
    private void irAGestionClientes() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/restaurante/vistas/Cliente.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Gestión de Clientes");
        stage.show();
    }

    @FXML
    private void irAGestionProductos() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/restaurante/vistas/Producto.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Gestión de Productos");
        stage.show();
    }

    @FXML
    private void irAGestionPedidos() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/restaurante/vistas/Pedidos.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Gestión de Pedidos");
        stage.show();
    }
}
