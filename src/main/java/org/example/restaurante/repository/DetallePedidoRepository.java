package org.example.restaurante.repository;

import org.example.restaurante.entity.DetallePedido;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Clase para manejar las operaciones relacionadas con los detalles de pedidos en la base de datos.
 *
 * @autor oscarruiz-code
 */
public class DetallePedidoRepository {

    private SessionFactory factory;

    /**
     * Constructor que inicializa el SessionFactory.
     */
    public DetallePedidoRepository() {
        factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(DetallePedido.class)
                .buildSessionFactory();
    }

    /**
     * Guarda o actualiza un detalle de pedido en la base de datos.
     *
     * @param detallePedido El objeto DetallePedido a guardar o actualizar.
     */
    public void guardarDetallePedido(DetallePedido detallePedido) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(detallePedido);
            session.getTransaction().commit();
        }
    }

    /**
     * Obtiene los detalles de un pedido por su ID.
     *
     * @param pedidoId El ID del pedido.
     * @return Una lista de objetos DetallePedido que corresponden al pedido.
     */
    public List<DetallePedido> obtenerDetallesPorPedido(Long pedidoId) {
        try (Session session = factory.openSession()) {
            return session.createQuery("from DetallePedido where pedido.id = :pedidoId", DetallePedido.class)
                    .setParameter("pedidoId", pedidoId)
                    .list();
        }
    }

    /**
     * Obtiene todos los detalles de pedidos.
     *
     * @return Una lista de todos los objetos DetallePedido.
     */
    public List<DetallePedido> obtenerTodosLosDetalles() {
        try (Session session = factory.openSession()) {
            return session.createQuery("from DetallePedido", DetallePedido.class).list();
        }
    }

    /**
     * Cierra el SessionFactory.
     */
    public void cerrar() {
        factory.close();
    }
}
