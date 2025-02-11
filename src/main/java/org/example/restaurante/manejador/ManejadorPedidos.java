package org.example.restaurante.manejador;

import org.example.restaurante.entity.Pedidos;
import org.example.restaurante.repository.PedidosRepository;

import java.util.List;

public class ManejadorPedidos {

    private PedidosRepository pedidosRepository;

    public ManejadorPedidos() {
        pedidosRepository = new PedidosRepository();
    }

    public void ingresarPedido(Pedidos pedido) {
        pedidosRepository.guardarPedido(pedido);
    }

    public void actualizarPedido(Pedidos pedido) {
        pedidosRepository.guardarPedido(pedido);
    }

    public void eliminarPedido(int id) {
        pedidosRepository.eliminarPedido(id);
    }

    public List<Pedidos> obtenerTodosLosPedidos() {
        return pedidosRepository.obtenerTodosLosPedidos();
    }

    public List<Pedidos> buscarPedidosPorEstado(String estado) {
        return pedidosRepository.buscarPedidosPorEstado(estado);
    }

    public void cerrar() {
        pedidosRepository.cerrar();
    }
}
