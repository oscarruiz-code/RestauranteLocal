package org.example.restaurante.manejador;

import org.example.restaurante.entity.Producto;
import org.example.restaurante.repository.ProductoRepository;
import java.util.List;

/**
 * Clase para manejar las operaciones relacionadas con los productos.
 *
 * @autor oscarruiz-code
 */
public class ManejadorProducto {

    private ProductoRepository productoRepository;

    /**
     * Constructor que inicializa el repositorio de productos.
     */
    public ManejadorProducto() {
        productoRepository = new ProductoRepository();
    }

    /**
     * Método para ingresar un producto.
     *
     * @param nombre El nombre del producto.
     * @param categoria La categoría del producto.
     * @param precio El precio del producto.
     * @param disponibilidad La disponibilidad del producto.
     */
    public void ingresarProducto(String nombre, String categoria, double precio, boolean disponibilidad, Integer stock) {
        Producto producto = new Producto(nombre, categoria, precio, disponibilidad, stock);
        productoRepository.guardarProducto(producto);
    }

    /**
     * Actualiza los datos de un producto existente.
     *
     * @param id             El ID del producto.
     * @param nombre         El nuevo nombre del producto.
     * @param categoria      La nueva categoría del producto.
     * @param precio         El nuevo precio del producto.
     * @param disponibilidad La nueva disponibilidad del producto.
     * @param stock
     */
    public void actualizarProducto(int id, String nombre, String categoria, double precio, boolean disponibilidad, Integer stock) {
        Producto producto = productoRepository.obtenerProductoPorId(id);
        if (producto != null) {
            producto.setNombre(nombre);
            producto.setCategoria(categoria);
            producto.setPrecio(precio);
            producto.setDisponibilidad(disponibilidad);
            productoRepository.guardarProducto(producto);
        }
    }

    /**
     * Elimina un producto por su ID.
     *
     * @param id El ID del producto a eliminar.
     */
    public void eliminarProducto(int id) {
        productoRepository.eliminarProducto(id);
    }

    /**
     * Obtiene una lista de todos los productos.
     *
     * @return Una lista de objetos Producto.
     */
    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.obtenerTodosLosProductos();
    }

    /**
     * Busca productos por nombre.
     *
     * @param nombre El nombre a buscar.
     * @return Una lista de objetos Producto que coinciden con el nombre.
     */
    public List<Producto> buscarProductosPorNombre(String nombre) {
        return productoRepository.buscarProductosPorNombre(nombre);
    }

    public List<Producto> obtenerProductosBajoStock() {
        return productoRepository.obtenerProductosBajoStock();
    }

    /**
     * Cierra el repositorio de productos.
     */
    public void cerrar() {
        productoRepository.cerrar();
    }


}
