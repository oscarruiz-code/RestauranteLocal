package org.example.restaurante.repository;

import org.example.restaurante.entity.DetallePedido;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DetallePedidoRepository {

    private SessionFactory factory;

    public DetallePedidoRepository() {
        factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(DetallePedido.class)
                .buildSessionFactory();
    }

    public void guardarDetallePedido(DetallePedido detallePedido) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(detallePedido);
            session.getTransaction().commit();
        }
    }

    public DetallePedido obtenerDetallePedidoPorId(int id) {
        DetallePedido detallePedido = null;
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            detallePedido = session.get(DetallePedido.class, id);
            session.getTransaction().commit();
        }
        return detallePedido;
    }

    public void eliminarDetallePedido(int id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            DetallePedido detallePedido = session.get(DetallePedido.class, id);
            if (detallePedido != null) {
                session.delete(detallePedido);
            }
            session.getTransaction().commit();
        }
    }

    public List<DetallePedido> obtenerTodosLosDetallesPedido() {
        try (Session session = factory.openSession()) {
            return session.createQuery("from DetallePedido", DetallePedido.class).list();
        }
    }

    public List<DetallePedido> buscarDetallesPedidoPorProducto(int productoId) {
        try (Session session = factory.openSession()) {
            return session.createQuery("from DetallePedido where producto.id = :productoId", DetallePedido.class)
                    .setParameter("productoId", productoId)
                    .list();
        }
    }

    public void cerrar() {
        factory.close();
    }
}
