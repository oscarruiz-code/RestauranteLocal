package org.example.restaurante.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.restaurante.entity.Producto;
import org.example.restaurante.manejador.ManejadorProducto;

import java.util.List;

/**
 * Controlador para gestionar los productos en la interfaz de usuario.
 *
 * @autor oscarruiz-code
 */
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
    private CheckBox disponibilidadField;
    @FXML
    private TextField buscarField;

    private ManejadorProducto manejadorProducto = new ManejadorProducto();
    private ObservableList<Producto> productoData = FXCollections.observableArrayList();

    /**
     * Inicializa la tabla de productos.
     */
    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        categoriaColumn.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        precioColumn.setCellValueFactory(new PropertyValueFactory<>("precio"));
        disponibilidadColumn.setCellValueFactory(new PropertyValueFactory<>("disponibilidad"));

        cargarDatos();
    }

    /**
     * Carga los datos de todos los productos en la tabla.
     */
    private void cargarDatos() {
        List<Producto> productos = manejadorProducto.obtenerTodosLosProductos();
        productoData.setAll(productos);
        productoTable.setItems(productoData);
    }

    /**
     * Agrega un nuevo producto utilizando los datos ingresados en los campos de texto.
     */
    @FXML
    private void agregarProducto() {
        String nombre = nombreField.getText();
        String categoria = categoriaField.getText();
        double precio = Double.parseDouble(precioField.getText());
        boolean disponibilidad = disponibilidadField.isSelected();
        manejadorProducto.ingresarProducto(nombre, categoria, precio, disponibilidad);
        cargarDatos();
        limpiarCampos();
    }

    /**
     * Actualiza los datos del producto seleccionado en la tabla.
     */
    @FXML
    private void actualizarProducto() {
        Producto productoSeleccionado = productoTable.getSelectionModel().getSelectedItem();
        if (productoSeleccionado != null) {
            String nombre = nombreField.getText();
            String categoria = categoriaField.getText();
            double precio = Double.parseDouble(precioField.getText());
            boolean disponibilidad = disponibilidadField.isSelected();
            manejadorProducto.actualizarProducto(productoSeleccionado.getId(), nombre, categoria, precio, disponibilidad);
            cargarDatos();
            limpiarCampos();
        }
    }

    /**
     * Elimina el producto seleccionado en la tabla.
     */
    @FXML
    private void eliminarProducto() {
        Producto productoSeleccionado = productoTable.getSelectionModel().getSelectedItem();
        if (productoSeleccionado != null) {
            manejadorProducto.eliminarProducto(productoSeleccionado.getId());
            cargarDatos();
        }
    }

    /**
     * Busca productos por nombre utilizando el texto ingresado en el campo de búsqueda.
     */
    @FXML
    private void buscarProducto() {
        String nombreBusqueda = buscarField.getText();
        List<Producto> productos = manejadorProducto.buscarProductosPorNombre(nombreBusqueda);
        productoData.setAll(productos);
        productoTable.setItems(productoData);
    }

    /**
     * Muestra todos los productos en la tabla.
     */
    @FXML
    private void mostrarTodos() {
        cargarDatos();
    }

    /**
     * Limpia los campos de entrada de texto.
     */
    private void limpiarCampos() {
        nombreField.clear();
        categoriaField.clear();
        precioField.clear();
        disponibilidadField.setSelected(false);
        buscarField.clear();
    }

    /**
     * Vuelve a la vista principal.
     */
    @FXML
    private void volverPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/restaurante/vistas/Principal.fxml"));
            Stage stage = (Stage) nombreField.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
