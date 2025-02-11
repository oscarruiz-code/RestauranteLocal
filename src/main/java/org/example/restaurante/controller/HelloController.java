package org.example.restaurante.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.restaurante.entity.Cliente;
import org.example.restaurante.manejador.ManejadorCliente;

import java.util.List;

public class HelloController {

    @FXML
    private TableView<Cliente> clienteTable;

    @FXML
    private TableColumn<Cliente, Integer> idColumn;

    @FXML
    private TableColumn<Cliente, String> nombreColumn;

    @FXML
    private TableColumn<Cliente, String> direccionColumn;

    @FXML
    private TableColumn<Cliente, String> telefonoColumn;

    @FXML
    private TextField nombreField;
    @FXML
    private TextField direccionField;
    @FXML
    private TextField telefonoField;
    @FXML
    private TextField buscarField;

    private ManejadorCliente manejadorCliente = new ManejadorCliente();
    private ObservableList<Cliente> clienteData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        direccionColumn.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        telefonoColumn.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        cargarDatos();
    }

    private void cargarDatos() {
        List<Cliente> clientes = manejadorCliente.obtenerTodosLosClientes();
        clienteData.setAll(clientes);
        clienteTable.setItems(clienteData);
    }

    @FXML
    private void agregarCliente() {
        String nombre = nombreField.getText();
        String direccion = direccionField.getText();
        String telefono = telefonoField.getText();
        manejadorCliente.ingresarCliente(nombre, direccion, telefono);
        cargarDatos();
        limpiarCampos();
    }

    @FXML
    private void actualizarCliente() {
        Cliente clienteSeleccionado = clienteTable.getSelectionModel().getSelectedItem();
        if (clienteSeleccionado != null) {
            String nombre = nombreField.getText();
            String direccion = direccionField.getText();
            String telefono = telefonoField.getText();
            manejadorCliente.actualizarCliente(clienteSeleccionado.getId(), nombre, direccion, telefono);
            cargarDatos();
            limpiarCampos();
        }
    }

    @FXML
    private void eliminarCliente() {
        Cliente clienteSeleccionado = clienteTable.getSelectionModel().getSelectedItem();
        if (clienteSeleccionado != null) {
            manejadorCliente.eliminarCliente(clienteSeleccionado.getId());
            cargarDatos();
        }
    }

    @FXML
    private void buscarCliente() {
        String nombreBusqueda = buscarField.getText();
        List<Cliente> clientes = manejadorCliente.buscarClientesPorNombre(nombreBusqueda);
        clienteData.setAll(clientes);
        clienteTable.setItems(clienteData);
    }

    @FXML
    private void mostrarTodos() {
        cargarDatos();
    }

    private void limpiarCampos() {
        nombreField.clear();
        direccionField.clear();
        telefonoField.clear();
        buscarField.clear();
    }
}
