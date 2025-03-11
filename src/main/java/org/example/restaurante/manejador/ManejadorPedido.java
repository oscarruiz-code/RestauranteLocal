package org.example.restaurante.manejador;

import org.example.restaurante.entity.Pedido;
import org.example.restaurante.entity.Cliente;
import org.example.restaurante.repository.PedidoRepository;

import java.util.List;

/**
 * Clase para manejar las operaciones relacionadas con los pedidos.
 *
 * @autor oscarruiz-code
 */
public class ManejadorPedido {

    private PedidoRepository pedidoRepository;

    /**
     * Constructor que inicializa el repositorio de pedidos.
     */
    public ManejadorPedido() {
        pedidoRepository = new PedidoRepository();
    }

    /**
     * Método para ingresar un pedido.
     *
     * @param pedido El objeto Pedido a ingresar.
     */
    public void ingresarPedido(Pedido pedido) {
        pedidoRepository.guardarPedido(pedido);
    }

    /**
     * Actualiza los datos de un pedido existente.
     *
     * @param id El ID del pedido.
     * @param cliente El cliente del pedido.
     * @param fecha La fecha del pedido.
     * @param hora La hora del pedido.
     * @param total El total del pedido.
     * @param estado El estado del pedido.
     */
    public void actualizarPedido(Long id, Cliente cliente, String fecha, String hora, Double total, String estado) {
        Pedido pedido = pedidoRepository.obtenerPedidoPorId(id);
        if (pedido != null) {
            pedido.setCliente(cliente);
            pedido.setFecha(fecha);
            pedido.setHora(hora);
            pedido.setTotal(total);
            pedido.setEstado(estado);
            pedidoRepository.guardarPedido(pedido);
        }
    }

    /**
     * Elimina un pedido por su ID.
     *
     * @param id El ID del pedido a eliminar.
     */
    public void eliminarPedido(Long id) {
        pedidoRepository.eliminarPedido(id);
    }

    /**
     * Obtiene una lista de todos los pedidos.
     *
     * @return Una lista de objetos Pedido.
     */
    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidoRepository.obtenerTodosLosPedidos();
    }

    /**
     * Busca pedidos por el nombre del cliente.
     *
     * @param nombreCliente El nombre del cliente a buscar.
     * @return Una lista de objetos Pedido que coinciden con el nombre del cliente.
     */
    public List<Pedido> buscarPedidosPorCliente(String nombreCliente) {
        return pedidoRepository.buscarPedidosPorCliente(nombreCliente);
    }

    public List<Pedido> obtenerPedidosEnPreparacion() {
        return pedidoRepository.obtenerPedidosEnPreparacion();
    }

    /**
     * Cierra el repositorio de pedidos.
     */
    public void cerrar() {
        pedidoRepository.cerrar();
    }
}
