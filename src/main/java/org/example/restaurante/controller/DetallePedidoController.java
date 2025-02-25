package org.example.restaurante.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.restaurante.HelloApplication;
import org.example.restaurante.entity.DetallePedido;
import org.example.restaurante.entity.Pedido;
import org.example.restaurante.manejador.ManejadorDetallePedido;

import java.util.List;

/**
 * Controlador para gestionar los detalles de los pedidos en la interfaz de usuario.
 *
 * @autor oscarruiz-code
 */
public class DetallePedidoController {

    @FXML private TableView<DetallePedido> detallePedidoTable;
    @FXML private TableColumn<DetallePedido, Long> idDetallePedidoColumn;
    @FXML private TableColumn<DetallePedido, Long> pedidoIdColumn;
    @FXML private TableColumn<DetallePedido, Integer> productoIdColumn;
    @FXML private TableColumn<DetallePedido, Integer> cantidadColumn;
    @FXML private TableColumn<DetallePedido, Double> precioColumn;
    @FXML private TableColumn<DetallePedido, Double> subtotalColumn;
    @FXML private Button volverButton;

    private ManejadorDetallePedido manejadorDetallePedido = new ManejadorDetallePedido();
    private ObservableList<DetallePedido> detallePedidoData = FXCollections.observableArrayList();

    /**
     * Establece el pedido a mostrar en la tabla de detalles de pedido.
     *
     * @param pedido El pedido a mostrar, o null para cargar todos los detalles de pedidos.
     */
    public void setPedido(Pedido pedido) {
        if (pedido != null) {
            cargarDetallePedidos(pedido);
        } else {
            cargarTodosLosDetalles();
        }
    }

    /**
     * Inicializa la tabla de detalles de pedido.
     */
    @FXML
    public void initialize() {
        idDetallePedidoColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        pedidoIdColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleLongProperty(cellData.getValue().getPedido().getId()).asObject());
        productoIdColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getProductoId()).asObject());
        cantidadColumn.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        precioColumn.setCellValueFactory(new PropertyValueFactory<>("precio"));
        subtotalColumn.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
    }

    /**
     * Carga los detalles del pedido especificado en la tabla.
     *
     * @param pedido El pedido cuyos detalles se van a cargar.
     */
    private void cargarDetallePedidos(Pedido pedido) {
        List<DetallePedido> detallePedidos = manejadorDetallePedido.obtenerDetallesPorPedido(pedido.getId());
        detallePedidoData.setAll(detallePedidos);
        detallePedidoTable.setItems(detallePedidoData);
    }

    /**
     * Carga todos los detalles de pedidos en la tabla.
     */
    private void cargarTodosLosDetalles() {
        List<DetallePedido> detallePedidos = manejadorDetallePedido.obtenerTodosLosDetalles();
        detallePedidoData.setAll(detallePedidos);
        detallePedidoTable.setItems(detallePedidoData);
    }

    /**
     * Vuelve a la vista de pedidos.
     */
    @FXML
    private void volverAPedido() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/restaurante/vistas/Pedido.fxml"));
            Scene scene = new Scene(loader.load(), 800, 600);
            HelloApplication.primaryStage.setScene(scene);
            HelloApplication.primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
