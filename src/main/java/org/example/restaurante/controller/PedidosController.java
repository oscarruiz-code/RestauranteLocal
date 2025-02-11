package org.example.restaurante.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.restaurante.entity.Cliente;
import org.example.restaurante.entity.Pedidos;
import org.example.restaurante.manejador.ManejadorPedidos;

import java.util.List;

public class PedidosController {

    @FXML
    private TableView<Pedidos> pedidoTable;

    @FXML
    private TableColumn<Pedidos, Integer> idColumn;

    @FXML
    private TableColumn<Pedidos, Cliente> clienteColumn;

    @FXML
    private TableColumn<Pedidos, String> estadoColumn;

    @FXML
    private TableColumn<Pedidos, Double> totalColumn;

    @FXML
    private TextField clienteField;
    @FXML
    private TextField estadoField;
    @FXML
    private TextField totalField;
    @FXML
    private TextField buscarField;

    private ManejadorPedidos manejadorPedidos = new ManejadorPedidos();
    private ObservableList<Pedidos> pedidoData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        clienteColumn.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        estadoColumn.setCellValueFactory(new PropertyValueFactory<>("estado"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));

        cargarDatos();
    }

    private void cargarDatos() {
        List<Pedidos> pedidos = manejadorPedidos.obtenerTodosLosPedidos();
        pedidoData.setAll(pedidos);
        pedidoTable.setItems(pedidoData);
    }

    @FXML
    private void agregarPedido() {
        // Implementación para agregar pedidos
    }

    @FXML
    private void actualizarPedido() {
        Pedidos pedidoSeleccionado = pedidoTable.getSelectionModel().getSelectedItem();
        if (pedidoSeleccionado != null) {
            String estado = estadoField.getText();
            double total = Double.parseDouble(totalField.getText());
            pedidoSeleccionado.setEstado(estado);
            pedidoSeleccionado.setTotal(total);
            manejadorPedidos.actualizarPedido(pedidoSeleccionado);
            cargarDatos();
            limpiarCampos();
        }
    }

    @FXML
    private void eliminarPedido() {
        Pedidos pedidoSeleccionado = pedidoTable.getSelectionModel().getSelectedItem();
        if (pedidoSeleccionado != null) {
            manejadorPedidos.eliminarPedido(pedidoSeleccionado.getId());
            cargarDatos();
        }
    }

    @FXML
    private void buscarPedido() {
        String estadoBusqueda = buscarField.getText();
        List<Pedidos> pedidos = manejadorPedidos.buscarPedidosPorEstado(estadoBusqueda);
        pedidoData.setAll(pedidos);
        pedidoTable.setItems(pedidoData);
    }

    @FXML
    private void mostrarTodos() {
        cargarDatos();
    }

    private void limpiarCampos() {
        clienteField.clear();
        estadoField.clear();
        totalField.clear();
        buscarField.clear();
    }
}
