package org.example.restaurante.repository;

import org.example.restaurante.entity.Pedido;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Clase para manejar las operaciones relacionadas con los pedidos en la base de datos.
 *
 * @autor oscarruiz-code
 */
public class PedidoRepository {

    private SessionFactory factory;

    /**
     * Constructor que inicializa el SessionFactory.
     */
    public PedidoRepository() {
        factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Pedido.class)
                .buildSessionFactory();
    }

    /**
     * Guarda o actualiza un pedido en la base de datos.
     *
     * @param pedido El objeto Pedido a guardar o actualizar.
     */
    public void guardarPedido(Pedido pedido) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(pedido);
            session.getTransaction().commit();
        }
    }

    /**
     * Obtiene un pedido por su ID.
     *
     * @param id El ID del pedido.
     * @return El objeto Pedido correspondiente al ID, o null si no se encuentra.
     */
    public Pedido obtenerPedidoPorId(Long id) {
        Pedido pedido = null;
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            pedido = session.get(Pedido.class, id);
            session.getTransaction().commit();
        }
        return pedido;
    }

    /**
     * Elimina un pedido por su ID.
     *
     * @param id El ID del pedido a eliminar.
     */
    public void eliminarPedido(Long id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Pedido pedido = session.get(Pedido.class, id);
            if (pedido != null) {
                session.delete(pedido);
            }
            session.getTransaction().commit();
        }
    }

    /**
     * Obtiene una lista de todos los pedidos.
     *
     * @return Una lista de objetos Pedido.
     */
    public List<Pedido> obtenerTodosLosPedidos() {
        List<Pedido> pedidos;
        try (Session session = factory.openSession()) {
            pedidos = session.createQuery("SELECT DISTINCT p FROM Pedido p LEFT JOIN FETCH p.detalles d LEFT JOIN FETCH d.producto", Pedido.class)
                    .getResultList();
        }
        return pedidos;
    }

    /**
     * Busca pedidos por el nombre del cliente.
     *
     * @param nombreCliente El nombre del cliente a buscar.
     * @return Una lista de objetos Pedido que coinciden con el nombre del cliente.
     */
    public List<Pedido> buscarPedidosPorCliente(String nombreCliente) {
        try (Session session = factory.openSession()) {
            return session.createQuery("from Pedido where cliente.nombre like :nombreCliente", Pedido.class)
                    .setParameter("nombreCliente", "%" + nombreCliente + "%")
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
