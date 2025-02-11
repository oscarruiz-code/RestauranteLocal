package org.example.restaurante.repository;

import org.example.restaurante.entity.Cliente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ClienteRepository {

    private SessionFactory factory;

    public ClienteRepository() {
        factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Cliente.class)
                .buildSessionFactory();
    }

    public void guardarCliente(Cliente cliente) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(cliente);
            session.getTransaction().commit();
        }
    }

    public Cliente obtenerClientePorId(int id) {
        Cliente cliente = null;
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            cliente = session.get(Cliente.class, id);
            session.getTransaction().commit();
        }
        return cliente;
    }

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

    public List<Cliente> obtenerTodosLosClientes() {
        try (Session session = factory.openSession()) {
            return session.createQuery("from Cliente", Cliente.class).list();
        }
    }

    public List<Cliente> buscarClientesPorNombre(String nombre) {
        try (Session session = factory.openSession()) {
            return session.createQuery("from Cliente where nombre like :nombre", Cliente.class)
                    .setParameter("nombre", "%" + nombre + "%")
                    .list();
        }
    }

    public void cerrar() {
        factory.close();
    }
}
