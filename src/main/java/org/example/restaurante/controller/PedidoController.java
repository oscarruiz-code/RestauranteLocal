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
import org.example.restaurante.entity.Cliente;
import org.example.restaurante.entity.DetallePedido;
import org.example.restaurante.entity.Pedido;
import org.example.restaurante.entity.Producto;
import org.example.restaurante.manejador.ManejadorCliente;
import org.example.restaurante.manejador.ManejadorPedido;
import org.example.restaurante.manejador.ManejadorProducto;

import java.util.List;

/**
 * Controlador para gestionar los pedidos en la interfaz de usuario.
 *
 * @autor oscarruiz-code
 */
public class PedidoController {

    @FXML
    private TableView<Pedido> pedidoTable;

    @FXML
    private TableColumn<Pedido, Long> idPedidoColumn;

    @FXML
    private TableColumn<Pedido, String> clienteColumn;

    @FXML
    private TableColumn<Pedido, String> fechaColumn;

    @FXML
    private TableColumn<Pedido, String> horaColumn;

    @FXML
    private TableColumn<Pedido, Double> totalColumn;

    @FXML
    private TableColumn<Pedido, String> estadoColumn;

    @FXML
    private TableColumn<Pedido, String> productoColumn;

    @FXML
    private TextField buscarField;

    @FXML
    private ComboBox<Cliente> clienteComboBox;

    @FXML
    private TextField clienteField;

    @FXML
    private TextField fechaField;

    @FXML
    private TextField horaField;

    @FXML
    private TextField estadoField;

    @FXML
    private ComboBox<Producto> productoComboBox;

    @FXML
    private TextField cantidadField;

    private ManejadorPedido manejadorPedido = new ManejadorPedido();
    private ManejadorCliente manejadorCliente = new ManejadorCliente();
    private ManejadorProducto manejadorProducto = new ManejadorProducto();
    private ObservableList<Pedido> pedidoData = FXCollections.observableArrayList();
    private ObservableList<Cliente> clienteData = FXCollections.observableArrayList();
    private ObservableList<Producto> productoData = FXCollections.observableArrayList();

    /**
     * Inicializa la tabla de pedidos y los ComboBox.
     */
    @FXML
    public void initialize() {
        idPedidoColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        clienteColumn.setCellValueFactory(new PropertyValueFactory<>("clienteNombre"));
        fechaColumn.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        horaColumn.setCellValueFactory(new PropertyValueFactory<>("hora"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        estadoColumn.setCellValueFactory(new PropertyValueFactory<>("estado"));
        productoColumn.setCellValueFactory(new PropertyValueFactory<>("productoNombre"));


        cargarPedidos();
        cargarClientes();
        cargarProductos();
    }

    /**
     * Carga los datos de todos los pedidos en la tabla.
     */
    private void cargarPedidos() {
        List<Pedido> pedidos = manejadorPedido.obtenerTodosLosPedidos();
        pedidoData.setAll(pedidos);
        pedidoTable.setItems(pedidoData);
    }

    /**
     * Carga los datos de todos los clientes en el ComboBox.
     */
    private void cargarClientes() {
        List<Cliente> clientes = manejadorCliente.obtenerTodosLosClientes();
        clienteData.setAll(clientes);
        clienteComboBox.setItems(clienteData);
        clienteComboBox.getItems().add(0, null); // Añadir opción de campo vacío
    }

    /**
     * Carga los datos de todos los productos en el ComboBox.
     */
    private void cargarProductos() {
        List<Producto> productos = manejadorProducto.obtenerTodosLosProductos();
        productoData.setAll(productos);
        productoComboBox.setItems(productoData);
        productoComboBox.getItems().add(0, null); // Añadir opción de campo vacío
    }

    @FXML
    public void agregarPedido() {
        Cliente cliente = clienteComboBox.getValue();
        if (cliente == null && !clienteField.getText().isEmpty()) {
            // Solicitar dirección y teléfono para el nuevo cliente
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Nuevo Cliente");
            dialog.setHeaderText("Ingrese la dirección y el teléfono del nuevo cliente separados por una coma.");
            dialog.setContentText("Dirección,Teléfono:");

            final Cliente finalCliente = cliente;
            dialog.showAndWait().ifPresent(datos -> {
                String[] datosCliente = datos.split(",");
                if (datosCliente.length == 2) {
                    Cliente nuevoCliente = crearNuevoCliente(datosCliente);
                    if (nuevoCliente != null) {
                        agregarPedidoConCliente(nuevoCliente);
                    }
                } else {
                    mostrarAlerta("Error", "Debe ingresar dirección y teléfono separados por comas.");
                }
            });
        } else if (cliente != null) {
            agregarPedidoConCliente(cliente);
        } else {
            mostrarAlerta("Error", "Debe seleccionar un cliente o ingresar el nombre de un nuevo cliente.");
        }
    }

    /**
     * Agrega un nuevo pedido con el cliente proporcionado.
     *
     * @param cliente El cliente para el nuevo pedido.
     */
    private void agregarPedidoConCliente(Cliente cliente) {
        String fecha = fechaField.getText();
        String hora = horaField.getText();
        String estado = estadoField.getText();
        Producto producto = productoComboBox.getValue();
        if (producto == null) {
            mostrarAlerta("Error", "Debe seleccionar un producto.");
            return;
        }
        int cantidad;
        try {
            cantidad = Integer.parseInt(cantidadField.getText());
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Debe ingresar una cantidad válida.");
            return;
        }

        double precio = producto.getPrecio();
        Pedido nuevoPedido = new Pedido(cliente, fecha, hora, estado);
        DetallePedido detalle = new DetallePedido(nuevoPedido, producto, cantidad, precio);
        nuevoPedido.getDetalles().add(detalle);
        nuevoPedido.calcularTotal();

        manejadorPedido.ingresarPedido(nuevoPedido);
        cargarPedidos();
        limpiarCampos();
    }
    /**
     * Crea un nuevo cliente si la información ingresada es válida.
     *
     * @param datos Los datos del cliente en formato de cadena.
     * @return El nuevo cliente creado, o null si la creación falla.
     */
    public Cliente crearNuevoCliente(String[] datos) {
        if (datos != null && datos.length == 2) {
            String direccion = datos[0].trim();
            String telefono = datos[1].trim();
            Cliente nuevoCliente = new Cliente(clienteField.getText(), direccion, telefono);
            manejadorCliente.ingresarCliente(nuevoCliente);
            cargarClientes(); // Refrescar lista de clientes
            return nuevoCliente;
        } else {
            mostrarAlerta("Error", "Debe ingresar dirección y teléfono separados por comas.");
        }
        return null;
    }

    /**
     * Muestra una alerta con el título y mensaje especificados.
     *
     * @param titulo El título de la alerta.
     * @param mensaje El mensaje de la alerta.
     */
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Limpia los campos de entrada de texto.
     */
    private void limpiarCampos() {
        clienteComboBox.setValue(null);
        clienteField.clear();
        fechaField.clear();
        horaField.clear();
        estadoField.clear();
        productoComboBox.setValue(null);
        cantidadField.clear();
    }

    /**
     * Busca pedidos por el nombre del cliente ingresado en el campo de búsqueda.
     */
    @FXML
    private void buscarPedido() {
        String nombreBusqueda = buscarField.getText();
        List<Pedido> pedidos = manejadorPedido.buscarPedidosPorCliente(nombreBusqueda);
        pedidoData.setAll(pedidos);
        pedidoTable.setItems(pedidoData);
    }

    /**
     * Actualiza el pedido seleccionado en la tabla con la nueva información ingresada.
     */
    @FXML
    private void actualizarPedido() {
        Pedido pedidoSeleccionado = pedidoTable.getSelectionModel().getSelectedItem();
        if (pedidoSeleccionado != null) {
            Cliente cliente = clienteComboBox.getValue();
            if (cliente == null) {
                String nombreCliente = clienteField.getText();
                if (!nombreCliente.isEmpty()) {
                    cliente = crearNuevoCliente(new String[]{clienteField.getText(), clienteField.getText()});
                    if (cliente == null) {
                        return; // Si el cliente no se crea, no continuar
                    }
                } else {
                    mostrarAlerta("Error", "Debe seleccionar o ingresar un cliente.");
                    return;
                }
            }

            String fecha = fechaField.getText();
            String hora = horaField.getText();
            String estado = estadoField.getText();
            Producto producto = productoComboBox.getValue();
            int cantidad = Integer.parseInt(cantidadField.getText());
            double precio = producto.getPrecio();

            DetallePedido detalle = new DetallePedido(pedidoSeleccionado, producto, cantidad, precio);
            pedidoSeleccionado.getDetalles().add(detalle);
            pedidoSeleccionado.calcularTotal();

            manejadorPedido.actualizarPedido(pedidoSeleccionado.getId(), cliente, fecha, hora, pedidoSeleccionado.getTotal(), estado);
            cargarPedidos();
            limpiarCampos();
        }
    }

    /**
     * Elimina el pedido seleccionado en la tabla.
     */
    @FXML
    private void eliminarPedido() {
        Pedido pedidoSeleccionado = pedidoTable.getSelectionModel().getSelectedItem();
        if (pedidoSeleccionado != null) {
            manejadorPedido.eliminarPedido(pedidoSeleccionado.getId());
            cargarPedidos();
        }
    }

    /**
     * Muestra todos los pedidos en la tabla.
     */
    @FXML
    private void mostrarTodos() {
        cargarPedidos();
    }

    /**
     * Muestra los detalles completos del pedido seleccionado.
     */
    @FXML
    private void verPedidoCompleto() {
        Pedido pedidoSeleccionado = pedidoTable.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/restaurante/vistas/DetallePedido.fxml"));
            Scene scene = new Scene(loader.load(), 800, 600);
            DetallePedidoController controller = loader.getController();
            controller.setPedido(pedidoSeleccionado);
            HelloApplication.primaryStage.setScene(scene);
            HelloApplication.primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Vuelve a la vista principal.
     */
    @FXML
    private void volverPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/restaurante/vistas/Principal.fxml"));
            Stage stage = (Stage) clienteComboBox.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
