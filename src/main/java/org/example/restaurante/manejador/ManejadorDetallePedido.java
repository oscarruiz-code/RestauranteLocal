package org.example.restaurante.manejador;

import org.example.restaurante.entity.DetallePedido;
import org.example.restaurante.repository.DetallePedidoRepository;

import java.util.List;

/**
 * Clase para manejar las operaciones relacionadas con los detalles de pedidos.
 *
 * @autor oscarruiz-code
 */
public class ManejadorDetallePedido {

    private DetallePedidoRepository detallePedidoRepository;

    /**
     * Constructor que inicializa el repositorio de detalles de pedidos.
     */
    public ManejadorDetallePedido() {
        detallePedidoRepository = new DetallePedidoRepository();
    }

    /**
     * Método para ingresar un detalle de pedido.
     *
     * @param detallePedido El objeto DetallePedido a ingresar.
     */
    public void ingresarDetallePedido(DetallePedido detallePedido) {
        detallePedidoRepository.guardarDetallePedido(detallePedido);
    }

    /**
     * Obtiene los detalles de un pedido por su ID.
     *
     * @param pedidoId El ID del pedido.
     * @return Una lista de objetos DetallePedido que corresponden al pedido.
     */
    public List<DetallePedido> obtenerDetallesPorPedido(Long pedidoId) {
        return detallePedidoRepository.obtenerDetallesPorPedido(pedidoId);
    }

    /**
     * Obtiene todos los detalles de pedidos.
     *
     * @return Una lista de todos los objetos DetallePedido.
     */
    public List<DetallePedido> obtenerTodosLosDetalles() {
        return detallePedidoRepository.obtenerTodosLosDetalles();
    }

    /**
     * Cierra el repositorio de detalles de pedidos.
     */
    public void cerrar() {
        detallePedidoRepository.cerrar();
    }
}
