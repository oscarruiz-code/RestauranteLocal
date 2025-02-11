package org.example.restaurante.repository;

import org.example.restaurante.entity.Producto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ProductoRepository {

    private SessionFactory factory;

    public ProductoRepository() {
        factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Producto.class)
                .buildSessionFactory();
    }

    public void guardarProducto(Producto producto) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(producto);
            session.getTransaction().commit();
        }
    }

    public Producto obtenerProductoPorId(int id) {
        Producto producto = null;
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            producto = session.get(Producto.class, id);
            session.getTransaction().commit();
        }
        return producto;
    }

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

    public List<Producto> obtenerTodosLosProductos() {
        try (Session session = factory.openSession()) {
            return session.createQuery("from Producto", Producto.class).list();
        }
    }

    public List<Producto> buscarProductosPorNombre(String nombre) {
        try (Session session = factory.openSession()) {
            return session.createQuery("from Producto where nombre like :nombre", Producto.class)
                    .setParameter("nombre", "%" + nombre + "%")
                    .list();
        }
    }

    public void cerrar() {
        factory.close();
    }
}
