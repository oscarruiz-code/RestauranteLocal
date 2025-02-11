package org.example.restaurante.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.restaurante.entity.Producto;
import org.example.restaurante.manejador.ManejadorProducto;

import java.util.List;

public class ProductoController {

    @FXML
    private TableView<Producto> productoTable;

    @FXML
    private TableColumn<Producto, Integer> idColumn;

    @FXML
    private TableColumn<Producto, String> nombreColumn;

    @FXML
    private TableColumn<Producto, String> categoriaColumn;

    @FXML
    private TableColumn<Producto, Double> precioColumn;

    @FXML
    private TableColumn<Producto, Boolean> disponibilidadColumn;

    @FXML
    private TextField nombreField;
    @FXML
    private TextField categoriaField;
    @FXML
    private TextField precioField;
    @FXML
    private TextField disponibilidadField;
    @FXML
    private TextField buscarField;

    private ManejadorProducto manejadorProducto = new ManejadorProducto();
    private ObservableList<Producto> productoData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        categoriaColumn.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        precioColumn.setCellValueFactory(new PropertyValueFactory<>("precio"));
        disponibilidadColumn.setCellValueFactory(new PropertyValueFactory<>("disponibilidad"));

        cargarDatos();
    }

    private void cargarDatos() {
        List<Producto> productos = manejadorProducto.obtenerTodosLosProductos();
        productoData.setAll(productos);
        productoTable.setItems(productoData);
    }

    @FXML
    private void agregarProducto() {
        String nombre = nombreField.getText();
        String categoria = categoriaField.getText();
        double precio = Double.parseDouble(precioField.getText());
        boolean disponibilidad = Boolean.parseBoolean(disponibilidadField.getText());
        manejadorProducto.ingresarProducto(nombre, categoria, precio, disponibilidad);
        cargarDatos();
        limpiarCampos();
    }

    @FXML
    private void actualizarProducto() {
        Producto productoSeleccionado = productoTable.getSelectionModel().getSelectedItem();
        if (productoSeleccionado != null) {
            String nombre = nombreField.getText();
            String categoria = categoriaField.getText();
            double precio = Double.parseDouble(precioField.getText());
            boolean disponibilidad = Boolean.parseBoolean(disponibilidadField.getText());
            manejadorProducto.actualizarProducto(productoSeleccionado.getId(), nombre, categoria, precio, disponibilidad);
            cargarDatos();
            limpiarCampos();
        }
    }

    @FXML
    private void eliminarProducto() {
        Producto productoSeleccionado = productoTable.getSelectionModel().getSelectedItem();
        if (productoSeleccionado != null) {
            manejadorProducto.eliminarProducto(productoSeleccionado.getId());
            cargarDatos();
        }
    }

    @FXML
    private void buscarProducto() {
        String nombreBusqueda = buscarField.getText();
        List<Producto> productos = manejadorProducto.buscarProductosPorNombre(nombreBusqueda);
        productoData.setAll(productos);
        productoTable.setItems(productoData);
    }

    @FXML
    private void mostrarTodos() {
        cargarDatos();
    }

    private void limpiarCampos() {
        nombreField.clear();
        categoriaField.clear();
        precioField.clear();
        disponibilidadField.clear();
        buscarField.clear();
    }
}
