package org.example.restaurante.manejador;

import org.example.restaurante.entity.Cliente;
import org.example.restaurante.repository.ClienteRepository;

import java.util.List;

/**
 * Clase para manejar las operaciones relacionadas con los clientes.
 *
 * @autor oscarruiz-code
 */
public class ManejadorCliente {

    private static ClienteRepository clienteRepository;

    /**
     * Constructor que inicializa el repositorio de clientes.
     */
    public ManejadorCliente() {
        clienteRepository = new ClienteRepository();
    }

    /**
     * Método para ingresar un cliente a partir de un objeto Cliente.
     *
     * @param cliente El objeto Cliente a ingresar.
     */
    public static void ingresarCliente(Cliente cliente) {
        clienteRepository.guardarCliente(cliente);
    }

    /**
     * Método para ingresar un cliente a partir de los atributos.
     *
     * @param nombre El nombre del cliente.
     * @param direccion La dirección del cliente.
     * @param telefono El teléfono del cliente.
     */
    public void ingresarCliente(String nombre, String direccion, String telefono) {
        Cliente cliente = new Cliente(nombre, direccion, telefono);
        clienteRepository.guardarCliente(cliente);
    }

    /**
     * Actualiza los datos de un cliente existente.
     *
     * @param id El ID del cliente.
     * @param nombre El nuevo nombre del cliente.
     * @param direccion La nueva dirección del cliente.
     * @param telefono El nuevo teléfono del cliente.
     */
    public void actualizarCliente(int id, String nombre, String direccion, String telefono) {
        Cliente cliente = clienteRepository.obtenerClientePorId(id);
        if (cliente != null) {
            cliente.setNombre(nombre);
            cliente.setDireccion(direccion);
            cliente.setTelefono(telefono);
            clienteRepository.guardarCliente(cliente);
        }
    }

    /**
     * Elimina un cliente por su ID.
     *
     * @param id El ID del cliente a eliminar.
     */
    public void eliminarCliente(int id) {
        clienteRepository.eliminarCliente(id);
    }

    /**
     * Obtiene una lista de todos los clientes.
     *
     * @return Una lista de objetos Cliente.
     */
    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.obtenerTodosLosClientes();
    }

    /**
     * Busca clientes por nombre.
     *
     * @param nombre El nombre a buscar.
     * @return Una lista de objetos Cliente que coinciden con el nombre.
     */
    public List<Cliente> buscarClientesPorNombre(String nombre) {
        return clienteRepository.buscarClientesPorNombre(nombre);
    }

    /**
     * Cierra el repositorio de clientes.
     */
    public void cerrar() {
        clienteRepository.cerrar();
    }
}
