package org.example.restaurante.manejador;

import org.example.restaurante.entity.Producto;
import org.example.restaurante.repository.ProductoRepository;

import java.util.List;

public class ManejadorProducto {

    private ProductoRepository productoRepository;

    public ManejadorProducto() {
        productoRepository = new ProductoRepository();
    }

    public void ingresarProducto(String nombre, String categoria, double precio, boolean disponibilidad) {
        Producto producto = new Producto(nombre, categoria, precio, disponibilidad);
        productoRepository.guardarProducto(producto);
    }

    public void actualizarProducto(int id, String nombre, String categoria, double precio, boolean disponibilidad) {
        Producto producto = productoRepository.obtenerProductoPorId(id);
        if (producto != null) {
            producto.setNombre(nombre);
            producto.setCategoria(categoria);
            producto.setPrecio(precio);
            producto.setDisponibilidad(disponibilidad);
            productoRepository.guardarProducto(producto);
        }
    }

    public void eliminarProducto(int id) {
        productoRepository.eliminarProducto(id);
    }

    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.obtenerTodosLosProductos();
    }

    public List<Producto> buscarProductosPorNombre(String nombre) {
        return productoRepository.buscarProductosPorNombre(nombre);
    }

    public void cerrar() {
        productoRepository.cerrar();
    }
}
