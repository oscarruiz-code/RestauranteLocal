package org.example.restaurante.repository;

import org.example.restaurante.entity.Cliente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Clase para manejar las operaciones relacionadas con los clientes en la base de datos.
 *
 * @autor oscarruiz-code
 */
public class ClienteRepository {

    private SessionFactory factory;

    /**
     * Constructor que inicializa el SessionFactory.
     */
    public ClienteRepository() {
        factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Cliente.class)
                .buildSessionFactory();
    }

    /**
     * Guarda o actualiza un cliente en la base de datos.
     *
     * @param cliente El objeto Cliente a guardar o actualizar.
     */
    public void guardarCliente(Cliente cliente) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(cliente);
            session.getTransaction().commit();
        }
    }

    /**
     * Obtiene un cliente por su ID.
     *
     * @param id El ID del cliente.
     * @return El objeto Cliente correspondiente al ID, o null si no se encuentra.
     */
    public Cliente obtenerClientePorId(int id) {
        Cliente cliente = null;
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            cliente = session.get(Cliente.class, id);
            session.getTransaction().commit();
        }
        return cliente;
    }

    /**
     * Elimina un cliente por su ID.
     *
     * @param id El ID del cliente a eliminar.
     */
    public void eliminarCliente(int id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Cliente cliente = session.get(Cliente.class, id);
            if (cliente != null) {
                session.delete(cliente);
            }
            session.getTransaction().commit();
        }
    }

    /**
     * Obtiene una lista de todos los clientes.
     *
     * @return Una lista de objetos Cliente.
     */
    public List<Cliente> obtenerTodosLosClientes() {
        try (Session session = factory.openSession()) {
            return session.createQuery("from Cliente", Cliente.class).list();
        }
    }

    /**
     * Busca clientes por nombre.
     *
     * @param nombre El nombre a buscar.
     * @return Una lista de objetos Cliente que coinciden con el nombre.
     */
    public List<Cliente> buscarClientesPorNombre(String nombre) {
        try (Session session = factory.openSession()) {
            return session.createQuery("from Cliente where nombre like :nombre", Cliente.class)
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
