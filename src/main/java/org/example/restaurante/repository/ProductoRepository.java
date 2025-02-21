package org.example.restaurante.repository;

import org.example.restaurante.entity.Producto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Clase para manejar las operaciones relacionadas con los productos en la base de datos.
 *
 * @autor oscarruiz-code
 */
public class ProductoRepository {

    private SessionFactory factory;

    /**
     * Constructor que inicializa el SessionFactory.
     */
    public ProductoRepository() {
        factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Producto.class)
                .buildSessionFactory();
    }

    /**
     * Guarda o actualiza un producto en la base de datos.
     *
     * @param producto El objeto Producto a guardar o actualizar.
     */
    public void guardarProducto(Producto producto) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(producto);
            session.getTransaction().commit();
        }
    }

    /**
     * Obtiene un producto por su ID.
     *
     * @param id El ID del producto.
     * @return El objeto Producto correspondiente al ID, o null si no se encuentra.
     */
    public Producto obtenerProductoPorId(int id) {
        Producto producto = null;
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            producto = session.get(Producto.class, id);
            session.getTransaction().commit();
        }
        return producto;
    }

    /**
     * Elimina un producto por su ID.
     *
     * @param id El ID del producto a eliminar.
     */
    public void eliminarProducto(int id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Producto producto = session.get(Producto.class, id);
            if (producto != null) {
                session.delete(producto);
            }
            session.getTransaction().commit();
        }
    }

    /**
     * Obtiene una lista de todos los productos.
     *
     * @return Una lista de objetos Producto.
     */
    public List<Producto> obtenerTodosLosProductos() {
        try (Session session = factory.openSession()) {
            return session.createQuery("from Producto", Producto.class).list();
        }
    }

    /**
     * Busca productos por nombre.
     *
     * @param nombre El nombre a buscar.
     * @return Una lista de objetos Producto que coinciden con el nombre.
     */
    public List<Producto> buscarProductosPorNombre(String nombre) {
        try (Session session = factory.openSession()) {
            return session.createQuery("from Producto where nombre like :nombre", Producto.class)
                    .setParameter("nombre", "%" + nombre + "%")
                    .list();
        }
    }

    /**
     * Cierra el SessionFactory.
     */
    public void cerrar() {
        factory.close();
    }
}
