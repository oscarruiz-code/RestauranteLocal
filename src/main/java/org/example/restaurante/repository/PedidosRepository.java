package org.example.restaurante.repository;

import org.example.restaurante.entity.Pedidos;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class PedidosRepository {

    private SessionFactory factory;

    public PedidosRepository() {
        factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Pedidos.class)
                .buildSessionFactory();
    }

    public void guardarPedido(Pedidos pedido) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(pedido);
            session.getTransaction().commit();
        }
    }

    public Pedidos obtenerPedidoPorId(int id) {
        Pedidos pedido = null;
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            pedido = session.get(Pedidos.class, id);
            session.getTransaction().commit();
        }
        return pedido;
    }

    public void eliminarPedido(int id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Pedidos pedido = session.get(Pedidos.class, id);
            if (pedido != null) {
                session.delete(pedido);
            }
            session.getTransaction().commit();
        }
    }

    public List<Pedidos> obtenerTodosLosPedidos() {
        try (Session session = factory.openSession()) {
            return session.createQuery("from Pedidos", Pedidos.class).list();
        }
    }

    public List<Pedidos> buscarPedidosPorEstado(String estado) {
        try (Session session = factory.openSession()) {
            return session.createQuery("from Pedidos where estado like :estado", Pedidos.class)
                    .setParameter("estado", "%" + estado + "%")
                    .list();
        }
    }

    public void cerrar() {
        factory.close();
    }
}
