package org.example.restaurante.manejador;

import org.example.restaurante.entity.DetallePedido;
import org.example.restaurante.repository.DetallePedidoRepository;

import java.util.List;

public class ManejadorDetallePedido {

    private DetallePedidoRepository detallePedidoRepository;

    public ManejadorDetallePedido() {
        detallePedidoRepository = new DetallePedidoRepository();
    }

    public void ingresarDetallePedido(DetallePedido detallePedido) {
        detallePedidoRepository.guardarDetallePedido(detallePedido);
    }

    public void actualizarDetallePedido(DetallePedido detallePedido) {
        detallePedidoRepository.guardarDetallePedido(detallePedido);
    }

    public void eliminarDetallePedido(int id) {
        detallePedidoRepository.eliminarDetallePedido(id);
    }

    public List<DetallePedido> obtenerTodosLosDetallesPedido() {
        return detallePedidoRepository.obtenerTodosLosDetallesPedido();
    }

    public List<DetallePedido> buscarDetallesPedidoPorProducto(int productoId) {
        return detallePedidoRepository.buscarDetallesPedidoPorProducto(productoId);
    }

    public void cerrar() {
        detallePedidoRepository.cerrar();
    }
}
