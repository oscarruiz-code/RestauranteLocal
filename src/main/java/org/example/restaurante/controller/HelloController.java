package org.example.restaurante.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.restaurante.HelloApplication;
import org.example.restaurante.entity.Cliente;
import org.example.restaurante.manejador.ManejadorCliente;
import org.example.restaurante.reportes.ReportGenerator;
import javafx.event.ActionEvent;
import java.awt.*;
import java.util.List;

/**
 * Controlador para gestionar los clientes en la interfaz de usuario.
 *
 * @autor oscarruiz-code
 */
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
    @FXML
    private Button btnGenerarReporteClientes;

    private ReportGenerator reportGenerator = new ReportGenerator();

    private ManejadorCliente manejadorCliente = new ManejadorCliente();
    private ObservableList<Cliente> clienteData = FXCollections.observableArrayList();

    /**
     * Inicializa la tabla de clientes.
     */
    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        direccionColumn.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        telefonoColumn.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        cargarDatos();
    }

    /**
     * Carga los datos de todos los clientes en la tabla.
     */
    private void cargarDatos() {
        List<Cliente> clientes = manejadorCliente.obtenerTodosLosClientes();
        clienteData.setAll(clientes);
        clienteTable.setItems(clienteData);
    }

    @FXML
    private void generarReporteClientes(ActionEvent event) {
        // Obtener la lista de clientes desde Hibernate
        List<Cliente> clientes = manejadorCliente.obtenerTodosLosClientes();

        // Generar el informe
        reportGenerator.generateClientesReport(clientes);
    }


    /**
     * Agrega un nuevo cliente utilizando los datos ingresados en los campos de texto.
     */
    @FXML
    private void agregarCliente() {
        String nombre = nombreField.getText();
        String direccion = direccionField.getText();
        String telefono = telefonoField.getText();
        manejadorCliente.ingresarCliente(nombre, direccion, telefono);
        cargarDatos();
        limpiarCampos();
    }

    /**
     * Actualiza los datos del cliente seleccionado en la tabla.
     */
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

    /**
     * Elimina el cliente seleccionado en la tabla.
     */
    @FXML
    private void eliminarCliente() {
        Cliente clienteSeleccionado = clienteTable.getSelectionModel().getSelectedItem();
        if (clienteSeleccionado != null) {
            manejadorCliente.eliminarCliente(clienteSeleccionado.getId());
            cargarDatos();
        }
    }

    /**
     * Busca clientes por nombre utilizando el texto ingresado en el campo de búsqueda.
     */
    @FXML
    private void buscarCliente() {
        String nombreBusqueda = buscarField.getText();
        List<Cliente> clientes = manejadorCliente.buscarClientesPorNombre(nombreBusqueda);
        clienteData.setAll(clientes);
        clienteTable.setItems(clienteData);
    }

    /**
     * Muestra todos los clientes en la tabla.
     */
    @FXML
    private void mostrarTodos() {
        cargarDatos();
    }

    /**
     * Vuelve a la vista principal.
     */
    @FXML
    private void volverPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/restaurante/vistas/Principal.fxml"));
            Scene scene = new Scene(loader.load());
            HelloApplication.primaryStage.setScene(scene);
            HelloApplication.primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Limpia los campos de texto.
     */
    private void limpiarCampos() {
        nombreField.clear();
        direccionField.clear();
        telefonoField.clear();
        buscarField.clear();
    }

}
